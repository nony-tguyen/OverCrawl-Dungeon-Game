package unsw.dungeon.combat;

import java.util.ArrayList;
import java.util.List;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.MovableEntity;
import unsw.dungeon.Player;
import unsw.dungeon.goals.GoalComponent;
import unsw.dungeon.goals.GoalSubject;
import unsw.dungeon.obstacles.Boulder;

/**
 * An enemy entity that tries to kill the player
 *
 */
public class Enemy extends MovableEntity implements GoalSubject {
	
	protected EnemyMovement moveStrategy;
	protected List<GoalComponent> goalObservers;
	protected EnemyType enemyType;
	
	public Enemy(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.moveStrategy = new MoveTowardsPlayer();
		this.goalObservers = new ArrayList<>();
		enemyType = EnemyType.NORMAL;
	}
	
	/**
	 * Set how the enemy moves
	 * @param moveStrategy
	 */
	public void setMoveStrategy(EnemyMovement moveStrategy) {
		this.moveStrategy = moveStrategy;
	}
	
	public void moveEnemy(int playerX, int playerY) {
		moveStrategy.moveEnemy(this, playerX, playerY);
	}

	@Override
	public void action() {
		if (dungeon.getPlayer(1).getX() == getX() && dungeon.getPlayer(1).getY() == getY()) {
			dungeon.getPlayer(1).handleEnemy(this);
		}
		// I think this makes enemy move continuously
		//moveEnemy(dungeon.getPlayer().getX(),dungeon.getPlayer().getY());
	}

	public boolean isBlocking(Entity subject) {
		if (subject instanceof Boulder || subject instanceof Enemy)
			return true;
		else
			return false;
	}
	
	/**
	 * The player notifies the enemy to change their movement behaviour
	 * @param player
	 */
	public void updateMovement(Player player) {
		if (player.isVulnerable() == true)
			setMoveStrategy(new MoveTowardsPlayer());
		else
			setMoveStrategy(new MoveAwayFromPlayer());
	}
	
	/**
	 * The enemy is killed by player
	 */
	public void killEnemy() {
		dungeon.removeEntity(this);
		removeVisible();
		notifyGoal();
	}
	
	public EnemyType getEnemyType() {
		return enemyType;
	}
	
	public void explode() {}

	public void addGoalObserver(GoalComponent goal) {
		goalObservers.add(goal);
	}
	
	public void notifyGoal() {
		for (GoalComponent goal : goalObservers) {
			goal.updateGoal(true);
		}
	}
}

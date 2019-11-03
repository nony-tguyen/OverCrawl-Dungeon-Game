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

public class Enemy extends MovableEntity implements GoalSubject {
	
	private EnemyMovement moveStrategy;
	private List<GoalComponent> goalObservers;
	
	public Enemy(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.moveStrategy = new MoveTowardsPlayer();
		this.goalObservers = new ArrayList<>();
	}
	
	public void setMoveStrategy(EnemyMovement moveStrategy) {
		this.moveStrategy = moveStrategy;
	}
	
	public void moveEnemy(int playerX, int playerY) {
		moveStrategy.moveEnemy(this, playerX, playerY);
	}

	@Override
	public void action() {
		if (dungeon.getPlayer().getX() == getX() && dungeon.getPlayer().getY() == getY()) {
			dungeon.getPlayer().handleEnemy(this);
		}
		// I think this makes enemy move continuously
		// moveEnemy(dungeon.getPlayer().getX(),dungeon.getPlayer().getY());
	}

	public boolean isBlocking(Entity subject) {
		if (subject instanceof Boulder || subject instanceof Enemy)
			return true;
		else
			return false;
	}
	
	public void updateMovement(Player player) {
		if (player.isVulnerable() == true)
			setMoveStrategy(new MoveTowardsPlayer());
		else
			setMoveStrategy(new MoveAwayFromPlayer());
	}
	
	public void killEnemy() {
		dungeon.removeEntity(this);
		notifyGoal();
	}
	
	public void killPlayer() {
		dungeon.setPlayer(null);
		dungeon.updateGameProgression();
	}

	public void addGoalObserver(GoalComponent goal) {
		goalObservers.add(goal);
	}
	
	public void notifyGoal() {
		for (GoalComponent goal : goalObservers) {
			goal.updateGoal(this);
		}
	}
}

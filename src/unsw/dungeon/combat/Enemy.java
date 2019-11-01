package unsw.dungeon.combat;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.MovableEntity;
import unsw.dungeon.Player;
import unsw.dungeon.obstacles.Boulder;

public class Enemy extends MovableEntity {
	
	private EnemyMovement moveStrategy;
	
	public Enemy(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.moveStrategy = new MoveTowardsPlayer();
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
		if (subject instanceof Boulder)
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
	}
	
	public void killPlayer() {
		dungeon.setPlayer(null);
	}

}

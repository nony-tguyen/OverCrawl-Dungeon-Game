package unsw.dungeon.combat;

import unsw.dungeon.Entity;

public class Enemy extends Entity {
	
	private EnemyMovement moveStrategy;
	
	public Enemy(int x, int y) {
		super(x, y);
		//this.moveStrategy = new MoveTowards();
	}
	
	public void setMoveStrategy(EnemyMovement moveStrategy) {
		this.moveStrategy = moveStrategy;
	}

}

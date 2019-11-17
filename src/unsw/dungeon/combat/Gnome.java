package unsw.dungeon.combat;

import java.util.ArrayList;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;

public class Gnome extends Enemy {

	public Gnome(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.moveStrategy = new MoveTowardsPlayer();
		enemyType = EnemyType.GNOME;
	}

	@Override
	public void explode() {
		ArrayList<Entity> entities;
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) continue;
				
				entities = dungeon.checkGrid(getX()+i, getY()+j);
				if (entities.contains(dungeon.getPlayer(1))) {
					dungeon.getPlayer(1).killPlayer();
				} else if (entities.contains(dungeon.getPlayer(2))) {
					dungeon.getPlayer(2).killPlayer();
				}
				killCloseEnemies(entities);
			}
		}
		this.killEnemy();
	}

	private void killCloseEnemies(ArrayList<Entity> entities) {
		for (Enemy enemy : dungeon.getEnemies()) {
			if (entities.contains(enemy))
				enemy.killEnemy();
		}
	}
}

package unsw.dungeon.combat;

/**
 * Implements enemy movement strategy to run away from the player in the dungeon
 *
 */
public class MoveAwayFromPlayer implements EnemyMovement {
		
	@Override
	public void moveEnemy(Enemy enemy, int playerX, int playerY) {
		int diff_x = enemy.getX() - playerX;
		if (diff_x != 0) {
			if (diff_x < 0) {
				enemy.moveLeft();
			} else if (diff_x > 0) {
				enemy.moveRight();
			}
		}
		
		if (diff_x != enemy.getX() - playerX) return;
		
		int diff_y = enemy.getY() - playerY;
		if (diff_y != 0) {
			if (diff_y < 0) {
				enemy.moveUp();
			} else if (diff_y > 0) {
				enemy.moveDown();
			}
		}	
		
		// Enemy has been blocked by something, try to move around it
		if (enemy.getX() - playerX == diff_x && enemy.getY() - playerY == diff_y) {
			
			enemy.moveLeft();
			if (enemy.getX() - playerX == diff_x) enemy.moveRight();
			if (enemy.getX() - playerX == diff_x) enemy.moveUp();
			if (enemy.getY() - playerY == diff_y) enemy.moveDown();
		}
	}

}
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
	}

}
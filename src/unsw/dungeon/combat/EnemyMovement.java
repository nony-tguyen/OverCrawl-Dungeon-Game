package unsw.dungeon.combat;

/**
 * A strategy interface to determine the movement of an enemy in the dungeon
 *
 */
public interface EnemyMovement {
	
	public abstract void moveEnemy(Enemy enemy, int playerX, int playerY);
}

package unsw.dungeon;

import unsw.dungeon.combat.Enemy;

/**
 * The current state that the player is in which determines its interaction
 *
 */
public interface PlayerState {
	
	/**
	 * Determine what happens to a player upon colliding with an enemy
	 * @param enemy
	 * @return
	 */
	public boolean handleEnemy(Enemy enemy);
	
	/**
	 * Determine if the player is vulnerable
	 * @return true if vulnerable, otherwise false
	 */
	public boolean isVulnerable();
}

package unsw.dungeon;

import unsw.dungeon.combat.Enemy;

/**
 * A player state that is vulnerable to enemies
 *
 */
public class NormalPlayer implements PlayerState {
	Player player;
	
	public NormalPlayer(Player player) {
		this.player = player;
	}
	
	@Override
	public boolean handleEnemy(Enemy enemy) {
		player.killPlayer();
		return false;
	}

	@Override
	public boolean isVulnerable() {
		return true;
	}

}

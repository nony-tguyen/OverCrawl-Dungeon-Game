package unsw.dungeon;

import unsw.dungeon.combat.Enemy;

public class NormalPlayer implements PlayerState {
	Player player;
	
	public NormalPlayer(Player player) {
		this.player = player;
	}
	
	@Override
	public boolean handleEnemy(Enemy enemy) {
		enemy.killPlayer();
		return false;
	}

	@Override
	public boolean isVulnerable() {
		return true;
	}

}

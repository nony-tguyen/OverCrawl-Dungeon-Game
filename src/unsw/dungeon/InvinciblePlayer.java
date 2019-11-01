package unsw.dungeon;

import unsw.dungeon.combat.Enemy;

public class InvinciblePlayer implements PlayerState {
	Player player;
	
	public InvinciblePlayer(Player player) {
		this.player = player;
	}
	
	@Override
	public boolean handleEnemy(Enemy enemy) {
		enemy.killEnemy();
		return true;
	}

	@Override
	public boolean isVulnerable() {
		return false;
	}

}

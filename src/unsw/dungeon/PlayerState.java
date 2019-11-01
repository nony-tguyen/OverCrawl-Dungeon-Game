package unsw.dungeon;

import unsw.dungeon.combat.Enemy;

public interface PlayerState {
	
	public boolean handleEnemy(Enemy enemy);
	public boolean isVulnerable();
}

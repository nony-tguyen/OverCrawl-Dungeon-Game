package unsw.dungeon.combat;

import unsw.dungeon.CollectableEntity;
import unsw.dungeon.Dungeon;
import unsw.dungeon.InvinciblePlayer;
import unsw.dungeon.NormalPlayer;
import unsw.dungeon.Player;

/**
 * An invisibility potion entity that puts player into an invincible state when used
 *
 */
public class InvinsibilityPotion extends CollectableEntity {

	private int timeLimit = 10;
	
	public InvinsibilityPotion(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
	}

	@Override
	public void useItem(Player player) {
		player.setPlayerState(new InvinciblePlayer(player));
	}
	
	@Override
	public void removeItem(Player player) {
		player.removeItem(this);
		player.setPlayerState(new NormalPlayer(player));
	}

	/**
	 * Get time duration of the potion
	 * @return
	 */
	public int getTimeLimit() {
		return timeLimit;
	}
}

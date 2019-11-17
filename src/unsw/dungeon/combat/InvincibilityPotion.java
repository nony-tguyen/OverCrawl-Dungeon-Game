package unsw.dungeon.combat;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.dungeon.CollectableEntity;
import unsw.dungeon.Dungeon;
import unsw.dungeon.InvinciblePlayer;
import unsw.dungeon.NormalPlayer;
import unsw.dungeon.Player;

/**
 * An invisibility potion entity that puts player into an invincible state when used
 *
 */
public class InvincibilityPotion extends CollectableEntity {
	
	public final int timeLimit;
	private IntegerProperty timeRemain;
	
	public InvincibilityPotion(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		timeLimit = 5;
		timeRemain = new SimpleIntegerProperty(5);
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
	
	/**
	 * Get time remaining for potion duration
	 * @return
	 */
	public IntegerProperty getTimeRemain() {
		return timeRemain;
	}
	
}

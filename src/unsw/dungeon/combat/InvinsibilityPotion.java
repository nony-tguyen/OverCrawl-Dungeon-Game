package unsw.dungeon.combat;

import unsw.dungeon.CollectableEntity;
import unsw.dungeon.Dungeon;
import unsw.dungeon.InvinciblePlayer;
import unsw.dungeon.NormalPlayer;
import unsw.dungeon.Player;

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


	public int getTimeLimit() {
		return timeLimit;
	}

	

	// Update enemy movement when potion acquired
	
	// Update enemy movement when potion expires
	
	// Update player state when potion expires
}

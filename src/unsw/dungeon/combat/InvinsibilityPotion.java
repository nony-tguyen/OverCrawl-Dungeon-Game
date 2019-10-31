package unsw.dungeon.combat;

import unsw.dungeon.Entity;

public class InvinsibilityPotion extends Entity {

	public InvinsibilityPotion(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean isBlocking(Entity subject, int desiredX, int desiredY) {
		// TODO Auto-generated method stub
		return false;
	}

	// Update enemy movement when potion acquired
	
	// Update enemy movement when potion expires
	
	// Update player state when potion expires
}

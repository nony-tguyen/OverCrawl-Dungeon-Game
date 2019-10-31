package unsw.dungeon.combat;

import unsw.dungeon.Entity;

public class Sword extends Entity {
	private int durability;
	
	public Sword(int x, int y) {
		super(x, y);
		this.durability = 5;
	}

	public int getDurability() {
		return durability;
	}
	
	public void decreaseDurability() {
		durability = durability - 1;
	}
	
	public void useSword() {
		/**
		 * Check if object is enemy and remaining sword durability
		 * If it is enemy, kill enemy and decrease durability
		 * If sword durability == 0, destroy the sword
		 */
		
	}
	
	public void removeSword() {
		
	}

	@Override
	public boolean isBlocking(Entity subject, int desiredX, int desiredY) {
		// TODO Auto-generated method stub
		return false;
	}
	
}

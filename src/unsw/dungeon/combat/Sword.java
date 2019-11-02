package unsw.dungeon.combat;

import unsw.dungeon.CollectableEntity;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;

public class Sword extends CollectableEntity {
	private int durability;
	
	public Sword(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.durability = 5;
	}

	public int getDurability() {
		return durability;
	}
	
	public void decreaseDurability() {
		durability = durability - 1;
	}
	

	/**
	 * Check if object is enemy 
	 * If it is enemy, kill enemy
	 * Decrease durability 
	 * If sword durability == 0, destroy the sword
	 */
	@Override
	public void useItem(Player player) {
		// +1 to enemies killed goal
		attack(player);
		decreaseDurability();
		if (durability <= 0) {
			removeItem(player);
		}
	}

	// Player can only carry one sword at a time
	@Override
	public void collectItem(Player player) {
		for (CollectableEntity e : player.getInventory()) {
			if (e instanceof Sword) {
				return;
			}
		}
		super.collectItem(player);
	}

	@Override
	public void removeItem(Player player) {
		player.removeItem(this);
	}

	public void attack(Player player) {
		for (Entity entity : player.getAdjacentEntity()) {
			if (entity instanceof Enemy) {
				((Enemy) entity).killEnemy();
				return;
			}
		}
	}
	
}

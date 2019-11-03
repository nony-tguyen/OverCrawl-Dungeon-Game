package unsw.dungeon.combat;

import unsw.dungeon.CollectableEntity;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;

/**
 * A sword entity that is used by the player to kill the enemy
 * @author z5207825
 *
 */
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
	
	@Override
	public void useItem(Player player) {
		attack(player);
		decreaseDurability();
		if (durability <= 0) {
			removeItem(player);
		}
	}

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

	/**
	 * Use the sword to kill the enemy
	 * @param player
	 */
	public void attack(Player player) {
		for (Entity entity : player.getAdjacentEntity()) {
			if (entity instanceof Enemy) {
				((Enemy) entity).killEnemy();
				return;
			}
		}
	}
	
}

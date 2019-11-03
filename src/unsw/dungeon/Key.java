package unsw.dungeon;

import java.util.List;

public class Key extends CollectableEntity {

	private int id;

	public Key(Dungeon dungeon, int x, int y, int id) {
		super(dungeon, x, y);
		this.id = id;
	}


	@Override
	public void useItem(Player player) {
		removeItem(player);
	}
	@Override
	public void removeItem(Player player) {
		player.removeItem(this);	
	}
	@Override
	public void collectItem(Player player) {
		// Only collect if there are no other keys in the inventory
		if (! hasKey(player)) {
			player.addItem(this);
			// Remove item from dungeon map
			dungeon.removeEntity(this);			
		}
	}
	/**
	 * Checks if the player has a key in their inventory
	 * @param subject
	 * @return true if they have a key, false if not
	 */
	public boolean hasKey(Player subject) {
		List<CollectableEntity> inv = subject.getInventory();
		for (CollectableEntity e : inv) {
			if (e instanceof Key) {
				return true;
			}
		}		
		return false;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}

package unsw.dungeon;

/**
 * A type entity that is able to be collected by the player
 * 
 */
public abstract class CollectableEntity extends Entity {
	protected Dungeon dungeon;
	protected String name;
	protected String description;
	
	public CollectableEntity(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}
	
	/**
	 * Apply the entity on the player if it is able to be applied
	 * @param player
	 */
	public abstract void useItem(Player player);
	
	/**
	 * Dispose of the item from the player's inventory
	 * @param player
	 */
	public abstract void removeItem(Player player);
	
	/**
	 * Add item to player's inventory from the dungeon
	 * @param player
	 */
	public void collectItem(Player player) {
		player.addItem(this);
		dungeon.removeEntity(this);
	}
}
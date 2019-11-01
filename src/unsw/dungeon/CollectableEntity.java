package unsw.dungeon;

public abstract class CollectableEntity extends Entity {
	protected Dungeon dungeon;
	protected String name;
	protected String description;
	
	public CollectableEntity(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}
	
	public abstract void useItem(Player player);
	public abstract boolean removeItem(Player player);
	
	public void collectItem(Player player) {
		player.addItem(this);
		// Remove item from dungeon map
		dungeon.removeEntity(this);
		// Check goals here/Add to goal tally
	}
	
	

}

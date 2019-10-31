package unsw.dungeon;

public abstract class CollectableEntity extends Entity {
	protected Dungeon dungeon;
	protected String name;
	protected String description;
	
	public CollectableEntity(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}
	
	
	
	

}

package unsw.dungeon;

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

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}

package unsw.dungeon;

public class Key extends CollectableEntity {

	private int id;

	public Key(Dungeon dungeon, int x, int y, int id) {
		super(dungeon, x, y);
		this.id = id;
	}


	@Override
	public void useItem(Player player) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean removeItem(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

}

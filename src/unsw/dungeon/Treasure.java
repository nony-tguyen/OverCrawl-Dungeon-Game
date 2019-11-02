package unsw.dungeon;

public class Treasure extends CollectableEntity {

	public Treasure(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
	}

	@Override
	public void useItem(Player player) {
	}

	@Override
	public void removeItem(Player player) {
		player.removeItem(this);	
	}

}

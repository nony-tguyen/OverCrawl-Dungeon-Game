package unsw.dungeon.obstacles;

import unsw.dungeon.Entity;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y);
    }
    
	@Override
	public boolean isBlocking(Entity subject, int desiredX, int desiredY) {
		// TODO Auto-generated method stub
		return true;
	}

}
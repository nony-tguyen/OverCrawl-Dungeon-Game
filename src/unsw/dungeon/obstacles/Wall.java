package unsw.dungeon.obstacles;

import unsw.dungeon.Entity;

/**
 * A wall entity that blocks entities from moving into the grid
 *
 */
public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y);
    }
    
	@Override
	public boolean isBlocking(Entity subject) {
		return true;
	}

}
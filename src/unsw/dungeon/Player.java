package unsw.dungeon;

import unsw.dungeon.obstacles.Boulder;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends MovableEntity {

    //private Dungeon dungeon;
    //private int x;
    //private int y;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
    }

	@Override
	public void action() {
		/*
		Entity eOverlapped = dungeon.checkGrid(x, y);
		if (eOverlapped instanceof Boulder) {
			eOverlapped.moveBoulder()
			
		}
		*/
		// based on the entity do something
		// d
	}
}

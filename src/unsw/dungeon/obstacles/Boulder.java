package unsw.dungeon.obstacles;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;

public class Boulder extends Entity {
	private Dungeon dungeon; 
	
    public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

}

package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    @Override
    public String toString() {
    	return this.getClass() + " x: " + getX() + " y: " + getY();
    }
    
    /**
     * Determine whether this entity blocks another entity 'subject'
     * Set to false as default, most entities will not block another
     * @param subject 
     * @return True if entity blocks subject, otherwise False
     */
    public boolean isBlocking(Entity subject) {
		return false;
	}
    
    /**
     * Determine whether the entity contributes to a dungeon goal
     * @return True if it affects a goal, otherwise False
     */
    public boolean affectGoal() {
    	return false;
    }
    
    /**
     * Allows only for CollectableEntity to be collected by the player
     * @param player
     */
    public void collectItem(Player player) {}
    
    /**
     * Allows only for entities related to goals to notify their observers
     */
    public void notifyGoal() {}
}

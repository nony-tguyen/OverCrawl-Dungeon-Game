package unsw.dungeon.obstacles;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import unsw.dungeon.CollectableEntity;
import unsw.dungeon.Entity;
import unsw.dungeon.Key;
import unsw.dungeon.Player;

/**
 * The door state where a key must be used to open the door
 *
 */
public class ClosedState implements DoorState {
	private Door door;
	private BooleanProperty closed;
	
	public ClosedState(Door door) {
		this.door = door;
		this.closed.set(true);
	}
	@Override
	public boolean isBlocking(Entity subject) {
		// check the subject. 
		if (isPlayer(subject)) {
			// Matching key
			if (checkForKey((Player) subject)) {
				door.changeState(new OpenState());
			}		
		}

		return true;
	}
	/**
	 * Checks if the given entity is a player
	 * @param subject
	 * @return 
	 */
	public boolean isPlayer(Entity subject) {
		if (subject instanceof Player) {
			return true;
		} else {
			return false; 
		}
	}
	/**
	 * Checks if a player has a matching key in its inventory
	 * @param subject
	 * @return true if the key has the same id as the door
	 */
	public boolean checkForKey(Player subject) {
		List<CollectableEntity> inv = subject.getInventory();
		for (CollectableEntity e : inv) {
			// Player has a key
			if (e instanceof Key) {
				// The key matches the id of the door
				if (door.getId() == ((Key) e).getId() ) {
					((Key) e).useItem(subject);
					return true;
				}
			}
		}		
		return false;
	}
	

}

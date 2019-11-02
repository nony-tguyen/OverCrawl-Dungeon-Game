package unsw.dungeon.obstacles;

import unsw.dungeon.Entity;

public class OpenState implements DoorState {

	@Override
	public boolean isBlocking(Entity subject) {
		return false;
	}

}

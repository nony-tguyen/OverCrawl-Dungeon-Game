package unsw.dungeon.obstacles;

public class ClosedState implements DoorState {

	@Override
	public boolean isBlocking() {
		// check the subject. 
		return true;
	}

}

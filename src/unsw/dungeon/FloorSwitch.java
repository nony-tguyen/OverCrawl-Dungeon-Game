package unsw.dungeon;

public class FloorSwitch extends Entity {

	private boolean triggered;
	
	public FloorSwitch(int x, int y) {
		super(x, y);
		this.triggered = false;
	}
	//TODO Make this a subject so that Goals can observe when this is triggered/untriggered 
	/**
	 * Set the trigger on
	 */
	public void trigger() {
		this.triggered = true;
		//System.out.println("Triggered");
	}
	/**
	 * Set the trigger off
	 */
	public void untrigger() {
		this.triggered = false;
		//System.out.println("Untriggered");
	}
}

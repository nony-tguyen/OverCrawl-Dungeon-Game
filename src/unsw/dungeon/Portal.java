package unsw.dungeon;

import java.util.*;

public class Portal extends Entity {
	
	private Dungeon dungeon;
	private int id;
	
	public Portal(Dungeon dungeon, int x, int y, int id) {
		super(x, y);
		this.dungeon = dungeon;
		this.id = id;
	}
	@Override
	public boolean isBlocking(Entity subject) {
		// ask its twin portal if its blocked
		if (subject instanceof MovableEntity) {
			Map<String, Integer> coordinates = isTwinBlocking(getTwinPortal(), (MovableEntity) subject);
			if (coordinates != null) {
				//System.out.println(coordinates);
				teleport(subject, coordinates.get("x"), coordinates.get("y"));
				return false;
				//System.out.println(subject);
			} else {
				return true;
			}

		} else {
			return true; 
		}
	}
	/**
	 * 
	 * @return it's corresponding portal
	 */
	public Portal getTwinPortal() {
		List<Entity> entities = dungeon.getEntities();
		for (Entity e : entities) {
			if (e instanceof Portal && ((Portal) e).getId() == this.id && e != this) {
				return (Portal) e;
			}
		}
		return null;
	}
	/**
	 * Checks if the desired grid next to the twin is available. 
	 * @param portal
	 * @return coordinates if the entity is allowed to teleport
	 */
	public Map<String, Integer> isTwinBlocking(Portal twin, MovableEntity subject) {
		// Figure out what direction the player came from
		int xDiff = this.getX() - subject.getX();
		int yDiff = this.getY() - subject.getY();
		// Set the direction 
		switch (xDiff) {
			case 1:
				subject.direction = Direction.RIGHT;
				break;
			case -1:
				subject.direction = Direction.LEFT;
				break;
		}
		switch (yDiff) {
			case 1:
				subject.direction = Direction.DOWN;
				break;
			case -1:
				subject.direction = Direction.UP;
				break;
		}
		Map<String, Integer> coordinates = new HashMap<String, Integer>();
		if (dungeon.isGridAvail(subject, twin.getX() + xDiff, twin.getY() + yDiff) == true) {
			// It will move again so no need to add the difference
			coordinates.put("x", twin.getX());
			coordinates.put("y", twin.getY());
			return coordinates;
		} else {
			return null;
		}
	}
	/*
	 * Teleports the entity to the desired location 
	 */
	public void teleport(Entity subject, int x, int y) {
		subject.x().set(x);
		subject.y().set(y);
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
//47108943
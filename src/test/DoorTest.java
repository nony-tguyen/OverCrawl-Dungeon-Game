package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
import unsw.dungeon.combat.Enemy;
import unsw.dungeon.obstacles.Boulder;
import unsw.dungeon.obstacles.Door;
import unsw.dungeon.obstacles.DoorState;
import unsw.dungeon.obstacles.OpenState;

class DoorTest {
	private Dungeon dungeon;
	private Player player;
	private Door d;
	private Enemy e;
	private Boulder b;
	
	@Before
	public void initializeDungeon() {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 3, 2, 1);
		dungeon.addPlayer(player);
		d = new Door(3, 3, 1);
		dungeon.addEntity(d);
		e = new Enemy(dungeon, 2, 3);
		dungeon.addEntity(e);
		b = new Boulder(dungeon, 4, 3);
		dungeon.addEntity(b);

	}
	@Test
	void testClosedBlock() {
		initializeDungeon();
		// Check that the player is blocked by the door
		player.moveDown();
		assertEquals(2, player.getY());
		// Check that the enemy is blocked by door

		e.moveRight();
		assertEquals(2, e.getX());
		
		// Check that the boulder is blocked by the door
		b.moveLeft();
		assertEquals(4, b.getX());
	}
	@Test
	void testClosedToOpen() {
		initializeDungeon();
		// if the player has a key with the non-matching ID
		Key k2 = new Key(dungeon, 2, 2, 2); 
		player.addItem(k2);
		player.moveDown();
		DoorState state = d.getState();
		boolean open = false;
		if (state instanceof OpenState) {
			open = true;
		}
		assertEquals(false, open);	
		
		// if the player has a key with a matching ID 		
		Key k1 = new Key(dungeon, 2, 2, 1); 
		player.addItem(k1);
		player.moveDown();
		state = d.getState();
		if (state instanceof OpenState) {
			open = true;
		}
		assertEquals(true, open);

		

	}
	@Test
	void testOpenNoBlock() {
		initializeDungeon();
		d.changeState(new OpenState(d));
		// Check that the enemy is not blocked by door when its open
		e.moveRight();
		assertEquals(3, e.getX());
		dungeon.removeEntity(e);
		// Check that the boulder is not blocked by the door when its open
		b.moveLeft();
		assertEquals(3, b.getX());
		dungeon.removeEntity(b);
		
		// Check that the player is not blocked by the door when its open
		player.moveDown();
		assertEquals(3, player.getY());
	}
}

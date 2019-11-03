package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.FloorSwitch;
import unsw.dungeon.Player;
import unsw.dungeon.combat.Enemy;
import unsw.dungeon.obstacles.Boulder;

class FloorSwitchTest {
	private Dungeon dungeon;
	private Player player;
	private Enemy e;
	private FloorSwitch fs;
	private Boulder b;
	
	@Before
	public void initializeDungeon() {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 3, 2);
		dungeon.setPlayer(player);
		e = new Enemy(dungeon, 2, 3);
		dungeon.addEntity(e);
		fs = new FloorSwitch(3,3);
		dungeon.addEntity(fs);
		b = new Boulder(dungeon, 4, 3);
		dungeon.addEntity(b);
	}
	@Test
	void testMoveOn() {
		initializeDungeon();
		e.moveRight();
		assertEquals(3, e.getX());
		dungeon.removeEntity(e);
		player.moveDown();
		assertEquals(3, player.getY());
	}
	@Test
	void testTrigger() {
		initializeDungeon();
		// Move boulder on switch
		player.moveRight();
		player.moveRight();
		player.moveDown();
		player.moveLeft();
		assertTrue(fs.isTriggered());
	}
	@Test
	void testUntrigger() {
		initializeDungeon();
		// Move boulder on switch
		player.moveRight();
		player.moveRight();
		player.moveDown();
		player.moveLeft();
		
		// Move boulder off switch
		player.moveDown();
		player.moveLeft();
		player.moveUp();
		assertFalse(fs.isTriggered());
	}
	@Test
	void testOnlyBoulderTrigger() {
		initializeDungeon();
		player.moveDown();
		assertFalse(fs.isTriggered());
		player.moveUp();
		
		e.moveRight();
		assertFalse(fs.isTriggered());
	}
	

}

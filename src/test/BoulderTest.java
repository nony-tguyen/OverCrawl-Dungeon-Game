package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.combat.Enemy;
import unsw.dungeon.obstacles.Boulder;

class BoulderTest {
	private Dungeon dungeon;
	private Player player;
	
	@Before
	public void initializeDungeon() {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 3, 0, 1);
		dungeon.addPlayer(player);
	}
	
	@Test
	void testMovedByPlayer() {
		initializeDungeon();
		// Check that the boulder has moved down
		Boulder b1 = new Boulder(dungeon, 3, 1);
		dungeon.addEntity(b1);
		player.moveDown();
		assertEquals(2, b1.getY());
		// Boulder now (3, 2)
		
		// Check that the boulder can't be moved by enemy 
		Enemy e = new Enemy(dungeon, 2, 2);
		dungeon.addEntity(e);
		e.moveRight();
		// Check the boulder is in the same spot
		assertEquals(3, b1.getX());
		
		// Check that the boulder can't be moved by another boulder
		Boulder b2 = new Boulder(dungeon, 3, 3);
		dungeon.addEntity(b2);
		b1.moveDown();
		assertEquals(2, b1.getY());
	}
	
	@Test
	void testOneMoved() {
		// A player can moveone boulder at a time 
		initializeDungeon();
		Boulder b1 = new Boulder(dungeon, 3, 1);
		Boulder b2 = new Boulder(dungeon, 3, 2);
		dungeon.addEntity(b1);
		dungeon.addEntity(b2);
		player.moveDown();
		assertEquals(1, b1.getY());
		assertEquals(2, b2.getY());
	}

	@Test
	void testBlockEntity() {
		initializeDungeon();
		Boulder b1 = new Boulder(dungeon, 3, 2);
		dungeon.addEntity(b1);
		// Check that the enemy is blocked by boulder
		Enemy e = new Enemy(dungeon, 2, 2);
		dungeon.addEntity(e);
		e.moveRight();
		// Check that the boulder is blocked by the other boulder
		assertEquals(2, e.getX());
		
		// Check that the boulder can't be moved by another boulder
		Boulder b2 = new Boulder(dungeon, 3, 3);
		dungeon.addEntity(b2);
		b1.moveDown();
		assertEquals(2, b1.getY());
	}
	@Test
	void testMovedCorrectGrid() {
		// test move all directions
		initializeDungeon();
		Boulder b1 = new Boulder(dungeon, 3, 1);
		// Test moving down
		dungeon.addEntity(b1);
		player.moveDown();
		// Boulder at (3, 2)
		assertEquals(2, b1.getY());
		
		// Test moving left
		player.moveRight();
		player.moveDown();
		player.moveLeft();
		assertEquals(2, b1.getX());
		
		// Test moving up
		player.moveDown();
		player.moveLeft();
		player.moveUp();
		assertEquals(1, b1.getY());
		
		// Testing moving right 
		player.moveLeft();
		player.moveUp();
		player.moveRight();
		assertEquals(3, b1.getX());
		
		// Test moving right
	}
}

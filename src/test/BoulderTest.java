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
		player = new Player(dungeon, 3, 0);
		dungeon.setPlayer(player);
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
		// Check they're in the same spot 
		assertEquals(2, e.getX());
		assertEquals(3, b1.getX());
		
		// Check that the boulder can't be moved by another boulder
		Boulder b2 = new Boulder(dungeon, 3, 3);
		dungeon.addEntity(b2);
		b1.moveDown();
		assertEquals(2, b1.getY());
		assertEquals(3, b2.getY());
	}
	

}

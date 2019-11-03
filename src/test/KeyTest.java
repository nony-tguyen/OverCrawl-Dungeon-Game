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

class KeyTest {
	private Dungeon dungeon;
	private Player player;
	private Enemy e;
	private Boulder b;
	
	@Before
	public void initializeDungeon() {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 3, 2);
		e = new Enemy(dungeon, 2, 3);
		dungeon.addEntity(e);
		b = new Boulder(dungeon, 4, 3);
		dungeon.addEntity(b);
		dungeon.setPlayer(player);
	}
	
	// (Tests two acceptance criteria)
	@Test
	void testCollection() {
		initializeDungeon();
		Key k = new Key(dungeon, 3, 3, 1);
		dungeon.addEntity(k);
		
		// Make sure enemy can't pick up key
		e.moveRight();
		// Check there's two entities still there. (The key and the enemy)
		assertEquals(2, dungeon.checkGrid(3, 3).size());
		dungeon.removeEntity(e);
		
		// Make sure boulder can't pick up key
		b.moveLeft();
		assertEquals(2, dungeon.checkGrid(3, 3).size());
		dungeon.removeEntity(b);
		
		// Check if player picked up the key
		player.moveDown();
		assertEquals(true, k.hasKey(player));
	}
	
	@Test
	void testMaxOneKey() {
		initializeDungeon();
		Key k1 = new Key(dungeon, 3, 3, 1);
		Key k2 = new Key(dungeon, 0, 3, 1);
		dungeon.addEntity(k1);
		dungeon.addEntity(k2);
		player.addItem(k1);
		player.addItem(k2);
		// The second key should remain on map because the player already has a key and can't pick up another
		assertEquals(1, dungeon.checkGrid(0, 3).size());
	}
	
	@Test
	void testOneUse() {
		initializeDungeon();
		Key k = new Key(dungeon, 0, 3, 1);
		dungeon.addEntity(k);
		player.addItem(k);
		Door d = new Door(3, 3, 1);
		dungeon.addEntity(d);
		player.moveDown();
		// Player should no longer have the key as they use it to open the door
		assertEquals(false, k.hasKey(player));
	}

}

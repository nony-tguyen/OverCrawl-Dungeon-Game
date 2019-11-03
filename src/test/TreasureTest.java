package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import unsw.dungeon.CollectableEntity;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
import unsw.dungeon.Treasure;
import unsw.dungeon.combat.Enemy;
import unsw.dungeon.obstacles.Boulder;

class TreasureTest {
	private Dungeon dungeon;
	private Player player;
	private Enemy e;
	private Boulder b;
	private Treasure t;
	
	@Before
	public void initializeDungeon() {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 3, 2);
		e = new Enemy(dungeon, 2, 3);
		dungeon.addEntity(e);
		b = new Boulder(dungeon, 4, 3);
		dungeon.addEntity(b);
		dungeon.setPlayer(player);
		Treasure t = new Treasure(dungeon, 3, 3);
		dungeon.addEntity(t);
	}
	
	// (Tests two acceptance criteria)
	@Test
	void testCollection() {
		initializeDungeon();	
		// Make sure enemy can't pick up treasure
		e.moveRight();
		// Check there's two entities still there. (The treasure and the enemy)
		assertEquals(2, dungeon.checkGrid(3, 3).size());
		dungeon.removeEntity(e);
		
		// Make sure boulder can't pick up treasure
		b.moveLeft();
		assertEquals(2, dungeon.checkGrid(3, 3).size());
		dungeon.removeEntity(b);
		
		// Check if player picked up the key
		player.moveDown();
		assertTrue(hasTreasure(player));
	}
	@Test 
	void testRemovedFromMap() {
		initializeDungeon();
		player.moveDown();
		assertEquals(0, dungeon.checkGrid(3, 3).size());
	}
	
	public boolean hasTreasure(Player subject) {
		List<CollectableEntity> inv = subject.getInventory();
		for (CollectableEntity e : inv) {
			if (e instanceof Treasure) {
				return true;
			}
		}		
		return false;
	}
}

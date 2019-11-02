package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import unsw.dungeon.CollectableEntity;
import unsw.dungeon.Direction;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import unsw.dungeon.combat.Enemy;
import unsw.dungeon.combat.Sword;

class SwordTest {
	private Dungeon dungeon;
	private Player player;

	@Before
	public void initializeDungeon(){
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
	}
	
	@Test
	public void testCollectingSword() {
		initializeDungeon();
		Sword s = new Sword(dungeon, 1, 1);
		dungeon.addEntity(s);
		
		assertTrue(dungeon.getEntities().contains(s));
		assertEquals(s.getX(), 1);
		assertEquals(s.getY(), 1);
		assertEquals(s.getDurability(), 5);
		
		// Move player to sword at (1,1)
		player.moveDown();
		player.moveRight();
		
		// Player should have collected the sword
		List<CollectableEntity> inventory = player.getInventory();
		assertEquals(inventory.size(), 1);
		assertTrue(inventory.contains(s));
		
		// Sword is removed from the dungeon map
		assertFalse(dungeon.getEntities().contains(s));		
		
		// A player can only hold one sword at a time
		Sword s2 = new Sword(dungeon, 2, 1);
		dungeon.addEntity(s2);
		player.moveRight();
		
		assertFalse(player.getInventory().contains(s2));
		assertTrue(dungeon.getEntities().contains(s2));
	}
	
	@Test
	public void testKillEnemy() {
		initializeDungeon();
		Sword s = new Sword(dungeon, 1, 0);
		dungeon.addEntity(s);
		player.moveRight();
		
		Enemy e1 = new Enemy(dungeon, 2, 0);
		dungeon.addEntity(e1);
		assertTrue(dungeon.getEnemies().contains(e1));
		s.useItem(player);
		assertFalse(dungeon.getEnemies().contains(e1));
		assertEquals(s.getDurability(), 4);
	}
	
	@Test
	public void testSwordRunsOutOfDurability() {
		initializeDungeon();
		Sword s = new Sword(dungeon, 1, 0);
		dungeon.addEntity(s);
		player.moveRight();
		
		assertEquals(s.getDurability(), 5);
		s.useItem(player);
		s.useItem(player);
		s.useItem(player);
		s.useItem(player);
		s.useItem(player);
		assertEquals(s.getDurability(), 0);
		assertFalse(player.getInventory().contains(s));
		assertFalse(dungeon.getEntities().contains(s));
	}
	
	@Test
	public void testPlayerStillVulnerableWithSword() {
		initializeDungeon();
		Sword s = new Sword(dungeon, 1, 0);
		dungeon.addEntity(s);
		player.moveRight();
		
		// Enemy is in the 'down' direction from the player
		Enemy e1 = new Enemy(dungeon, 1, 1);
		dungeon.addEntity(e1);
		
		// Player is facing in the 'right' direction and will swing the sword to the right, enemy will not die
		assertEquals(player.getDirection(), Direction.RIGHT);
		s.useItem(player);
		assertTrue(dungeon.getEnemies().contains(e1));
		assertEquals(s.getDurability(), 4);
		
		e1.moveEnemy(1, 0);
		assertTrue(dungeon.gameOver());
	}

}

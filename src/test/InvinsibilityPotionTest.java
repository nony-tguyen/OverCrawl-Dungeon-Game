package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.combat.Enemy;
import unsw.dungeon.combat.InvinsibilityPotion;

class InvinsibilityPotionTest {
	private Dungeon dungeon;
	private Player player;

	@Before
	public void initializeDungeon(){
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
	}
	
	@Test
	void testCollectInvinsibilityPotion() {
		initializeDungeon();
		InvinsibilityPotion ip = new InvinsibilityPotion(dungeon, 1, 0);
		dungeon.addEntity(ip);
		
		assertTrue(dungeon.getEntities().contains(ip));
		assertEquals(ip.getX(), 1);
		assertEquals(ip.getY(), 0);
		
		player.moveRight();
		assertTrue(player.getInventory().contains(ip));
		assertFalse(dungeon.getEntities().contains(ip));
		
		// Enemies cannot collect this item
		InvinsibilityPotion ip2 = new InvinsibilityPotion(dungeon, 9, 9);
		dungeon.addEntity(ip2);
		Enemy e1 = new Enemy(dungeon, 8, 9);
		dungeon.addEntity(e1);
		e1.moveRight();
		assertTrue(dungeon.getEntities().contains(ip2));
	}
	
	@Test
	void testUseInvincibilityPotion() {
		// Testing player states due to invincibility potion
		initializeDungeon();
		InvinsibilityPotion ip = new InvinsibilityPotion(dungeon, 1, 0);
		dungeon.addEntity(ip);
		
		// Player in normal state
		assertTrue(player.isVulnerable());
		
		// Player now in invincible state
		player.moveRight();
		ip.useItem(player);
		assertFalse(player.isVulnerable());
		
		// Player returns to normal state after potion expires
		assertEquals(ip.getTimeLimit(), 10);
		ip.removeItem(player);
		assertTrue(player.isVulnerable());
		
		// Invincibility potion is now gone
		assertFalse(player.getInventory().contains(ip));
		assertFalse(dungeon.getEntities().contains(ip));
	}
	
	@Test
	void testEnemyBehaviourWithPotion() {
		// Player activates invincibility potion
		initializeDungeon();
		InvinsibilityPotion ip = new InvinsibilityPotion(dungeon, 1, 0);
		dungeon.addEntity(ip);
		Enemy e = new Enemy(dungeon, 2, 2);
		dungeon.addEntity(e);
		
		player.moveRight();
		ip.useItem(player);
		
		// Enemy will move away from player 
		e.moveEnemy(1, 0);
		assertTrue(e.getX() != 1);
		assertTrue(e.getY() != 0);
		
		e.moveEnemy(1, 0);
		assertTrue(e.getX() != 1);
		assertTrue(e.getY() != 0);
	}
	
	@Test
	void testKillEnemyWithPotion() {
		initializeDungeon();
		InvinsibilityPotion ip = new InvinsibilityPotion(dungeon, 1, 0);
		dungeon.addEntity(ip);
		Enemy e = new Enemy(dungeon, 1, 1);
		dungeon.addEntity(e);
		
		player.moveRight();
		ip.useItem(player);
		
		// Enemy is at (1, 1), Player is at (1, 0)
		player.moveDown();
		assertTrue(player.getX() == 1 && player.getY() == 1);
		assertFalse(player.isVulnerable());
		assertFalse(dungeon.getEnemies().contains(e));
		assertFalse(dungeon.isGameFinished());
	}

}

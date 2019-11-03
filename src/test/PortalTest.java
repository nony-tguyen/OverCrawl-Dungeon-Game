package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Portal;
import unsw.dungeon.combat.Enemy;
import unsw.dungeon.obstacles.Boulder;

class PortalTest {

	private Dungeon dungeon;
	private Player player;
	private Enemy e;
	private Boulder b;
	
	@Before
	public void initializeDungeon() {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 3, 2);
		dungeon.setPlayer(player);
		e = new Enemy(dungeon, 2, 3);
		dungeon.addEntity(e);
		b = new Boulder(dungeon, 4, 3);
		dungeon.addEntity(b);
		dungeon.setPlayer(player);
	}
	
	@Test
	void testOneOtherPortal() {
		initializeDungeon();
		Portal p1 = new Portal(dungeon, 3, 3, 1);
		Portal p2 = new Portal(dungeon, 1, 4, 1);
		dungeon.addEntity(p1);
		dungeon.addEntity(p2);
		assertEquals(p2, p1.getTwinPortal());
	}
	@Test
	void testEntitiesTeleport() {
		initializeDungeon();
		Portal p1 = new Portal(dungeon, 3, 3, 1);
		Portal p2 = new Portal(dungeon, 5, 4, 1);
		dungeon.addEntity(p1);
		dungeon.addEntity(p2);
		// Boulder move into portal and teleported to adjacent left grid
		b.moveLeft();
		assertEquals(4, b.getX());
		assertEquals(4, b.getY());
		// Enemy move into portal and teleported adjacent right grid 
		e.moveRight();
		assertEquals(6, e.getX());
		assertEquals(4, e.getY());
		// Player move into portal and teleported adjacent down grid
		player.moveDown();
		assertEquals(5, player.getX());
		assertEquals(5, player.getY());
		
		// Teleported top adjacent grid
		Boulder b2 = new Boulder(dungeon, 3, 4);
		dungeon.addEntity(b2);
		b2.moveUp();
		assertEquals(5, b2.getX());
		assertEquals(3, b2.getY());
	}

	@Test
	void testEntitiesBlocked() {
		initializeDungeon();
		//Boulder 
		
	}

}

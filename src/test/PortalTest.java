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
	private Portal p1;
	private Portal p2;
	
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
		p1 = new Portal(dungeon, 3, 3, 1);
		p2 = new Portal(dungeon, 5, 4, 1);
		dungeon.addEntity(p1);
		dungeon.addEntity(p2);
	}
	
	@Test
	void testOneOtherPortal() {
		initializeDungeon();
		dungeon.addEntity(p1);
		dungeon.addEntity(p2);
		assertEquals(p2, p1.getTwinPortal());
	}
	@Test
	void testEntitiesTeleport() {
		initializeDungeon();
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
		Boulder b2 = new Boulder(dungeon, 4, 4);
		dungeon.addEntity(b2);
		// Boulder blocked by obstacle and player blocked by two boulders
		p1.teleport(player, 5, 3);
		player.moveLeft();
		assertEquals(4, b.getX());
		assertEquals(3, b.getY());
		dungeon.removeEntity(b);
		dungeon.removeEntity(b2);
		
		// Player push one boulder out of the way
		Boulder b3 = new Boulder(dungeon, 4,3);
		dungeon.addEntity(b3);
		p1.teleport(player, 5, 3);
		player.moveLeft();
		assertEquals(4, b3.getX());
		assertEquals(4, b3.getY());
		assertEquals(4, player.getX());
		assertEquals(3, player.getY());
		
		// Enemy blocked by obstacle
		Boulder b4 = new Boulder(dungeon, 6, 4);
		dungeon.addEntity(b4);
		e.moveRight();
		assertEquals(2, e.getX());
		assertEquals(3, e.getY());
		
	}

}

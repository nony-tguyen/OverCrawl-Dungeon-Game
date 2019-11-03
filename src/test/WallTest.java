package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.combat.Enemy;
import unsw.dungeon.obstacles.Boulder;
import unsw.dungeon.obstacles.Wall;

class WallTest {
	private Dungeon dungeon;
	private Player player;
	
	@Before
	public void initializeDungeon() {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 3, 2);
		dungeon.setPlayer(player);
	}
	
	@Test
	void testBlockEntity() {
		initializeDungeon();
		Wall w = new Wall(3, 3);
		dungeon.addEntity(w);
		
		// Check player is blocked by wall
		player.moveDown();
		assertEquals(2, player.getY());
		
		// Check enemy is blocked by wall	
		Enemy e = new Enemy(dungeon, 2, 3);
		dungeon.addEntity(e);
		e.moveRight();
		assertEquals(2, e.getX());
		
		// Check boulder is blocked by wall
		Boulder b = new Boulder(dungeon, 4, 3);
		dungeon.addEntity(b);
		b.moveLeft();
		assertEquals(4, b.getX());
	}

}

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;

class PlayerTest {
	private Dungeon dungeon;
	private Player player;
	
	@Before
	public void initializeDungeon() {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 3, 2);
		dungeon.setPlayer(player);
	}
	
	@Test
	void testMoveUp() {
		initializeDungeon();
		player.moveUp();
		assertEquals(1, player.getY());
	}
	@Test
	void testMoveDown() {
		initializeDungeon();
		player.moveDown();
		assertEquals(3, player.getY());
	}
	@Test
	void testMoveRight() {
		initializeDungeon();
		player.moveRight();
		assertEquals(4, player.getX());
	}

	@Test
	void testMoveLeft() {
		initializeDungeon();
		player.moveLeft();
		assertEquals(2, player.getX());
	}


}

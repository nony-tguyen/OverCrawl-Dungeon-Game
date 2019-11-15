package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.combat.Enemy;
import unsw.dungeon.obstacles.Boulder;
import unsw.dungeon.obstacles.Wall;

class EnemyTest {
	private Dungeon dungeon;
	private Player player;
	private Enemy e1, e2, e3;

	@Before
	public void initializeDungeon(){
		dungeon = new Dungeon(10, 10);
		
		player = new Player(dungeon, 0, 0, 1);
		dungeon.addPlayer(player);
	}
	
	@Test
	public void testEnemyMovementToStationaryPlayer() {
		initializeDungeon();
		e1 = new Enemy(dungeon, 2, 2);
		dungeon.addEntity(e1);
		assertEquals(e1.getX(), 2);
		assertEquals(e1.getY(), 2);
		
		// Testing enemy move strategy whether it constantly moves towards player
		e1.moveEnemy(0, 0);
		assertEquals(e1.getX(), 1);
		assertEquals(e1.getY(), 2);
		
		e1.moveEnemy(0, 0);
		assertEquals(e1.getX(), 0);
		assertEquals(e1.getY(), 2);
		
		e1.moveEnemy(0, 0);
		assertEquals(e1.getX(), 0);
		assertEquals(e1.getY(), 1);
	}
	
	@Test
	public void testMultipleEnemyMovement() {
		initializeDungeon();
		e1 = new Enemy(dungeon, 2, 2);
		dungeon.addEntity(e1);
		e2 = new Enemy(dungeon, 9, 0);
		dungeon.addEntity(e2);
		e3 = new Enemy(dungeon, 0, 9);
		dungeon.addEntity(e3);
		
		e1.moveEnemy(0, 0);
		assertEquals(e1.getX(), 1);
		assertEquals(e1.getY(), 2);
		
		e2.moveEnemy(0, 0);
		assertEquals(e2.getX(), 8);
		assertEquals(e2.getY(), 0);
		
		e3.moveEnemy(0, 0);
		assertEquals(e3.getX(), 0);
		assertEquals(e3.getY(), 8);
	}
	
	@Test
	public void testEnemyMovementWithMovingPlayer() {
		initializeDungeon();
		e1 = new Enemy(dungeon, 0, 5);
		dungeon.addEntity(e1);
		
		// Player moves right to (1, 0)
		player.moveRight();
		e1.moveEnemy(1, 0);
		assertEquals(e1.getX(), 1);
		assertEquals(e1.getY(), 5);
		
		// Player moves right to (2, 0)
		player.moveRight();
		e1.moveEnemy(2, 0);
		assertEquals(e1.getX(), 2);
		assertEquals(e1.getY(), 5);
		
		// Player moves down to (2, 1)
		player.moveDown();
		e1.moveEnemy(2, 1);
		assertEquals(e1.getX(), 2);
		assertEquals(e1.getY(), 4);		
	}
	
	@Test
	public void testEnemyMovementWithObstruction() {
		initializeDungeon();
		e1 = new Enemy(dungeon, 2, 2);
		dungeon.addEntity(e1);
		
		Wall wall = new Wall(1, 2);
		dungeon.addEntity(wall);
		
		// Enemy can not be at the wall's position
		e1.moveEnemy(0, 0);
		assertTrue(e1.getX() != 1 && e1.getY() != 2);
		dungeon.removeEntity(wall);
		
		
		Boulder boulder = new Boulder(dungeon, 1, 1);
		dungeon.addEntity(boulder);
		
		// Enemy can not be at the boulder's position
		e1.moveEnemy(0, 0);
		assertTrue(e1.getX() != 1 && e1.getY() != 1);
		dungeon.removeEntity(boulder);
	}
	
	@Test
	public void testEnemyKillPlayer() {
		initializeDungeon();
		e1 = new Enemy(dungeon, 1, 1);
		dungeon.addEntity(e1);
		
		e1.moveEnemy(0, 0);
		e1.moveEnemy(0, 0);
		
		// Enemy is now at player's position
		assertTrue(e1.getX() == 0 && e1.getY() == 0);
		assertTrue(dungeon.isGameFinished());
	}

}

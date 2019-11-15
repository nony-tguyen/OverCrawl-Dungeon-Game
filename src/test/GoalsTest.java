package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Exit;
import unsw.dungeon.FloorSwitch;
import unsw.dungeon.Player;
import unsw.dungeon.Treasure;
import unsw.dungeon.combat.Enemy;
import unsw.dungeon.combat.Sword;
import unsw.dungeon.goals.BouldersGoal;
import unsw.dungeon.goals.EnemyGoal;
import unsw.dungeon.goals.ExitGoal;
import unsw.dungeon.goals.GoalComponent;
import unsw.dungeon.goals.TreasureGoal;
import unsw.dungeon.obstacles.Boulder;

class GoalsTest {
	private Dungeon dungeon;
	private Player player;

	@Before
	public void initializeDungeon(){
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 0, 0, 1);
		dungeon.addPlayer(player);
	}
	
	@Test
	void testEnemyGoal() {
		initializeDungeon();
		Enemy e1 = new Enemy(dungeon, 2, 0);
		Enemy e2 = new Enemy(dungeon, 3, 0);
		Sword s = new Sword(dungeon, 1, 0);
		dungeon.addEntity(e1);
		dungeon.addEntity(e2);
		dungeon.addEntity(s);
		
		// Attach goals to the subject and set the goal
		GoalComponent goal = new EnemyGoal(dungeon);
		dungeon.setGoal(goal);
		e1.addGoalObserver(goal);
		e2.addGoalObserver(goal);
		goal.setGoalTotal(2);
		
		// Kill 1 enemy
		player.moveRight();
		s.useItem(player);
		assertEquals(goal.getCurrentTotal(), 1);
		assertFalse(goal.checkGoalCompleted());
		assertFalse(dungeon.isGameFinished());
		
		// Kill 2 enemy - finished goal
		player.moveRight();
		s.useItem(player);
		assertEquals(goal.getCurrentTotal(), 2);
		assertTrue(goal.checkGoalCompleted());
		assertTrue(dungeon.isGameFinished());
	}
	
	@Test
	void testTreasureGoal() {
		initializeDungeon();
		Treasure t1 = new Treasure(dungeon, 1, 0);
		Treasure t2 = new Treasure(dungeon, 2, 0);
		dungeon.addEntity(t1);
		dungeon.addEntity(t2);
		
		GoalComponent goal = new TreasureGoal(dungeon);
		dungeon.setGoal(goal);
		t1.addGoalObserver(goal);
		t2.addGoalObserver(goal);
		goal.setGoalTotal(2);
		
		// Collect 1 treasure
		player.moveRight();
		assertEquals(goal.getCurrentTotal(), 1);
		assertFalse(goal.checkGoalCompleted());
		assertFalse(dungeon.isGameFinished());
		
		// Kill 2 enemy - finished goal
		player.moveRight();
		assertEquals(goal.getCurrentTotal(), 2);
		assertTrue(goal.checkGoalCompleted());
		assertTrue(dungeon.isGameFinished());		
	}
	
	@Test
	void testBouldersGoal() {
		initializeDungeon();
		FloorSwitch fs1 = new FloorSwitch(2, 0);
		Boulder b1 = new Boulder(dungeon, 1, 0);
		FloorSwitch fs2 = new FloorSwitch(1, 2);
		Boulder b2 = new Boulder(dungeon, 1, 1);

		dungeon.addEntity(fs1);
		dungeon.addEntity(fs2);
		dungeon.addEntity(b1);
		dungeon.addEntity(b2);
		
		GoalComponent goal = new BouldersGoal(dungeon);
		dungeon.setGoal(goal);
		fs1.addGoalObserver(goal);
		fs2.addGoalObserver(goal);
		goal.setGoalTotal(2);
		
		// Push 1 boulder onto 1 floor switch
		player.moveRight();
		assertEquals(goal.getCurrentTotal(), 1);
		assertFalse(goal.checkGoalCompleted());
		assertFalse(dungeon.isGameFinished());
		
		// Push 2nd boulder into 2nd floor switch
		player.moveDown();
		assertEquals(goal.getCurrentTotal(), 2);
		assertTrue(goal.checkGoalCompleted());
		assertTrue(dungeon.isGameFinished());
				
		// Push 2nd boulder away from the floor switch - goal is not longer completed
		player.moveDown();
		assertEquals(goal.getCurrentTotal(), 1);
		assertFalse(goal.checkGoalCompleted());
		assertFalse(dungeon.isGameFinished());
	}
	
	@Test
	void testExitGoal() {
		initializeDungeon();
		Exit exit = new Exit(2, 1);
		dungeon.addEntity(exit);
		
		GoalComponent goal = new ExitGoal(dungeon);
		dungeon.setGoal(goal);
		exit.addGoalObserver(goal);
		
		
		// Player at (1, 0)
		player.moveRight();	
		assertFalse(goal.checkGoalCompleted());
		assertFalse(dungeon.isGameFinished());
		
		// Player at (2, 0)
		player.moveRight();	
		assertFalse(goal.checkGoalCompleted());
		assertFalse(dungeon.isGameFinished());
		
		// Player at (2, 1) -> Goal completed
		player.moveDown();	
		assertTrue(goal.checkGoalCompleted());
		assertTrue(dungeon.isGameFinished());
	}
}

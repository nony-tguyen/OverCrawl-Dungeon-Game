package test;

import static org.junit.Assert.assertFalse;
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
import unsw.dungeon.goals.ANDSubGoal;
import unsw.dungeon.goals.BouldersGoal;
import unsw.dungeon.goals.CompositeGoal;
import unsw.dungeon.goals.EnemyGoal;
import unsw.dungeon.goals.ExitGoal;
import unsw.dungeon.goals.GoalComponent;
import unsw.dungeon.goals.GoalConditions;
import unsw.dungeon.goals.ORSubGoal;
import unsw.dungeon.goals.TreasureGoal;
import unsw.dungeon.obstacles.Boulder;

class CompositeGoalsTest {
	private Dungeon dungeon;
	private Player player;

	@Before
	public void initializeDungeon(){
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 0, 0, 1);
		dungeon.addPlayer(player);
	}
	
	@Test
	void testCompositeGoal1() {
		// Destroying all enemies AND getting to an exit
		
		initializeDungeon();
		Enemy e1 = new Enemy(dungeon, 2, 0);
		Enemy e2 = new Enemy(dungeon, 3, 0);
		Sword s = new Sword(dungeon, 1, 0);
		dungeon.addEntity(e1);
		dungeon.addEntity(e2);
		dungeon.addEntity(s);
		
		GoalComponent enemyGoal = new EnemyGoal(dungeon);
		e1.addGoalObserver(enemyGoal);
		e2.addGoalObserver(enemyGoal);
		enemyGoal.setGoalTotal(2);
		
		Exit exit = new Exit(2, 1);
		dungeon.addEntity(exit);
		
		GoalComponent exitGoal = new ExitGoal(dungeon);
		exit.addGoalObserver(exitGoal);
		
		// Allows the goal to be checked if completed using AND
		GoalConditions strategy = new ANDSubGoal();
		GoalComponent goal = new CompositeGoal(dungeon, strategy);
		goal.addGoal(enemyGoal);
		goal.addGoal(exitGoal);
		dungeon.setGoal(goal);
		
		assertFalse(goal.checkGoalCompleted().get());
		assertFalse(dungeon.isGameFinished().get());
		
		// Finish enemy goal but overall goal not finished
		player.moveRight();
		s.useItem(player);
		player.moveRight();
		s.useItem(player);
		assertTrue(enemyGoal.checkGoalCompleted().get());
		assertFalse(goal.checkGoalCompleted().get());
		assertFalse(dungeon.isGameFinished().get());
		
		// Finish exit goal, overall goal is now completed
		player.moveDown();
		assertTrue(exitGoal.checkGoalCompleted().get());
		assertTrue(goal.checkGoalCompleted().get());
		assertTrue(dungeon.isGameFinished().get());
	}
	
	@Test
	void testCompositeGoal2() {
		// Collecting all treasure OR having a boulder on all floor switches
		
		initializeDungeon();
		Treasure t1 = new Treasure(dungeon, 1, 0);
		Treasure t2 = new Treasure(dungeon, 2, 0);
		dungeon.addEntity(t1);
		dungeon.addEntity(t2);
		FloorSwitch fs = new FloorSwitch(0, 2);
		Boulder b = new Boulder(dungeon, 0, 1);
		dungeon.addEntity(fs);
		dungeon.addEntity(b);
		
		GoalComponent treasureGoal = new TreasureGoal(dungeon);
		t1.addGoalObserver(treasureGoal);
		t2.addGoalObserver(treasureGoal);
		treasureGoal.setGoalTotal(2);
		GoalComponent boulderGoal = new BouldersGoal(dungeon);
		fs.addGoalObserver(boulderGoal);
		
		GoalConditions strategy = new ORSubGoal();
		GoalComponent goal = new CompositeGoal(dungeon, strategy);
		goal.addGoal(boulderGoal);
		goal.addGoal(treasureGoal);
		dungeon.setGoal(goal);
		
		assertFalse(goal.checkGoalCompleted().get());
		assertFalse(dungeon.isGameFinished().get());
		
		// Collect all treasure
		player.moveRight();
		player.moveRight();
		
		// OR: requires only 1 of the goals to be completed
		assertTrue(treasureGoal.checkGoalCompleted().get());
		assertFalse(boulderGoal.checkGoalCompleted().get());
		assertTrue(goal.checkGoalCompleted().get());
		assertTrue(dungeon.isGameFinished().get());
	}
	
	@Test
	void testCompositeGoal3() {
		// Getting to an exit AND (destroying all enemies OR collecting all treasure)
		
		initializeDungeon();
		
		// Enemy goal
		Enemy e = new Enemy(dungeon, 2, 0);
		Sword s = new Sword(dungeon, 3, 0);
		dungeon.addEntity(e);
		dungeon.addEntity(s);
		GoalComponent enemyGoal = new EnemyGoal(dungeon);
		e.addGoalObserver(enemyGoal);
		enemyGoal.setGoalTotal(1);
		
		// Treasure goal
		Treasure t = new Treasure(dungeon, 1, 0);
		dungeon.addEntity(t);
		GoalComponent treasureGoal = new TreasureGoal(dungeon);
		t.addGoalObserver(treasureGoal);
		treasureGoal.setGoalTotal(1);
		
		// Exit goal
		Exit exit = new Exit(1, 1);
		dungeon.addEntity(exit);
		GoalComponent exitGoal = new ExitGoal(dungeon);
		exit.addGoalObserver(exitGoal);
		
		// Create composite goal "destroying all enemies OR collecting all treasure"
		GoalComponent subGoal = new CompositeGoal(dungeon, new ORSubGoal());
		subGoal.addGoal(enemyGoal);
		subGoal.addGoal(treasureGoal);
		
		// Create the overall goal "Getting to an exit AND subGoal"
		GoalComponent goal = new CompositeGoal(dungeon, new ANDSubGoal());
		goal.addGoal(exitGoal);
		goal.addGoal(subGoal);
		dungeon.setGoal(goal);
		
		
		// Collect 1st treasure and finish sub goal
		player.moveRight();
		assertTrue(treasureGoal.checkGoalCompleted().get());
		assertFalse(enemyGoal.checkGoalCompleted().get());
		assertTrue(subGoal.checkGoalCompleted().get());
		assertFalse(goal.checkGoalCompleted().get());
		assertFalse(dungeon.isGameFinished().get());
		
		// Proceed to exit - finished all the goals
		player.moveDown();
		assertTrue(exitGoal.checkGoalCompleted().get());
		assertTrue(goal.checkGoalCompleted().get());
		assertTrue(dungeon.isGameFinished().get());
	}
	

}

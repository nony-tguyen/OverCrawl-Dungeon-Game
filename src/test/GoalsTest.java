package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.combat.Enemy;
import unsw.dungeon.combat.Sword;
import unsw.dungeon.goals.EnemyGoal;
import unsw.dungeon.goals.GoalComponent;

class GoalsTest {
	private Dungeon dungeon;
	private Player player;
	private Enemy e1, e2, e3;

	@Before
	public void initializeDungeon(){
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
	}
	@Test
	void testEnemyGoal() {
		initializeDungeon();
		Enemy e1 = new Enemy(dungeon, 2, 0);
		Enemy e2 = new Enemy(dungeon, 3, 0);
		Sword s = new Sword(dungeon, 1, 0);
		dungeon.addEntity(e1);
		dungeon.addEntity(e2);
		
		GoalComponent goal = new EnemyGoal();
		e1.addGoalObserver(goal);
		e2.addGoalObserver(goal);
		
		goal.setGoalTotal(2);
		if (goal.checkGoalCompleted(dungeon)) System.out.println("completed");
		else System.out.println("not completed");
		System.out.println(goal.getCurrentTotal());
		player.moveRight();
		s.attack(player);
		System.out.println(goal.getCurrentTotal());
		player.moveRight();
		s.attack(player);
		System.out.println(goal.getCurrentTotal());
		
		if (goal.checkGoalCompleted(dungeon)) System.out.println("completed");
		else System.out.println("not completed");
	}

}

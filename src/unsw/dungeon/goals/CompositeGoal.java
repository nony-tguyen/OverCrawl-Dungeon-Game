package unsw.dungeon.goals;

import java.util.ArrayList;
import java.util.List;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;

public class CompositeGoal implements GoalComponent {
	
	Dungeon dungeon;
	List<GoalComponent> goals;
	GoalConditions checkGoalStrategy;
	
	public CompositeGoal(Dungeon dungeon, GoalConditions checkGoalStrategy) {
		this.dungeon = dungeon;
		this.checkGoalStrategy = checkGoalStrategy;
		goals = new ArrayList<>();
	}
	
	@Override
	public boolean checkGoalCompleted() {
		return checkGoalStrategy.checkGoalCompleted(goals);
	}

	@Override
	public void updateGoal(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGoalTotal(int total) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCurrentTotal() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void addGoal(GoalComponent goal) {
		goals.add(goal);
	}

}

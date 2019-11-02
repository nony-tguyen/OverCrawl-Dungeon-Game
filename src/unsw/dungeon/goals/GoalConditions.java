package unsw.dungeon.goals;

import java.util.List;

import unsw.dungeon.Dungeon;

public interface GoalConditions {
	
	public boolean checkGoalCompleted(Dungeon dungeon, List<GoalComponent> goals);
}

package unsw.dungeon.goals;

import java.util.List;

public interface GoalConditions {
	
	public boolean checkGoalCompleted(List<GoalComponent> goals);
}

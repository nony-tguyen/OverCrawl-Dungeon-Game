package unsw.dungeon.goals;

import java.util.List;

import unsw.dungeon.Dungeon;

public class ANDSubGoal implements GoalConditions {
	
	/**
	 * AND subgoals, all sub goals must be completed for the composite goal to be satisfied
	 */
	@Override
	public boolean checkGoalCompleted(Dungeon dungeon, List<GoalComponent> goals) {
		for (GoalComponent g : goals) {
			if (!g.checkGoalCompleted(dungeon)) {
				return false;
			}
		}
		return true;
	}

}

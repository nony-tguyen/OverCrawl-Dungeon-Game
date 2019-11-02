package unsw.dungeon.goals;

import java.util.List;

import unsw.dungeon.Dungeon;

public class ORSubGoal implements GoalConditions {

	/**
	 * OR Subgoals, at least one sub goal must be completed for the composite goal to be satisfied
	 */
	@Override
	public boolean checkGoalCompleted(Dungeon dungeon, List<GoalComponent> goals) {
		for (GoalComponent g : goals) {
			if (g.checkGoalCompleted(dungeon)) {
				return true;
			}
		}
		return false;
	}

}

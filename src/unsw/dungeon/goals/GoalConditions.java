package unsw.dungeon.goals;

import java.util.List;

/**
 * A strategy interface to determine the grouping of subgoals
 * @author z5207825
 *
 */
public interface GoalConditions {
	
	public boolean checkGoalCompleted(List<GoalComponent> goals);
}

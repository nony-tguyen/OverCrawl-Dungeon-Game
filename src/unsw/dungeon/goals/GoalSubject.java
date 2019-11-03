package unsw.dungeon.goals;

/**
 * The subject that the goal class observes to determine whether the goal is achieved
 *
 */
public interface GoalSubject {
	
	public void addGoalObserver(GoalComponent goal);
	public void notifyGoal();
}

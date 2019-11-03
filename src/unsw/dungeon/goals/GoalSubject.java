package unsw.dungeon.goals;

public interface GoalSubject {
	
	public void addGoalObserver(GoalComponent goal);
	public void notifyGoal();
}

package unsw.dungeon.goals;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;

public interface GoalComponent {
	
	/**
	 * Determine whether the goal is completed
	 * @return true if completed otherwise false
	 */
	public boolean checkGoalCompleted();
	
	/**
	 * Update the goal given an action by the goal subject
	 * @param entity
	 */
	public void updateGoal(Entity entity);
	
	/**
	 * Set target total for the goal to be accomplished
	 * @param total
	 */
	public void setGoalTotal(int total);
	
	/**
	 * @return The current total achieved by the player
	 */
	public int getCurrentTotal();
	
	/**
	 * Add leaf goal 
	 * @param goal
	 */
	public void addGoal(GoalComponent goal);
}

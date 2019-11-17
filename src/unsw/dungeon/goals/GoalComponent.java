package unsw.dungeon.goals;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;

public interface GoalComponent {
	
	/**
	 * Determine whether the goal is completed
	 * @return true if completed otherwise false
	 */
	public BooleanProperty checkGoalCompleted();
	
	/**
	 * Update the goal given an action by the goal subject
	 * @param goalAchieved
	 */
	public void updateGoal(Boolean goalAchieved);
	
	/**
	 * Set target total for the goal to be accomplished
	 * @param total
	 */
	public void setGoalTotal(int total);
	
	/**
	 * @return The current total achieved by the player
	 */
	public IntegerProperty getCurrentTotal();
	
	/**
	 * Add leaf goal 
	 * @param goal
	 */
	public void addGoal(GoalComponent goal);
	
	/**
	 * @return Description of the goal
	 */
	public String getDescription();
	
	/**
	 * @return Total goal
	 */
	public int getGoalTotal();
}

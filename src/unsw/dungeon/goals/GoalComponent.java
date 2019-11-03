package unsw.dungeon.goals;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;

public interface GoalComponent {
	
	public boolean checkGoalCompleted();
	public void updateGoal(Entity entity);
	public void setGoalTotal(int total);
	public int getCurrentTotal();
}

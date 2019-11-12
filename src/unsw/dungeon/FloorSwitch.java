package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import unsw.dungeon.goals.GoalComponent;
import unsw.dungeon.goals.GoalSubject;

/**
 * A floor switch entity to place boulders on top of in order to complete a goal.
 * @author z5207825
 *
 */
public class FloorSwitch extends Entity implements GoalSubject {

	private boolean triggered;
	private List<GoalComponent> goalObservers;
	
	public FloorSwitch(int x, int y) {
		super(x, y);
		this.triggered = false;
		this.goalObservers = new ArrayList<>();
	}
	/**
	 * Set the trigger on
	 */
	public void trigger() {
		this.triggered = true;
		notifyGoal();
	}
	/**
	 * Set the trigger off
	 */
	public void untrigger() {
		this.triggered = false;
		notifyGoal();
	}
	
	public boolean isTriggered() {
		return this.triggered;
	}
	
	@Override
	public void addGoalObserver(GoalComponent goal) {
		goalObservers.add(goal);
	}
	
	@Override
	public void notifyGoal() {
		for (GoalComponent goal : goalObservers) {
			goal.updateGoal(triggered);
		}
	}
}

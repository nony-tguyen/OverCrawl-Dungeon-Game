package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import unsw.dungeon.goals.GoalComponent;
import unsw.dungeon.goals.GoalSubject;

public class FloorSwitch extends Entity implements GoalSubject {

	private boolean triggered;
	private List<GoalComponent> goalObservers;
	
	public FloorSwitch(int x, int y) {
		super(x, y);
		this.triggered = false;
		this.goalObservers = new ArrayList<>();
	}
	//TODO Make this a subject so that Goals can observe when this is triggered/untriggered 
	/**
	 * Set the trigger on
	 */
	public void trigger() {
		this.triggered = true;
		notifyGoal();
		//System.out.println("Triggered");
	}
	/**
	 * Set the trigger off
	 */
	public void untrigger() {
		this.triggered = false;
		notifyGoal();
		//System.out.println("Untriggered");
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
			goal.updateGoal(this);
		}
	}
}

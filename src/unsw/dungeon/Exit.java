package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import unsw.dungeon.goals.GoalComponent;
import unsw.dungeon.goals.GoalSubject;

public class Exit extends Entity implements GoalSubject {
	
	private List<GoalComponent> goalObservers;
	
	public Exit(int x, int y) {
		super(x, y);
		this.goalObservers = new ArrayList<>();
	}

	@Override
	public boolean affectGoal() {
		return true;
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

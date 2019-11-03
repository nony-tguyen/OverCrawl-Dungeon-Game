package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import unsw.dungeon.goals.GoalComponent;
import unsw.dungeon.goals.GoalSubject;

/**
 * A treasure entity that is collected to complete a goal
 *
 */
public class Treasure extends CollectableEntity implements GoalSubject{

	private List<GoalComponent> goalObservers;
	
	public Treasure(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.goalObservers = new ArrayList<>();
	}

	@Override
	public void useItem(Player player) {
	}

	@Override
	public void removeItem(Player player) {
		player.removeItem(this);	
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

package unsw.dungeon.goals;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import unsw.dungeon.Dungeon;

public class CompositeGoal implements GoalComponent {
	
	private Dungeon dungeon;
	private List<GoalComponent> goals;
	private GoalConditions checkGoalStrategy;
	private BooleanProperty complete;
	
	
	public CompositeGoal(Dungeon dungeon, GoalConditions checkGoalStrategy) {
		this.dungeon = dungeon;
		this.checkGoalStrategy = checkGoalStrategy;
		goals = new ArrayList<>();
		complete = new SimpleBooleanProperty(false);
	}
	
	@Override
	public BooleanProperty checkGoalCompleted() {
		if (checkGoalStrategy.checkGoalCompleted(goals)) {
			complete.set(true);
			return complete;
		} else {
			complete.set(false);
			return complete;
		}
	}

	@Override
	public void updateGoal(Boolean goalAchieved) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGoalTotal(int total) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCurrentTotal() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void addGoal(GoalComponent goal) {
		goals.add(goal);
	}

}

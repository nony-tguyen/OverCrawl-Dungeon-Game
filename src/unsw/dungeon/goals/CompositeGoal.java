package unsw.dungeon.goals;

import java.util.ArrayList;
import java.util.List;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;

public class CompositeGoal implements GoalComponent {
	
	List<GoalComponent> goals;
	
	
	public CompositeGoal() {
		goals = new ArrayList<>();
	}
	
	@Override
	public boolean checkGoalCompleted(Dungeon dungeon) {
		
		return false;
	}

	@Override
	public void updateGoal(Entity entity) {
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

}

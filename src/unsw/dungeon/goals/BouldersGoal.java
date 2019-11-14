package unsw.dungeon.goals;

import unsw.dungeon.Dungeon;

public class BouldersGoal implements GoalComponent {
	
	Dungeon dungeon;
	int totalFloorSwitch;
	int currentCount;
	private boolean complete;
	
	public BouldersGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
		this.complete = false;
	}
	
	@Override
	public boolean checkGoalCompleted() {
		return complete;
	}

	@Override
	public void updateGoal(Boolean goalAchieved) {
		if (goalAchieved) {
			currentCount++;
			if (currentCount == totalFloorSwitch) {
				complete = true;
			} 
		} else {
			currentCount--;
			complete = false;
		}
		dungeon.updateGameProgression();
	}

	@Override
	public void setGoalTotal(int total) {
		totalFloorSwitch = total;
	}

	@Override
	public int getCurrentTotal() {
		return currentCount;
	}

	@Override
	public void addGoal(GoalComponent goal) {}

}

package unsw.dungeon.goals;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;

public class TreasureGoal implements GoalComponent {
	
	Dungeon dungeon;
	int totalTreasure;
	int currentCount;
	
	public TreasureGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	
	@Override
	public boolean checkGoalCompleted(Dungeon dungeon) {
		if (currentCount == totalTreasure) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void updateGoal(Entity entity) {
		currentCount = currentCount + 1;
	}

	@Override
	public void setGoalTotal(int total) {
		totalTreasure = total;
	}

	@Override
	public int getCurrentTotal() {
		return currentCount;
	}

}

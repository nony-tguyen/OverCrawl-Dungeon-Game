package unsw.dungeon.goals;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;

public class TreasureGoal implements GoalComponent {
	
	Dungeon dungeon;
	int totalTreasure;
	int currentCount;
	private boolean complete;
	
	public TreasureGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
		this.complete = false;
	}
	
	@Override
	public boolean checkGoalCompleted() {
		return complete;
	}

	@Override
	public void updateGoal(Entity entity) {
		currentCount = currentCount + 1;
		if (currentCount == totalTreasure) {
			complete = true;
			dungeon.updateGameProgression();
		}
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

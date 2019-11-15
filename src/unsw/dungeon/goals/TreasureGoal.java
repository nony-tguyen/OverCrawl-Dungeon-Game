package unsw.dungeon.goals;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import unsw.dungeon.Dungeon;

public class TreasureGoal implements GoalComponent {
	
	Dungeon dungeon;
	int totalTreasure;
	int currentCount;
	private BooleanProperty complete;
	
	public TreasureGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
		complete = new SimpleBooleanProperty(false);
	}
	
	@Override
	public BooleanProperty checkGoalCompleted() {
		return complete;
	}

	@Override
	public void updateGoal(Boolean goalAchieved) {
		currentCount = currentCount + 1;
		if (currentCount == totalTreasure) {
			complete.set(true);
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

	@Override
	public void addGoal(GoalComponent goal) {}

}

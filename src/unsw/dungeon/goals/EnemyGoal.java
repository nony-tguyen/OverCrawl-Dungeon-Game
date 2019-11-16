package unsw.dungeon.goals;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import unsw.dungeon.Dungeon;

public class EnemyGoal implements GoalComponent {
	
	private Dungeon dungeon;
	private int totalEnemies;
	private int currentCount;
	private BooleanProperty complete;
	
	public EnemyGoal(Dungeon dungeon) {
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
		if (currentCount == totalEnemies) {
			complete.set(true);
			dungeon.updateGameProgression();
		}
	}

	@Override
	public void setGoalTotal(int total) {
		this.totalEnemies = total;
	}

	@Override
	public int getCurrentTotal() {
		return currentCount;
	}

	@Override
	public void addGoal(GoalComponent goal) {}
}

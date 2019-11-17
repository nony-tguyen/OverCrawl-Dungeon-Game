package unsw.dungeon.goals;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.dungeon.Dungeon;

public class EnemyGoal implements GoalComponent {
	
	private Dungeon dungeon;
	private int totalEnemies;
	private IntegerProperty currentCount;
	private BooleanProperty complete;
	
	public EnemyGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
		complete = new SimpleBooleanProperty(false);
		currentCount = new SimpleIntegerProperty(0);
	}
	
	@Override
	public BooleanProperty checkGoalCompleted() {
		return complete;
	}

	@Override
	public void updateGoal(Boolean goalAchieved) {
		currentCount.set(getCurrentTotal().get() + 1);
		if (currentCount.get() == totalEnemies) {
			complete.set(true);
			dungeon.updateGameProgression();
		}
	}

	@Override
	public void setGoalTotal(int total) {
		this.totalEnemies = total;
	}

	@Override
	public IntegerProperty getCurrentTotal() {
		return currentCount;
	}

	@Override
	public void addGoal(GoalComponent goal) {}

	@Override
	public String getDescription() {
		return "Kill all enemies";
	}

	@Override
	public int getGoalTotal() {
		return totalEnemies;
	}
}

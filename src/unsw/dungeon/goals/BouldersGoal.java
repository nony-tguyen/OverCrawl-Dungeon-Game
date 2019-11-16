package unsw.dungeon.goals;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.dungeon.Dungeon;

public class BouldersGoal implements GoalComponent {
	
	private Dungeon dungeon;
	private int totalFloorSwitch;
	private IntegerProperty currentCount;
	private BooleanProperty complete;
	
	public BouldersGoal(Dungeon dungeon) {
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
		if (goalAchieved) {
			currentCount.set(getCurrentTotal().get() + 1);
			if (currentCount.get() == totalFloorSwitch) {
				complete.set(true);
			} 
		} else {
			currentCount.set(getCurrentTotal().get() - 1);
			complete.set(false);
		}
		dungeon.updateGameProgression();
	}

	@Override
	public void setGoalTotal(int total) {
		totalFloorSwitch = total;
	}

	@Override
	public IntegerProperty getCurrentTotal() {
		return currentCount;
	}

	@Override
	public void addGoal(GoalComponent goal) {}

	@Override
	public String getDescription() {
		return "Place boulders on all floor switches";
	}

	@Override
	public int getGoalTotal() {
		return totalFloorSwitch;
	}

}

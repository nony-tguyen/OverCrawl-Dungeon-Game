package unsw.dungeon.goals;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.FloorSwitch;

public class BouldersGoal implements GoalComponent {
	
	private Dungeon dungeon;
	private int totalFloorSwitch;
	private IntegerProperty currentCount;
	private BooleanProperty complete;
	
	public BouldersGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
		complete = new SimpleBooleanProperty(false);
		currentCount = new SimpleIntegerProperty(getInitialFloorSwitches());
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

	public List<FloorSwitch> getFloorSwitches(Dungeon dungeon) {
		List<FloorSwitch> floorSwitch = new ArrayList<>();
		for (Entity entity : dungeon.getEntities()) {
			if (entity instanceof FloorSwitch)
				floorSwitch.add((FloorSwitch) entity);
		}
		return floorSwitch;
	}
	
	private int getInitialFloorSwitches() {
		int count = 0;
		for (FloorSwitch fs : getFloorSwitches(dungeon)) {
			if (fs.isTriggered()) count++;
		}
		return count;
	}
}

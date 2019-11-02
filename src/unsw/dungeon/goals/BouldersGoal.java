package unsw.dungeon.goals;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.FloorSwitch;

public class BouldersGoal implements GoalComponent {
	
	int totalFloorSwitch;
	int currentCount;
	
	@Override
	public boolean checkGoalCompleted(Dungeon dungeon) {
		if (currentCount == totalFloorSwitch) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void updateGoal(Entity entity) {
		if (((FloorSwitch) entity).isTriggered()) {
			currentCount++;
		} else {
			currentCount--;
		}
	}

	@Override
	public void setGoalTotal(int total) {
		totalFloorSwitch = total;
	}

	@Override
	public int getCurrentTotal() {
		return currentCount;
	}

}

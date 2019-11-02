package unsw.dungeon.goals;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;

public class EnemyGoal implements GoalComponent {
	
	Dungeon dungeon;
	int totalEnemies;
	int currentCount;
	
	public EnemyGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	
	@Override
	public boolean checkGoalCompleted(Dungeon dungeon) {
		if (currentCount == totalEnemies) {
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
		this.totalEnemies = total;
	}

	@Override
	public int getCurrentTotal() {
		return currentCount;
	}
}

package unsw.dungeon.goals;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;

public class EnemyGoal implements GoalComponent {
	
	private Dungeon dungeon;
	private int totalEnemies;
	private int currentCount;
	private boolean complete;
	
	public EnemyGoal(Dungeon dungeon) {
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
		if (currentCount == totalEnemies) {
			complete = true;
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
}

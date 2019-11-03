package unsw.dungeon.goals;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Exit;

public class ExitGoal implements GoalComponent {
	
	Dungeon dungeon;
	private boolean complete;
	
	public ExitGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
		this.complete = false;
	}
	
	@Override
	public boolean checkGoalCompleted() {
		return complete;
	}

	@Override
	public void updateGoal(Entity entity) {
		Exit exit = getExit(dungeon);
		if (dungeon.getPlayer().getX() == exit.getX() && dungeon.getPlayer().getY() == exit.getY()) {
			complete = true;
			dungeon.updateGameProgression();
		} else {
			complete = false;
			dungeon.updateGameProgression();
		}
	}

	@Override
	public void setGoalTotal(int total) { }

	@Override
	public int getCurrentTotal() {
		return 0;
	}
	
	public Exit getExit(Dungeon dungeon) {
		for (Entity entity : dungeon.getEntities()) {
			if (entity instanceof Exit)
				return (Exit) entity;
		}
		return null;	
	}

	@Override
	public void addGoal(GoalComponent goal) {}

}

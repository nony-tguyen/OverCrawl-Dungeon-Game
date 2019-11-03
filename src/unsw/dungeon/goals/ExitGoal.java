package unsw.dungeon.goals;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Exit;

public class ExitGoal implements GoalComponent {
	
	Dungeon dungeon;
	
	@Override
	public boolean checkGoalCompleted(Dungeon dungeon) {
		Exit exit = getExit(dungeon);
		if (dungeon.getPlayer().getX() == exit.getX() && dungeon.getPlayer().getY() == exit.getY()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void updateGoal(Entity entity) {
		checkGoalCompleted(dungeon);
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

}

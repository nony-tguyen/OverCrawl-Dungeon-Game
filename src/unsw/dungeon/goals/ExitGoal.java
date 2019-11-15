package unsw.dungeon.goals;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Exit;

public class ExitGoal implements GoalComponent {
	
	private Dungeon dungeon;
	private BooleanProperty complete;
	
	public ExitGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
		complete = new SimpleBooleanProperty(false);
	}
	
	@Override
	public BooleanProperty checkGoalCompleted() {
		return complete;
	}

	@Override
	public void updateGoal(Boolean goalAchieved) {
		Exit exit = getExit(dungeon);
		if (dungeon.getPlayer().getX() == exit.getX() && dungeon.getPlayer().getY() == exit.getY()) {
			complete.set(true);
			dungeon.updateGameProgression();
		} else {
			complete.set(false);
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

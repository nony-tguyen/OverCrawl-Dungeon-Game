package unsw.dungeon.goals;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Exit;

public class ExitGoal implements GoalComponent {
	
	private Dungeon dungeon;
	private BooleanProperty complete;
	private IntegerProperty currentCount;
	private int total;
	
	public ExitGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
		complete = new SimpleBooleanProperty(false);
		currentCount = new SimpleIntegerProperty(0);
		total = 1;
	}
	
	@Override
	public BooleanProperty checkGoalCompleted() {
		return complete;
	}

	@Override
	public void updateGoal(Boolean goalAchieved) {
		Exit exit = getExit(dungeon);
		// TODO change?
		if ((dungeon.getPlayer(1).getX() == exit.getX() && dungeon.getPlayer(1).getY() == exit.getY())
				|| (dungeon.getPlayer(2).getX() == exit.getX() && dungeon.getPlayer(2).getY() == exit.getY())) {
			complete.set(true);
			currentCount.set(1);
			dungeon.updateGameProgression();
		} else {
			complete.set(false);
			dungeon.updateGameProgression();
		}
	}

	@Override
	public void setGoalTotal(int total) { }

	@Override
	public IntegerProperty getCurrentTotal() {
		return currentCount;
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

	@Override
	public String getDescription() {
		return "Find the exit";
	}

	@Override
	public int getGoalTotal() {
		return total;
	}

}

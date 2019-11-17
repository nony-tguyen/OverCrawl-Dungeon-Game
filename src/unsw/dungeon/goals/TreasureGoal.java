package unsw.dungeon.goals;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Treasure;

public class TreasureGoal implements GoalComponent {
	
	private Dungeon dungeon;
	private int totalTreasure;
	private IntegerProperty currentCount;
	private BooleanProperty complete;
	
	public TreasureGoal(Dungeon dungeon) {
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
		if (currentCount.get() == totalTreasure) {
			complete.set(true);
			dungeon.updateGameProgression();
		}
	}

	@Override
	public void setGoalTotal(int total) {
		totalTreasure = total;
	}

	@Override
	public IntegerProperty getCurrentTotal() {
		return currentCount;
	}

	@Override
	public void addGoal(GoalComponent goal) {}


	@Override
	public String getDescription() {
		return "Collect all Treasures";
	}

	@Override
	public int getGoalTotal() {
		return totalTreasure;
	}

	public List<Treasure> getTreasures(Dungeon dungeon) {
		List<Treasure> treasures = new ArrayList<>();
		for (Entity entity : dungeon.getEntities()) {
			if (entity instanceof Treasure)
				treasures.add((Treasure) entity);
		}
		return treasures;
	}
}

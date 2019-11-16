package unsw.application;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Exit;
import unsw.dungeon.FloorSwitch;
import unsw.dungeon.Key;
import unsw.dungeon.Portal;
import unsw.dungeon.Treasure;
import unsw.dungeon.combat.Enemy;
import unsw.dungeon.combat.Gnome;
import unsw.dungeon.combat.Hound;
import unsw.dungeon.combat.InvincibilityPotion;
import unsw.dungeon.combat.Sword;
import unsw.dungeon.goals.BouldersGoal;
import unsw.dungeon.goals.CompositeGoal;
import unsw.dungeon.goals.EnemyGoal;
import unsw.dungeon.goals.ExitGoal;
import unsw.dungeon.goals.GoalComponent;
import unsw.dungeon.goals.TreasureGoal;
import unsw.dungeon.obstacles.Boulder;
import unsw.dungeon.obstacles.Door;
import unsw.dungeon.obstacles.Wall;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private HashMap<Entity, ImageView> entities;
    private GoalControllerLoader goalLoader;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image boulderImage;
    private Image floorSwitchImage;
    private Image closedDoorImage;
    private Image openDoorImage;
    private Image keyImage;
    private Image portalImage;
    private Image treasureImage;
    private Image swordImage;
    private Image invincibilityImage;
	private Image enemyArcherImage;
	private Image houndImage;
	private Image gnomeImage;

	private Image exitImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new HashMap<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        boulderImage = new Image("/boulder.png");
        floorSwitchImage = new Image("/pressure_plate.png");
        closedDoorImage = new Image("/closed_door.png");
        openDoorImage = new Image("/open_door.png");
        keyImage = new Image("/key.png");
        portalImage = new Image("/portal.png");
        treasureImage = new Image("/gold_pile.png");
        swordImage = new Image("/greatsword_1_new.png");
        invincibilityImage = new Image("/brilliant_blue_new.png");
        enemyArcherImage = new Image("/deep_elf_master_archer.png");
        houndImage = new Image("/hound.png");
        gnomeImage = new Image("/gnome.png");
        exitImage = new Image("/exit.png");
        
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    @Override
    public void onLoad(Boulder boulder) {
		ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
    }
    @Override
    public void onLoad(FloorSwitch fs) {
		ImageView view = new ImageView(floorSwitchImage);
        addEntity(fs, view);
    }
    @Override
    public void onLoad(Door door) {
		ImageView view = new ImageView(closedDoorImage);
        addEntity(door, view);
    }
    @Override
    public void onLoad(Key key) {
		ImageView view = new ImageView(keyImage);
        addEntity(key, view);
    }
    @Override
    public void onLoad(Portal portal) {
		ImageView view = new ImageView(portalImage);
        addEntity(portal, view);
    }
	@Override
	public void onLoad(Enemy enemy) {
		ImageView view = new ImageView(enemyArcherImage);
        addEntity(enemy, view);
	}
	@Override
	public void onLoad(Hound hound) {
		ImageView view = new ImageView(houndImage);
        addEntity(hound, view);
	}
	@Override
	public void onLoad(Gnome gnome) {
		ImageView view = new ImageView(gnomeImage);
        addEntity(gnome, view);
	}
	@Override
	public void onLoad(Sword sword) {
		ImageView view = new ImageView(swordImage);
        addEntity(sword, view);		
	}

	@Override
	public void onLoad(InvincibilityPotion invincibility) {
		ImageView view = new ImageView(invincibilityImage);
        addEntity(invincibility, view);
	}

	@Override
	public void onLoad(Exit exit) {
		ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
	}

	@Override
	public void onLoad(Treasure treasure) {
		ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
	}
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.put(entity, view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
        
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }

    
    /**
     * Creating goals for the dungeon game
     */

	@Override
	public void onLoadGoal(BouldersGoal bouldersGoal, Dungeon dungeon) {
		for (FloorSwitch floorSwitch : getFloorSwitches(dungeon))
			floorSwitch.addGoalObserver(bouldersGoal);
		
		bouldersGoal.setGoalTotal(getFloorSwitches(dungeon).size());
		trackGoalProgression(bouldersGoal, dungeon);
	}

	@Override
	public void onloadGoal(TreasureGoal treasureGoal, Dungeon dungeon) {
		for (Treasure treasure : getTreasures(dungeon))
			treasure.addGoalObserver(treasureGoal);
		
		treasureGoal.setGoalTotal(getTreasures(dungeon).size());
		trackGoalProgression(treasureGoal, dungeon);
	}

	@Override
	public void onLoadGoal(EnemyGoal enemyGoal, Dungeon dungeon) {
		for (Enemy enemy : dungeon.getEnemies()) 
			enemy.addGoalObserver(enemyGoal);
		
		enemyGoal.setGoalTotal(dungeon.getEnemies().size());
		trackGoalProgression(enemyGoal, dungeon);	
	}

	@Override
	public void onLoadGoal(ExitGoal exitGoal, Dungeon dungeon) {
		Exit exit = exitGoal.getExit(dungeon);
		exit.addGoalObserver(exitGoal);
		trackGoalProgression(exitGoal, dungeon);	
	}
	
	@Override
	public void onloadGoal(CompositeGoal cg, Dungeon dungeon) {
		trackGoalProgression(cg, dungeon);
	}
	
	private void trackGoalProgression(GoalComponent goal, Dungeon dungeon) {
		Label countLabel = new Label();
		
		if (goal.getGoalTotal() == 0) {
			goalLoader.addCompositeDisplay(countLabel, goal);
		} else {
			countLabel.textProperty().bind(goal.getCurrentTotal().asString());
			goalLoader.addDisplay(countLabel, goal);
		}
		
		goal.checkGoalCompleted().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue == true) {
					countLabel.setTextFill(Color.GREEN);
					System.out.println("goal completed");
				}	
			}
		});
	}
	
	public void setGoalControllerLoader(GoalControllerLoader goalLoader) {
        this.goalLoader = goalLoader;
    }
	/**
	 * @param dungeon
	 * @return Get floor switches in dungeon for goal
	 */
	private List<FloorSwitch> getFloorSwitches(Dungeon dungeon) {
		List<FloorSwitch> floorSwitch = new ArrayList<>();
		for (Entity entity : dungeon.getEntities()) {
			if (entity instanceof FloorSwitch)
				floorSwitch.add((FloorSwitch) entity);
		}
		return floorSwitch;
	}
	
	/**
	 * @param dungeon
	 * @return Get treasures in dungeon for goal
	 */
	private List<Treasure> getTreasures(Dungeon dungeon) {
		List<Treasure> treasures = new ArrayList<>();
		for (Entity entity : dungeon.getEntities()) {
			if (entity instanceof Treasure)
				treasures.add((Treasure) entity);
		}
		return treasures;
	}



}

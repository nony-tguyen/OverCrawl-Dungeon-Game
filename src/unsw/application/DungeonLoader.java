package unsw.application;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Exit;
import unsw.dungeon.FloorSwitch;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
import unsw.dungeon.Portal;
import unsw.dungeon.Treasure;
import unsw.dungeon.combat.Enemy;
import unsw.dungeon.combat.Gnome;
import unsw.dungeon.combat.Hound;
import unsw.dungeon.combat.InvincibilityPotion;
import unsw.dungeon.combat.Sword;
import unsw.dungeon.goals.ANDSubGoal;
import unsw.dungeon.goals.BouldersGoal;
import unsw.dungeon.goals.CompositeGoal;
import unsw.dungeon.goals.EnemyGoal;
import unsw.dungeon.goals.ExitGoal;
import unsw.dungeon.goals.GoalComponent;
import unsw.dungeon.goals.GoalConditions;
import unsw.dungeon.goals.ORSubGoal;
import unsw.dungeon.goals.TreasureGoal;
import unsw.dungeon.obstacles.Boulder;
import unsw.dungeon.obstacles.ClosedState;
import unsw.dungeon.obstacles.Door;
import unsw.dungeon.obstacles.Wall;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {
	
    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        JSONObject jsonGoals = json.getJSONObject("goal-condition");
        GoalComponent goal = loadGoal(dungeon, jsonGoals);
        dungeon.setGoal(goal);
        
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        int id = 0;
        if (type.equals("door") || type.equals("key") || type.equals("portal")) {
            id = json.getInt("id");    	
        }


        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "boulder":
        	Boulder boulder = new Boulder(dungeon, x, y);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "switch":
        	FloorSwitch fs = new FloorSwitch(x, y);
        	onLoad(fs);
        	entity = fs;
        	break;
        case "door": 
        	Door door = new Door(x, y, id);
        	onLoad(door);
        	entity = door;
        	break;
        case "key": 
        	Key key = new Key(dungeon, x, y, id);
        	onLoad(key);
        	entity = key;
        	break;
        case "portal":
        	Portal portal = new Portal(dungeon, x, y, id);
        	onLoad(portal);
        	entity = portal;
        	break;
        case "enemy": 
        	Enemy enemy = new Enemy(dungeon, x, y);
        	onLoad(enemy);
        	entity = enemy; 
        	break;
        case "hound": 
        	Hound hound = new Hound(dungeon, x, y);
        	onLoad(hound);
        	entity = hound; 
        	break;
        case "gnome": 
        	Gnome gnome = new Gnome(dungeon, x, y);
        	onLoad(gnome);
        	entity = gnome; 
        	break;
        case "sword": 
        	Sword sword = new Sword(dungeon, x, y);
        	onLoad(sword);
        	entity = sword; 
        	break;
        case "invincibility": 
        	InvincibilityPotion invincibility = new InvincibilityPotion(dungeon, x, y);
        	onLoad(invincibility);
        	entity = invincibility; 
        	break;
        case "exit": 
        	Exit exit = new Exit(x, y);
        	onLoad(exit);
        	entity = exit; 
        	break;
        case "treasure": 
        	Treasure treasure = new Treasure(dungeon, x, y);
        	onLoad(treasure);
        	entity = treasure; 
        	break;
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);
    public abstract void onLoad(Wall wall);
    public abstract void onLoad(Boulder boulder);
    public abstract void onLoad(FloorSwitch fs);
    public abstract void onLoad(Door door);
    public abstract void onLoad(Key key);
    public abstract void onLoad(Portal portal);
    public abstract void onLoad(Enemy enemy);
    public abstract void onLoad(Hound hound);
    public abstract void onLoad(Gnome gnome);
    public abstract void onLoad(Sword sword);
    public abstract void onLoad(InvincibilityPotion invincibility);
    public abstract void onLoad(Exit exit);
    public abstract void onLoad(Treasure treasure);


    // TODO Create additional abstract methods for the other entities

    // Create Goals 
    private GoalComponent loadGoal(Dungeon dungeon, JSONObject json) {
    	String dungeonGoal = json.getString("goal");
    	
    	GoalComponent goal = null;
    	switch(dungeonGoal) {
    	case "exit":
    		ExitGoal exitGoal = new ExitGoal(dungeon);
    		goal = exitGoal;
    		onLoadGoal(exitGoal, dungeon);
    		break;
    	case "enemies":
    		EnemyGoal enemyGoal = new EnemyGoal(dungeon);
    		goal = enemyGoal;
    		onLoadGoal(enemyGoal, dungeon);
    		break;
    	case "boulders":
    		BouldersGoal bouldersGoal = new BouldersGoal(dungeon);
    		goal = bouldersGoal;
    		onLoadGoal(bouldersGoal, dungeon);
    		break;
    	case "treasure":
    		TreasureGoal treasureGoal = new TreasureGoal(dungeon);
    		goal = treasureGoal;
    		onloadGoal(treasureGoal, dungeon);
    		break;
    	case "AND":
    		CompositeGoal cgAND = buildCompositeSubGoal(json.getJSONArray("subgoals"), dungeon, new ANDSubGoal());
    		goal = cgAND;
    		onloadGoal(cgAND, dungeon);
    		break;
    	case "OR":
    		CompositeGoal cgOR = buildCompositeSubGoal(json.getJSONArray("subgoals"), dungeon, new ORSubGoal());
    		goal = cgOR;
    		onloadGoal(cgOR, dungeon);
    		break;
    	}
    	return goal;
    }

    public abstract void onloadGoal(CompositeGoal cg, Dungeon dungeon);
	public abstract void onLoadGoal(BouldersGoal bouldersGoal, Dungeon dungeon);
	public abstract void onloadGoal(TreasureGoal treasureGoal, Dungeon dungeon);
	public abstract void onLoadGoal(EnemyGoal goal, Dungeon dungeon);
    public abstract void onLoadGoal(ExitGoal exitGoal, Dungeon dungeon);
    
	public CompositeGoal buildCompositeSubGoal(JSONArray goals, Dungeon dungeon, GoalConditions strategy) {
		CompositeGoal goal = new CompositeGoal(dungeon, strategy);
		for (int i = 0; i < goals.length(); i++) {
			goal.addGoal(loadGoal(dungeon, goals.getJSONObject(i)));
		}
		return goal;
	}
}

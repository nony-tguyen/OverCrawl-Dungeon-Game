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
import unsw.dungeon.combat.InvincibilityPotion;
import unsw.dungeon.combat.Sword;
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
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        int id = 0;
        int playerNum = 1;
        if (type.equals("door") || type.equals("key") || type.equals("portal")) {
            id = json.getInt("id");    	
        }
        if (type.equals("player")) {
        	playerNum = json.getInt("playerNum");
        }


        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y, playerNum);
            dungeon.addPlayer(player);
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
    public abstract void onLoad(Sword sword);
    public abstract void onLoad(InvincibilityPotion invincibility);
    public abstract void onLoad(Exit exit);
    public abstract void onLoad(Treasure treasure);


    // TODO Create additional abstract methods for the other entities

}

package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import unsw.dungeon.combat.Enemy;
import unsw.dungeon.obstacles.Boulder;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends MovableEntity {
	PlayerState normalPlayer;
	PlayerState invinciblePlayer;
	List<CollectableEntity> inventory;
	
    PlayerState playerState;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        inventory = new ArrayList<>();
        normalPlayer = new NormalPlayer(this);
        invinciblePlayer = new InvinciblePlayer(this);
        playerState = normalPlayer;
    }

	@Override
	public void action() {
		/*
		Entity eOverlapped = dungeon.checkGrid(x, y);
		if (eOverlapped instanceof Boulder) {
			eOverlapped.moveBoulder()
			
		}
		*/
		// based on the entity do something
		// d
	}
	
	public void addItem(CollectableEntity entity) {
		inventory.add(entity);
	}
	
	public void removeItem(CollectableEntity entity) {
		inventory.remove(entity);
	}
	
	public List<CollectableEntity> getInventory() {
		return inventory;
	}
	
	public void setPlayerState(PlayerState playerState) {
		this.playerState = playerState;
		notifyEnemies();
	}
	
	public boolean handleEnemy(Enemy enemy) {
		return playerState.handleEnemy(enemy);
	}

	public boolean isVulnerable() {
		return playerState.isVulnerable();
	}
	
	public void notifyEnemies() {
		for (Enemy enemy : dungeon.getEnemies()) {
			enemy.updateMovement(this);
		}
	}
}

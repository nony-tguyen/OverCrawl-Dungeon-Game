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
		Enemy enemy = foundEnemy();
		if (enemy != null) {
			handleEnemy(enemy);
		}
		
		for (Entity entity : dungeon.checkGrid(getX(), getY())) {
			if (entity instanceof CollectableEntity) 
				((CollectableEntity) entity).collectItem(this);
		}
	}
	
	public void addItem(CollectableEntity entity) {
		System.out.println("Adding" + entity);
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
	
	public Enemy foundEnemy() {
		for (Enemy enemy : dungeon.getEnemies()) {
			if (enemy.getX() == getX() && enemy.getY() == getY())
				return enemy;
		}
		return null;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	/**
	 * Retrieve the entities that are in the adjacent grid in the direction of the player
	 * @return List of entity in the adjacent grid
	 */
	public List<Entity> getAdjacentEntity() {
		
		if (this.direction == Direction.UP) 
			return dungeon.checkGrid(getX(), getY() - 1);
		else if (this.direction == Direction.DOWN) 
			return dungeon.checkGrid(getX(), getY() + 1);
		else if (this.direction == Direction.RIGHT) 
			return dungeon.checkGrid(getX() + 1, getY());
		else if (this.direction == Direction.LEFT) 
			return dungeon.checkGrid(getX() - 1, getY());
		else 
			return null;
	}
}

package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import unsw.dungeon.combat.Enemy;
import unsw.dungeon.combat.Sword;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends MovableEntity {
	private int playerNum;
	PlayerState normalPlayer;
	PlayerState invinciblePlayer;
	List<CollectableEntity> inventory;
	Sword sword;
	
    PlayerState playerState;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y, int playerNum) {
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
			//System.out.println("hello enemy");
			handleEnemy(enemy);
			//if (dungeon.getPlayer() == null) System.out.println("player is null");
		}
		
		for (Entity entity : dungeon.checkGrid(getX(), getY())) {
			entity.collectItem(this);
			if (entity.affectGoal()) {
				entity.notifyGoal();
			}
			
			/*if (entity instanceof CollectableEntity) 
				((CollectableEntity) entity).collectItem(this);
			
			if (entity.affectGoal())
				((GoalSubject) entity).notifyGoal();	*/
		}
	}
	
	/**
	 * Add a collectable entity to player's inventory
	 * @param entity
	 */
	public void addItem(CollectableEntity entity) {
		inventory.add(entity);
	}
	
	/**
	 * Remove an item from the player's inventory
	 * @param entity
	 */
	public void removeItem(CollectableEntity entity) {
		inventory.remove(entity);
	}
	
	/**
	 * Get player's item currently obtained
	 * @return inventory
	 */
	public List<CollectableEntity> getInventory() {
		return inventory;
	}
	
	/**
	 * 
	 * @return the player's sword if they have one
	 */
	public Sword getSword() {
		return sword;
	}
	
	public void setSword(Sword sword) {
		this.sword = sword;
	}
	
	/**
	 * Change the player's current state
	 * @param playerState
	 */
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
	
	/**
	 * Enemies observe the player's state and move accordingly
	 */
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
	
	/**
	 * The player is killed
	 */
	public void killPlayer() {
		dungeon.removePlayer(this.playerNum);
		this.removeVisible();
		dungeon.updateGameProgression();
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

	/**
	 * @return the playerNum
	 */
	public int getPlayerNum() {
		return playerNum;
	}
	@Override
	public boolean isBlocking(Entity subject) {
		if (subject instanceof Player) {
			return true;
		}
		else {
			return false;
		}
	}
}

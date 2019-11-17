package unsw.dungeon.combat;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;

/**
 * Hound Enemy that is faster than normal enemy and always runs towards player
 *
 */
public class Hound extends Enemy {

	public Hound(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.moveStrategy = new MoveTowardsPlayer();
		enemyType = EnemyType.HOUND;
	}

	@Override
	public void updateMovement(Player player) {}

	
}

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.obstacles.Boulder;

class BoulderTest {

	@Test
	void testAction() {
		Dungeon dungeon = new Dungeon(5, 5);
		Player player = new Player(dungeon, 1, 0);
		dungeon.setPlayer(player);
		Boulder boulder = new Boulder(dungeon, 1, 1);
		dungeon.addEntity(boulder);
		player.moveDown();
		System.out.println(boulder);
		System.out.println(player);	
		//fail("Not yet implemented");
	}
	

}

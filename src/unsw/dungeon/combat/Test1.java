package unsw.dungeon.combat;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;

public class Test1 {
	
	public static void main(String[] args) {
		/*Sword s = new Sword(1, 2);
		System.out.println(s.getX() + " + " + s.getY());*/
		Dungeon d = new Dungeon(10, 10);
		Entity e1 = new InvinsibilityPotion(d, 0, 0);
		d.addEntity(e1);
		
		Entity e2 = new InvinsibilityPotion(d, 1, 1);
		d.addEntity(e2);
		
		System.out.println(d.getEntities().size());
		
		d.removeEntity(e2);
		//System.out.println(d.getEntities().size());
		//d.printEntities();
		
		Enemy e3 = new Enemy(d, 5, 5);
		d.addEntity(e3);
		d.printEntities();
		e3.killEnemy();
		d.printEntities();
		Player p = new Player(d, 1, 2);
		
	}
	
}

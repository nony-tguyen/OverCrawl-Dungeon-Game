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
		d.removeEntity(e1);

		
		Enemy e3 = new Enemy(d, 1, 1);
		d.addEntity(e3);
		d.printEntities();

		Player p = new Player(d, 1, 3);
		d.setPlayer(p);
		System.out.println(p.getX() + " " + p.getY() + " " + p.getDirection());
		/*e3.moveEnemy(1, 3);
		d.printEntities();
		e3.moveEnemy(1, 3);
		d.printEntities();
		//p.moveUp();
		System.out.println(p.getX() + " " + p.getY() + " " + p.getDirection());
		if (d.gameOver()) System.out.println("game over");*/
		
		Sword s = new Sword(d, 1, 2);
		d.addEntity(s);
		d.printEntities();
		p.moveUp();
		System.out.println(p.getX() + " " + p.getY() + " " + p.getDirection());
		d.printEntities();
		System.out.println(p.getInventory());
		
		
	}
	
}

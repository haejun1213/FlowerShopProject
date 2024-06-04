package flowershop;

import flowershop.controller.FlowerShopController;
import flowershop.model.Cart;
import flowershop.model.FlowerStorage;
import flowershop.view.ConsoleView;

public class FlowerShop {
	
	public static void main(String[] args) {

		FlowerStorage flowerStorage = new FlowerStorage();
		
		Cart cart = new Cart();

		ConsoleView view = new ConsoleView();

		FlowerShopController controller = new FlowerShopController(flowerStorage, cart, view);

		controller.start();	
	}
	
}

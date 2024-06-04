package flowershop.controller;

import java.util.List;

import flowershop.model.Cart;
import flowershop.model.Flower;
import flowershop.model.FlowerStorage;
import flowershop.view.ConsoleView;

public class FlowerShopController {

	FlowerStorage flowerStorage;
	ConsoleView view;
	Cart cart;
	
	String[] colorMenuList = {"0. 뒤로가기", "1. 레드", "2. 핑크", "3. 노랑", "4. 화이트", "5. 보라", "6. 믹스"};

	String[] typeMenuList = { "0. 뒤로가기", "1. 꽃다발", "2. 꽃바구니", "3. 꽃화병", "4. 플라워박스" };

	String[] customerMenuList = { "0. 종료", "1. 꽃 목록 보기", "2. 꽃 검색", "3. 장바구니 보기", "4. 장바구니에 꽃 담기", "5. 장바구니 꽃 삭제",
			"6. 장바구니 꽃 수량 변경", "7. 장바구니 비우기", "8. 주문", "9. 관리자 모드" };

	public FlowerShopController(FlowerStorage flowerStorage, Cart cart, ConsoleView view) {

		this.flowerStorage = flowerStorage;
		this.cart = cart;
		this.view = view;

	}

	public void start() {

		view.displayWelcome();

		int menu;

		do {

			menu = view.selectMenuNum(customerMenuList);

			switch (menu) {

			case 1 -> viewFlowerType();

			case 2 -> sreachFlowerInfo();

			case 3 -> viewCart();

			case 4 -> addFlower2Cart();

			case 5 -> deleteFlowerInCart();

			case 6 -> updateFlowerInCart();

			case 7 -> resetCart();

			case 8 -> order();

			case 9 -> adminMode();

			case 0 -> end();

			}

		} while (menu != 0);

	}

	private void viewFlowerType() {

		int menu;

		do {

			menu = view.selectMenuNum(typeMenuList);

			switch (menu) {

			case 1 -> viewFlowerInfoType(typeMenuList[1].substring(3, typeMenuList[1].length()));

			case 2 -> viewFlowerInfoType(typeMenuList[2].substring(3, typeMenuList[2].length()));

			case 3 -> viewFlowerInfoType(typeMenuList[3].substring(3, typeMenuList[3].length()));

			case 4 -> viewFlowerInfoType(typeMenuList[4].substring(3, typeMenuList[4].length()));

			case 0 -> end();

			}
			return;

		} while (menu != 0);
	}
	
	
	
	private void viewFlowerInfoType(String type) {
		List<Flower> flowers = flowerStorage.getFlowersByType(type);
		view.displayFlowerList(flowers);
	}
	
	private void viewFlowerInfoColor(String color) {
		List<Flower> flowers = flowerStorage.getFlowersByColor(color);
		view.displayFlowerList(flowers);
	}
	
	
	private void sreachFlowerInfo() {

		int menu;

		do {

			menu = view.selectMenuNum(colorMenuList);

			switch (menu) {

			case 1 -> viewFlowerInfoColor(colorMenuList[1].substring(3, typeMenuList[1].length()));

			case 2 -> viewFlowerInfoColor

			case 3 -> viewFlowerInfoColor

			case 4 -> viewFlowerInfoColor

			case 5 -> viewFlowerInfoColor

			case 6 -> viewFlowerInfoColor

			case 0 -> end();

			}

		} while (menu != 0);

	}
		
		
	
	}

	private Object viewCart() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object addFlower2Cart() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object deleteFlowerInCart() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object updateFlowerInCart() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object resetCart() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object order() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object adminMode() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object end() {
		// TODO Auto-generated method stub
		return null;
	}
}

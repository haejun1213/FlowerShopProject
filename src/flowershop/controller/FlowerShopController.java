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

	String[] searchMenu = { "0. 돌아가기", "1. 색상 검색", "2. 가격 검색" };

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

			menu = view.selectMenuNum(flowerStorage.getTypeMenuList());

			switch (menu) {

			case 1 -> viewFlowerInfoType(1);

			case 2 -> viewFlowerInfoType(2);

			case 3 -> viewFlowerInfoType(3);

			case 4 -> viewFlowerInfoType(4);

			case 0 -> quit();

			}
			return;

		} while (menu != 0);
	}

	private void viewFlowerInfoType(int i) {
		List<Flower> flowers = flowerStorage.getFlowersByType(i);
		view.displayFlowerList(flowers);
	}

	private void sreachFlowerInfo() {

		int menu;

		menu = view.selectMenuNum(searchMenu);

		do {

			switch (menu) {

			case 1 -> searchFlowerColor();

			case 2 -> System.out.println("돈보단 마음이 중요합니다.");// searchFlowerPrice();

			case 0 -> quit();
			}
			return;

		} while (menu != 0);

	}

	private void searchFlowerColor() {

		int menu;

		do {

			menu = view.selectMenuNum(flowerStorage.getColorMenuList());

			switch (menu) {

			case 1 -> viewFlowerInfoColor(1);

			case 2 -> viewFlowerInfoColor(2);

			case 3 -> viewFlowerInfoColor(3);

			case 4 -> viewFlowerInfoColor(4);

			case 5 -> viewFlowerInfoColor(5);

			case 6 -> viewFlowerInfoColor(6);

			case 0 -> quit();

			}
			return;

		} while (menu != 0);
	}

	private void viewFlowerInfoColor(int i) {
		List<Flower> flowers = flowerStorage.getFlowersByColor(i);
		view.displayFlowerList(flowers);
	}

	private void viewCart() {
		view.displayCart(cart);
	}

	private void addFlower2Cart() {
//		view.displayFlowerInfo(flowerStorage);
		Flower flower = flowerStorage.getFlowerID(view.selectFLowerID(flowerStorage));
		cart.addItem(flower);
		view.displayFlowerInfo(flower);
		view.showMessage(">> 장바구니에 꽃을 추가하였습니다.");
	}

	private void deleteFlowerInCart() {
		view.displayCart(cart);
		if (!cart.isEmpty()) {
			// 도서 ID 입력받기
			int flowerID = view.selectFlowerID(cart);
			if (view.askConfirm(">> 해당 상품을 삭제하려면 \"yes\"를 입력하세요 : ", "yes")) {
				// 해당 도서 ID의 cartItem삭제
				cart.deleteItem(flowerID);
				view.showMessage(">> 해당 상품을 삭제했습니다.");
			} else {
				view.showMessage(">> 해당 상품을 삭제하지 않았습니다.");
			}
		}
	}

	private void updateFlowerInCart() {
		view.displayCart(cart);

		if (!cart.isEmpty()) {
			// 도서 ID 입력받기
			int flowerID = view.selectFlowerID(cart);
			// 수량 입력 받기
			int quantity = view.inputNumber(0, flowerStorage.getMaxQuantitiy());
			// 도서 ID에 해당하는 cartItem 가져옴
			cart.updateQuantity(flowerID, quantity);
			view.showMessage(">> 해당 상품의 수량을 변경하였습니다.");
		}
	}

	private void resetCart() {
		view.displayCart(cart);
		if (!cart.isEmpty()) {
			if (view.askConfirm(">> 장바구니를 비우시려면 \"yes\"를 입력하세요 : ", "yes")) {
				cart.resetCart();
				view.showMessage(">> 장바구니를 비웠습니다.");
			} else {
				view.showMessage(">> 장바구니를 비우지 않았습니다.");
			}
		}
	}

	private Object order() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object adminMode() {
		// TODO Auto-generated method stub
		return null;
	}

	private void quit() {

	}

	private void end() {
		view.showMessage("🌼 𝑪𝒍𝒐𝒔𝒆 𝒕𝒉𝒆 𝑯𝒂𝒆𝒋𝒖𝒏'𝒔 𝑭𝒍𝒐𝒘𝒆𝒓 𝑺𝒉𝒐𝒑 🌼");
	}
}

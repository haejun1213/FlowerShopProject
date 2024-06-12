package flowershop.controller;

import java.util.List;

import flowershop.model.Admin;
import flowershop.model.Cart;
import flowershop.model.Customer;
import flowershop.model.Flower;
import flowershop.model.FlowerStorage;
import flowershop.view.ConsoleView;

public class FlowerShopController {

	FlowerStorage flowerStorage;
	ConsoleView view;
	Cart cart;
	Customer customer;
	Admin admin;

	String[] adminMenuList = { "0. 종료", "1. 꽃 정보 추가", "2. 꽃 정보 삭제", "3. 꽃 정보 파일 저장" };

	String[] orderMenuList = { "0. 돌아가기", "1. 주문 정보 추가 및 수정", "2. 주문하기" };

	String[] searchMenu = { "0. 돌아가기", "1. 색상 검색", "2. 가격 검색" };

	String[] customerMenuList = { "0. 종료", "1. 꽃 목록 보기", "2. 꽃 검색", "3. 장바구니 보기", "4. 장바구니에 꽃 담기", "5. 장바구니 꽃 삭제",
			"6. 장바구니 꽃 수량 변경", "7. 장바구니 비우기", "8. 주문", "9. 관리자 모드" };

	public FlowerShopController(FlowerStorage flowerStorage, Cart cart, ConsoleView view) {

		this.flowerStorage = flowerStorage;
		this.cart = cart;
		this.view = view;
		customer = new Customer();
		admin = new Admin();

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

			case 8 -> orderMenu();

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

			case 2 -> searchFlowerPrice();

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

	private void searchFlowerPrice() {
		view.showMessage("상품들 중 최저 가격 : " + flowerStorage.getMinPrice() + ", 최대 가격 : " + flowerStorage.getMaxPrice());
		int minPrice = view.inputPrice(">> 최저 가격을 입력하세요: ");
		int maxPrice = view.inputPrice(">> 최대 가격을 입력하세요: ");
		List<Flower> flowers = flowerStorage.getFlowersByPriceRange(minPrice, maxPrice);
		view.displayFlowerList(flowers);
		if (flowers.isEmpty())
			view.showMessage(">> 조건에 맞는 상품이 없습니다.");
	}

	private void viewFlowerInfoColor(int i) {
		List<Flower> flowers = flowerStorage.getFlowersByColor(i);
		view.displayFlowerList(flowers);
		if (flowers.isEmpty())
			view.showMessage(">> 조건에 맞는 상품이 없습니다.");
	}

	private void viewCart() {
		view.displayCart(cart);
	}

	private void addFlower2Cart() {
		Flower flower = flowerStorage.getFlowerID(view.selectFlowerID(flowerStorage));
		cart.addItem(flower);
		view.displayFlowerInfo(flower);
		view.showMessage(">> 장바구니에 꽃을 추가하였습니다.");
	}

	private void deleteFlowerInCart() {
		view.displayCart(cart);
		if (!cart.isEmpty()) {
			int flowerID = view.selectFlowerID(cart);
			if (view.askConfirm(">> 해당 상품을 삭제하려면 \"yes\"를 입력하세요 : ", "yes")) {
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
			int flowerID = view.selectFlowerID(cart);
			int quantity = view.inputNumberMinMax(0, flowerStorage.getMaxQuantitiy());
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

	private void orderMenu() {

		int menu;

		do {

			menu = view.selectMenuNum(orderMenuList);

			switch (menu) {

			case 1 -> orderInfoUpadate();

			case 2 -> order();

			case 0 -> quit();

			}
			return;

		} while (menu != 0);
	}

	private void order() {
		if (!cart.isEmpty()) {
			if (customer.getName() == null) {
				view.showMessage(">> 주문 정보가 없습니다.");
				view.inputCustomerInfo(customer);
			}

			view.displayDeliveryInfo(cart, customer);

			if (view.askConfirm(">> 주문하려면 \"yes\"를 입력하세요 : ", "yes")) {
				cart.resetCart();
				view.showMessage(">> 주문을 완료했습니다.");
			} else {
				view.showMessage(">> 주문을 취소했습니다.");
			}
		} else {
			view.displayCart(cart);
		}

	}

	private void orderInfoUpadate() {
		view.inputCustomerInfo(customer);
	}

	private void adminMode() {

		if (!authenticateAdmin()) {
			view.showMessage("인증 실패");
			return;
		} else {
			int menu;
			do {
				menu = view.selectMenuNum(adminMenuList);

				switch (menu) {

				case 1 -> addFlower2Storage();

				case 2 -> deleteFlowerInStorage();

				case 3 -> saveFlowerList2File();

				case 0 -> adminEnd();

				default -> view.showMessage(">> 잘못된 메뉴 번호입니다.");

				}
			} while (menu != 0);

		}

	}

	private void addFlower2Storage() {
		view.showMessage("새로운 꽃을 추가합니다");

		flowerStorage.addFlower(view.inputString("종류 : "), view.inputString("상품명 : "), view.inputStringArr("구성 : "),
				view.inputString("색상 : "), view.inputPrice("가격 : "));

	}

	private void deleteFlowerInStorage() {

		if (flowerStorage.isEmpty()) {
			view.showMessage("꽃 창고에 꽃이 없습니다.");
			return;
		}
		viewFlowerInfo();
		int flowerID = view.selectFlowerID(flowerStorage);
		if (view.askConfirm(">> 해당 상품을 삭제하려면 \"yes\"를 입력하세요 : ", "yes")) {
			flowerStorage.deleteItem(flowerID);
			view.showMessage(">> 해당 상품를 삭제했습니다.");
		} else {
			view.showMessage(">> 해당 상품를 삭제하지 않았습니다.");
		}

	}

	private void saveFlowerList2File() {

		if (flowerStorage.isSaved()) {
			view.showMessage("저장할 변경사항이 없습니다.");
		} else {
			flowerStorage.saveFlowerList2File();
			view.showMessage("꽃 상품을 저장했습니다.");
		}
	}

	private void adminEnd() {
		view.showMessage("관리자 모드가 종료되었습니다.");
	}

	private boolean authenticateAdmin() {
		// 관리자 인증 (id, password)
		view.showMessage("관리자 모드 진입을 위한 인증");

		String id = view.inputString("관리자 ID : ");
		String password = view.inputString("관리자 PW : ");

		return admin.login(id, password);
	}

	private void quit() {

	}

	private void end() {
		view.showMessage("🌼 𝑪𝒍𝒐𝒔𝒆 𝒕𝒉𝒆 𝑯𝒂𝒆𝒋𝒖𝒏'𝒔 𝑭𝒍𝒐𝒘𝒆𝒓 𝑺𝒉𝒐𝒑 🌼");
	}

	private void viewFlowerInfo() {

		view.displayFlowerInfo(flowerStorage);

	}
}

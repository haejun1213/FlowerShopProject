package flowershop.view;

import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import flowershop.model.Cart;
import flowershop.model.Customer;
import flowershop.model.Flower;
import flowershop.model.FlowerStorage;

public class ConsoleView {

	public void displayWelcome() {

		String welcome = "*****************************************\n"
				+ "*    🌼 𝑾𝒆𝒍𝒄𝒐𝒎𝒆 𝒕𝒐 𝑯𝒂𝒆𝒋𝒖𝒏'𝒔 𝑭𝒍𝒐𝒘𝒆𝒓 𝑺𝒉𝒐𝒑 🌼    *\n"
				+ "*****************************************";

		System.out.println(welcome);
// 🌹 🥀 🌷 🌺 🏵️ 💐 🌸 🌼
	}

	public int selectMenuNum(String[] menuList) {

		displayMenu(menuList);

		int menu;

		do {
			System.out.print(">> 메뉴 선택 : ");

			menu = inputNumber(">> 숫자를 입력하세요 : ");

			if (menu < 0 || menu >= menuList.length)
				System.out.println(">> 0부터 " + (menuList.length - 1) + "번 사이의 숫자를 입력해 주세요.");

		} while (menu < 0 || menu >= menuList.length);
		return menu;
	}

	private void displayMenu(String[] menuList) {

		System.out.println("=========================================");
		for (int i = 1; i < menuList.length; i++) {
			System.out.println(menuList[i]);
		}
		System.out.println(menuList[0]);
		System.out.println("=========================================");

	}

	private int inputNumber(String message) {
		Scanner sc = new Scanner(System.in);
		try {
			int number = sc.nextInt();
			return number;
		} catch (Exception e) {
			System.out.print(message);
			return inputNumber(message);
		}
	}

	public void displayFlowerList(List<Flower> flowers) {
		for (Flower flower : flowers) {
			System.out.println(flower.toString());
		}
	}

	public void displayCart(Cart cart) {
		if (cart.isEmpty()) {
			System.out.println(">> 장바구니가 비어 있습니다.");
			return;
		}
		System.out.println("------------------------------------------------------------------------");
		for (int i = 0; i < cart.getNumItem(); i++) {
			System.out.println(cart.getItemInfo(i));
		}
		System.out.println(">> 총 상품금액 : " + cart.getTotPrice() + "원");
		System.out.println("------------------------------------------------------------------------");
	}

	public int selectFlowerID(FlowerStorage flowerStorage) {
		int flowerID;
		boolean result;
		do {
			System.out.print(">> 추가하실 꽃의 ID를 입력하세요 : ");
			flowerID = inputNumber(">> 숫자를 입력하세요: ");
			result = flowerStorage.isValidFlower(flowerID);
			if (!result)
				System.out.println(">> 잘못된 상품의 ID입니다.");
		} while (!result);
		return flowerID;
	}

	public int selectFlowerID(Cart cart) {
		int flowerID;
		boolean result;
		do {
			System.out.print(">> 추가하실 꽃의 ID를 입력하세요 : ");
			flowerID = inputNumber(">> 숫자를 입력하세요: ");
			result = cart.isValidItem(flowerID);
			if (!result)
				System.out.println(">> 잘못된 상품의 ID입니다.");
		} while (!result);
		return flowerID;
	}

	public void showMessage(String message) {
		System.out.println(message);
	}

	public boolean askConfirm(String message, String string) {
		System.out.print(message);

		Scanner sc = new Scanner(System.in);

		if (sc.nextLine().equals(string))
			return true;

		return false;
	}

	public void displayFlowerInfo(Flower flower) {
		System.out.println(flower.toString());
	}
	
	public void displayFlowerInfo(FlowerStorage flowerStorage) {
		System.out.println("------------------------------------------------------------------------");
		for (int i = 0; i < flowerStorage.getNumFlowers(); i++) {
			String bookInfo = flowerStorage.getFlowerInfo(i);
			System.out.println(bookInfo);
		}
		System.out.println("------------------------------------------------------------------------");
	}
	

	public int inputNumberMinMax(int min, int max) {
		int number;
		do {
			System.out.print(">> 수량 입력 (" + min + " ~ " + max + "): ");
			number = inputNumber(">> 숫자를 입력하세요: ");
			if (number < min || number > max)
				System.out.println(">> 잘못된 수량입니다.");
		} while (number < min || number > max);
		return number;
	}

	public void inputCustomerInfo(Customer customer) {
		Scanner sc = new Scanner(System.in);
		System.out.println("이름과 전화번호를 입력하세요.");
		System.out.print(">> 이름 : ");
		customer.setName(sc.nextLine());
		System.out.print(">> 전화번호 : ");
		customer.setPhone(sc.nextLine());
		System.out.println("주소와 메일을 입력하세요.");
		System.out.print(">> 주소 : ");
		customer.setAddress(sc.nextLine());
		System.out.print(">> 메일 : ");
		customer.setEmail(sc.nextLine());

	}
	
	public void displayDeliveryInfo(Cart cart, Customer customer) {

		System.out.println();
		System.out.println("***** 주문할 도서 ******");
		displayCart(cart);

		System.out.println("***** 배송 정보 ******");
		System.out.println(">> 이름 : " + customer.getName());
		System.out.println(">> 전화번호 : " + customer.getPhone());
		System.out.println(">> 이메일 : " + customer.getEmail());
		System.out.println(">> 주소 : " + customer.getAddress());

	}
	
	public String inputString(String message) {
		Scanner sc = new Scanner(System.in);
		System.out.print(message);
		return sc.nextLine();
	}

	public int inputPrice(String message) {
		Scanner sc = new Scanner(System.in);
		System.out.print(message);
		try {
			int number = sc.nextInt();
			return number;
		} catch (Exception e) {
			return inputPrice(message);
		}
	}

	public String[] inputStringArr(String message) {
		System.out.print(message);
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine(), ", ");
		int token = st.countTokens();
		
		String[] compositions = new String[token];
		
		for(int i = 0; i < token; i++) {
			compositions[i] = st.nextToken(); 
		}
		
		return compositions;
		
		
	}

}



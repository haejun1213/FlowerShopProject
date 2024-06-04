package flowershop.view;

import java.util.List;
import java.util.Scanner;

import flowershop.model.Flower;

public class ConsoleView {

	public void displayWelcome() {
		
		String welcome = "*****************************************\n" + "*    🌼 𝑾𝒆𝒍𝒄𝒐𝒎𝒆 𝒕𝒐 𝑯𝒂𝒆𝒋𝒖𝒏'𝒔 𝑭𝒍𝒐𝒘𝒆𝒓 𝑺𝒉𝒐𝒑 🌼    *\n" + "*****************************************";

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
				System.out.println(">> 0부터 " + (menuList.length - 1) + "번 사이의 숫자를 입력하세요.");

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
			System.out.println(flower.getFlowerID() + ", " + flower.getType() + ", " + flower.getName() + ", " + flower.getColor() + ", " + flower.getPrice() + "원");
		}
	}

}

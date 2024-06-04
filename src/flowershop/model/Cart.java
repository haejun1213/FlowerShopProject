package flowershop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private ArrayList<CartItem> itemList = new ArrayList<>();

	public boolean isEmpty() {
		return itemList.isEmpty();
	}

	public int getNumItem() {
		return itemList.size();
	}

	public List<CartItem> getItemList() {
		return itemList;
	}

	public String getItemInfo(int index) {
		return itemList.get(index).toString();
	}

	public void resetCart() {
		itemList.clear();
	}

}
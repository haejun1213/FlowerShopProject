package flowershop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	
	private ArrayList<CartItem> itemList = new ArrayList<>();
	int totPrice;

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
	//

	public void addItem(Flower flower) {
		CartItem item = getCartItem(flower);
		if (item == null) {
			itemList.add(new CartItem(flower));
		} else {
			item.addQuantity(1);
		}
		totPrice += flower.getPrice();
	}
	
	private CartItem getCartItem(Flower flower) {
		for (CartItem item : itemList) {
			if (item.getFlower() == flower) {
				return item;
			}
		}

		return null;
	}

	private CartItem getCartItem(int flowerID) {
		for (CartItem item : itemList) {
			if (item.getFlowerID() == flowerID) {
				return item;
			}
		}

		return null;
	}

	public boolean isValidItem(int flowerID) {

		for (CartItem item : itemList) {
			if (item.getFlowerID() == flowerID)
				return true;
		}
		return false;

	}

	public void deleteItem(int bookId) {
		CartItem item = getCartItem(bookId);
		itemList.remove(item);
		totPrice -= item.getPrice();

	}

	public void updateQuantity(int bookId, int quantity) {

		if (quantity == 0) {
			deleteItem(bookId);
		} else {
			CartItem item = getCartItem(bookId);
			totPrice -= item.getPrice();
			item.setQuantity(quantity);
			totPrice += item.getPrice();
		}

	}

	public int getTotPrice() {
		return totPrice;
	}

	public void setTotPrice(int totPrice) {
		this.totPrice = totPrice;
	}
}
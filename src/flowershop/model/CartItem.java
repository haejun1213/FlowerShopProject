package flowershop.model;

public class CartItem {

	Flower flower;
	int flowerID;
	int quantity;

	
	public int getFlowerID() {
		return flowerID;
	}

	public void setFlowerID(int flowerID) {
		this.flowerID = flowerID;
	}

	public CartItem(Flower flower) {
		this.flower = flower;
		this.flowerID = flower.getFlowerID();
		this.quantity = 1;
	}
	
	public Flower getFlower() {
		return flower;
	}
	public void setflower(Flower flower) {
		this.flower = flower;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}
	
	public int getPrice() {
		return flower.getPrice() * quantity;
	}
	
	public String toString() {
		return flower.getFlowerID() + ", " + flower.getType() + ", " + flower.getName() + ", " + flower.getColor() + ", " + quantity + "개, "+ getPrice() + "원";
}
}


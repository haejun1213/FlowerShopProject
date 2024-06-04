package flowershop.model;

public class Flower {

	private int flowerID;
	private String name;
	private String type;
	private int price;
	private String color;
	private String[] composition;

	public Flower(int flowerID, String type, String name, String[] composition, String color, int price) {
		this.flowerID = flowerID;
		this.type = type; // 꽃다발, 꽃바구니, 꽃화병, 플라워박스
		this.name = name;
		this.composition = composition;
		this.color = color; // 레드, 핑크, 노랑, 화이트, 보라, 믹스
		this.price = price;
	}

	public int getFlowerID() {
		return flowerID;
	}

	public void setFlowerID(int flowerID) {
		this.flowerID = flowerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String[] getComposition() {
		return composition;
	}

	public void setComposition(String[] composition) {
		this.composition = composition;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	public String toString() {
		return getFlowerID() + ", " + getType() + ", " + getName() + ", " + getColor() + ", " + getPrice() + "원";
}
}

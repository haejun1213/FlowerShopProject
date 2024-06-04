package flowershop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlowerStorage {

	ArrayList<Flower> flowerList = new ArrayList<>();
	
	private int flowerLastID = 1000;
	
	public FlowerStorage() {
		flowerList.add(new Flower(flowerLastID++, "꽃다발", "flowery fiction - 메리제인", new String[] {"장미"}, "핑크", 64000));
		flowerList.add(new Flower(flowerLastID++, "꽃다발", "The Roses Bloom - 핑크 하트", new String[] {"장미"}, "핑크", 225000));
		flowerList.add(new Flower(flowerLastID++, "꽃다발", "Rose & Cozy yellow", new String[] {"장미"}, "노랑", 68000));
		flowerList.add(new Flower(flowerLastID++, "꽃다발", "Spring violet - 보라빛 봄빛 햇살", new String[] {"장미, 거베라, 스토크"}, "보라", 49000));
		flowerList.add(new Flower(flowerLastID++, "꽃바구니", "Be my love - Glory", new String[] {"장미"}, "핑크", 63000));
		flowerList.add(new Flower(flowerLastID++, "꽃바구니", "Lovely sweet II - White blossom", new String[] {"장미"}, "핑크", 240000));
		flowerList.add(new Flower(flowerLastID++, "꽃화병", "Beautiful colors - innocence", new String[] {"백합"}, "핑크", 135000));
		flowerList.add(new Flower(flowerLastID++, "꽃화병", "Great Season", new String[] {"아네모네, 씨드유카리"}, "보라", 135000));
		
	}
	
	//종류
	public List<Flower> getFlowersByType(String type) {
		return flowerList.stream()
			.filter(flower -> flower.getType().equals(type))
			.collect(Collectors.toList());
	}
	
	public List<Flower> getFlowersByColor(String color) {
		return flowerList.stream()
			.filter(flower -> flower.getColor().equals(color))
			.collect(Collectors.toList());
	}
}

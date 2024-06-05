package flowershop.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class FlowerStorage {

	private String[] typeMenuList = { "0. 뒤로가기", "1. 꽃다발", "2. 꽃바구니", "3. 꽃화병", "4. 플라워박스" };
	
	private String[] colorMenuList = {"0. 뒤로가기", "1. 레드", "2. 핑크", "3. 노랑", "4. 화이트", "5. 보라", "6. 믹스"};

	final int MAX_QUANTITIY = 10;
	
	private String flowerFilename = "flowerlist.txt";
	
	private boolean isSaved;
	
	ArrayList<Flower> flowerList = new ArrayList<>();
	
	private int flowerLastID;
	
	public FlowerStorage() throws IOException {
		loadFlowerListFromFile();
		generateLastId();
	}
	

	private void generateLastId() {
		flowerLastID = 0;
		for (Flower flower : flowerList) {
			int id = flower.getFlowerID();
			if (id > flowerLastID)
				flowerLastID = id;
		}
	}

	private void loadFlowerListFromFile() throws IOException {
		FileReader fr;
		try {
			fr = new FileReader(flowerFilename);
			BufferedReader br = new BufferedReader(fr);
			String idStr;
			while ((idStr = br.readLine()) != null) {
				int id = Integer.parseInt(idStr);
				String type = br.readLine();
				String name = br.readLine();
				String[] composition = inputStringArr(br.readLine());
				String color = br.readLine();
				int price = Integer.parseInt(br.readLine());
				flowerList.add(new Flower(id, type, name, composition, color, price));
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException | NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String[] inputStringArr(String string) {
		
		StringTokenizer st = new StringTokenizer(string, ", ");
		int token = st.countTokens();
		
		String[] compositions = new String[token];
		
		for(int i = 0; i < token; i++) {
			compositions[i] = st.nextToken(); 
		}
		
		return compositions;
	}


	//종류
	public List<Flower> getFlowersByType(int i) {
		return flowerList.stream()
			.filter(flower -> flower.getType().equals(getTypeMenuList()[i].substring(3, getTypeMenuList()[i].length())))
			.collect(Collectors.toList());
	}
	
	public List<Flower> getFlowersByColor(int i) {
		return flowerList.stream()
			.filter(flower -> flower.getColor().equals(getColorMenuList()[i].substring(3, getColorMenuList()[i].length())))
			.collect(Collectors.toList());
	}

	public String[] getTypeMenuList() {
		return typeMenuList;
	}

	public void setTypeMenuList(String[] typeMenuList) {
		this.typeMenuList = typeMenuList;
	}

	public String[] getColorMenuList() {
		return colorMenuList;
	}

	public void setColorMenuList(String[] colorMenuList) {
		this.colorMenuList = colorMenuList;
	}

	public boolean isValidFlower(int flowerID) {
		for (Flower flower : flowerList) {
			if (flower.getFlowerID() == flowerID)
				return true;
		}

		return false;
	}

	public Flower getFlowerID(int flowerID) {
		for (Flower flower : flowerList) {
			if (flower.getFlowerID() == flowerID)
				return flower;
		}

		return null;
	}
	
	public int getMaxQuantitiy() {
		return MAX_QUANTITIY;
	}
	
	public boolean isEmpty() {
		return flowerList.size() == 0;
	}
	
	public boolean isSaved() {
		return isSaved;
	}
	
	public void deleteItem(int bookId) {
		flowerList.remove(getFlowerID(bookId));
	}
	
	public void addBook(String type, String name, String[] composition, String color, int price) {

		Flower book = new Flower(++flowerLastID, type, name, composition, color, price);
		flowerList.add(book);
	}
	
	public int getNumFlowers() {
		return flowerList.size();
	}

	public String getFlowerInfo(int i) {
		return flowerList.get(i).toString();
	}
	
	public void saveFlowerList2File() {

		try {
			FileWriter fw = new FileWriter(flowerFilename);
			for (Flower flower : flowerList) {
				fw.write(flower.getFlowerID() + "\n");
				fw.write(flower.getType() + "\n");
				fw.write(flower.getName() + "\n");
				fw.write(flower.getComposition() + "\n");
				fw.write(flower.getColor() + "\n");
				fw.write(flower.getPrice() + "\n");
			}
			fw.close();
			isSaved = true;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

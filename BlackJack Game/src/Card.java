import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Card {
	
	//private  variables
	private Integer value;
	private String suit;
	private BufferedImage image = null;
	
	//Constructor
	public Card (Integer val, String s, String f) {
		//set value and suit
		this.value = val;
		this.suit = s;
		
		//read image from the string passed and set the image
		try {
			this.image = ImageIO.read(new File(f));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//Getters
	public Integer getValue() {
		return value;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public BufferedImage getImage() {
		return image;
	}
}

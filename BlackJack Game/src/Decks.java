import java.util.Vector;
import java.util.Collections;

public class Decks {
	//Private variables

	private Vector <Card> gameDeck;
	private Vector <Card> playerDeck = new Vector<Card>();
	private Vector <Card> dealerDeck = new Vector<Card>();
	int playerDeckValue = 0;
	int dealerDeckValue = 0;
	//Constructor
	public Decks() {
		gameDeck = new Vector<Card>();
		
		//create a holder so that face cards carry a value of 10
		int holder = 0;
		
		//create cards and push them to vector
		for (int i = 1; i <= 13; i++) {
			Card card;
			holder = i;
			if (i > 10) {
				i = 10;
			}
			card = new Card (i, "Heart", "Heart" + i + ".png");
			gameDeck.add(card);
			
			card = new Card(i, "Diamond", "Diamond" + i + ".png");
			gameDeck.add(card);
			
			card = new Card(i, "Spade", "Spade" + i + ".png");
			gameDeck.add(card);
			
			card = new Card(i, "Club", "Club" + i + ".png");
			gameDeck.add(card);
			i = holder;
		}
		
		//shuffle the deck
		Collections.shuffle(gameDeck);
	}
	
	//Add to certain decks
	public void addToPlayerDeck(Card card) {
		playerDeck.add(card);
		this.playerDeckValue += card.getValue();
	}
	
	public void addToDealerDeck(Card card) {
		dealerDeck.add(card);
		this.dealerDeckValue += card.getValue();

	}
	
	//Getters
	public Vector<Card> getPlayerDeck () {
		return playerDeck;
	}
	
	public Vector<Card> getDealerDeck () {
		return dealerDeck;
	}
	public int getPlayerScore() {
		return playerDeckValue;
	}
	public int getDealerScore() {
		return dealerDeckValue;
	}
	public Card getCurrentCardFromGameDeck(int count) {
		return gameDeck.get(count);
	}
}

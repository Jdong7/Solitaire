package edu.buffalo.cse116;

public class Card {

	/**
	 * Array of Strings containing the ranking of the cards
	 */
	private static String Ranking[] = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
	
	/**
	 * Array of Strings containing the suit of the cards
	 */
	private static String Suit[] = { "Diamond", "Clover", "Heart", "Spade" };
	
	/**
	 * 1diam 2clover 3heart 4spade
	 */
	private int suit;
	
	/**
	 * 1-13 (A-K) 1 = A, 13 = K
	 */
	private int ranking;
	
	/**
	 * Card constructor
	 * 
	 * @param suit Takes an integer, representing the 4 suits
	 * @param ranking Takes an integer, representing the ranking of the 13 cards
	 * in each suit
	 */
	public Card(int suit, int ranking) {
		this.suit = suit;
		this.ranking = ranking;

	}

	/**
	 * Returns numerical equivalent of ranking (1-13)
	 * 
	 * @return ranking of the card
	 */
	public int getRank() {
		return ranking;
	}

	/**
	 * Returns numerical equivalent of suit(1-4)
	 * 
	 * @return suit of the card
	 */
	public int getSuit() {
		return suit;
	}

	/**
	 * If suit is Diamond or Heart return red
	 * If suit is Clover and Spade return black
	 * 
	 * @return 0 for red, 1 for black
	 */
	public int getColor() {

		if (suit == 1 || suit == 3) {
			return 0;// red
		} else
			return 1;// black

	}

	/**
	 * Returns string containing ranking of card
	 * 
	 * @return card rank but in a string
	 */
	public String CardRank() {
		return Ranking[this.ranking - 1];
	}

	/**
	 * Returns string containing suit of card
	 * 
	 * @return card suit but in a string
	 */
	public String CardSuit() {
		return Suit[this.suit - 1];
	}

	/**
	 * Finds returns color of card.
	 * 
	 * @return color of card but in a string
	 */
	public String CardColor() {
		if (this.getColor() == 0) {
			return "Red";
		} else {
			return "Black";
		}
	}

	
	/* 
	 * Returns format in Color Suit Rank
	 * Example: Red Clover 12
	 */
	public String toString() {
		return this.CardColor() + " " + this.CardSuit() + " " + this.CardRank();
	}


	/**
	 * @return true if the card is a King.
	 * 		   false if otherwise.
	 */
	public boolean isKing() {
		if (this.ranking == 13) {
			return true;
		}
		return false;
	}

	/**
	 * @return true if the card is an Ace.
	 * 		   false if otherwise.
	 */
	public boolean isAce() {
		if (this.ranking == 1) {
			return true;
		}
		return false;
	}


	/**
	 * @param input card that is compared against.
	 * @return true if the "this" card is one rank less than that of "input"
	 * 		   false if otherwise
	 */
	public boolean isConsecutive(Card input) {
		if (this.ranking == input.ranking - 1) {
			return true;
		}
		return false;
	}


	/**
	 *  Opposite of isConsecutive because when placing cards into home,
	 *  cards are ordered smallest to greatest as opposed to greatest to smallest
	 * 
	 * @param input card that is compared against
	 * @return true if the "this" card is one rank more than that of "input"
	 * 		   false if otherwise
	 */
	public boolean isHome(Card input) {
		if ((this.ranking == (input.ranking + 1)) && (this.getSuit() == input.getSuit())) {
			return true;
		}
		return false;
	}

	/**
	 * @param input card that is compared against
	 * @return true if the "this" card is a different color than that of "input"
	 * 		   false if otherwise.
	 */
	public boolean isColorAlt(Card input) {
		if (this.getColor() != input.getColor()) {
			return true;
		}
		return false;
	}

	/**
	 * @param input card that is compared against
	 * @return true if cards have same suit
	 * 		   false if otherwise
	 */
	public boolean isSameSuit(Card input) {
		if (this.getSuit() == input.getSuit()) {
			return true;
		}
		return false;
	}

	/**
	 * @param input card that is compared against
	 * @return true if the two cards are identical
	 * 		   false if otherwise
	 */
	public boolean compareTo(Card input) {
		if ((this.isSameSuit(input)) && (this.CardRank().equals(input.CardRank()))) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the file path of a card
	 */
	public String URL()
	{
		return "src/Cards/"+this.suit+""+this.ranking+".gif";
	}
	

}

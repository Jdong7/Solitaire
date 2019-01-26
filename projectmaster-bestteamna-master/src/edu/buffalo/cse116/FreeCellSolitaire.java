package edu.buffalo.cse116;

import java.util.ArrayList;

public class FreeCellSolitaire extends Game {

	/**
	 * Deck the game is played with
	 */
	private Deck deck = new Deck();
	/**
	 * ArrayList of type Cell containing Home Cells
	 */
	private ArrayList<Cell> HC = new ArrayList<Cell>();
	/**
	 * ArrayList of type Cell containing Free Cells
	 */
	private ArrayList<Cell> FC = new ArrayList<Cell>();
	/**
	 * ArrayList of type Cell containing Tableau Cells
	 */
	private ArrayList<Cell> TC = new ArrayList<Cell>();

	/**
	 * Free Cell constructor
	 */
	public FreeCellSolitaire() {
		super();
		initialize();
	}

	/**
	 * initializes game
	 * creates 8 Tableau Cells, four of which contain 6 cards, the other four containing 7 cards
	 * creates 4 Free Cells containing no cards
	 * creates 4 Home Cells containing no cards
	 * adds them all to their respective ArrayList<Cell>
	 */
	public void initialize() {
		deck.shuffle();
		
		for (int i = 0; i < 8; i++) {
			if (i < 4) {
				CardPile pile = deck.Draw(6);
				Cell c = new TableauCell(pile);
				TC.add(c);
			} else {
				CardPile pile = deck.Draw(7);
				Cell c = new TableauCell(pile);
				TC.add(c);
			}
		}
		for (int i = 0; i < 4; i++) {
			ArrayList<Card> Empty = new ArrayList<Card>();
			CardPile P = new CardPile(Empty);
			
			Cell H = new HomeCell(P);
			
			HC.add(H);
		}
		for (int i = 0; i < 4; i++) {
			ArrayList<Card> Empty = new ArrayList<Card>();
			CardPile P = new CardPile(Empty);
			Cell F = new FreeCell(P);
			FC.add(F);
		}
	}

	/**
	 * @param c Cell that is compared
	 * @param d Cell that is compared
	 * @return integer based on the type of Cells inputted 
	 */
	public int methodComparison(Cell c, Cell d) {
		if (c.isTC() && d.isTC()) {
			return 1;
		} else if (c.isTC() && d.isFC()) {
			return 2;
		} else if (c.isTC() && d.isHC()) {
			return 3;
		} else if (c.isFC() && d.isTC()) {
			return 4;
		} else if (c.isFC() && d.isHC()) {
			return 5;
		} else {
			return 0;
		}
	}

	/**
	 * Moves the last card from one cell to another while obeying the rules of the Baker's Dozen Game
	 * 
	 * @param A index of the cell extracting from
	 * @param B index of the cell adding to
	 * @param C integer value from methodComparison
	 */
	public void move(int A, int B, int C) {
		int index1 = A - 1;
		int index2 = B - 1;
		if (C == 1) {
			if (!(isEmpty(TC, index1))) {
				if (TC.get(index2).isEmpty()) {

					TC.get(index2).addLastCard(TC.get(index1));
					TC.get(index1).removeLastCard();

				} else {
					if ((lastCard(TC, index1).isConsecutive(lastCard(TC, index2))
							&& (lastCard(TC, index1).isColorAlt(lastCard(TC, index2))))) {

						TC.get(index2).addLastCard(TC.get(index1));
						TC.get(index1).removeLastCard();
					}
				}
			}

		}
		if (C == 2) {
			if (!(isEmpty(TC, index1))) {
				if (isEmpty(FC, index2)) {

					FC.get(index2).addLastCard(TC.get(index1));
					TC.get(index1).removeLastCard();

				}
			}
		}
		if (C == 3) {
			if (!(isEmpty(TC, index1))) {
				if ((isEmpty(HC, index2) && (isAce(TC, index1)))) {

					HC.get(index2).addLastCard(TC.get(index1));
					TC.get(index1).removeLastCard();

				}
				if (!isEmpty(HC, index2))
					if (lastCard(TC, index1).isHome(lastCard(HC, index2))) {

						HC.get(index2).addLastCard(TC.get(index1));
						TC.get(index1).removeLastCard();

					}
			}
		}
		if (C == 4) {
			if (!(isEmpty(FC, index1))) {
				if (isEmpty(TC, index2)) {

					TC.get(index2).addLastCard(FC.get(index1));
					FC.get(index1).removeLastCard();

				} else if ((lastCard(FC, index1).isConsecutive(lastCard(TC, index2))
						&& (lastCard(FC, index1).isColorAlt(lastCard(TC, index2))))) {
					TC.get(index2).addLastCard(FC.get(index1));
					FC.get(index1).removeLastCard();

				}

			}

		}
		if (C == 5) {
			if (!(isEmpty(FC, index1))) {
				if ((isEmpty(HC, index2) && (isAce(FC, index1)))) {
					HC.get(index2).addLastCard(FC.get(index1));
					FC.get(index1).removeLastCard();

				}
				if (!isEmpty(HC, index2))
					if (lastCard(FC, index1).isHome(lastCard(HC, index2))) {
						HC.get(index2).addLastCard(FC.get(index1));
						FC.get(index1).removeLastCard();

					}
			}
		}

	}

	/**
	 * Helps simplify the code, checks if the cell is empty
	 * 
	 * @param c ArrayList of Cells the Cell is located in
	 * @param a the integer of the index of the Cell
	 * @return True if the Cell is empty
	 * 		   False if otherwise
	 */
	public boolean isEmpty(ArrayList<Cell> c, int a) {
		return c.get(a).isEmpty();
	}

	/**
	 * Helps simplify the code, checks if the card is an Ace
	 * 
	 * @param c ArrayList of Cells the Cell is located in
	 * @param a the integer of the index of the Cell
	 * @return True if the card is an Ace
	 * 		   False if otherwise
	 */
	public boolean isAce(ArrayList<Cell> c, int a) {
		return c.get(a).getLastCard().isAce();
	}

	/**
	 * Helps simplify the code, returns the last card in the Cell
	 * 
	 * @param c ArrayList of Cells the Cell is located in
	 * @param a the integer of the index of the Cell
	 * @return the last card in the Cell
	 */
	public Card lastCard(ArrayList<Cell> c, int a) {
		return c.get(a).getLastCard();
	}
	
	/**
	 * Helps simplify the code, returns the Tableau Cell
	 * 
	 * @return Tableau Cell
	 */
	public ArrayList<Cell> getTC()
	{
		return TC;
	}
	
	/**
	 * Helps simplify the code, returns the Free Cell
	 * 
	 * @return Free Cell
	 */
	public ArrayList<Cell> getFC()
	{
		return FC;
	}
	
	/**
	 * Helps simplify the code, returns the Home Cell
	 * 
	 * @return Home Cell
	 */
	public ArrayList<Cell> getHC()
	{
		return HC;
	}
	
	
	/**
	 * for junit test
	 * 
	 * @return deck
	 */
	public Deck getDeck()
	{
		return this.deck;
	}
	
	
	/**
	 * for junit test
	 * 
	 * @return true if all the items in the ArrayList are Tableau Cells
	 * 		   false if otherwise
	 */
	public boolean allTC()
	{
		int count = 0;
		for(int i = 0;i<TC.size();i++)
		{
			if(TC.get(i).isTC())
					{
				count++;
					}
		}
		if(count==TC.size())
			return true;
		else
			return false;
	}
	
	/**
	 * for junit test
	 * 
	 * @return true if all the items in the ArrayList are Home Cells
	 * 		   false if otherwise
	 */
	public boolean allHC()
	{
		int count = 0;
		for(int i = 0;i<HC.size();i++)
		{
			if(HC.get(i).isHC())
					{
				count++;
					}
		}
		if(count==HC.size())
			return true;
		else
			return false;
	}
	
	
	/**
	 * for junit test
	 * 
	 * @return true if all the items in the ArrayList are Free Cells
	 * 		   false if otherwise
	 */
	public boolean allFC()
	{
		int count = 0;
		for(int i = 0;i<FC.size();i++)
		{
			if(FC.get(i).isFC())
					{
				count++;
					}
		}
		if(count==FC.size())
			return true;
		else
			return false;
	}
	
	/**
	 * helps junit test 
	 * 
	 * @param type Type of Cell
	 * @param a index of Cell in ArrayList of type Cells
	 * @return size of the CardPile in the Cell
	 */
	public int getSize(String type, int a) {
		if (type.equals("TC"))
			return TC.get(a - 1).getCardPile().getCount();
		else if (type.equals("FC"))
			return FC.get(a - 1).getCardPile().getCount();
		else
			return HC.get(a - 1).getCardPile().getCount();
	}
	
	/** 
	 * Tableau Cell Setter
	 * 
	 * @param c ArrayList of type Cell
	 */
	public void setTC(ArrayList<Cell> c) {
		this.TC = c;
	}

	/**
	 * Home Cell Setter
	 * 
	 * @param c ArrayList of type Cell
	 */
	public void setHC(ArrayList<Cell> c) {
		this.HC = c;
	}

	/**
	 * Free Cell Setter
	 * 
	 * @param c ArrayList of type Cell
	 */
	public void setFC(ArrayList<Cell> c) {
		this.FC = c;
	}
	/**
	 * Returns the tableau cell at input index
	 * 
	 * @param index index of tableau cell
	 */
	public Cell getTC(int index)
	{
		return  TC.get(index);
	}
	/**
	 * Returns the home cell at input index
	 * 
	 * @param index index of home cell
	 */
	public Cell getHC(int index)
	{
		return HC.get(index);
	}
	/**
	 * Returns the free cell at input index
	 * 
	 * @param index index of free cell
	 */
	public Cell getFC(int index)
	{
		return FC.get(index);
	}

	@Override
	public void move(int a, int b, boolean x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canRemove(Card c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void drawCard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTopCards() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(Card c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTC(Card c, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean emptyInTop() {
		// TODO Auto-generated method stub
		return false;
	}



	
}

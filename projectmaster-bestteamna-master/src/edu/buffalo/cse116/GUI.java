package edu.buffalo.cse116;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GUI {

	/**
	 * Outermost JFrame upon which the games are displayed on.
	 */
	private JFrame frame = new JFrame();
	/**
	 * JLayeredPane upon which the JLabels of cards are added to be displayed.
	 */
	private static JLayeredPane lpane = new JLayeredPane();

	private Game game;
	/**
	 * Dimensions of the frame.
	 */
	private static int x = 150;
	private static int y = 350;
	/**
	 * Dimensions of each card.
	 */
	private static final int w = 80;
	private static final int h = 110;
	/**
	 * The amount of components in JLayeredPane.
	 */
	private static Integer _size = 0;
	/**
	 * The main menu which contains options to create new games.
	 */
	private JMenuBar menuBar = new JMenuBar();
	/**
	 * Menu which leads to other options for the frame.
	 */
	private JMenu menu = new JMenu("New Game!");
	/**
	 * Instantiates a new Baker's Dozen game, with all relevant piles and cards.
	 */
	private JMenuItem baker = new JMenuItem("New Baker's Dozen Game");
	/**
	 * Instantiates a new Freecell game, with all relevant piles and cards.
	 */
	private JMenuItem Freecell = new JMenuItem("New FreeCell Game");
	/**
	 * Instantiates a new Ace's Up game, with all relevant piles and cards.
	 */
	private JMenuItem AceUp = new JMenuItem("New AceUp Game");
	/**
	 * Closes the frame/process.
	 */
	private JMenuItem quit = new JMenuItem("Quit");
	/**
	 * Records how many times the mouse was clicked. Counter resets every two
	 * clicks. First click is the first card selected. Second click is the card
	 * that the first card will be moved to.
	 */
	private int mouseCounter = 0;
	/**
	 * Records the index of the pile of the card selected.
	 */
	private int firstCellIdx;
	/**
	 * Integer value which determines what combination of cells a card is moving
	 * between. Takes in a value 1 to 5.
	 */
	private int compareInt;
	/**
	 * Integer value which determines what cell is the first card selected from.
	 * 0 = tableau cell. 1 = free cell. 2 = home cell.
	 */
	private int firstInput;
	/**
	 * Integer value which determines what cell is the second card selected
	 * from. 0 = tableau cell. 1 = free cell. 2 = home cell.
	 */
	private int secondInput;
	/**
	 * Boolean value which determines if the move made is legal. If move is
	 * legal, value set to false. If move is illegal, value set to true and
	 * "error message" displayed.
	 */
	private int gameType;
	private boolean nopeE;
	/**
	 * Used only for testing purposes.
	 */
	private Card firstCard;
	/**
	 * Used only for testing purposes.
	 */
	private Card nextCard;

	/**
	 * Constructor for GUI.
	 */

	public GUI() {
		menuBar.add(menu);
		menu.add(baker);
		menu.add(Freecell);
		menu.add(AceUp);
		menu.add(quit);

		frame.setJMenuBar(menuBar);

		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if ("Quit".equals(e.getActionCommand())) {
					System.exit(0);
				}

			}

		});

		baker.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if ("New Baker's Dozen Game".equals(e.getActionCommand())) {
					gameType = 0;
					reset(true);
				}

			}

		});
		Freecell.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if ("New FreeCell Game".equals(e.getActionCommand())) {
					gameType = 1;
					reset(true);
				}

			}

		});
		AceUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if ("New AceUp Game".equals(e.getActionCommand())) {
					gameType = 2;
					reset(true);
				}
				// TODO Auto-generated method stub

			}

		});

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(new BorderLayout());
		frame.add(lpane, BorderLayout.CENTER);
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

	/**
	 * Creates a JLabel holding the image of a card.
	 * 
	 * @param C
	 *            card
	 * @return JLabel containing ImageIcon of a card.
	 */
	public static JLabel image(Card c) {
		ImageIcon card1 = null;
		try {
			card1 = new ImageIcon(c.URL());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JLabel cardimage = new JLabel(card1);
		return cardimage;
	}

	
	public static void XYreset() {
		x = 150;
		y = 250;
	}

	


	/**
	 * Resets game depending on game type
	 * 
	 * @param c
	 *            resets when true
	 */
	public void reset(boolean c) {
		if (gameType == 0) {

			lpane.removeAll();
			lpane.revalidate();
			lpane.repaint();
			if (c == true) {
				mouseCounter = 0;
				game = new BakersDozen();
			}
			x = 150;
			y = 350;
			_size = 0;
			for (int i = 0; i < game.getTC().size(); i++) {
				for (int j = 0; j < game.getTC(i).getCardPile().toArray().size(); j++) {
					JLabel cards = image(game.getTC(i).getCardPile().toArray().get(j));
					cards.setBounds(x, y, w, h);
					y += 33;
					int hello = i;

					if (j == game.getTC(i).getCardPile().toArray().size() - 1) {
						cards.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								if (mouseCounter == 1) {
									// move
									nopeE = true;
									nextCard = game.getTC(hello).getCardPile().getLastCard();
									if (!game.isEmpty(game.getTC(), firstCellIdx))
										if (!game.isEmpty(game.getTC(), hello))
											if (game.lastCard(game.getTC(), firstCellIdx)
													.isConsecutive(game.lastCard(game.getTC(), hello))) {
												game.getTC(hello).addLastCard(game.getTC(firstCellIdx));
												game.getTC(firstCellIdx).removeLastCard();

												nopeE = false;
											}
									nopeError(nopeE);
									mouseCounter = 0;
									reset(false);
								} else if (mouseCounter == 0) {
									// store the card in this pile
									cards.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
									firstCellIdx = hello;
									firstCard = game.getTC(hello).getCardPile().getLastCard();
									System.out.println(firstCard.toString());
									mouseCounter += 1;
								}
							}
						});
					}
					lpane.add(cards, _size, 0);
					_size++;
				}
				x += w;
				y = 350;

			}
			x = 150;
			y = 150;
			for (int i = 0; i < game.getHC().size(); i++) {
				if (game.getHC(i).isEmpty()) {
					ImageIcon cells = new ImageIcon("src/Cards/gold.gif");
					JLabel Cards = new JLabel(cells);
					Cards.setBounds(x, y, w, h);
					int hello = i;
					Cards.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							if (mouseCounter == 1) {
								nopeE = true;

								if (!(game.isEmpty(game.getTC(), firstCellIdx))) {
									if ((game.isEmpty(game.getHC(), hello))
											&& (game.isAce(game.getTC(), firstCellIdx))) {
										game.getHC(hello).addLastCard(game.getTC(firstCellIdx));
										game.getTC(firstCellIdx).removeLastCard();

										nopeE = false;
									}
									if (!game.isEmpty(game.getHC(), hello))
										if (game.lastCard(game.getTC(), firstCellIdx)
												.isHome(game.lastCard(game.getHC(), hello))) {
											game.getHC(hello).addLastCard(game.getTC(firstCellIdx));
											game.getTC(firstCellIdx).removeLastCard();

											nopeE = false;
										}

								}
								nopeError(nopeE);
								mouseCounter = 0;
								reset(false);
							}

						}
					});

					lpane.add(Cards, _size, 0);
					x += (w * 2);
					_size++;
				} else {
					ImageIcon cells = new ImageIcon(game.getHC(i).getLastCard().URL());
					JLabel Cards = new JLabel(cells);
					Cards.setBounds(x, y, w, h);
					int hello = i;
					Cards.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							if (mouseCounter == 1) {

								if (!(game.isEmpty(game.getTC(), firstCellIdx))) {
									if ((game.isEmpty(game.getHC(), hello))
											&& (game.isAce(game.getTC(), firstCellIdx))) {
										game.getHC(hello).addLastCard(game.getTC(firstCellIdx));
										game.getTC(firstCellIdx).removeLastCard();
									}
									if (!game.isEmpty(game.getHC(), hello))
										if (game.lastCard(game.getTC(), firstCellIdx)
												.isHome(game.lastCard(game.getHC(), hello))) {
											game.getHC(hello).addLastCard(game.getTC(firstCellIdx));
											game.getTC(firstCellIdx).removeLastCard();
										}

								}
								mouseCounter = 0;
								reset(false);

							}

						}
					});

					lpane.add(Cards, _size, 0);
					x += (w * 2);
					_size++;

				}

			}

		} else if (gameType == 1) {
			lpane.removeAll();
			lpane.revalidate();
			lpane.repaint();
			if (c == true) {
				game = new FreeCellSolitaire();
				mouseCounter = 0;
			}
			XYreset();
			_size = 0;
			for (int i = 0; i < game.getTC().size(); i++) {
				for (int j = 0; j < game.getTC(i).getCardPile().toArray().size(); j++) {
					JLabel cards = image(game.getTC(i).getCardPile().toArray().get(j));
					cards.setBounds(x, y, w, h);
					y += 33;
					int hello = i;
					if (j == game.getTC(i).getCardPile().toArray().size() - 1) {
						cards.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								if (mouseCounter == 1) {
									secondInput = 0;
									methodComparison(firstInput, secondInput);
									nopeE = true;
									if (compareInt == 1) {
										if (!(game.isEmpty(game.getTC(), firstCellIdx))) {
											if (game.getTC(hello).isEmpty()) {

												game.getTC(hello).addLastCard(game.getTC(firstCellIdx));
												game.getTC(firstCellIdx).removeLastCard();

												nopeE = false;

											} else {
												if ((game.lastCard(game.getTC(), firstCellIdx)
														.isConsecutive(game.lastCard(game.getTC(), hello))
														&& (game.lastCard(game.getTC(), firstCellIdx)
																.isColorAlt(game.lastCard(game.getTC(), hello))))) {

													game.getTC(hello).addLastCard(game.getTC(firstCellIdx));
													game.getTC(firstCellIdx).removeLastCard();

													nopeE = false;
												}
											}
										}
									}
									if (compareInt == 4) {
										if (!(game.isEmpty(game.getFC(), firstCellIdx))) {
											if (game.isEmpty(game.getTC(), hello)) {

												game.getTC(hello).addLastCard(game.getFC(firstCellIdx));
												game.getFC(firstCellIdx).removeLastCard();

												nopeE = false;

											} else if ((game.lastCard(game.getFC(), firstCellIdx)
													.isConsecutive(game.lastCard(game.getTC(), hello))
													&& (game.lastCard(game.getFC(), firstCellIdx)
															.isColorAlt(game.lastCard(game.getTC(), hello))))) {
												game.getTC(hello).addLastCard(game.getFC(firstCellIdx));
												game.getFC(firstCellIdx).removeLastCard();

												nopeE = false;

											}

										}

									}
									nopeError(nopeE);
									mouseCounter = 0;
									reset(false);
								} else if (mouseCounter == 0) {
									cards.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
									firstCellIdx = hello;
									firstInput = 0;
									mouseCounter += 1;
								}
							}
						});
					}

					lpane.add(cards, _size, 0);
					_size++;
				}
				x += w;
				y = 250;
			}
			x = 150;
			y = 50;

			for (int i = 0; i < game.getHC().size(); i++) {
				if (game.getHC(i).isEmpty()) {
					ImageIcon cells = new ImageIcon("src/Cards/gold.gif");
					JLabel Card = new JLabel(cells);
					Card.setBounds(x, y, w, h);
					int hello = i;
					Card.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (mouseCounter == 1) {
								nopeE = true;
								secondInput = 2;
								methodComparison(firstInput, secondInput);
								if (compareInt == 3) {
									if (!(game.isEmpty(game.getTC(), firstCellIdx))) {
										if ((game.isEmpty(game.getHC(), hello)
												&& (game.isAce(game.getTC(), firstCellIdx)))) {

											game.getHC(hello).addLastCard(game.getTC(firstCellIdx));
											game.getTC(firstCellIdx).removeLastCard();

											nopeE = false;

										}
										if (!game.isEmpty(game.getHC(), hello))
											if (game.lastCard(game.getTC(), firstCellIdx)
													.isHome(game.lastCard(game.getHC(), hello))) {

												game.getHC(hello).addLastCard(game.getTC(firstCellIdx));
												game.getTC(firstCellIdx).removeLastCard();

												nopeE = false;

											}
									}
								}
								if (compareInt == 5) {
									if (!(game.isEmpty(game.getFC(), firstCellIdx))) {
										if ((game.isEmpty(game.getHC(), hello)
												&& (game.isAce(game.getFC(), firstCellIdx)))) {
											game.getHC(hello).addLastCard(game.getFC(firstCellIdx));
											game.getFC(firstCellIdx).removeLastCard();

											nopeE = false;

										}
										if (!game.isEmpty(game.getHC(), hello))
											if (game.lastCard(game.getFC(), firstCellIdx)
													.isHome(game.lastCard(game.getHC(), hello))) {
												game.getHC(hello).addLastCard(game.getFC(firstCellIdx));
												game.getFC(firstCellIdx).removeLastCard();

												nopeE = false;

											}
									}
								}
								nopeError(nopeE);
								mouseCounter = 0;
								reset(false);
							} else {
								for (int i = 0; i < game.getHC().size(); i++) {
									for (int j = 0; j < game.getHC(i).getCardPile().toArray().size(); j++) {
										System.out.println("HC " + i + " has "
												+ game.getHC(i).getCardPile().toArray().get(j).toString());
									}
									System.out.println("Size: " + game.getHC(i).getCardPile().toArray().size());
								}
							}
						}
					});
					lpane.add(Card, _size, 0);
					x += (w * 2);
					_size++;
				} else {
					ImageIcon cells = new ImageIcon(game.getHC(i).getLastCard().URL());
					JLabel Card = new JLabel(cells);
					Card.setBounds(x, y, w, h);
					int hello = i;
					Card.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							if (mouseCounter == 1) {
								nopeE = true;
								secondInput = 2;
								methodComparison(firstInput, secondInput);
								if (compareInt == 3) {
									if (!(game.isEmpty(game.getTC(), firstCellIdx))) {
										if ((game.isEmpty(game.getHC(), hello)
												&& (game.isAce(game.getTC(), firstCellIdx)))) {

											game.getHC(hello).addLastCard(game.getTC(firstCellIdx));
											game.getTC(firstCellIdx).removeLastCard();

											nopeE = false;

										}
										if (!game.isEmpty(game.getHC(), hello))
											if (game.lastCard(game.getTC(), firstCellIdx)
													.isHome(game.lastCard(game.getHC(), hello))) {

												game.getHC(hello).addLastCard(game.getTC(firstCellIdx));
												game.getTC(firstCellIdx).removeLastCard();

												nopeE = false;

											}
									}
								}
								if (compareInt == 5) {
									if (!(game.isEmpty(game.getFC(), firstCellIdx))) {
										if ((game.isEmpty(game.getHC(), hello)
												&& (game.isAce(game.getFC(), firstCellIdx)))) {
											game.getHC(hello).addLastCard(game.getFC(firstCellIdx));
											game.getFC(firstCellIdx).removeLastCard();

											nopeE = false;

										}
										if (!game.isEmpty(game.getHC(), hello))
											if (game.lastCard(game.getFC(), firstCellIdx)
													.isHome(game.lastCard(game.getHC(), hello))) {
												game.getHC(hello).addLastCard(game.getFC(firstCellIdx));
												game.getFC(firstCellIdx).removeLastCard();

												nopeE = false;

											}
									}
								}
								nopeError(nopeE);
								mouseCounter = 0;
								reset(false);
							} else {
								for (int i = 0; i < game.getHC().size(); i++) {
									for (int j = 0; j < game.getHC(i).getCardPile().toArray().size(); j++) {
										System.out.println("HC " + i + " has "
												+ game.getHC(i).getCardPile().toArray().get(j).toString());
										System.out.println("Size: " + game.getHC(i).getCardPile().toArray().size());
									}
								}
							}
						}
					});
					lpane.add(Card, _size, 0);
					x += (w * 2);
					_size++;

				}

			}
			for (int i = 0; i < game.getFC().size(); i++) {
				if (game.getFC(i).isEmpty()) {
					ImageIcon cell = new ImageIcon("src/Cards/green.gif");
					JLabel Cards = new JLabel(cell);
					Cards.setBounds(x, y, w, h);
					int hello = i;
					Cards.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							if (mouseCounter == 1) {
								nopeE = true;
								secondInput = 1;
								methodComparison(firstInput, secondInput);
								if (compareInt == 2) {
									if (!(game.isEmpty(game.getTC(), firstCellIdx))) {
										if (game.isEmpty(game.getFC(), hello)) {

											game.getFC(hello).addLastCard(game.getTC(firstCellIdx));
											game.getTC(firstCellIdx).removeLastCard();

											nopeE = false;

										}
									}
								}
								nopeError(nopeE);
								mouseCounter = 0;
								reset(false);
							} else if (mouseCounter == 0) {
								Cards.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
								firstInput = 1;
								firstCellIdx = hello;
								for (int i = 0; i < game.getFC().size(); i++) {
									for (int j = 0; j < game.getFC(i).getCardPile().toArray().size(); j++) {
										System.out.println("FC " + i + " has "
												+ game.getFC(i).getCardPile().toArray().get(j).toString());
										System.out.println("Size: " + game.getFC(i).getCardPile().toArray().size());
									}

								}
								mouseCounter += 1;
							}
						}
					});
					lpane.add(Cards, _size, 0);
					x += (w * 2);
					_size++;
				} else {
					ImageIcon cell = new ImageIcon(game.getFC(i).getLastCard().URL());
					JLabel Cards = new JLabel(cell);
					Cards.setBounds(x, y, w, h);
					int hello = i;
					Cards.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							if (mouseCounter == 1) {
								nopeE = true;
								secondInput = 1;
								methodComparison(firstInput, secondInput);
								if (compareInt == 2) {
									if (!(game.isEmpty(game.getTC(), firstCellIdx))) {
										if (game.isEmpty(game.getFC(), hello)) {

											game.getFC(hello).addLastCard(game.getTC(firstCellIdx));
											game.getTC(firstCellIdx).removeLastCard();

											nopeE = false;

										}
									}
								} else if (compareInt == 6) {
									if (!(game.isEmpty(game.getFC(), firstCellIdx))) {
										if (game.isEmpty(game.getFC(), hello)) {

											game.getFC(hello).addLastCard(game.getFC(firstCellIdx));
											game.getFC(firstCellIdx).removeLastCard();

											nopeE = false;

										}
									}
								}
								nopeError(nopeE);
								mouseCounter = 0;
								reset(false);
							} else if (mouseCounter == 0) {
								Cards.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
								firstInput = 1;
								firstCellIdx = hello;
								for (int i = 0; i < game.getFC().size(); i++) {
									for (int j = 0; j < game.getFC(i).getCardPile().toArray().size(); j++) {
										System.out.println("FC " + i + " has "
												+ game.getFC(i).getCardPile().toArray().get(j).toString());
										System.out.println("Size: " + game.getFC(i).getCardPile().toArray().size());
									}
								}
								mouseCounter += 1;
							}
						}
					});
					lpane.add(Cards, _size, 0);
					x += (w * 2);
					_size++;

				}

			}

			XYreset();

		} else if (gameType == 2) {
			lpane.removeAll();
			lpane.revalidate();
			lpane.repaint();
			if (c == true) {
				mouseCounter = 0;
				game = new AceUp();
			}
			XYreset();
			_size = 0;
			y=150;
			
			//tc
			for (int i = 0; i < game.getTC().size(); i++) {
				for (int j = 0; j < game.getTC(i).getCardPile().toArray().size(); j++) {
					JLabel cards = image(game.getTC(i).getCardPile().toArray().get(j));
					cards.setBounds(x, y, w, h);
					y += 33;
					int hello = i;
					cards.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							nopeE = true;
							if(game.canRemove(game.getTC(hello).getLastCard())) {
								nopeE = false;
							}
							if(game.emptyInTop()) {
								nopeE = false;
							}
							game.move(game.getTC(hello).getLastCard());
							reset(false);
							
							nopeError(nopeE);
						}
					});
					
					lpane.add(cards, _size, 0);
					_size++;
				}
				x += w;
				y = 150;
			}
			
			//hc
			x+=(w*2);
			if(game.getHC(0).isEmpty())
			{
				ImageIcon cell = new ImageIcon("src/Cards/gold.gif");
				JLabel Cards = new JLabel(cell);
				Cards.setBounds(x, y, w, h);
				lpane.add(Cards, _size, 0);
				_size++;
				
			}
			else
			{
				ImageIcon cell = new ImageIcon(game.getHC(0).getLastCard().URL());
				JLabel Cards = new JLabel(cell);
				Cards.setBounds(x, y, w, h);
				lpane.add(Cards, _size, 0);
				_size++;
			}
			y+=200;
			
			//stockpile
			if(!game.getDeck().isEmpty())
			{
				ImageIcon cell = new ImageIcon("src/Cards/CardBack.gif");//its green now, but need to change it to backcard
				JLabel Cards = new JLabel(cell);
				Cards.setBounds(x, y, w, h);//actionlistener, carddraw if its click and we have to reset(false);
				Cards.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						game.drawCard();
						reset(false);
					}
				});
				lpane.add(Cards, _size, 0);
				_size++;
			}
			else//when empty, no action listener
			{
				ImageIcon cell = new ImageIcon("src/Cards/hertzEgg.jpg");//easteregg (12)
				JLabel Cards = new JLabel(cell);
				Cards.setBounds(x, y, w, h);
				lpane.add(Cards, _size, 0);
				_size++;
			}

		}

	}

	/**
	 * Moves the last card from one cell to another while obeying the rules of
	 * the Baker's Dozen Game
	 * 
	 * @param A
	 *            index of the cell extracting from
	 * @param B
	 *            index of the cell adding to
	 * @param C
	 *            integer value from methodComparison
	 */
	public void move(int A, int B, int C) {
		int firstCellIdx = A - 1;
		int hello = B - 1;
		if (C == 1) {
			if (!(game.isEmpty(game.getTC(), firstCellIdx))) {
				if (game.getTC(hello).isEmpty()) {

					game.getTC(hello).addLastCard(game.getTC(firstCellIdx));
					game.getTC(firstCellIdx).removeLastCard();

				} else {
					if ((game.lastCard(game.getTC(), firstCellIdx).isConsecutive(game.lastCard(game.getTC(), hello))
							&& (game.lastCard(game.getTC(), firstCellIdx)
									.isColorAlt(game.lastCard(game.getTC(), hello))))) {

						game.getTC(hello).addLastCard(game.getTC(firstCellIdx));
						game.getTC(firstCellIdx).removeLastCard();
					}
				}
			}

		}
		if (C == 2) {
			if (!(game.isEmpty(game.getTC(), firstCellIdx))) {
				if (game.isEmpty(game.getFC(), hello)) {

					game.getFC(hello).addLastCard(game.getTC(firstCellIdx));
					game.getTC(firstCellIdx).removeLastCard();

				}
			}
		}
		if (C == 3) {
			if (!(game.isEmpty(game.getTC(), firstCellIdx))) {
				if ((game.isEmpty(game.getHC(), hello) && (game.isAce(game.getTC(), firstCellIdx)))) {

					game.getHC(hello).addLastCard(game.getTC(firstCellIdx));
					game.getTC(firstCellIdx).removeLastCard();

				}
				if (!game.isEmpty(game.getHC(), hello))
					if (game.lastCard(game.getTC(), firstCellIdx).isHome(game.lastCard(game.getHC(), hello))) {

						game.getHC(hello).addLastCard(game.getTC(firstCellIdx));
						game.getTC(firstCellIdx).removeLastCard();

					}
			}
		}
		if (C == 4) {
			if (!(game.isEmpty(game.getFC(), firstCellIdx))) {
				if (game.isEmpty(game.getTC(), hello)) {

					game.getTC(hello).addLastCard(game.getFC(firstCellIdx));
					game.getFC(firstCellIdx).removeLastCard();

				} else if ((game.lastCard(game.getFC(), firstCellIdx).isConsecutive(game.lastCard(game.getTC(), hello))
						&& (game.lastCard(game.getFC(), firstCellIdx)
								.isColorAlt(game.lastCard(game.getTC(), hello))))) {
					game.getTC(hello).addLastCard(game.getFC(firstCellIdx));
					game.getFC(firstCellIdx).removeLastCard();

				}

			}

		}
		if (C == 5) {
			if (!(game.isEmpty(game.getFC(), firstCellIdx))) {
				if ((game.isEmpty(game.getHC(), hello) && (game.isAce(game.getFC(), firstCellIdx)))) {
					game.getHC(hello).addLastCard(game.getFC(firstCellIdx));
					game.getFC(firstCellIdx).removeLastCard();

				}
				if (!game.isEmpty(game.getHC(), hello))
					if (game.lastCard(game.getFC(), firstCellIdx).isHome(game.lastCard(game.getHC(), hello))) {
						game.getHC(hello).addLastCard(game.getFC(firstCellIdx));
						game.getFC(firstCellIdx).removeLastCard();

					}
			}
		}
		if (C == 6) {
			if (!(game.isEmpty(game.getFC(), firstCellIdx))) {
				if (game.isEmpty(game.getFC(), hello)) {

					game.getFC(hello).addLastCard(game.getFC(hello));
					game.getFC(hello).removeLastCard();

				}
			}
		}

	}

	/**
	 * Compares what cells a card is moving between. Inputs are 0 = TC, 1 = FC,
	 * 2 = HC Sets compareInt to 1-5, depending on which cells moving between.
	 * 
	 * @param A
	 *            first input
	 * @param B
	 *            second input
	 */
	public void methodComparison(int a, int b) {
		if (a == 0 && b == 0) {
			compareInt = 1;
		} else if (a == 0 && b == 1) {
			compareInt = 2;
		} else if (a == 0 && b == 2) {
			compareInt = 3;
		} else if (a == 1 && b == 0) {
			compareInt = 4;
		} else if (a == 1 && b == 2) {
			compareInt = 5;
		} else if (a == 1 && b == 1) {
			compareInt = 6;
		}
	}

	/**
	 * If input is true, then illegal move was made. Creates a new frame with a
	 * picture of the noptupus. Acts as an error message.
	 * 
	 * @param b
	 *            boolean value determining if move made was legal
	 */
	public void nopeError(boolean b) {
		if (b) {
			ImageIcon nope = new ImageIcon("src/Cards/nope.gif");
			JFrame frame = new JFrame();
			frame.setSize(500, 300);
			JLabel label = new JLabel(nope);
			frame.add(label);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
	}

	public static void main(String[] args) {
		new GUI();

	}

}

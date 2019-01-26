package edu.buffalo.cse116;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Main {
	//declare fields
    private JFrame frame = new JFrame();
    private JLayeredPane lpane = new JLayeredPane();
    private JLayeredPane lpane2 = new JLayeredPane();
    private BakersDozen game = new BakersDozen();
   // private JPanel panel1 = new JPanel();
  //  private JPanel panel2 = new JPanel();
   // private JPanel panel3 = new JPanel();
    //frame dimensions
    public static int  x = 150;
    public static int  y = 150;
    public static final int w = 80;
    public static final int h = 110;
    public static final int dimx = 1600;
    public static final int dimy = 900;
    public Integer _size = 0;
    public Main()
    {
    	//declare cards
    	Card card1 = new Card(1,1);
    	Card card2 = new Card(4,1);
    	Card card3 = new Card(1,13);
    	
    	//convert to icons Jlabels
    	JLabel j1 = image(game.getTC().get(1).getCardPile().toArray().get(1));
    	JLabel j2 = image(card2);
    	JLabel j3 = image(card3);
    	  
    	for(int i = 0; i<game.getTC().size();i++)
    	{
    		for(int j =0; j<game.getTC(i).getCardPile().toArray().size();j++)
    		{
    			JLabel cards = image(game.getTC(i).getCardPile().toArray().get(j));
    			cards.setBounds(x,y,w,h);
    			y+=33;
    			lpane.add(cards,_size,0);
    			_size++;
    		}
    		x+=w;
    		y=150;
    	}
    	//frame settings
        frame.setPreferredSize(new Dimension(dimx, dimy));
        frame.setLayout(new BorderLayout());
        
        //frame adds lpane
        frame.add(lpane, BorderLayout.CENTER);
       // frame.add(lpane2);
        
        //lpane.setBounds(x, y, 900-x, 900-y);
        frame.getContentPane().setBackground(Color.GREEN);
       
        //panels add the icon Jlabels
       // panel1.add(j1);
      // j1.setBounds(x, y, w, h);//keep constant
      //  panel1.setOpaque(true);//sets it visible
      //  y+=33;//moves card down ontop
        
        
       // panel2.add(j2);
        //j2.setBounds(x, y, w, h);
       // panel2.setOpaque(true);
      //  y+=33;
        
       
       // panel3.add(j3);
     //  j3.setBounds(x, y, w, h);
        //panel3.setOpaque(true);
     //   y+=33;
        
      //  lpane.ge
        
      //  lpane.add(j1, new Integer(0), 0);
       // lpane.add(j2, new Integer(1), 0);
       // lpane.add(j3, new Integer(2), 0);
       // lpane2 = lpane;
        //lpane.add(panel1, new Integer(3));
       
     
       // frame.setBackground(Color.GREEN);
        frame.pack();
        frame.setVisible(true);
        
        
    }
    public static JLabel image(Card c)
    {
    	ImageIcon card1 = null;
    	try{
    	card1 = new ImageIcon(c.URL());
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	JLabel cardimage = new JLabel(card1);
    	return cardimage;
    }



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Main();
    }

}
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class BoxLayoutTest {

  public static void main(String[] args) {
    JFrame frame = new JFrame("BoxLayout Test");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS); // top to bottom
    frame.setLayout(boxLayout);
    ImageIcon icon1 = new ImageIcon("src/Cards/10c.gif");
    JLabel label = new JLabel(icon1);
    JLabel label2 = new JLabel(icon1);
    JLabel label3 = new JLabel(icon1);
    JLabel label4 = new JLabel(icon1);
    
  //  JButton a = new JButton("HELLO");
   // JButton b = new JButton("HELLO");
    //JButton c = new JButton("HELLO");
    label.setBorder(new EmptyBorder(-30, 0, 0, 0));
    label2.setBorder(new EmptyBorder(-30, 0, 0, 0));
    label3.setBorder(new EmptyBorder(-30, 0, 0, 0));
     frame.add(label);
     frame.add(label2);
     frame.add(label3);

    frame.pack();

    frame.setVisible(true);
  }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.text.*;
class rule extends JFrame implements ActionListener
{
	JButton b2 ;
	JLabel l1;
	public rule()
	{
		
		super("Lawns Management System");
		try
        {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex)
        {
           System.out.println(ex);
        }	
		setLayout(null);
		
		l1= new JLabel(new ImageIcon("rule1.jpg"), JLabel.CENTER);
		l1.setBounds(30,0,700,700);
		add(l1);	
		
        JButton b1=new JButton("HOME");
     	b1.setBounds(470,550,150,50);
     	b1.setMnemonic('H');
     	b1.setBorder(new LineBorder(Color.RED,4));
     	add(b1);
        b1.addActionListener(this);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1100,700);
		setVisible(true);
     }
       
       public void actionPerformed(ActionEvent ae)
     {
     	  String st=ae.getActionCommand();
     	    setVisible(false);
            main m = new main();
	   		m.setVisible(true); 
	   		 dispose();
     }    			

       	
	
	public static void main(String args[])
	{
	

		new rule();
	}
} 

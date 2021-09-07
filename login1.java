import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.*;
import java.text.*;

class login1 extends JFrame implements ActionListener
{
	
	JLabel l1, l0,ll, luname, lpass;
	JTextField  name;
	JPasswordField t2;
	JButton b1,b2;

	Font f3;
	
	Font f2=new Font("Monotype Corsiva",4,50);
	Connection cn1;
	ResultSet rs1;
	Statement st1, st2;
	

	login1()
	{
		super("Login Frame");
		try
        {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex)
        {
           System.out.println(ex);
        }
		
		f3=new Font("Arial",Font.BOLD,18);

	
		setLayout(null);		
		
		l1= new JLabel(new ImageIcon("xyz.jpg"), JLabel.CENTER);
		l1.setBounds(375,180,265,320);
		l1.setBorder(new LineBorder(Color.black,5));
		l1.setFont(f3);	
		
		l1.setBackground(Color.yellow);
		ll=new JLabel("Lawns Management System");
		
		ll.setBounds(250,25,800,90);
		
		ll.setFont(f2);
		l1.setForeground(Color.red);
		
		add(ll); 
		
		luname=new JLabel("User Name:");
		luname.setBounds(400,300,150,25);
		luname.setFont(f3);
		add(luname);
		
		name=new JTextField();
		name.setBounds(520,300,100,25);
		name.setFont(f3);
		add(name);		
    	name.setBorder(BorderFactory.createLineBorder(Color.black,1));
		
		lpass = new JLabel("Password  :");
        lpass.setBounds(400,350,120,25);
        lpass.setFont(f3);
        add(lpass);
       
       t2=new JPasswordField();
       t2.setEchoChar('*');
       t2.setBounds(520,350,100,25); 
       t2.setFont(f3);   
       add(t2);
       //t2.setBorder(BorderFactory.createLineBorder(Color.black,1));

       b1 = new JButton("Login");
       b1.setBounds(400,430,80,25);
       add(b1);
       //b1.setBorder(BorderFactory.createLineBorder(Color.red,2));
       
       b1.addActionListener(this);

       
       b2 = new JButton("Exit");
       b2.setBounds(510,430,80,25);
       add(b2);
       //b2.setBorder(BorderFactory.createLineBorder(Color.red,2));
       b2.addActionListener(this);
       
        add(l1);
		setResizable(false);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900,700);
		setVisible(true);
	}
    
   
    public void actionPerformed(ActionEvent e1)
   { 
      
      String s1=e1.getActionCommand();
      if(s1.equals("Exit"))			//cancel button ic clicked
      {
          System.exit(0);
         
      }
      else								//login button is clicked
      {
          try
	  {
	     int flag = 0;
	     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	     cn1 = DriverManager.getConnection("jdbc:odbc:lawns1");
         Statement st1 = cn1.createStatement();
	     Statement st2 = cn1.createStatement();
         st1 = cn1.createStatement(); 
         ResultSet rs1 = st1.executeQuery("SELECT* FROM login");
         String s2 = name.getText();
         String s3 = t2.getText();
	     while(rs1.next())
             {
		        String s4 = rs1.getString("uname");
                System.out.println("Login name : "+s4);
                String s5 = rs1.getString("password");
                System.out.println("Password : "+s5);
                if((s2.equals(s4)) && (s3.equals(s5)))
                {
		   			flag = 1;
		   			break;                
                }
             }
             if(flag==1)
             {
             	JOptionPane.showMessageDialog((Component)null,"LOGIN,Successful...!","Login screen",-1);

  		         setVisible(false);
                 main m = new main();
                 
             }
             else
             {
               int dt1 = JOptionPane.PLAIN_MESSAGE;
	           JOptionPane.showMessageDialog((Component)null,"WRONG LOGIN,Plese Retry...!","Login screen",dt1);
	           name.setText("");		 
	           t2.setText("");	
               name.requestFocus();
            }
            
             cn1.close();
          }
          catch(Exception e)
          {
             System.out.println("SQL Error!"+e.getMessage());
          }
         
       }
    }
  
        
		public static void main(String args[])
	{
		new login1();
	}
}
		
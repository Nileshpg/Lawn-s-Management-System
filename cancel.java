import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.event.*;
import java.text.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
class cancel extends JFrame implements ActionListener
{
	JLabel lcid,lbid,lcname,lav,lch,ltotal,limg,ldate;
	JTextField cid, bid, cname, av,ch,total,date; 
	ImageIcon img;
	JButton search,exit,del,home,calc;
	Font f1;
	int l,m,s;
	String tot;
	Connection con;
	Statement st1,st2,st3,st4,st5,st6,st7;
	ResultSet rs1,rs2,rs3,rs4,rs5,rs6,rs7;
	cancel()
	{
		super("Customer Registration cancelled");
		try
    	{
    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        f1=new Font("Cambria",Font.BOLD,17);
		img=new ImageIcon("cancelled.png");
		limg=new JLabel(img);
		limg.setBounds(200,200,600,500);
		
		lcid=new JLabel("Customer ID:");
		lcid.setBounds(160,130,120,20);
		lcid.setFont(f1);
		add(lcid);
		
		cid=new JTextField();
		cid.setBounds(270,130,50,20);
		add(cid);
		
		search=new JButton("Search");
		search.setBounds(330,127,80,25);
		search.addActionListener(this);
		add(search);
		
		ldate=new JLabel("Date:");
		ldate.setBounds(450,130,80,20);
		ldate.setFont(f1);
		add(ldate);
		
		date=new JTextField();
		date.setBounds(510,130,80,20);
		date.setEditable(false);
		add(date);
		
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter= new SimpleDateFormat("d/MM/yyyy");
		String dateNow = formatter.format(currentDate.getTime());
		date.setText(dateNow);
		
		lcname=new JLabel("Customer Name:");
		lcname.setBounds(130,170,140,20);
		lcname.setFont(f1);
		add(lcname);
		
		cname=new JTextField();
		cname.setBounds(270,170,320,20);
		cname.setEditable(false);
		add(cname);		
		
		
		lav=new JLabel("advance amount:");
		lav.setBounds(130,210,140,25);
		lav.setFont(f1);
		add(lav);
		
		av=new JTextField();
		av.setBounds(270,210,100,20);
		av.setEditable(false);
		add(av);
		
		lch=new JLabel("charges:");
		lch.setBounds(195,255,140,25);
		lch.setFont(f1);
		add(lch);
		
		ch=new JTextField();
		ch.setBounds(270,260,100,20);
		add(ch);
		
		ltotal=new JLabel("Total Amount:");
		ltotal.setBounds(150,310,140,25);
		ltotal.setFont(f1);
		add(ltotal);
		
		total=new JTextField();
		total.setBounds(270,320,100,20);
		total.setEditable(false);
		add(total);
		
		calc=new JButton("Calculate");
		calc.setBounds(420,280,100,40);
		calc.addActionListener(this);
		add(calc);	
		
		del=new JButton("Delete");
		del.setBounds(200,400,80,40);
		del.addActionListener(this);
		add(del);
		
		home=new JButton("Home");
		home.setBounds(320,400,80,40);
		home.addActionListener(this);
		add(home);
		
		exit=new JButton("Exit");
		exit.setBounds(440,400,80,40);
		exit.addActionListener(this);
		add(exit);		
		
		
		add(limg);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(800,650);
	}
	public static void main(String args[])
	{
		new cancel();
	}
	
	void setConnection()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		    con=DriverManager.getConnection("jdbc:odbc:lawns1");		    
		   	System.out.println("Connection succesfully");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"Database Connection Error!"+e.getMessage(),"Database Error",JOptionPane.ERROR_MESSAGE);	
		}
	}//setConnection
	void clearfield()
	{
		cid.setText("");
		cname.setText("");		
		total.setText("");
		ch.setText("");
		av.setText("");
	}
	
	void enablefields(boolean flag)
	{
		//cid, bid, cname,total, aamount,date
		
		cname.setEnabled(flag);			
		total.setEnabled(flag);	
		av.setEnabled(flag);
		date.setEnabled(flag);
	}//enablefields
	
	void showfields(ResultSet rs1, ResultSet rs2)
	{
		try
		{
			cname.setText(rs1.getString(2));
			av.setText(rs2.getString(8));											
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(this,"Data Show Error!","Show Error",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}//showfield
	public void actionPerformed(ActionEvent ae1)
	{
		try
		{	
			String s1=ae1.getActionCommand();
			if(s1.equals("Exit"))
				System.exit(0);
				
			else if(s1.equals("Search"))
			{
				setConnection();
				int id=Integer.parseInt(cid.getText());
				System.out.println(""+id);
				st1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				st2=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs1=st1.executeQuery("select * from customer where CID="+id);
				rs2=st2.executeQuery("select * from bill where CID="+id);
				System.out.println("Error");
				if(rs1.next()&& rs2.next())
		    	{
		    		showfields(rs1,rs2);
		    	}
			}
			else if(s1.equals("Calculate"))
			{
				int am=Integer.parseInt(av.getText());
				if(ch.getText().equals(""))
				{
					JOptionPane.showMessageDialog(this,"Enter Advance Amount","Error",JOptionPane.ERROR_MESSAGE);
				}
				int tt=Integer.parseInt(ch.getText());
				total.setText(String.valueOf(am-tt));
				
			}
			else if(s1.equals("Home"))
			{
				main m1=new main();
				dispose();
			}
			else if(s1.equals("Delete"))
			{
				try
				{
					int id=Integer.parseInt(cid.getText());
					st3=con.createStatement();
					st4=con.createStatement();
					st5=con.createStatement();
					st6=con.createStatement();
					st7=con.createStatement();
					
					st3.executeUpdate("delete * from customer where CID="+id);
					st4.executeUpdate("delete * from function where fid="+id);
					st5.executeUpdate("delete * from service1 where sid="+id);
					st6.executeUpdate("delete * from menu where mid="+id);
					st7.executeUpdate("delete * from bill where bid="+id);
					
					JOptionPane.showMessageDialog(this,"RECORD IS DELETE SUCCESSFULLY","Record Deleted",JOptionPane.INFORMATION_MESSAGE);
					clearfield();
				}
					
				catch(Exception et)
				{
					JOptionPane.showMessageDialog(this,"Record does not delete","Record Delete Error",JOptionPane.ERROR_MESSAGE);
				}
			}		
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(this,"Action Listener error","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
}

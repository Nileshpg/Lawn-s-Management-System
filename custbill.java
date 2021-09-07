import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.event.*;
import java.text.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
class custbill extends JFrame implements ActionListener
{
	JLabel lcid,lbid,lcname,llc, lsc, lmc, ltotal,laamount,lnamount, ldate, limg,rupee;
	JTextField cid, bid, cname, lc, sc, mc,total, aamount, namount, date; 
	ImageIcon img;
	JButton search,exit,save,print,home,calc,last,next,prev;
	Font f1;
	int l,m,s;
	String tot;
	Connection con;
	Statement st1,st2,st3,st4,st5,st6,st7;
	ResultSet rs1,rs2,rs3,rs4,rs5,rs6,rs7;
	custbill()
	{
		super("Customer Bill");
		try
    	{
    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        f1=new Font("Cambria",Font.BOLD,17);
		img=new ImageIcon("bill.jpg");
		limg=new JLabel(img);
		limg.setBounds(200,200,600,500);
		
		lcid=new JLabel("Customer ID:");
		lcid.setBounds(130,180,120,20);
		lcid.setFont(f1);
		add(lcid);
		
		cid=new JTextField();
		cid.setBounds(240,180,50,20);
		add(cid);
		
		search=new JButton("Search");
		search.setBounds(300,177,80,25);
		search.addActionListener(this);
		add(search);
		
		ldate=new JLabel("Date:");
		ldate.setBounds(500,205,80,20);
		ldate.setFont(f1);
		add(ldate);
		
		date=new JTextField();
		date.setBounds(560,205,80,20);
		date.setEditable(false);
		add(date);
		
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter= new SimpleDateFormat("d/MM/yyyy");
		String dateNow = formatter.format(currentDate.getTime());
		date.setText(dateNow);
		
		lcname=new JLabel("Customer Name:");
		lcname.setBounds(130,240,140,20);
		lcname.setFont(f1);
		add(lcname);
		
		cname=new JTextField();
		cname.setBounds(270,240,300,20);
		cname.setEditable(false);
		add(cname);
		
		llc=new JLabel("Lawns Charge:");
		llc.setBounds(130,270,140,25);
		llc.setFont(f1);
		add(llc);
		
		lc=new JTextField();
		lc.setBounds(270,270,100,20);
		lc.setEditable(false);
		add(lc);
		
		lsc=new JLabel("Service Charge:");
		lsc.setBounds(130,300,140,25);
		lsc.setFont(f1);
		add(lsc);
		
		sc=new JTextField();
		sc.setBounds(270,300,100,20);
		sc.setEditable(false);
		add(sc);
		
		lmc=new JLabel("Menu Charge:");
		lmc.setBounds(130,330,140,25);
		lmc.setFont(f1);
		add(lmc);
		
		mc=new JTextField();
		mc.setBounds(270,330,100,20);
		mc.setEditable(false);
		add(mc);
		
		ltotal=new JLabel("Total Amount:");
		ltotal.setBounds(430,380,140,25);
		ltotal.setFont(f1);
		add(ltotal);
		
		total=new JTextField();
		total.setBounds(550,380,100,20);
		total.setEditable(false);
		add(total);
		
		laamount=new JLabel("Advance Amount:");
		laamount.setBounds(405,410,140,25);
		laamount.setFont(f1);
		add(laamount);
		
		aamount=new JTextField();
		aamount.setBounds(550,410,100,20);
		add(aamount);
		
		lnamount=new JLabel("Balance Amount:");
		lnamount.setBounds(410,440,140,25);
		lnamount.setFont(f1);
		add(lnamount);
		
		namount=new JTextField();
		namount.setBounds(550,445,100,20);
		namount.setEditable(false);
		add(namount);
		
		next=new JButton("Next");
		next.setBounds(130,480,80,30);
		next.addActionListener(this);
		add(next);
		
		save=new JButton("Save");
		save.setBounds(240,480,80,30);
		save.addActionListener(this);
		add(save);
		
		prev=new JButton("Previous");
		prev.setBounds(345,480,80,30);
		prev.addActionListener(this);
		add(prev);
		
		home=new JButton("Home");
		home.setBounds(345,520,80,30);
		home.addActionListener(this);
		add(home);
		
		exit=new JButton("Exit");
		exit.setBounds(460,480,80,30);
		exit.addActionListener(this);
		add(exit);
		
		
		last=new JButton("Last");
		last.setBounds(570,480,80,30);
		last.addActionListener(this);
		add(last);
		
		calc=new JButton("Calculate");
		calc.setBounds(270,400,100,50);
		calc.addActionListener(this);
		add(calc);
		
		add(limg);
		setConnection();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(800,650);
	}
	public static void main(String args[])
	{
		new custbill();
	}
	void setConnection()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		    con=DriverManager.getConnection("jdbc:odbc:lawns1");
		    st1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		    st2=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		    st3=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		    st4=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		    st5=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		    
		    st6=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		    st7=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		    rs6=st6.executeQuery("select * from bill");
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
		lc.setText(""); 
		sc.setText(""); 
		mc.setText("");
		total.setText("");
		aamount.setText("");
		namount.setText("");
		//date.setText("");
	}
	
		void enablefields(boolean flag)
	{
		//cid, bid, cname, lc, sc, mc,total, aamount, namount, date
		
		cname.setEnabled(flag);	
		lc.setEnabled(flag);	
		sc.setEnabled(flag);	
		mc.setEnabled(flag);	
		total.setEnabled(flag);	
		namount.setEnabled(flag);
		date.setEnabled(flag);
	}//enablefields
	
	void showfields(ResultSet rs6)
	{
		try
		{
			bid.setText(rs6.getString(1));
			date.setText(rs6.getString(2));
			cid.setText(rs6.getString(3));
			cname.setText(rs6.getString(4));
			lc.setText(rs6.getString(5));
			sc.setText(rs6.getString(6));
			mc.setText(rs6.getString(7));
			total.setText(rs6.getString(8));
			aamount.setText(rs6.getString(9));
			namount.setText(rs6.getString(10));
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(this,"Data Show Error!","Show Error",JOptionPane.INFORMATION_MESSAGE);
		}
	}
		
	public void actionPerformed(ActionEvent ae1)
	{
		try
		{	
			String s1=ae1.getActionCommand();
			if(s1.equals("Exit"))
				System.exit(0);
				
			else if(s1.equals("Search"))
			{
							
				
				int id=Integer.parseInt(cid.getText());
				rs1=st1.executeQuery("select * from customer where CID="+id);
				System.out.println(""+id);
				rs2=st2.executeQuery("select * from function where fid="+id);
				rs3=st3.executeQuery("select * from service1 where sid="+id);
				rs4=st4.executeQuery("select * from menu where mid="+id);
		
				if(rs1.next())
		   			cname.setText(rs1.getString(2));
				if(rs2.next())
					lc.setText(rs2.getString(8));
				if(rs3.next())
					sc.setText(rs3.getString(14));
				if(rs4.next())
					mc.setText(rs4.getString(16));
				
				l=Integer.parseInt(lc.getText());
				s=Integer.parseInt(sc.getText());
				m=Integer.parseInt(mc.getText());
				total.setText(String.valueOf(l+m+s));
				try
				{
					
	    			rs7=st7.executeQuery("select Max(bid) from bill");
	    							
					if(rs7.next()==true)
					{
						int n1=rs7.getInt(1);
						n1++;
						bid.setText(""+n1);
					}
					else
						bid.setText("1");
				
				}
				catch(SQLException e2)
				{
					JOptionPane.showMessageDialog(this,"Customer id Storing Error!!!","c_id Error",JOptionPane.INFORMATION_MESSAGE);	
				}
			
			}
			else if(s1.equals("Calculate"))
			{
				int tt=Integer.parseInt(total.getText());
				if(aamount.getText().equals(""))
				JOptionPane.showMessageDialog(this,"Enter Advance Amount","Error",JOptionPane.ERROR_MESSAGE);
				int am=Integer.parseInt(aamount.getText());
				namount.setText(String.valueOf(tt-am));
				
			}
			else if(s1.equals("Home"))
			{
				main m1=new main();
				dispose();
			}
			else if(s1.equals("Save"))
			{
				try
				{
					rs5=st5.executeQuery("select * from bill");
					
					int cd=Integer.parseInt(cid.getText());
					String cn=cname.getText();
					String l2=date.getText();
					int ll=Integer.parseInt(lc.getText());
					int ss=Integer.parseInt(sc.getText());
					int mm=Integer.parseInt(mc.getText());
					int tt=Integer.parseInt(total.getText());
					int aa=Integer.parseInt(aamount.getText());
					int na=Integer.parseInt(namount.getText());
					
					rs5.updateString(2,l2); 
					rs5.updateInt(3,cd);
					rs5.updateString(4,cn);
					rs5.updateInt(5,ll);
					rs5.updateInt(6,ss);
					rs5.updateInt(7,mm);
					rs5.updateInt(8,tt);
					rs5.updateInt(9,aa);
					rs5.updateInt(10,na);
					
					rs5.insertRow();
					JOptionPane.showMessageDialog(this,"Adding new record successfully","Save",JOptionPane.INFORMATION_MESSAGE);
					clearfield();
					enablefields(false);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(this,"Record does not save","Save",JOptionPane.ERROR_MESSAGE);
				}
				
			}	
			else if(s1.equals("Next"))
			{
				try
				{
					if(rs6.next())
					{
						showfields(rs6);
					}
					else
					{
						rs6.last();
						showfields(rs6);
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(this,"Next","Error",JOptionPane.ERROR_MESSAGE);
				}
			}//Next
					
			else if(s1.equals("Previous"))
			{
				if(rs6.previous())
					showfields(rs6);
				else
				{
					rs6.first();
					showfields(rs6);
				}
					
			}//Previous
			else if(s1.equals("Last"))
			{
				if(rs6.last())
					showfields(rs6);
			}		
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(this,"Action Listener error","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
}
				
		
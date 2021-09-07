import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.event.*;
import java.text.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
class workerbill extends JFrame implements ActionListener
{
	JLabel lwid,lbid,lwname,lwtype, lsalary, lwday, ltotal,laamount,lnamount, ldate, limg,rupee;
	JTextField wid, bid, wname, wtype, salary, wday,total, aamount, namount, date; 
	ImageIcon img;
	JButton search,exit,save,print,home,calc;
	Font f1;
	int l,m,s;
	String tot;
	Connection con;
	Statement st1,st2,st3,st4,st5;
	ResultSet rs1,rs2,rs3,rs4,rs5;
	workerbill()
	{
		super("Worker Bill");
		try
    	{
    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        f1=new Font("Cambria",Font.BOLD,17);
		img=new ImageIcon("worker bill.jpg");
		limg=new JLabel(img);
		limg.setBounds(200,200,600,500);
		
		lwid=new JLabel("Worker ID:");
		lwid.setBounds(130,180,120,20);
		lwid.setFont(f1);
		add(lwid);
		
		wid=new JTextField();
		wid.setBounds(240,180,50,20);
		add(wid);
		
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
		
		lwname=new JLabel("Worker Name:");
		lwname.setBounds(130,240,140,20);
		lwname.setFont(f1);
		add(lwname);
		
		wname=new JTextField();
		wname.setBounds(270,240,200,20);
		wname.setEditable(false);
		add(wname);
		
		lwtype=new JLabel("Worker Type:");
		lwtype.setBounds(135,270,140,25);
		lwtype.setFont(f1);
		add(lwtype);
		
		wtype=new JTextField();
		wtype.setBounds(270,270,200,20);
		wtype.setEditable(false);
		add(wtype);
		
		lsalary=new JLabel("salary:");
		lsalary.setBounds(190,300,100,25);
		lsalary.setFont(f1);
		add(lsalary);
		
		salary=new JTextField();
		salary.setBounds(270,300,100,20);
		salary.setEditable(false);
		add(salary);
		
		lwday=new JLabel("work day:");
		lwday.setBounds(165,330,140,25);
		lwday.setFont(f1);
		add(lwday);
		
		wday=new JTextField();
		wday.setBounds(270,330,100,20);
		add(wday);
		
		ltotal=new JLabel("Total Amount:");
		ltotal.setBounds(135,370,140,25);
		ltotal.setFont(f1);
		add(ltotal);
		
		total=new JTextField();
		total.setBounds(270,370,100,20);
		total.setEditable(false);
		add(total);
		
		save=new JButton("Save");
		save.setBounds(220,460,80,40);
		save.addActionListener(this);
		add(save);	
		
		home=new JButton("Home");
		home.setBounds(320,460,80,40);
		home.addActionListener(this);
		add(home);
		
		exit=new JButton("Exit");
		exit.setBounds(420,460,80,40);
		exit.addActionListener(this);
		add(exit);
		
		calc=new JButton("Calculate");
		calc.setBounds(400,350,100,30);
		calc.addActionListener(this);
		add(calc);
		
		add(limg);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(800,650);
	}
	public static void main(String args[])
	{
		new workerbill();
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
		wid.setText("");
		wname.setText("");
		wtype.setText(""); 
		salary.setText(""); 
		wday.setText("");
		total.setText("");
	}
	
	void showfields()
	{
		try
		{
			
			wid.setText(rs1.getString(1));
			wname.setText(rs1.getString(2));		
			salary.setText(rs1.getString(6));
			wtype.setText(rs1.getString(7));
											
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
			{
				System.exit(0);
			}
			else if(s1.equals("Home"))
			{
				main m1=new main();
				dispose();
			}
				
			else if(s1.equals("Search"))
			{
				setConnection();
				int id=Integer.parseInt(wid.getText());
				System.out.println(""+id);
				st1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs1=st1.executeQuery("select * from worker where w_id="+id);
				if(rs1.next())
		    		{
		    			showfields();
		    		}
			}
			else if(s1.equals("Calculate"))
			{
				String sal=salary.getText();
				int sal1=Integer.parseInt(sal);
				System.out.println("Salary:"+sal1);
				String day=wday.getText();
				int day1=Integer.parseInt(day);
				System.out.println("Wday;"+day1);
				int am=sal1*day1;
				System.out.println("Tot:"+am);
				total.setText(String.valueOf(am));
			}
			else if(s1.equals("Save"))
			{
				
				try
				{
				st2=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs2=st2.executeQuery("select * from workerbill");
				
				int wid1=Integer.parseInt(wid.getText());
				String name1=wname.getText();
				String wtype1=wtype.getText();
				String sal1=salary.getText();
				String wdy1=wday.getText(); 
				String tot1=total.getText();
					
				
				
				rs2.updateInt(2,wid1);
				rs2.updateString(3,name1);
				rs2.updateString(4,wtype1);
				rs2.updateString(5,sal1);
				rs2.updateString(6,wdy1);
				rs2.updateString(7,tot1);
				
				if(rs2.next())
				//System.out.println("Error:");
				rs2.insertRow();
				//rs2.insertRow();
				System.out.println("Error:");
					
				JOptionPane.showMessageDialog(this,"Adding new record successfully","Save",JOptionPane.INFORMATION_MESSAGE);
				clearfield();
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(this,"Record does not save","Save",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(this,"Action Listener error","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
}

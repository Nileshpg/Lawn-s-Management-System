import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.text.*;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

class function extends JFrame implements ActionListener
{
	JLabel l1, lfid, lfname,ldob,ldor,lcharge,lhname;
	JTextField fid1,str1,charge;	
	JComboBox day, month, year,fname,hname;
	JButton next, prev, last, first,modify, anew, delete, save, cancel, exit,home;  
	Font f3;
	int n1;
	String s;
	String glist[]={"None","Male","Female"};
	String llist[]={"None","Lawns","Hall"};
	String dlist[]={" ","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16",
							"17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	String mlist[]={" ","1","2","3","4","5","6","7","8","9","10","11","12"};
	String ylist[]={" ","2009","2010","2011","2012","2013","2014","2015"};	
	String flist[]={"None","Day marrage","night marrage","Engegment","Birth day party","Exibisition","Conference"};
	
   	 Date date;
     GregorianCalendar cal;
     String strdate;
	
	int flag=1,c=0;
	Connection cn1,con;
	ResultSet rs1,rs2,rs3;
	Statement st1, st2,st3;
	
	//Container c =getContentPane();
	function()
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
		
		f3=new Font("Arial",Font.BOLD,15);
		setLayout(null);
		l1= new JLabel(new ImageIcon("Function.png"), JLabel.CENTER);
		l1.setBounds(70,50,900,600);
	
		
		lfid=new JLabel("Function ID :");
		lfid.setBounds(335,170,150,20);
		lfid.setFont(f3);
		add(lfid);
				
		fid1= new JTextField();
		fid1.setBounds(430,170,50,20);
		//fid1.setEditable(false);
		add(fid1);
		
		lfname = new JLabel("Function Name:");
	    lfname.setBounds(315,215,150,25);
	    lfname.setFont(f3);
	    add(lfname);
	  
	     fname= new JComboBox(flist);
	   	 fname.setBounds(430,215,200,25);
		 fname.setFont(f3);			
		 add(fname);		 
	
		ldob=new JLabel("Date of Booking :");
		ldob.setBounds(300,270,140,25);
		ldob.setFont(f3);
		add(ldob);
		
		day=new JComboBox(dlist);
		day.setBounds(430,270,50,25);
		add(day);
		
		month=new JComboBox(mlist);
		month.setBounds(485,270,75,25);
		add(month);
		
		year=new JComboBox(ylist);
		year.setBounds(565,270,60,25);
		add(year);
				
		lhname=new JLabel(" Lawns/Hall :");
		lhname.setBounds(335,310,180,25);
		lhname.setFont(f3);
		add(lhname);
		
		hname= new JComboBox(llist);
		hname.setBounds(430,310,200,25);
		add(hname);
		
		lcharge=new JLabel("Lawns charges:");
		lcharge.setBounds(315,360,180,25);
		lcharge.setFont(f3);
		add(lcharge);
		
	    charge= new JTextField();
	    charge.setBounds(430,360,200,25);
		add(charge);
		
	     hname.addItemListener(new ItemListener()
	     { 
			public void itemStateChanged(ItemEvent sie)
			{
					if(sie.getStateChange()==ItemEvent.SELECTED)
					{
						if(hname.getSelectedItem().equals("None"))
							charge.setText(" ");
						else if(hname.getSelectedItem().equals("Lawns"))
							charge.setText("50000");
						else if(hname.getSelectedItem().equals("Hall"))
						    charge.setText("75000");
					}
			}
		});
		
		next = new JButton("Next");
		next.setBounds(280,400,80,25);
		next.addActionListener(this);
		add(next);
		
		prev = new JButton("Previous");
		prev.setBounds(380,400,80,25);
		prev.addActionListener(this);
		add(prev);
		
		first = new JButton("First");
		first.setBounds(480,400,80,25);
		first.addActionListener(this);
		add(first);
		
		last = new JButton("Last");
		last.setBounds(580,400,80,25);
		last.addActionListener(this);
		add(last);
		
		anew = new JButton("New");
		anew.setBounds(680,400,80,25);
		anew.addActionListener(this);
		add(anew);
		
		modify = new JButton("Modify");
		modify.setBounds(280,450,80,25);
		modify.addActionListener(this);
		add(modify);
		
		delete = new JButton("Delete");
		delete.setBounds(380,450,80,25);
		delete.addActionListener(this);
		add(delete);
		
		save = new JButton("Save");
		save.setBounds(480,450,80,25);
		save.addActionListener(this);
		add(save);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(580,450,80,25);
		cancel.addActionListener(this);
		add(cancel);
		
		exit = new JButton("Exit");
		exit.setBounds(680,450,80,25);
		exit.addActionListener(this);
		add(exit);
		
		JButton b1=new JButton("HOME");
     	b1.setBounds(420,510,150,50);
     	b1.setMnemonic('H');
     	b1.setBorder(new LineBorder(Color.black,4));
        b1.addActionListener(this);
     	add(b1);		
		add(l1);
	    try
		{
			setConnection();
			enablefields(false);
			
			if(rs1.next())
			{
				enabledbuttons(true);
				showfields();
			}
		}
		catch(Exception e) 
		{
			JOptionPane.showMessageDialog(this,"Database Missing Error!","Database Show Error",JOptionPane.ERROR_MESSAGE);
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1100,700);
		setVisible(true);
	}
	
   void setConnection()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		    con=DriverManager.getConnection("jdbc:odbc:lawns1");
		    System.out.println("Connection succesfully");
		    st1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
		    rs1=st1.executeQuery("Select * from function");
		    st2=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
		    
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"Database Connection Error!","Database Error",JOptionPane.ERROR_MESSAGE);	
		}
	}//setConnection
	
	void enabledbuttons(boolean flag)
	{
		next.setEnabled(flag);
		prev.setEnabled(flag);
		first.setEnabled(flag);
		last.setEnabled(flag);
		anew.setEnabled(flag);
		modify.setEnabled(flag);
		delete.setEnabled(flag);
		save.setEnabled(flag);
		cancel.setEnabled(flag);
		exit.setEnabled(flag);
	}//enableButton 
	
	void showfields()
	{
		try
		{
			//fid,amnt,amtr,day, month, year,fname;
			fid1.setText(rs1.getString(1));
			fname.setSelectedItem(rs1.getString(2));
			//str1.setText(rs1.getString(3));		
			day.setSelectedItem(rs1.getString(4));
			month.setSelectedItem(rs1.getString(5));
			year.setSelectedItem(rs1.getString(6));
			hname.setSelectedItem(rs1.getString(7));
			charge.setText(rs1.getString(8));
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(this,"Data Show Error!","Show Error",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}//showfields
	
	void clearfields()
	{
		//fid,amnt,amtr,day, month, year,fname;
		fid1.setText("");
		fname.setSelectedItem("None");
		//str.setText("");
		day.setSelectedItem(" ");
		month.setSelectedItem(" ");
		year.setSelectedItem(" ");								
		hname.setSelectedItem("None");
		charge.setText("");							
	}//clearfields	
	
	void enablefields(boolean flag)
	{
		//fid,amnt,amtr,day, month, year,fname;
		fid1.setEnabled(flag);
		fname.setEnabled(flag);	
		//str1.setEnabled(flag);	
		day.setEnabled(flag);
		month.setEnabled(flag);
		year.setEnabled(flag);				
	    hname.setEnabled(flag);
	    charge.setEnabled(flag);	
	}//enablefields*/
	
	public void actionPerformed(ActionEvent e1)
	{
		String s1=e1.getActionCommand();
		try
		{
			if(s1.equals("Exit"))
			{
				System.exit(0);
			}//Exit
			
			else if(s1.equals("Next"))
			{
				if(rs1.next())
				showfields();
				else
				{
					rs1.last();
					showfields();
				}
			}//Next
			else if(s1.equals("Previous"))
			{
				if(rs1.previous())
				showfields();
				else
				{
					rs1.first();
					showfields();
				}
					
			}//Previous
			
			else if(s1.equals("First"))
			{
				rs1.first();
				showfields();
			}//First
			
			else if(s1.equals("Last"))
			{
				rs1.last();
				showfields();
			}//Last
					
			else if(s1.equals("New"))
			{
				
				clearfields();
				try
	            {
	            	
	            	rs2=st2.executeQuery("select max(fid) from function");
	            	System.out.println("Number found-----");
					if(rs2.next()==true)
					{
						
						n1=rs2.getInt(1);
						n1++;
						System.out.println("Number increment to-----" + n1);
						//String m11=String.valueOf(n1);
						fid1.setText(""+n1);
					}
					else
						fid1.setText("1");
				}
	   		  	catch(SQLException e2)
	   		  	{
	   		  		JOptionPane.showMessageDialog(this,"Customer id Storing Error!!!","fid Error",JOptionPane.INFORMATION_MESSAGE);	
	   		  	}
				
				next.setEnabled(false);
				prev.setEnabled(false);
				first.setEnabled(false);
				last.setEnabled(false);
				modify.setEnabled(false);
				delete.setEnabled(false);
				
				enablefields(true);
				
				day.setSelectedItem(" ");
				month.setSelectedItem(" ");
				year.setSelectedItem(" ");
				//str1.setEnabled(false);
				fname.setSelectedItem("None");
			    hname.setSelectedItem("None");
				
				flag=1;
			}//Add New
					
			else if(s1.equals("Cancel"))
			{
				enabledbuttons(true);
				enablefields(false);
				
			}//Cancel
			
			else if(s1.equals("Modify"))
			{
				System.out.println("Error");
				enabledbuttons(true);
				anew.setEnabled(false);
				enablefields(true);
			
				flag=2;
			}//Modify
			
			else if(s1.equals("Delete"))
			{
				try
				{
				    int id=Integer.parseInt(fid1.getText());
				    st2=cn1.createStatement();
					String qry="Delete * from function where f_id="+id;
					st2.executeUpdate(qry);
					JOptionPane.showMessageDialog(this,"RECORD IS DELETE SUCCESSFULLY","Record Deleted",JOptionPane.INFORMATION_MESSAGE);
					clearfields();
				}
				
				catch(Exception et)
				{
					JOptionPane.showMessageDialog(this,"Record does not delete","Record Delete Error",JOptionPane.ERROR_MESSAGE);
				}
			}//Delete
			if(s1.equals("HOME"))
     		{
     	  		setVisible(false);
            	main m = new main();      	
	   			m.setVisible(true);
      		}
					
			else if(s1.equals("Save"))
			{
				try
				{
					
				
					if(flag==1)
					{
						rs1.moveToInsertRow();
					}
					
					int no=Integer.parseInt(fid1.getText());						
					String f=fname.getSelectedItem().toString();
					//String d1=str1.getText();
					String d=day.getSelectedItem().toString();
					String m=month.getSelectedItem().toString();
					String y=year.getSelectedItem().toString();
					String h=hname.getSelectedItem().toString();					
				    String c=charge.getText();
				    
				    				
					rs1.updateInt(1,no);
					rs1.updateString(2,f);
					//rs1.updateString(3,d1);					
					rs1.updateString(4,d);
					rs1.updateString(5,m);
					rs1.updateString(6,y);					
					rs1.updateString(7,h);
					rs1.updateString(8,c);					
					if(flag==1)
					{
						rs1.insertRow();
						JOptionPane.showMessageDialog(this,"Adding New Record Successfully!!","Add New Record",JOptionPane.INFORMATION_MESSAGE);
						clearfields();
					}
					else
					{
						rs1.updateRow();
						rs1.refreshRow();
						JOptionPane.showMessageDialog(this,"Record Update Successfully!!","Record Update",JOptionPane.INFORMATION_MESSAGE);
					}
					enabledbuttons(true);
					enablefields(false);
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(this,"DataBase Storing Error !","Saving Error",JOptionPane.INFORMATION_MESSAGE);
				}
			}//Save
					
		}
		catch(Exception e2)
		{
			JOptionPane.showMessageDialog(this,"Form Number ERROR!","Form Number Missing",JOptionPane.WARNING_MESSAGE);	
	
		}
	}	
	
	
	public static void main(String args[])
	{
		new function();
	}
}
	
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

class customer extends JFrame implements ActionListener
{
	
	JLabel l1, lcid, lcname, lgender, laddr, lcno,  ldob,lamnt,lhname,lcharge,dob;
	JTextField cid, cname,cno,amnt,str1,charge;
	JTextArea addr;
	JComboBox day, month, year,dday, mmonth, yyear, gender,hname;
	JButton next, prev, last, first,modify, anew, delete, save, cancel, exit,home;  
	Font f3;
	int n1;
	String s;
	String glist[]={"None","Male","Female"};
	String llist[]={"None","Lawns","Hall"};
	String dlist[]={" ","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16",
							"17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	String mlist[]={" ","1","2","3","4","5","6","7","8","9","10","11","12"};
	String ylist[]={" ","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025"};

	String ddlist[]={" ","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16",
							"17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	String mmlist[]={" ","1","2","3","4","5","6","7","8","9","10","11","12"};
	String yylist[]={" ","1950","1951","1952","1953","1954","1955","1956","1957","1958","1959","1960",
						"1961","1962","1963","1964","1965","1967","1968","1969","1970 ","1971","1972",
						"1973","1974","1975","1976","1977","1978","1979","1980","1981","1982","1983",
						"1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995"};

    
     Date date;
     GregorianCalendar cal;
     String strdate;
	
	int flag=1,c=0;
	
	Connection cn1;
	ResultSet rs1,rs2,r;
	Statement st1, st2, st3;
	customer()
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
		
		l1= new JLabel(new ImageIcon("Customer.png"), JLabel.CENTER);
		l1.setBounds(70,50,900,600);
		
		JLabel dat1=new JLabel("Date :-");		
		dat1.setBounds(645,150,80,30);
		add(dat1);
       	dat1.setFont(f3);  
	    
		date=new Date();
		cal=new GregorianCalendar();
		strdate=cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+(cal.get(Calendar.YEAR));
	    
	    str1=new JTextField(strdate);    
		str1.setBounds(700,150,80,25);
	    add(str1);
       	str1.setFont(f3);    
       	
		lcid = new JLabel("Customer ID:");
	    lcid.setBounds(305,150,150,25);
	    lcid.setFont(f3);
	    add(lcid);
	  
	    cid = new JTextField();
	    cid.setBounds(400,150,50,25);
        add(cid);
		
		lcname = new JLabel("Customer Name:");
	    lcname.setBounds(280,200,150,25);
	    lcname.setFont(f3);
	    add(lcname);
	  
	    cname = new JTextField();
	    cname.setBounds(400,200,380,25);
        add(cname);
        
        cname.addKeyListener(new KeyAdapter() 
		{
        	public void keyPressed(KeyEvent EVT) 
        	{
                                
        		String value = cname.getText();
                int l = value.length();
                  if (EVT.getKeyChar() >= 'a' && EVT.getKeyChar() <= 'z' ||EVT.getKeyChar() >= 'A' && EVT.getKeyChar() <= 'Z'|| EVT.getKeyChar()=='\b'|| EVT.getKeyChar()==' '|| EVT.isShiftDown()==true)
                {
                		cname.setEditable(true);
                    
                }
                else 
                {
                    cname.setEditable(true);
                    JOptionPane.showMessageDialog((Component)null,"Enter Only Characters","Lawns system",JOptionPane.INFORMATION_MESSAGE);
             
             	}
             }
          });
          
          
        dob = new JLabel("Date Of Birth:");
	    dob.setBounds(300,250,150,25);
	    dob.setFont(f3);
	    add(dob);
	    
	    dday=new JComboBox(ddlist);
		dday.setBounds(400,250,50,25);
		add(dday);
		
		mmonth=new JComboBox(mmlist);
		mmonth.setBounds(455,250,50,25);
		add(mmonth);
		
		yyear=new JComboBox(yylist);
		yyear.setBounds(510,250,50,25);
		add(yyear);
		
		lgender=new JLabel("Gender :");
		lgender.setBounds(610,250,150,25);
		lgender.setFont(f3);
		add(lgender);
		
		gender= new JComboBox(glist);
		gender.setBounds(680,250,100,25);
		//gender.setFont(f3);			
		add(gender);
	    	    
        
        laddr = new JLabel("Address:");
	    laddr.setBounds(330,300,150,25);
	    laddr.setFont(f3);
	    add(laddr);
	    	    
	    addr = new JTextArea(12,25);
	    addr.setBounds(400,300,300,50);	    
	    addr.setBorder(BorderFactory.createLineBorder(Color.black,1));
	    add(addr);
	   
	    
	    lcno = new JLabel("Mobile No:");
	    lcno.setFont(f3);
        lcno.setBounds(320,370,150,25);
    	add(lcno);
    	
	    cno = new JTextField();
	    cno.setBounds(400,370,100,25);
        add(cno);
        
        cno.addKeyListener(new KeyAdapter() 
			{
        		public void keyPressed(KeyEvent EVT) 
        		{
                
        	               
        		String value = cno.getText();
                int l = value.length();
                if(l<10)
                {            	
                if (EVT.getKeyChar() >= '0' && EVT.getKeyChar() <= '9' ||EVT.getKeyChar()=='\b')               
                {
                	cno.setEditable(true);
                }
                else 
                {
                    cno.setEditable(true);
                    JOptionPane.showMessageDialog((Component)null,"Enter Only Numbers","Lawns system",JOptionPane.INFORMATION_MESSAGE);
             	}
          		}
             	else
             		JOptionPane.showMessageDialog((Component)null,"Enter Only 10 Numbers","Lawns system",JOptionPane.INFORMATION_MESSAGE);
          }

         	});
        
		
		ldob=new JLabel("Date of Booking :");
		ldob.setBounds(505,370,140,25);
		ldob.setFont(f3);
		add(ldob);
		
		day=new JComboBox(dlist);
		day.setBounds(630,370,40,25);
		add(day);
		
		month=new JComboBox(mlist);
		month.setBounds(680,370,40,25);
		add(month);
		
		year=new JComboBox(ylist);
		year.setBounds(730,370,50,25);
		add(year);
	
		
		next = new JButton("Next");
		next.setBounds(280,420,80,25);
		next.addActionListener(this);
		add(next);
		
		prev = new JButton("Previous");
		prev.setBounds(390,420,80,25);
		prev.addActionListener(this);
		add(prev);
		
		first = new JButton("First");
		first.setBounds(500,420,80,25);
		first.addActionListener(this);
		add(first);
		
		last = new JButton("Last");
		last.setBounds(600,420,80,25);
		last.addActionListener(this);
		add(last);
		
		anew = new JButton("New");
		anew.setBounds(700,420,80,25);
		anew.addActionListener(this);
		add(anew);
		
		modify = new JButton("Modify");
		modify.setBounds(280,470,80,25);
		modify.addActionListener(this);
		add(modify);
		
		delete = new JButton("Delete");
		delete.setBounds(400,470,80,25);
		delete.addActionListener(this);
		add(delete);
		
		save = new JButton("Save");
		save.setBounds(500,470,80,25);
		save.addActionListener(this);
		add(save);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(600,470,80,25);
		cancel.addActionListener(this);
		add(cancel);
		
		exit = new JButton("Exit");
		exit.setBounds(700,470,80,25);
		exit.addActionListener(this);
		add(exit);
		
		JButton b1=new JButton("HOME");
     	b1.setBounds(430,530,150,50);
     	b1.setMnemonic('H');
     	b1.setBorder(new LineBorder(Color.black,3));
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
		    cn1=DriverManager.getConnection("jdbc:odbc:lawns1");
		    System.out.println("Connection succesfully");
		    st1=cn1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
		    rs1=st1.executeQuery("Select * from customer");
		    st2=cn1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
		    
		    
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
			//cid, cname,addr,cno,amnt,hname,day, month, year, gender
			cid.setText(rs1.getString(1));
			cname.setText(rs1.getString(2));
			addr.setText(rs1.getString(3));
			cno.setText(rs1.getString(4));
			gender.setSelectedItem(rs1.getString(5));
			day.setSelectedItem(rs1.getString(6));
			month.setSelectedItem(rs1.getString(7));
			year.setSelectedItem(rs1.getString(8));	
			str1.setText(rs1.getString(9));	
			dday.setSelectedItem(rs1.getString(10));
			mmonth.setSelectedItem(rs1.getString(11));
			yyear.setSelectedItem(rs1.getString(12));	
											
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(this,"Data Show Error!","Show Error",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}//showfield
	
	void clearfields()
	{
				//cid, cname,addr,cno,amnt,hname,day, month, year, gender
				cid.setText("");
				cname.setText("");
				addr.setText("");
				cno.setText("");
				gender.setSelectedItem("None");
				day.setSelectedItem(" ");
				month.setSelectedItem(" ");
				year.setSelectedItem(" ");
				dday.setSelectedItem(" ");
				mmonth.setSelectedItem(" ");
				yyear.setSelectedItem(" ");
	}//clearfields	
	
	void enablefields(boolean flag)
	{
		//cid, cname,addr,cno,amnt,hname,day, month, year, gender
		cid.setEnabled(flag);
		cname.setEnabled(flag);
		addr.setEnabled(flag);
		cno.setEnabled(flag);
		gender.setEnabled(flag);
		day.setEnabled(flag);
		month.setEnabled(flag);
		year.setEnabled(flag);		
		dday.setEnabled(flag);
		mmonth.setEnabled(flag);
		yyear.setEnabled(flag);
	
		str1.setEnabled(flag);	
		
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
	            
			        			
			        			rs2=st2.executeQuery("select max(CID) from customer");
								
								if(rs2.next()==true)
								{
									System.out.println("Number found-----");
									n1=Integer.parseInt(rs2.getString(1));
									n1++;
									System.out.println("Number increment to-----" + n1);
									String m11=String.valueOf(n1);
									cid.setText(m11);
								}
								else
									cid.setText("1");
							
	   		  			}
	   		  			catch(SQLException e2)
	   		  			{
	   		  				JOptionPane.showMessageDialog(this,"Customer id Storing Error!!!","c_id Error",JOptionPane.INFORMATION_MESSAGE);	
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
				str1.setEnabled(false);
				gender.setSelectedItem("None");
				
				
				cname.requestFocus();
				flag=1;
				/*}
				catch(SQLException se1)
				{}*/
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
				cname.requestFocus();
				flag=2;
				
			}//Modify
			
			else if(s1.equals("Delete"))
			{
				try
				{
					
				    int id=Integer.parseInt(cid.getText());
				    st2=cn1.createStatement();
					String qry="Delete * from customer where c_id="+id;
					
					st2.executeUpdate(qry);
					JOptionPane.showMessageDialog(this,"RECORD IS DELETE SUCCESSFULLY","Record Deleted",JOptionPane.INFORMATION_MESSAGE);
					clearfields();
					
				}
					
				catch(Exception et)
				{
					JOptionPane.showMessageDialog(this,"Record does not delete","Record Delete Error",JOptionPane.ERROR_MESSAGE);
				}
			}//Delete
					
			else if(s1.equals("Save"))
			{
				try
				{
					
					
					if(flag==1)
					{
						rs1.moveToInsertRow();
					}
					int no=Integer.parseInt(cid.getText());
					String name=cname.getText();
					String adr=addr.getText();
					String cn=cno.getText();
					String g=gender.getSelectedItem().toString();
					String d=day.getSelectedItem().toString();
					String m=month.getSelectedItem().toString();
					String y=year.getSelectedItem().toString();
				//	String e=hname.getSelectedItem().toString();

					String d1=str1.getText();
					
					rs1.updateInt(1,no);
					rs1.updateString(2,name);
					rs1.updateString(3,adr);
					rs1.updateString(4,cn);
					rs1.updateString(5,g);
					rs1.updateString(6,d);
					rs1.updateString(7,m);
					rs1.updateString(8,y);					
							
					rs1.updateString(9,d1);
					
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
		
		if(s1.equals("HOME"))
     	  {
     	  	setVisible(false);
            main m = new main();      	

	   		m.setVisible(true);
     	  }
		
	}

	public static void main(String args[])
	{
		new customer();
	}
}
		
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.text.*;
class worker1 extends JFrame implements ActionListener
{
	
	JLabel l1,lwid, lwname,lgender, laddr, lwno,lsal, ltype;
	JTextField wid, wname,wno,sal;
	JTextArea addr;
	JComboBox  gender, type;
	JButton next, prev, last, first,modify, anew, delete, save, cancel, exit;  
	Font f3;
	int n1;
	String s;
	String glist[]={"None","Male","Female"};
	String wlist[]={"None","Decorationteam","Wellcometeam","Bandteam","clarinateteam","Ketorsteam"};
	
	int flag=1,c=0;
	
	Connection cn;
	ResultSet rs1,rs2,rs3;
	Statement st1, st2,st3;
	
	
	worker1()
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
		
		l1= new JLabel(new ImageIcon("Worker.png"), JLabel.CENTER);
		l1.setBounds(70,50,900,600);
		
		
		lwid = new JLabel("Worker ID:");
	    lwid.setBounds(305,150,150,25);
	    lwid.setFont(f3);
	    add(lwid);
	  
	    wid = new JTextField();
	    wid.setBounds(400,150,50,25);
        add(wid);
        
        lwname = new JLabel("Worker Name:");
	    lwname.setBounds(280,190,150,25);
	    lwname.setFont(f3);
	    add(lwname);
	  
	    wname = new JTextField();
	    wname.setBounds(400,190,350,25);
        add(wname);
        
        wname.addKeyListener(new KeyAdapter() 
		{
        	public void keyPressed(KeyEvent EVT) 
        	{
                                
        		String value = wname.getText();
                int l = value.length();
                if (EVT.getKeyChar() >= 'a' && EVT.getKeyChar() <= 'z' ||EVT.getKeyChar() >= 'A' && EVT.getKeyChar() <= 'Z'|| EVT.getKeyChar()=='\b'|| EVT.isShiftDown()==true)               
                {
                	wname.setEditable(true);
                    
                }
                else 
                {
                    wname.setEditable(true);
                    JOptionPane.showMessageDialog((Component)null,"Enter Only Characters","Lawns system",JOptionPane.INFORMATION_MESSAGE);
             
             	}
             }
          });
        
        laddr = new JLabel("Address:");
	    laddr.setBounds(315,230,150,25);
	    laddr.setFont(f3);
	    add(laddr);
	    	    
	    addr = new JTextArea(12,25);
	    addr.setBounds(400,230,250,50);	    
	    addr.setBorder(BorderFactory.createLineBorder(Color.black,1));
	    add(addr);
	   
	    
	    lwno = new JLabel("Mobile No:");
	    lwno.setFont(f3);
        lwno.setBounds(305,300,150,25);
    	add(lwno);
    	
	    wno = new JTextField();
	    wno.setBounds(400,300,150,25);
        add(wno);
        
        wno.addKeyListener(new KeyAdapter() 
			{
        		public void keyPressed(KeyEvent EVT) 
        		{
                
        	               
        		String value = wno.getText();
                int l = value.length();
                if(l<10)
                {            	
                if (EVT.getKeyChar() >= '0' && EVT.getKeyChar() <= '9' ||EVT.getKeyChar()=='\b')               
                {
                	wno.setEditable(true);
                }
                else 
                {
                    wno.setEditable(true);
                    JOptionPane.showMessageDialog((Component)null,"Enter Only Numbers","Lawns system",JOptionPane.INFORMATION_MESSAGE);
             	}
          		}
             	else
             		JOptionPane.showMessageDialog((Component)null,"Enter Only 10 Numbers","Lawns system",JOptionPane.INFORMATION_MESSAGE);
          }

         	});
        
        lgender=new JLabel("Gender :");
		lgender.setBounds(553,300,150,25);
		lgender.setFont(f3);
		add(lgender);
		
		gender= new JComboBox(glist);
		gender.setBounds(620,300,130,25);
		gender.setFont(f3);			
		add(gender);
		
		ltype=new JLabel("Worker Type:");
		ltype.setBounds(520,350,200,25);
		ltype.setFont(f3);
		add(ltype);
		
		type= new JComboBox(wlist);
		type.setBounds(620,350,130,25);
	//	type.setFont(f3);			
		add(type);

		
		lsal=new JLabel("Salary :");
		lsal.setBounds(330,350,180,25);
		lsal.setFont(f3);
		add(lsal);
		
		sal= new JTextField();
		sal.setBounds(400,350,100,25);
		add(sal);
		
		sal.addKeyListener(new KeyAdapter() 
			{
        		public void keyPressed(KeyEvent EVT) 
        		{
                
        	               
        		String value = wno.getText();
                int l = value.length();
                   	
                if (EVT.getKeyChar() >= '0' && EVT.getKeyChar() <= '9' ||EVT.getKeyChar()=='\b')               
                {
                	sal.setEditable(true);
                }
                else 
                {
                   sal.setEditable(true);
                    JOptionPane.showMessageDialog((Component)null,"Enter Only Numbers","lawns system",JOptionPane.INFORMATION_MESSAGE);
             	}
          		}
             
          

         	});
         	
		
		next = new JButton("Next");
		next.setBounds(280,420,80,25);
		add(next);
		
		prev = new JButton("Previous");
		prev.setBounds(380,420,80,25);
		add(prev);
		
		first = new JButton("First");
		first.setBounds(480,420,80,25);
		add(first);
		
		last = new JButton("Last");
		last.setBounds(580,420,80,25);
		add(last);
		
		anew = new JButton("New");
		anew.setBounds(680,420,80,25);
		add(anew);
		
		modify = new JButton("Modify");
		modify.setBounds(280,470,80,25);
		add(modify);
		
		delete = new JButton("Delete");
		delete.setBounds(380,470,80,25);
		add(delete);
		
		save = new JButton("Save");
		save.setBounds(480,470,80,25);
		add(save);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(580,470,80,25);
		add(cancel);
		
		exit = new JButton("Exit");
		exit.setBounds(680,470,80,25);
		add(exit);
		
		JButton b1=new JButton("HOME");
     	b1.setBounds(420,530,150,50);
     	b1.setMnemonic('H');
     	b1.setBorder(new LineBorder(Color.black,2));
        b1.addActionListener(this);
     	add(b1);
		
		next.addActionListener(this);
		prev.addActionListener(this);
		first.addActionListener(this);
		last.addActionListener(this);
		anew.addActionListener(this);
		modify.addActionListener(this);
		delete.addActionListener(this);
		save.addActionListener(this);
		cancel.addActionListener(this);
		exit.addActionListener(this);
     
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
		    cn=DriverManager.getConnection("jdbc:odbc:lawns1");
		    
		    st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
		    rs1=st1.executeQuery("Select * from worker");
		    System.out.println("Connection succesfully");
		    st2=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		  	st3=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		    
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"Database Connection Error!"+e,"Database Error",JOptionPane.ERROR_MESSAGE);	
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
			
			wid.setText(rs1.getString(1));
			wname.setText(rs1.getString(2));
			addr.setText(rs1.getString(3));	
			wno.setText(rs1.getString(4));			
			gender.setSelectedItem(rs1.getString(5));
			sal.setText(rs1.getString(6));
			type.setSelectedItem(rs1.getString(7));
											
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(this,"Data Show Error!","Show Error",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}//showfield
	
	void clearfields()
	{
				
				wid.setText("");
				wname.setText("");
				addr.setText("");			
				wno.setText("");
				gender.setSelectedItem("None");
				sal.setText("");
				type.setSelectedItem("None");							
			
		
	}//clearfields	
	
	void enablefields(boolean flag)
	{
		
		wid.setEnabled(flag);
		wname.setEnabled(flag);	
		addr.setEnabled(flag);	
		gender.setEnabled(flag);	
		wno.setEnabled(flag);	
		sal.setEnabled(flag);	
		type.setEnabled(flag);
	}//enablefields
	
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
	    			rs3=st3.executeQuery("select max(w_id) from worker");
					
					if(rs3.next()==true)
					{
						n1=rs3.getInt(1);
						n1++;
						wid.setText(""+n1);
					}
					else
						wid.setText("1");
				
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
				gender.setSelectedItem("None");	
				type.setSelectedItem("None");		
				wname.requestFocus();
				flag=1;
				
			}//Add New
					
			else if(s1.equals("Cancel"))
			{
				enabledbuttons(true);
				enablefields(false);
				
			}//Cancel
			
			else if(s1.equals("Modify"))
			{
				enabledbuttons(true);
				anew.setEnabled(false);
				enablefields(true);
				wname.requestFocus();
				flag=2;
				
			}//Modify
			
			else if(s1.equals("Delete"))
			{
				try
				{
					
				    int id=Integer.parseInt(wid.getText());
				    st2=cn.createStatement();
					String qry="Delete * from worker where w_id="+id;
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
					int no=Integer.parseInt(wid.getText());
					String name=wname.getText();
					String adr=addr.getText();
					String wn=wno.getText();				
					String g=gender.getSelectedItem().toString();				
					int am=Integer.parseInt(sal.getText());
					String t=type.getSelectedItem().toString();
					
					rs1.updateInt(1,no);
					rs1.updateString(2,name);					
					rs1.updateString(3,adr);
					rs1.updateString(4,wn);			
					rs1.updateString(5,g);				
					rs1.updateInt(6,am);
					rs1.updateString(7,t);
					
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
		new worker1();
	  
	}
}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.lang.String.*;
import javax.swing.JOptionPane;
class menu extends JFrame implements ActionListener, ItemListener
{

	ImageIcon i1;
	JComboBox iname, siname;
	JTextField rate,iid, total,qty,amount;
	JTable tab;
	JLabel liid,liname,lsiname,lrate,ltotal,lamount,lqty,img;
	JButton add,save ,modify ,exit,home,del;
	JScrollPane jsp;
	DefaultTableModel model;
	String ilist[]={"None","Rice","Sweet","Puri","Dal","Suki Bhaji","Bhaji","Farsan","Other"};
	
    Font f3=new Font("Arial",Font.BOLD,15);		
	String[] colhead={"Sr.No","Item","Rate"};     
    Object[][] data=new Object[0][3];
    
    int k=1,sum=0,ww,aa,tt, rcount,i;
    int flag=1,c=0,row,col,rcnt;
	int ar=0,as=0,ap=0,ad=0,asb=0,ab=0,af=0,ao=0;
	int n1;
	
	Connection con;
	ResultSet rs1,rs2,rs3;
	Statement st1, st2,st3;
	
	menu()
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
       	
       	
       	i1= new ImageIcon("ser.png");
    	JLabel l1=new JLabel(i1);
		l1.setBounds(100,50,600,500);
		
		
		img = new JLabel(new ImageIcon("1.2.jpg"));
    	img.setBounds(0,0,900,700);
     			
		
	    liid=new JLabel("Item ID:");
		liid.setBounds(235,110,100,20);
		liid.setFont(f3);
		add(liid);
		
		iid=new JTextField();
		iid.setBounds(300,110,50,20);
		add(iid);
		
	    liname=new JLabel("Item:");
		liname.setBounds(255,150,100,20);
		liname.setFont(f3);
		add(liname);
						
	    
	    siname=new JComboBox();
		siname.setBounds(300,180,100,20);
		siname.setEnabled(true);
		add(siname);
		
		siname.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent sie)
			{
					if(sie.getStateChange()==ItemEvent.SELECTED)
					{
						if(siname.getSelectedItem().equals("Masala Rice"))
							rate.setText("18");
						else if(siname.getSelectedItem().equals("Sada"))
							rate.setText("10");
						else if(siname.getSelectedItem().equals("JiraRice"))
							rate.setText("12");
						else if(siname.getSelectedItem().equals("Pulav"))
							rate.setText("15");
						else if(siname.getSelectedItem().equals("Gulabjamb"))
							rate.setText("15");
						else if(siname.getSelectedItem().equals("Jilebi"))
							rate.setText("10");
						else if(siname.getSelectedItem().equals("Shrikhand"))
							rate.setText("20");
						else if(siname.getSelectedItem().equals("RajaRani"))
							rate.setText("20");
	    	    	    else if(siname.getSelectedItem().equals("Balushai"))
							rate.setText("10");
						else if(siname.getSelectedItem().equals("Sadhi Puri"))
							rate.setText("5");
						else if(siname.getSelectedItem().equals("Masala Puri"))
							rate.setText("7");
						else if(siname.getSelectedItem().equals("Palak Puri"))
							rate.setText("8");
						else if(siname.getSelectedItem().equals("Chapati"))
							rate.setText("3");
						else if(siname.getSelectedItem().equals("Sadhi dal"))
							rate.setText("10");
						else if(siname.getSelectedItem().equals("Dal tadaka"))
							rate.setText("12");
						else if(siname.getSelectedItem().equals("Dal fry"))
							rate.setText("15");
						else if(siname.getSelectedItem().equals("Bengan masala"))
							rate.setText("15");
						else if(siname.getSelectedItem().equals("Aalu flower"))
							rate.setText("10");
						else if(siname.getSelectedItem().equals("Bhendi masala"))
							rate.setText("10");
						else if(siname.getSelectedItem().equals("Aalu matar"))
							rate.setText("12");
						else if(siname.getSelectedItem().equals("Chana masala"))
							rate.setText("15");
						else if(siname.getSelectedItem().equals("Mataki usal"))
							rate.setText("18");
						else if(siname.getSelectedItem().equals("Sadhi Bhajji"))
							rate.setText("5");
						else if(siname.getSelectedItem().equals("Kanda Bhajji"))
							rate.setText("7");
						else if(siname.getSelectedItem().equals("Palak Bhajji"))
							rate.setText("7");
						else if(siname.getSelectedItem().equals("Mattha"))
							rate.setText("10");
						else if(siname.getSelectedItem().equals("Pan Masala"))
							rate.setText("5");
						else if(siname.getSelectedItem().equals("Kulfi"))
							rate.setText("5");
						else if(siname.getSelectedItem().equals("Panipuri"))
							rate.setText("10");
						else if(siname.getSelectedItem().equals("Aachar"))
							rate.setText("4");
						else if(siname.getSelectedItem().equals("Papad"))
							rate.setText("3");
						else if(siname.getSelectedItem().equals("Koshmbir"))
							rate.setText("5");
					}
			}
				
				
		});
	    iname= new JComboBox(ilist);
		iname.setBounds(300,150,100,20);
		add(iname);
				
		iname.addItemListener(new ItemListener()
		{
      		public void itemStateChanged(ItemEvent ie)
      		{
        		
        		try
        		{
        			if(ie.getStateChange()==ItemEvent.SELECTED)
        			{
        				
        				if(iname.getSelectedItem().equals("Rice"))
        				{
        					if(ar<2)
        					{
	        					rate.setText("");
	        					siname.setEnabled(true);
	        					rate.setEnabled(false);
		       					siname.removeAllItems();
	        					siname.addItem("None");
	        					siname.addItem("Masala Rice");
	        					siname.addItem("JiraRice");
	        					siname.addItem("Sada");
	        					siname.addItem("Pulav");
	        					ar++;
	        				}
	        				else
	        				{
	        					siname.setEnabled(false);
	        					rate.setEnabled(false);
	        				}
        				}
        				else if(iname.getSelectedItem().equals("Sweet"))
        				{
        					if(as<2)
        					{
	        					rate.setText("");
	        					siname.setEnabled(true);
	        					rate.setEnabled(true);
	        					siname.removeAllItems();
	        					siname.addItem("None");
	        					siname.addItem("Gulabjamb");
	        					siname.addItem("Jilebi");
	        					siname.addItem("Shrikhand");
	        					siname.addItem("RajaRani");
	        					siname.addItem("Balushai");
	        					as++;
	        				}
	        				else
	        				{
	        					siname.setEnabled(false);
	        					rate.setEnabled(false);
	        				}
        				}
        				
        				else if(iname.getSelectedItem().equals("Puri"))
        				{
        					
        					if(ap<2)
        					{
        						rate.setText("");
	        					siname.setEnabled(true);
	        					rate.setEnabled(true);
	        					siname.removeAllItems();
	        					siname.addItem("None");
	        					siname.addItem("Sadhi Puri");
	        					siname.addItem("Masala Puri");
	        					siname.addItem("Palak puri");
	        					siname.addItem("Chapati");
	        					ap++;
	        				}
	        				else
	        				{
	        					siname.setEnabled(false);
	        					rate.setEnabled(false);
	        				}
        				}
        				
        				else if(iname.getSelectedItem().equals("Dal"))
        				{
        					if(ad<1)
        					{
	        					rate.setText("");
	        					siname.setEnabled(true);
	        					rate.setEnabled(true);
	        					siname.removeAllItems();
	        					siname.addItem("None");
	        					siname.addItem("Sadhi dal");
	        					siname.addItem("Dal tadaka");
	        					siname.addItem("Dal fry");
	        					ad++;
	        				}
	        				else
	        				{
	        					siname.setEnabled(false);
	        					rate.setEnabled(false);
	        				}
        				}
        				
        				else if(iname.getSelectedItem().equals("Suki Bhaji"))
        				{
        					if(asb<2)
        					{
	        					rate.setText("");
	        					siname.setEnabled(true);
	        					rate.setEnabled(true);
	        					siname.removeAllItems();
	        					siname.addItem("None");
	        					siname.addItem("Bengan masala");
	        					siname.addItem("Aalu flower");
	        					siname.addItem("Bhendi masala");
	        					asb++;
	        				}
	        				else
	        				{
	        					siname.setEnabled(false);
	        					rate.setEnabled(false);
	        				}	        					
        				}
        				
        				else if(iname.getSelectedItem().equals("Bhaji"))
        				{
        					if(ab<2)
        					{
        						rate.setText("");
	        					siname.setEnabled(true);
	        					rate.setEnabled(true);
	        					siname.removeAllItems();
	        					siname.addItem("None");
	        					siname.addItem("Aalu matar");
	        					siname.addItem("Chana masala");
	        					siname.addItem("Mataki usal");
	        					ab++;
	        				}
	        				else
	        				{
	        					siname.setEnabled(false);
	        					rate.setEnabled(false);
	        				}
	        				       					
        				}
        				
        				else if(iname.getSelectedItem().equals("Farsan"))
        				{
        					
        					if(af<2)
        					{
        						rate.setText("");
	        					siname.setEnabled(true);
	        					rate.setEnabled(true);
	        					siname.removeAllItems();
	        					siname.addItem("None");
	        					siname.addItem("Sadhi Bhajji");
	        					siname.addItem("Kanda Bhajji");
	        					siname.addItem("Palak Bhajji"); 
	        					siname.addItem("Papad"); 
	        					siname.addItem("Aachar"); 
	        					siname.addItem("Koshimbir"); 
	        					af++;
	        				}
                            else
	        				{
	        					siname.setEnabled(false);
	        					rate.setEnabled(false);
	        				}
	        				
	        				
        				}
        			
        				else if(iname.getSelectedItem().equals("Other"))
        				{
        					if(ao<2)
        					{
	        					rate.setText("");
	        					siname.setEnabled(true);
	        					rate.setEnabled(true);
	        					siname.removeAllItems();
	        					siname.addItem("None");
	        					siname.addItem("Mattha");
	        					siname.addItem("Kulfi");
	        					siname.addItem("Panipuri");
	        					siname.addItem("Pan Masala"); 
	        					ao++;
	        				}
	        				else
	        				{
	        					siname.setEnabled(false);
	        					rate.setEnabled(false);
	        				}  					
        				}
        			}
        		}
        		catch(Exception m1)
        		{
//        			JOptionPane.showMessageDialog(this, "fsdf","Item Selection",JOptionPane.ERROR_MESSAGE);
        		}
        	}	   	
      	
    	});


		lsiname=new JLabel("Sub Item:");
		lsiname.setBounds(220,180,100,20);
		lsiname.setFont(f3);
		add(lsiname);
			
		lrate=new JLabel("Rate:");
		lrate.setBounds(250,210,80,20);
		lrate.setFont(f3);
		add(lrate);
			
		rate=new JTextField();
		rate.setEnabled(false);
		rate.setBounds(300,210,100,20);
		add(rate);
			
		
	    add = new JButton("Add");
		add.setBounds(450,150,80,30);
		add.addActionListener(this);
		add(add);
		
	
		del = new JButton("Delete");
		del.setBounds(450,210,80,30);
		del.addActionListener(this);
		add(del);
		
		model = new DefaultTableModel(data,colhead);
		tab=new JTable(model);
		tab.addMouseListener(new MouseAdapter()   
		{   
			public void mouseClicked(MouseEvent evt)   
			{   
								   	
			   		col=tab.getSelectedColumn();
					row = tab.getSelectedRow();   
					//amount.setText((String)tab.getModel().getValueAt(row,col));  
			}   
		});  
		
     	jsp=new JScrollPane(tab);
        jsp.setBounds(170,270,390,120);
        jsp.setEnabled(true);
        add(jsp);
        
     	ltotal=new JLabel("Total :");
		ltotal.setBounds(410,400,80,20);
		ltotal.setFont(f3);
		add(ltotal);
		
		total=new JTextField();
		total.setBounds(460,400,100,20);
		add(total);

     	lqty=new JLabel("Qty :");
		lqty.setBounds(420,430,80,20);
		lqty.setFont(f3);
		add(lqty);
		
		qty=new JTextField();
		qty.setBounds(460,430,100,20);
		add(qty);
     	
     	lamount=new JLabel("Net Amount :");
		lamount.setBounds(360,460,120,20);
		lamount.setFont(f3);
		add(lamount);
		
		amount=new JTextField();
		amount.setBounds(460,460,100,20);
		add(amount);
     	
        save = new JButton("Save");
		save.setBounds(250,500,80,30);
		save.addActionListener(this);
		add(save);
		
		modify = new JButton("Calculate");
		modify.setBounds(355,500,80,30);
		modify.addActionListener(this);
		add(modify);
     	
        exit = new JButton("Exit");
		exit.setBounds(460,500,80,30);
		exit.addActionListener(this);
		add(exit);
		
		JButton b1=new JButton("HOME");
     	b1.setBounds(350,570,150,50);
     	b1.setMnemonic('H');
     	b1.setBorder(new LineBorder(Color.black,4));
        b1.addActionListener(this);
     	add(b1);
	
		add(l1);
		//	add(img);
		setConnection();
		try
		{

    			
    			rs3=st3.executeQuery("select max(mid) from menu");
				
				if(rs3.next()==true)
				{
					System.out.println("Number found-----");
					n1=rs3.getInt(1);
					n1++;
					System.out.println("Number increment to-----" + n1);
					
					iid.setText(""+n1);
				}
				else
					iid.setText("1");
			
		}
		catch(SQLException e2)
		{
			JOptionPane.showMessageDialog(this,"Customer id Storing Error!!!","c_id Error",JOptionPane.INFORMATION_MESSAGE);	
		}		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900,700);
		setVisible(true);
	}
	
	void clearfields()
	{
		iid.setText("");
		iname.setSelectedItem("None");
		siname.setSelectedItem("None");
		rate.setText("");
		total.setText("");
		qty.setText("");
		amount.setText("");
		rcnt=tab.getRowCount();
		for(i=0;i<=rcnt;i++)
		{
			model.removeRow(0);
		}
	}
	
	void setConnection()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		    con=DriverManager.getConnection("jdbc:odbc:lawns1");
		    System.out.println("Connection succesfully");
		    st1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
		    rs1=st1.executeQuery("Select * from menu");
		    st2=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		    st3=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
		    
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"Database Connection Error!","Database Error",JOptionPane.ERROR_MESSAGE);	
		}
	}//setConnection
	
	 
	public void actionPerformed(ActionEvent ae)
	{
		String st=ae.getActionCommand();
		try
		{
			if(st.equals("Exit"))
			{
				System.exit(0);
	    	}
	    	
	    	else if(st.equals("Add"))
	    	{
	    		if(iname.getSelectedItem().equals("None"))
	    		{
	    			JOptionPane.showMessageDialog(this, "Please Select Item!!","Item Selection Error", JOptionPane.ERROR_MESSAGE);
	    		}
	    		if(siname.getSelectedItem().equals("None"))
	    		{
	    			JOptionPane.showMessageDialog(this, "Please Select SubItem!!","SubItem Selection Error", JOptionPane.ERROR_MESSAGE);
	    		}
	    		String i=siname.getSelectedItem().toString();
		    	String j=rate.getText();
		    	int n=Integer.parseInt(rate.getText());
		    	sum=sum+n;
		    	model.addRow(new Object[]{k++,i,j});
		    	iname.setSelectedItem("None");
		    	siname.setSelectedItem("None");
		    	rate.setText("");
		    	total.setText(""+sum);
			    	
		    }
		    	
			
	   
	    	else if(st.equals("Delete"))
	    	{
	    		
	    		try
	    		{
		    		ww = Integer.parseInt((String)tab.getModel().getValueAt(row,2));
		    		System.out.println("Error"+ww);
		    		aa = Integer.parseInt(total.getText());
		    		tt = aa - ww;
		    		total.setText("");
		    		total.setText((String.valueOf(tt)));
		    		model.removeRow(row);
		    	}
		    	catch(Exception d)
		    	{
		    		System.out.println(""+d);
		    	}
		    }
	    	
	    	else if(st.equals("Calculate"))
	    	{
	    		int q,t,a;
	    		q=Integer.parseInt(qty.getText());
	    		t=Integer.parseInt(total.getText());
	    		a=q*t;
	    		amount.setText(""+a);
	    		
	    	}
	    	
	    	else if(st.equals("Save"))
			{
				try
				{
					
						  		
					int m=Integer.parseInt(iid.getText());
					rcount=tab.getRowCount();
					System.out.println(rcount);
					int j,k=0;
					
					String[] si=new String[rcount];
					for(i=0;i<rcount;i++)
					{
						si[k]=((String)tab.getModel().getValueAt(i,1));
						k++;
					}
					
					k=0;
					String ri[]=new String[rcount];
					for(i=0;i<rcount;i++)
					{
						ri[k++]=((String)tab.getModel().getValueAt(i,2));
					}
					k=0;
					for(j=2;j<rcount+2;j++)
					{
						rs1.updateString(j,si[k]);
						k++;
					}
					int tt=Integer.parseInt(total.getText());
					int q=Integer.parseInt(qty.getText());
					int am=Integer.parseInt(amount.getText());
						
					rs1.updateInt(1,m);
					rs1.updateInt(14,tt);
					rs1.updateInt(15,q);					
					rs1.updateInt(16,am);
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
//					enabledbuttons(true);
//					enablefields(false);
					
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(this,"DataBase Storing Error !","Saving Error",JOptionPane.INFORMATION_MESSAGE);
				}
			}//Save
	    }
	    catch(Exception e4)
	    {
	    	System.out.println(" "+e4);
	    	//JOptionPane.showMessageDialog((Component)null,"Rate ","Lawns",JOptionPane.INFORMATION_MESSAGE);
	    }
	    
	    if(st.equals("HOME"))
     	  {
     	  	setVisible(false);
            main m = new main();      	

	   		m.setVisible(true);
     	  }
	    
     }
     
	public void itemStateChanged(ItemEvent i1)
	{
		
		
	}
	public static void main(String args[])
	{
		new menu();
	}
}
		
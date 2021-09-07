import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.lang.String.*;
import javax.swing.JOptionPane;
class ser extends JFrame implements ActionListener, ItemListener
{

	ImageIcon i1;
	JComboBox  sname;
	JTextField rate,iid, total,qty,amount;
	JTable tab;
	JLabel liid,lsiname,lrate,ltotal,lamount,lqty,img;
	JButton add,save,exit,del,cal;
	JScrollPane jsp;
	DefaultTableModel model;
     
    
    Font f3=new Font("Arial",Font.BOLD,15);		
	  
	String slist[]={"None","Maharaja chairs","Band","Akshada","Horse","Bramhan","Room","Buffe eating","Generater",
					" Rose","Banner","Sound system"," Guest Chairs"};
	String[] colhead={"Sr.No","Item","Rate","qty","total"};   
    Object[][] data=new Object[0][5];
    
    int k=1,sum=0,ww,aa,tt, rcount,i;
    int flag=1,c=0,row,col,rcnt;
	int ar=0,as=0,ap=0,ad=0,asb=0,ab=0,af=0,ao=0;
	int n1;
	
	Connection con;
	ResultSet rs1,rs2,rs3;
	Statement st1, st2,st3;
	
	ser()
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
       
       
        
     img = new JLabel(new ImageIcon("123.jpg"));
     img.setBounds(0,0,800,700);
	
		setLayout(null);
		i1= new ImageIcon("Service.png");
    	JLabel l1=new JLabel(i1);
		l1.setBounds(100,50,600,500);
		
		
	    liid=new JLabel("Service ID:");
		liid.setBounds(220,100,130,20);
		liid.setFont(f3);
		add(liid);
		
		
		iid=new JTextField();
		iid.setBounds(310,100,50,20);
		add(iid);
		
	    lsiname=new JLabel("service Name:");
		lsiname.setBounds(200,140,120,20);
		lsiname.setFont(f3);
		add(lsiname);
						
	    
	    sname=new JComboBox(slist);
		sname.setBounds(310,140,150,20);
		sname.setEnabled(true);
		add(sname);
		
		

		sname.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent sie)
			{
					if(sie.getStateChange()==ItemEvent.SELECTED)
					{
						if(sname.getSelectedItem().equals("None"))
							rate.setText(" ");
							
						else if(sname.getSelectedItem().equals("Maharaja chairs"))
							rate.setText("500");
							
						else if(sname.getSelectedItem().equals("Band"))
							rate.setText("3500");
							
						else if(sname.getSelectedItem().equals("Akshada"))
							rate.setText("5");
							
						else if(sname.getSelectedItem().equals("Horse"))
							rate.setText("1500");
							
						else if(sname.getSelectedItem().equals("Bramhan"))
							rate.setText("2000");
							
						else if(sname.getSelectedItem().equals("Room"))
							rate.setText("200");
							
						else if(sname.getSelectedItem().equals("Buffe eating"))
							rate.setText("1000");
							
	    	    	    else if(sname.getSelectedItem().equals("Genrater"))
							rate.setText("1000");
							
						else if(sname.getSelectedItem().equals("Rose"))
							rate.setText("5");
							
						else if(sname.getSelectedItem().equals("Banner"))
							rate.setText("300");
							
						else if(sname.getSelectedItem().equals("Sound system"))
							rate.setText("1000");
							
						else if(sname.getSelectedItem().equals("Guest chairs"))
							rate.setText("2000");
							
						
					}
			}
				
				
		});
			
		lrate=new JLabel("Rate:");
		lrate.setBounds(265,180,80,20);
		lrate.setFont(f3);
		add(lrate);
			
		rate=new JTextField();
		rate.setEnabled(false);
		rate.setBounds(310,180,100,20);
		add(rate);
		
		lqty=new JLabel("Qty :");
		lqty.setBounds(270,220,80,20);
		lqty.setFont(f3);
		add(lqty);
		
		qty=new JTextField();
		qty.setBounds(310,220,100,20);
		add(qty);
		
		ltotal=new JLabel("Total :");
		ltotal.setBounds(260,260,80,20);
		ltotal.setFont(f3);
		add(ltotal);
		
		total=new JTextField();
		total.setBounds(310,260,100,20);
		add(total);
		
		cal = new JButton("Calculate");
		cal.setBounds(470,150,80,25);
		cal.addActionListener(this);
		add(cal);
			
		
	    add = new JButton("Add");
		add.setBounds(470,200,80,30);
		add.addActionListener(this);
		add(add);
		
		
		del = new JButton("Delete");
		del.setBounds(470,250,80,30);
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
        jsp.setBounds(170,300,410,120);
        jsp.setEnabled(true);
        add(jsp);
        
     	     	     	
     	lamount=new JLabel("Net Amount :");
		lamount.setBounds(360,430,120,20);
		lamount.setFont(f3);
		add(lamount);
		
		amount=new JTextField();
		amount.setBounds(460,430,120,20);
		add(amount);
     	
        save = new JButton("Save");
		save.setBounds(250,480,80,30);
		save.addActionListener(this);
		add(save);
		
	 	
        exit = new JButton("Exit");
		exit.setBounds(470,480,80,30);
		exit.addActionListener(this);
		add(exit);
		
		JButton b1=new JButton("Home");
     	b1.setBounds(360,480,80,30);
     	b1.setMnemonic('H');
     //	b1.setBorder(new LineBorder(Color.black,4));
        b1.addActionListener(this);
     	add(b1);
		
		add(l1);
		
		setConnection();
		
		try
		{

    			
    			rs2=st2.executeQuery("select max(sid) from service1");
				
				if(rs2.next()==true)
				{
					System.out.println("Number found-----");
					n1=rs2.getInt(1);
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
		setSize(800,700);
		setVisible(true);
		
	//	add(img);
	}
	
	void clearfields()
	{
		iid.setText("");
		sname.setSelectedItem("None");
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
		    rs1=st1.executeQuery("Select * from service1");
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
	    	
	    	else if(st.equals("Calculate"))
	    	{
																	    	
			    int n=Integer.parseInt(rate.getText());
		    	int q=Integer.parseInt(qty.getText());
		    	int s=q*n;
		    	sum=sum+s;
		    	total.setText(""+s);										
	    		
	    	}
	    	
	    	
	    	else if(st.equals("Add"))
	    	{
	    		
	    		if(sname.getSelectedItem().equals("None"))
	    		{
	    			JOptionPane.showMessageDialog(this, "Please Select SubItem!!","SubItem Selection Error", JOptionPane.ERROR_MESSAGE);
	    		}
	    		String i=sname.getSelectedItem().toString();
		    	String j=rate.getText();
		    	String qt=qty.getText();
		    	String t=total.getText();		    	
		    	model.addRow(new Object[]{k++,i,j,qt,t});
		    	sname.setSelectedItem("None");
		    	rate.setText("");
		    	qty.setText("");
		    	total.setText("");
		    	amount.setText(""+sum);
			    	
		    }
		    	
			
	   
	    	else if(st.equals("Delete"))
	    	{
	    		
	    		try
	    		{
		    		ww = Integer.parseInt((String)tab.getModel().getValueAt(row,4));
		    	//	System.out.println("Error"+ww);
		    		aa = Integer.parseInt(amount.getText());
		    		tt = aa - ww;
		    		amount.setText("");
		    		amount.setText((String.valueOf(tt)));
		    		model.removeRow(row);
		    	}
		    	catch(Exception d)
		    	{
		    		System.out.println(""+d);
		    	}
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
					for(j=2;j<rcount+2;j++)
					{
						rs1.updateString(j,si[k]);
						k++;
     				}
	
					int am=Integer.parseInt(amount.getText());
						
					rs1.updateInt(1,m);
					rs1.updateInt(14,am);

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
	    	
	    }
	    
	    if(st.equals("Home"))
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
		new ser();
	}
}		
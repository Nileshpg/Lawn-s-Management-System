import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class Report1 extends JFrame implements ActionListener
{
          
     JTabbedPane tb=new JTabbedPane();
     JPanel cust_report,monthwise_report,func_report,worker_report;
     JTable table,tab1;
     JScrollPane jsp1;
     JTextField tmonth,tyear;
     String[] colhead={"Id","Customer Name","Address","ContactNo","Gender","DOB","Regis.Date"};
     String[] colhead1={"Fid","Function name","Regis.Date", "Function Date","Lawns/Hall","Total"};
	 String[] colhead2={"Fid","Function name","Regis.Date", "Function Date","Lawns/Hall","Total"};
     String[] colhead3={"report_no","worker Id", "worker Name", "Address", "Contact_no","salary"};


     Object[][] data=new Object[30][7];
     Object[][] data1=new Object[30][6];
     Object[][] data2=new Object[30][6];
     Object[][] data3=new Object[30][7];
     
     Connection cn1;
     Statement stmt,stmt1,stmt2,st;
     ResultSet rs1,rs2,rs3,rs;
     int cnt1=0,cnt2=0,cnt3=0;
     
     
     int[] count1=new int[65];
     
     public Report1()
     {
     	super("REPORT....!");
     	try
    	{
    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
     	
     	Container c=getContentPane();
     	c.setLayout(null);
     	//c.setBackground(Color.CYAN);
     	
     	cust_report=new JPanel();
     	cust_report.setBounds(100,100,768,100);
     	cust_report.setLayout(null);
     	
     	monthwise_report=new JPanel();
		monthwise_report.setLayout(null);
		
	    func_report	=new JPanel();
	    func_report.setLayout(null);	
	
        worker_report=new JPanel();
    	worker_report.setLayout(null);
     	
     	tb.addTab("cust_report",cust_report);     	
     	tb.addTab("monthwise-report",monthwise_report);
     	tb.addTab("func_REPORT",func_report);
     	tb.addTab("worker_report",worker_report);
		tb.setBounds(120,80,600,550);
     	tb.setBorder(new LineBorder(Color.RED,5));
     	c.add(tb);
     	
     	JButton b1=new JButton("HOME");
     	b1.setBounds(395,640,150,50);
     	b1.setMnemonic('H');
     	b1.setBorder(new LineBorder(Color.black,3));
     	b1.addActionListener(this);
     	c.add(b1);
     	 
     	Font f=new Font("Cambria",4,35);
     	JLabel l2=new JLabel("REPORTS");
     	l2.setFont(f);
     	 
     	l2.setBounds(350,20,500,60);
     	c.add(l2);
     	//PANEL Customer 	
     	try
     	{
     	    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	       	cn1=DriverManager.getConnection("jdbc:odbc:lawns1");
	       	System.out.println("Connection successfully");
	       	stmt=cn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	       	//stmt2=cn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE); 
	       	rs1=stmt.executeQuery("select * from customer");
	       	   
	       	    while(rs1.next())
	       	    {
	       	    	int j=0;
	       	    	for(int i=0;i<30;i++)
	       	    	{
	       	    		data[i][j]=rs1.getString(1);
	       	    		j++;
	       	    		data[i][j]=rs1.getString(2);
	       	    		j++;
	       	    		data[i][j]=rs1.getString(3);
	       	    		j++;
	       	    		data[i][j]=rs1.getString(4);
	       	    		j++;
	       	    		data[i][j]=rs1.getString(5);
	       	    		j++;
	       	    		data[i][j]=rs1.getString(6)+"/"+rs1.getString(7)+"/"+rs1.getString(8);
	       	    		j++;
	       	    		data[i][j]=rs1.getString(9);
	       	    		j-=6;
	       	    		rs1.next();
	       	    	}
	       	    	
	       	    }
	       	   //rs1.close();
     	   }//try
       	   catch(SQLException kl)
       	   {
       	   	    System.out.println(kl);
       	   }
       	   catch(ClassNotFoundException nm)
       	   {
       	   		System.out.println(nm);
       	   }
       	   catch(ArrayIndexOutOfBoundsException out)
       	   {
       	   		System.out.println(out);
       	   }  
       	   
       	   //PANEL Worker
       	   try
     	{
     	    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	       	cn1=DriverManager.getConnection("jdbc:odbc:lawns1");
	       	System.out.println("Connection successfully");
	       	st=cn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	       	rs=st.executeQuery("select * from worker");
	       	   
	       	    while(rs.next())
	       	    {
	       	    	int j=0;
	       	    	for(int i=0;i<30;i++)
	       	    	{
	       	    		data3[i][j]=i+1;
	       	    		j++;
	       	    		data3[i][j]=rs.getString(1);
	       	    		j++;
	       	    		data3[i][j]=rs.getString(2);
	       	    		j++;
	       	    		data3[i][j]=rs.getString(3);
	       	    		j++;
	       	    		data3[i][j]=rs.getString(4);
	       	    		j++;
	       	    		data3[i][j]=rs.getString(6);
	       	    		j-=5;
	       	    		rs.next();
	       	    	}
	       	    	
	       	    }
	       	   //rs1.close();
     	   }//try
       	   catch(SQLException kl)
       	   {
       	   	    System.out.println(kl);
       	   }
       	   catch(ClassNotFoundException nm)
       	   {
       	   		System.out.println(nm);
       	   }
       	   catch(ArrayIndexOutOfBoundsException out)
       	   {
       	   		System.out.println(out);
       	   }  
     	
       	   
       	   
     	
          JTable tab=new JTable(data,colhead);
     	  JScrollPane jsp=new JScrollPane(tab);
          jsp.setBounds(10,30,565,545);
      	  cust_report.add(jsp); 
     	   
     	  tab1=new JTable(data1,colhead1);
          JScrollPane jsp1=new JScrollPane(tab1);
          jsp1.setBounds(10,60,565,545);
          monthwise_report.add(jsp1);
          
          JTable tab2=new JTable(data2,colhead2);
          JScrollPane jsp2=new JScrollPane(tab2);
          jsp2.setBounds(10,30,565,545);
          func_report.add(jsp2);
          
          
          JTable tab3=new JTable(data3,colhead3);
     	  JScrollPane jsp3=new JScrollPane(tab3);
          jsp3.setBounds(10,30,565,545);
      	  worker_report.add(jsp3); 
     	  
     	  Font f1=new Font("Cambria",1,16);    
    	 /* JLabel l1=new JLabel("Total cust_report="+cnt1);          	;
          l1.setBounds(25,3,300,30);
          l1.setFont(f1);
          cust_report.add(l1);*/
		  
		  
		  JLabel l3=new JLabel("Enter Month:");
	      l3.setBounds(25,5,100,20);
	      l3.setFont(f1);
	      monthwise_report.add(l3);
	      
	      tmonth= new JTextField();
	      tmonth.setBounds(135,6,70,20);
	      monthwise_report.add(tmonth);
	      
	      JButton bmonth=new JButton("Search");
	      bmonth.setBounds(215,5,80,25);
	      bmonth.addActionListener(this);
	      monthwise_report.add(bmonth);
	      
	      JLabel lyear=new JLabel("Enter Year:");
	      lyear.setBounds(25,5,100,20);
	      lyear.setFont(f1);
	      func_report.add(lyear);
	      
	      tyear= new JTextField();
	      tyear.setBounds(115,6,100,20);
	      func_report.add(tyear);
	      
	      JButton byear=new JButton("Search Year");
	      byear.setBounds(225,5,100,25);
	      byear.addActionListener(this);
	      func_report.add(byear);
	     
	      /*JLabel l4=new JLabel("Total func_report="+cnt3);
	      l4.setBounds(25,3,300,30);
	      l4.setFont(f1);
	      func_report.add(l4);*/
	      
	      /*JLabel l5=new JLabel("Total month_report="+cnt2);
	      l5.setBounds(25,3,300,30);
	      l5.setFont(f1);
	      worker_report.add(l5);*/
	
		  setSize(1024,768);
		  setVisible(true);
   }
     
     public void actionPerformed(ActionEvent ae)
     {
     	  String st=ae.getActionCommand();
     	  
     	  if(st.equals("HOME"))
     	  {
     	  	setVisible(false);
            main m = new main();
  	   		m.setVisible(true);
     	  }
     	  else if(st.equals("Search"))
     	  {
     	  	String mm=tmonth.getText();
     	  	try
     		{
	     	    stmt1=cn1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
		       	rs2=stmt1.executeQuery("select * from function where fmonth='"+mm+"'");
	       	   	
	       	    while(rs2.next())
	       	    {
	       	    	System.out.println(""+mm);
	       	    	
	       	    	for(int k=0;k<30;k++)
	       	    	{
	       	    		for(int m=0;m<6;m++)
	       	    		{
	       	    			data1[k][m]="";
	       	    		}
	       	    	}
	       	    	int j=0;
	       	    	for(int i=0;i<30;i++)
	       	    	{
	       	    		data1[i][j]=rs2.getString(1);
	       	    		j++;
	       	    		data1[i][j]=rs2.getString(2);
	       	    		j++;
	       	    		data1[i][j]=rs2.getString(3);
	       	    		j++;
	       	    		data1[i][j]=rs2.getString(4)+"/"+rs2.getString(5)+"/"+rs2.getString(6);
	       	    		j++;
	       	    		data1[i][j]=rs2.getString(7);
	       	    		j++;
	       	    		data1[i][j]=rs2.getString(8);
	       	    		j-=5;
	       	    		rs2.next();
	       	    	}
	       	    }
	       	   //rs2.close();
     	   }//try
       	   catch(SQLException kl)
       	   {
       	   	    System.out.println(kl);
       	   }
       	   catch(ArrayIndexOutOfBoundsException out)
       	   {
       	   		System.out.println(out);
       	   }   
     	 }
     	 
     	 else if(st.equals("Search Year"))
     	  {
     	  	String yy=tyear.getText();
     	  	try
     		{
	     	    stmt2=cn1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
		       	rs3=stmt2.executeQuery("select * from function where f_year='"+yy+"'");
	       	   	
	       	    while(rs3.next())
	       	    {
	       	    	System.out.println(""+yy);
	       	    	
	       	    	for(int k=0;k<30;k++)
	       	    	{
	       	    		for(int m=0;m<6;m++)
	       	    		{
	       	    			data2[k][m]="";
	       	    		}
	       	    	}
	       	    	int j=0;
	       	    	for(int i=0;i<30;i++)
	       	    	{
	       	    		data2[i][j]=rs3.getString(1);
	       	    		j++;
	       	    		data2[i][j]=rs3.getString(2);
	       	    		j++;
	       	    		data2[i][j]=rs3.getString(3);
	       	    		j++;
	       	    		data2[i][j]=rs3.getString(4)+"/"+rs3.getString(5)+"/"+rs3.getString(6);
	       	    		j++;
	       	    		data2[i][j]=rs3.getString(7);
	       	    		j++;
	       	    		data2[i][j]=rs3.getString(8);
	       	    		j-=5;
	       	    		rs3.next();
	       	    	}
	       	    }
	       	   //rs2.close();
     	   }//try
       	   catch(SQLException kl)
       	   {
       	   	    System.out.println(kl);
       	   }
       	   catch(ArrayIndexOutOfBoundsException out)
       	   {
       	   		System.out.println(out);
       	   }   
     	 }
     	  
     }
     public static void main(String m[])
     {
     	new Report1();
     }

} 
    
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.event.*;
import java.text.*;
import javax.swing.border.*;
class main extends JFrame implements ActionListener

 {
   
   Container c1;
   JButton regi,rule,function,menu,cust,worker,workerb,serv,report,b8,can;
   JLabel img2,wel;
   Font f0 = new Font("Monotype Corsiva",Font.ITALIC,20 );
   Font f1 = new Font("Arial",Font.BOLD+Font.ITALIC,50);
    
    main()
    {
      super(" MainFrame  ");
      try
    	{
    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
      c1 = getContentPane();
      c1.setLayout(null);     
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //setResizable(false);
      img2 = new JLabel(new ImageIcon("man.jpg"));
      img2.setBounds(120,90,800,450);
      //img2.setBorder(BorderFactory.createLineBorder(Color.red,2));
          
      
      wel = new JLabel("WEL-COME ");
      wel.setBounds(370,40,370,60);
      wel.setForeground(Color.red);
      wel.setFont(f1);
      c1.add(wel); 

      regi = new JButton("Registration");
      regi.setMnemonic('R');
      regi.setBounds(120,250,200,40);    
      c1.add(regi);
      regi.setBorder(BorderFactory.createLineBorder(Color.red,2));
      
      rule = new JButton("Rules");
      rule.setMnemonic('R');
      rule.setBounds(120,200,200,40);  
      c1.add(rule);
      rule.setBorder(BorderFactory.createLineBorder(Color.red,2));
      
      
      function = new JButton("Function Details");
      function.setMnemonic('F');
      function.setBounds(120,300,200,40);    
      c1.add(function);
      function.setBorder(BorderFactory.createLineBorder(Color.red,2));
        
      serv = new JButton("Services");
      serv.setMnemonic('S');
      serv.setBounds(120,350,200,40);    
      c1.add(serv);
      serv.setBorder(BorderFactory.createLineBorder(Color.red,2));
        

      menu = new JButton("Menu");
      menu.setMnemonic('M');
      menu.setBounds(120,400,200,40);  
      c1.add(menu);
      menu.setBorder(BorderFactory.createLineBorder(Color.red,2)); 
      
      
      worker = new JButton("Worker Details");
      worker.setMnemonic('W');
      worker.setBounds(700,200,200,40);   
      c1.add(worker);
      worker.setBorder(BorderFactory.createLineBorder(Color.red,2));
      
      can = new JButton("Registration cancel");
      can.setMnemonic('R');
      can.setBounds(700,250,200,40);
      c1.add(can);
      can.setBorder(BorderFactory.createLineBorder(Color.red,2));
      
      cust = new JButton("Customer Bill");
      cust.setMnemonic('C');
      cust.setBounds(700,300,200,40);
      c1.add(cust);
      cust.setBorder(BorderFactory.createLineBorder(Color.red,2));
      
      workerb = new JButton("Worker Bill");
      workerb.setMnemonic('W');
      workerb.setBounds(700,350,200,40);  
      c1.add(workerb);
      workerb.setBorder(BorderFactory.createLineBorder(Color.red,2)); 
      
      report = new JButton("Report");
      report.setMnemonic('R');
      report.setBounds(700,400,200,40);  
      c1.add(report); 
      report.setBorder(BorderFactory.createLineBorder(Color.red,2));   
      
      b8=new JButton("Exit");
      b8.setBounds(440,470,120,50);
      b8.setFont(f0);  
      b8.setForeground(Color.black);  
      c1.add(b8);
      b8.setBorder(BorderFactory.createLineBorder(Color.red,2));     
      
      regi.addActionListener(this);
      rule.addActionListener(this);
      function.addActionListener(this);
      worker.addActionListener(this);
      cust.addActionListener(this);
      workerb.addActionListener(this);
      serv.addActionListener(this); 
      menu.addActionListener(this); 
      report.addActionListener(this);
      can.addActionListener(this);
      b8.addActionListener(this);  
     
     
      setSize(1100,700);
      //setResizable(false);
      setVisible(true);
       c1.add(img2); 
      
    }

   public void actionPerformed(ActionEvent ae)
    {
	 if(ae.getSource() == regi)//Registation
	 {
	    
	    new customer();
	    dispose();
        
	    
      }
      
      if(ae.getSource() == rule)//Rule
	 {
       	new rule();
	    dispose();
      }

      if(ae.getSource() == function)//Fun-Details
	 {
	    
        new function();
	    dispose();
      }

      if(ae.getSource() == serv)//Services
	 {
        new ser();
	    dispose();
      }
      
       if(ae.getSource() == menu)//Menu
	 {
	   
        new menu();
         dispose();
	   
     }
      
       if(ae.getSource() == worker)//worker
	 {
	    
        new worker1();
	    dispose();
      }
      
       if(ae.getSource() == cust)//cbill
	 {
	   
       new custbill();
       dispose();	
     }
     
     if(ae.getSource() == workerb)//wbill
	 {
	    
        new workerbill();
        dispose();
	    
     }
     
     if(ae.getSource() == report)//wbill
	 {
	    
        new Report1();
        dispose();
	   
     }
     
     if(ae.getSource() == can)//wbill
	 {
	    
        new cancel();
        dispose();
	   
     }
     
     
     if(ae.getSource() == b8)//wbill
	 {
	    
        System.exit(0);
	    
     }
     
    
}
   public static void main(String args[])
   {
    main m = new main();
    m.setVisible(true);
    //m.setSize(800,600);
   }     
 } 
package action;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.util.*;
class DV extends JFrame
{
	DV()
	{
		PaintPanel p=new PaintPanel();

	Vector<Vector> data=new Vector<Vector>();
	int j=0;
	int z=1;
	String abc="H";
	for(int x=0;x<p.i;x++)
	{
		   //j++;
		Vector<String> row=new Vector<String>();
		 row.add("H"+x);
          		 
		 for(int u=0;u<p.i;u++)
		 {	
			 //row.add("H"+x);
			 for(int v=0;v<p.i;v++)
		     {
			  
             // row.add(p.route[u][v]); 
		     }
		  }
		 
          data.add(row);	

	}
	//hello
	

 //  data.add(row);

   Vector<String> cols=new Vector<String>();
  	for(int x=0;x<p.i;x++)
        cols.add("H"+x);
   
   JTable tab=new JTable(data,cols);

   JTableHeader head=tab.getTableHeader();


   Container c=getContentPane();
   c.setLayout(new BorderLayout());
   c.add("North",head);
   c.add("Center",tab);


      this.setSize(200,200);
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

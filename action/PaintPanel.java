package action;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.util.Date;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;


class node
{
int  dist[]=new int[50];
int  from[]=new int[50];
}

public class PaintPanel extends JPanel// implements ActionListener
{
 JFrame f=new JFrame("Creating Link");
 
 JPanel panel=new JPanel(new GridLayout(4,6));

	private int pointCount=0;	
	private Point points[]=new Point[1000];
	String name,l1,l2;
	int i=0,j=1,k,link1,link2;
	int hostport=5000;
        public static int x[]=new int[51];
        public static int y[]=new int [51];
        int linklist1[]=new int[51];
        int linklist2[]=new int[51];
        public int cost[][]=new int[51][51];
        public static int route[][]=new int[51][51];
        public static int via[][]=new int[51][51];
        public  int u,v;
	Date d=new Date();	
	public static HostContainer nt = new HostContainer();
	JButton link,dlink;
        JButton  rtable;
	JLabel lb1;
	JLabel lb2,lb3,lb4;
	JTextField txt1;
	JTextField txt2;

	public PaintPanel()
	{	

lb1=new JLabel("First Node",JLabel.LEFT);
panel.setLayout(new FlowLayout());
panel.add(lb1);

txt1=new JTextField();
txt1.setColumns(15);
txt1.setVisible(true);
panel.add(txt1);

lb2=new JLabel("Second Node");
panel.setLayout(new FlowLayout());
panel.add(lb2);

txt2=new JTextField();
txt2.setColumns(15);
txt2.setVisible(true);
panel.add(txt2);


     link = new JButton("Link");
     dlink = new JButton("Delete Link");
     rtable = new JButton("routing table");
     link.setSize(10, 10);
     dlink.setSize(10, 10);
     rtable.setSize(10, 10);

     link.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e) 
{
    if(l1!="NULL" && l2!="NULL")
       {
          l1 = txt1.getText();
          l2=txt2.getText();
          link1=Integer.parseInt(l1);
          link2=Integer.parseInt(l2);
          linklist1[j]=link1;
          linklist2[j]=link2;
          j++;
           }
  }
});



      dlink.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e) 
{
    if(l1!="NULL" && l2!="NULL")
       {
          l1 = txt1.getText();
          l2=txt2.getText();
          link1=Integer.parseInt(l1);
          link2=Integer.parseInt(l2);

          
           for(k=1;k<=j;k++)
               {
                
                if(linklist1[k]==link1 && linklist2[k]==link2||linklist1[k]==link2 && linklist2[k]==link1)
                   {
                        linklist1[k]=0;
                        linklist2[k]=0;
                        cost[link1][link2]=9999;
                        cost[link2][link1]=9999;
                   }
                }
}
  }
});


rtable.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e) 
{
    

     routing();
JFrame rf=new JFrame("Routing Table");
 
 
 JPanel rpanel=new JPanel(new GridLayout(4,6));
 

lb3=new JLabel("Routing Table",JLabel.RIGHT);
rpanel.setLayout(new FlowLayout());
rpanel.add(lb3);

lb4=new JLabel("9999 specifies no link",JLabel.LEFT);
lb4.setForeground(Color.RED);
rpanel.setLayout(new FlowLayout());
rpanel.add(lb4);

JTextArea ta=new JTextArea("",50,100);
String st="";
 String newline = "\n";
String newtab = "\t";


for(u=1;u<=i;u++)
     {
         
         for(v=1;v<=i;v++)
          {
             
            st=Integer.toString(route[u][v]);
			ta.append(st+newtab);

            }

             ta.append(newline);

      }


rpanel.add(ta);
 rf.setVisible(true);

rf.pack();
rf.add(rpanel);    
          
  }
});
panel.add(link);
panel.add(dlink);
panel.add(rtable);

f.setVisible(true);

f.pack();
f.add(panel);



		addMouseListener(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent event)
					{
						if (i<50)
						{
							long host_id=new Date().getTime();
							Point point = event.getPoint();   //Get the Point Position
							name="Host";
							i++;
							name=name+String.valueOf(i);	
							hostport++;    					//Assign unique port for every Host
							Host node = new Host(point, name,hostport,host_id);  //Create Object and pass arguments through constructor
							nt.addHost(node);   //Add object of Host class to HostContainer Class
							repaint();
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Allowed only 50 Receiver Hosts");
						}
					}
				}		
		);
	}	
	public void paint(final Graphics g)
	{
		Graphics2D gg = (Graphics2D) g;
		gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.RED);
		gg.clearRect(0, 0, getWidth(), getHeight());
		Iterator treeTraverse = nt.getIterator();  
		Vector v = nt.getVector();		
		while (treeTraverse.hasNext())
		{	
			Host host = (Host) treeTraverse.next();			
			gg.setColor(Color.RED);
                                      x[i]=host.position.x - 10;
                                      y[i]=host.position.y - 10;
			gg.fillOval(host.position.x - 10, host.position.y - 10, 18, 18);
			gg.setColor(Color.BLACK);
			gg.drawString("[" +host.name +"]", host.position.x - 20,host.position.y + 20);

                         gg.setColor(Color.GREEN);
                         for(k=1;k<=j;k++)
          
                        gg.drawLine(x[linklist1[k]],y[linklist1[k]],x[linklist2[k]],y[linklist2[k]]);

		}
	}

public void routing()
{
 
   node rt[]=new node[20]; 
	  for(int b=0;b<20;b++) 
		   rt[b]=new node();
   for(u=1;u<=i;u++)
    for(v=1;v<=i;v++)
      if(u==v)
         cost[u][v]=0;
      else
         cost[u][v]=9999;

     //calculating distance

    for(int a=1;a<=j;a++) //iterating edges
    {
           
         int p=linklist1[a];
         int q=linklist2[a];
		 
         cost[p][q]=(int)Math.sqrt(Math.pow((x[p]-x[q]), 2) + Math.pow((y[p]-y[q]), 2));; 
         cost[q][p]=(int)Math.sqrt(Math.pow((x[p]-x[q]), 2) + Math.pow((y[p]-y[q]), 2));; 
     }
//routing table

for(u=1;u<=i;u++)
         for(v=1;v<=i;v++)
         {
                              
             
             rt[u].dist[v]=cost[u][v];
              
             rt[u].from[v]=v;
             PaintPanel.via[u][v]=v;
          }
int count;

     do
     {
          count=0;
          for(u=1;u<=i;u++)
            for(v=1;v<=i;v++)
              for(k=1;k<=i;k++)
                  if(rt[u].dist[v]>cost[u][k]+rt[k].dist[v])
                  {
                       rt[u].dist[v]=rt[u].dist[k]+rt[k].dist[v];
                       rt[u].from[v]=k;
                       PaintPanel.via[u][v]=k;
                       count++;
                   }
       }while(count!=0);
for(u=1;u<=i;u++)
     {
         
         for(v=1;v<=i;v++)
          {
             
             PaintPanel.route[u][v]=rt[u].dist[v];
             System.out.print(PaintPanel.via[u][v]+"\t");
            }
              System.out.println("\n");
      }
new SenderForm();

 }// end of routing

}// end of PaintPanel
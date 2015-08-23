package action;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JOptionPane;
import java.io.File;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class SenderForm extends JFrame implements ActionListener
{
	private JLabel label1;
	private JLabel label2;
        private JLabel label3;
	private JTextField txt1;
	private JButton browse,submit,cancel;
	private JComboBox sou,des;
	private String name,sname,dname;
	private GridBagLayout layout;
	private GridBagConstraints cons;
	private HostContainer containerOBJ;
	int host_count,u,k,l,m=0,j;
        int[] p=new int[30];
	
	int i=-1;	
	public SenderForm()
	{	
		super("Select File to Traversing..");

                 

		containerOBJ=PaintPanel.nt;
		layout=new GridBagLayout();
		setLayout(layout);
		cons=new GridBagConstraints();
		host_count=containerOBJ.size();
		final String source_nodes[]=new String[host_count];
		Iterator treeTraverse = containerOBJ.getIterator();
		while (treeTraverse.hasNext())
		{
			i++;
			Host host = (Host) treeTraverse.next();
			source_nodes[i]=host.name;
		}




                                


		label1=new JLabel("Select Source Node");
		label1.setToolTipText("Source");
		cons.fill=GridBagConstraints.HORIZONTAL;
		addComponent(label1,0,4,5,5);

		sou=new JComboBox(source_nodes);
		sou.setMaximumRowCount(5);
		cons.fill=GridBagConstraints.HORIZONTAL;
		addComponent(sou,0,12,5,5);

		label2=new JLabel("Select Destination Node");
		label2.setToolTipText("Destination");
		cons.fill=GridBagConstraints.HORIZONTAL;
		addComponent(label2,15,4,5,5);

                label3=new JLabel();
                label3.setForeground(Color.RED);
		label3.setToolTipText("path");
		cons.fill=GridBagConstraints.HORIZONTAL;
		addComponent(label3,150,2,5,5);


		des=new JComboBox(source_nodes);
		des.setMaximumRowCount(5);
		cons.fill=GridBagConstraints.HORIZONTAL;
		addComponent(des,15,12,5,5);		
		txt1=new JTextField();
		txt1.setToolTipText("TextBox");
		cons.fill=GridBagConstraints.HORIZONTAL;
		addComponent(txt1,20,2,5,5);
		browse=new JButton("Browse");
		cons.fill=GridBagConstraints.HORIZONTAL;
		addComponent(browse,20,10,5,5);
		browse.addActionListener(this);
		submit=new JButton("Submit");
		cons.fill=GridBagConstraints.HORIZONTAL;



		addComponent(submit,30,2,2,2);
		
                


               submit.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent ea) 
                                         {
                                            

                                             if (txt1.getText().equals(""))
				  	{
				  		JOptionPane.showMessageDialog(null,"Select FileName you want to Transfer");
				  	}
				  	else if (sou.getSelectedItem().equals(des.getSelectedItem()))
				  	{
					  	JOptionPane.showMessageDialog(null,"Please Select Different Destination Name");  
				  	}
				  	else if(host_count<3)
				  	{
				  		JOptionPane.showMessageDialog(null,"Number of Receiver should be Greater 10");
				  	}
				  	else if(host_count>50)
				  	{
				  		JOptionPane.showMessageDialog(null,"Number of Receiver should be Greater 50");
				  	}
                                        
				  	else
				  	{
				  		BufferTransfer bufferTransfer=new BufferTransfer();
				  		sname=(String)sou.getSelectedItem();
				  		dname=(String)des.getSelectedItem();
                                                label3.setText("Path:");
                                                String s1=sname.substring(4);
                                                String d1=dname.substring(4);
                                                int s=Integer.parseInt(s1);
                                                int d=Integer.parseInt(d1);
                                                u=s;
                                                k=d;
                                                l=d;
                                                  if(PaintPanel.via[u][k]!=k)
                                                           path();
                                                  else{label3.setText(label3.getText()+s+"----"+d);}
                                               if(PaintPanel.route[s][d]==9999){
                                                          
                                                            label3.setText(label3.getText()+"No Link");
                                                            JOptionPane.showMessageDialog(null,"No Link between selected nodes");}
                                                 
                                                
                                                    else{ 
                                                          
                                                             
				  		try {
							new TransferObject(sname,dname,txt1.getText(),bufferTransfer);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						
                                            }
                                          }
				  	}

                                           }//end of action performed
                                                            });//end of link actionlistener






		
		cancel=new JButton("Cancel");
		cons.fill=GridBagConstraints.HORIZONTAL;
		addComponent(cancel,30,10,5,5);
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");
		setVisible(true);
		setSize(400,400);



       

	}



   public void path()
  {
       
       label3.setText(label3.getText()+u);
     while(PaintPanel.via[u][k]!=k)
          {
            u=PaintPanel.via[u][k];
            p[m]=u;
            m++;
          }//end of while
           
            for(j=0;j<m;j++)
              { 
                 
                 label3.setText(label3.getText()+"---"+p[j]);
              }//end of for
                
                label3.setText(label3.getText()+"---"+k);
   }//end of path


	private void addComponent(Component component,int row,int column,int width,int height)
	{
		cons.gridx=column;
		cons.gridy=row;
		cons.gridwidth=width;
		cons.gridheight=height;
		layout.setConstraints(component, cons);
		add(component);
	}
		public void actionPerformed(ActionEvent ae) 
		  {
			  if (ae.getSource()==browse)
			  {  
				  JFileChooser chooser = new JFileChooser();
				    chooser.setCurrentDirectory(new File("."));
				    chooser.setFileFilter(new FileFilter() 
						{
					 public boolean accept(File f) 
				        {
						 return f.getName().toLowerCase().endsWith(".jpg")|| f.isDirectory();
				        }
				        public String getDescription() {
				          return "JPEG Files";
				        }
						});//end of chooser,setfilter
				int returnVal = chooser.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) 
				{
			         name = chooser.getSelectedFile().getPath();
			         File filename = chooser.getSelectedFile();
			         txt1.setText(filename.getAbsolutePath());
			         System.out.println("Cover Image" +name);
			     }
				  
			  } //End Code For browse
			  if(ae.getSource().equals(cancel))
				{
					setVisible(false);
				}
			  
			  
		  }//end of action performed


		
	}






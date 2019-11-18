
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;




import java.awt.Toolkit;
import java.awt.Dimension;

public class loginwindow extends JFrame implements ActionListener {
	 JLabel userlabel,passlabel,imagePanel,label,choicelabel;
     JTextField usertext;
     JPasswordField password;
     JButton denglu,tuichu;
     ImageIcon title_pic,background;
     JComboBox comboBox;
     PreparedStatement pstm;	   
     ResultSet rs;     
     DataBaseManager db = new DataBaseManager();
     Container c; 
     static String  username,passwordSTR;
     static Statement stmt;
     MainFrame mainFrame = new MainFrame();
    public loginwindow()
    {
 	   super("ͼ�����ϵͳ��¼");
 	   init();	  
 	   setSize(445,275);
 	   //��¼������Ļ����
 	   int windowWidth = this.getWidth(); //��ô��ڿ�
 	   int windowHeight = this.getHeight(); //��ô��ڸ�
 	   Toolkit kit = Toolkit.getDefaultToolkit(); //���幤�߰�
 	   Dimension screenSize = kit.getScreenSize(); //��ȡ��Ļ�ĳߴ�
 	   int screenWidth = screenSize.width; //��ȡ��Ļ�Ŀ�
 	   int screenHeight = screenSize.height; //��ȡ��Ļ�ĸ�
 	   this.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//���ô��ھ�����ʾ

 	   setResizable(false);
 	   setDefaultCloseOperation(EXIT_ON_CLOSE);
       setVisible(true);
    }
    public void init()
	{
		this.setLayout(null);
		
		Font font1=new Font("�����п�",0,18);
		
		title_pic=new ImageIcon(getClass().getResource("/com/student/images/title.png"));
		
		this.setIconImage(title_pic.getImage());
	
		userlabel=new JLabel("�û���");
		passlabel=new JLabel("��    ��");
		usertext=new JTextField("");
		password=new JPasswordField("");
		choicelabel=new JLabel("��    ɫ");
		String[] ids = new String[]{"����Ա","����"};
		comboBox = new JComboBox(ids);
		
     	denglu=new JButton("��½");
		tuichu=new JButton("�˳�");
		 

        label = new JLabel(background);  
         

        // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��  
        JPanel imagePanel = (JPanel)this.getContentPane();  
        imagePanel.setOpaque(false);  
        // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����  
        getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));  
        
		userlabel.setBounds(120, 55, 60, 20);
		usertext.setBounds(180, 52, 80, 23);
		passlabel.setBounds(120, 95, 60, 20);
		password.setBounds(180, 92, 80, 23);
		password.setEchoChar('*');
		choicelabel.setBounds(120, 135, 60, 20);
		comboBox.setBounds(180, 130, 80, 23);
		denglu.setBounds(125, 175, 60, 30);
		tuichu.setBounds(200, 175, 60, 30);
		
		userlabel.setFont(font1);	
		passlabel.setFont(font1);
		choicelabel.setFont(font1);
		
		//��Ӽ�������
		denglu.addActionListener(this);
        tuichu.addActionListener(this);
      
		add(userlabel);
		add(usertext);
		add(passlabel);
		add(password);
		add(choicelabel);
		add(comboBox);
		
		add(denglu);
		add(tuichu);
		
	}
	public void actionPerformed(ActionEvent e) {	
		if(e.getSource()==denglu)
		{		 	      		
		//��֤�û��������붼û����������
			DataBaseManager1();
			 char[] password1 = password.getPassword();	      
			 username = usertext.getText().trim();
		      passwordSTR = new String(password1);
			  if (usertext.getText().trim().equals("")) 
		      {
		        JOptionPane.showMessageDialog(null, "�û�������Ϊ��!");
		        return;
		      }
			if(!usertext.getText().trim().equals("")&&password.getPassword().equals(""))
		     {
				JOptionPane.showMessageDialog(getParent(), "����д���룡",
					"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
				return ;
		     }
			if((usertext.getText()).equals("")&&!password.getPassword().equals(""))
		     {
				JOptionPane.showMessageDialog(getParent(), "����д�û�����",
					"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
				return ;
		     }
			String choice=comboBox.getSelectedItem().toString();//ѡ��
			if(choice.equals("����Ա"))
			{ 
				String strSQL;
				strSQL = "select * from UserTable where UserName='" +
						usertext.getText().trim() + "'and Password='" +
						passwordSTR + "'";
				db.getResult(strSQL);
				rs = db.getResult(strSQL);
				boolean isExist = false;
				try {
					isExist = rs.first();
                	}
                catch (SQLException sqle) 
                {
                	System.out.println(sqle.toString());
                }                     
          	  if(!isExist)
               {           		  
                  JOptionPane.showMessageDialog(null, "���ǷǷ��û�!");  
                                                                        	  
                }                 
                else {                	
                		MainFrame mainFrame = new MainFrame();
                		mainFrame.setSize(500,400);
                		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                		mainFrame.setLocation( (d.width - mainFrame.getSize().width) / 2,
            	                      (d.height - mainFrame.getSize().height) / 2);
                		mainFrame.show();
                        try {
              	  	        	rs.first();           	  	
              	  	        	//mainFrame.setEnable(rs.getString("power").trim());          
              	  	        	//db.closeConnection();
              	  	        	this.dispose();
                            }
                        catch (SQLException sqle2) {
                        System.out.println(sqle2.toString());
                      }            
                JOptionPane.showMessageDialog(null, "��½�ɹ�");   //������¼�ɹ�����  
                mainFrame.setEnable("ϵͳ����Ա"); //���ӵ�ϵͳ����Ա��Ȩ�� 
                this.dispose();
                }          	                
              }   
      
            if(choice.equals("����"))
		      {
            	String strSQL;
                strSQL = "select * from reader where readername='" +
						usertext.getText().trim() + "'and Password='" +
						passwordSTR + "'";                
                rs = db.getResult(strSQL);
                boolean isExist = false;
                try {
                  isExist = rs.first();
                }
                catch (SQLException sqle) {
                  System.out.println(sqle.toString());
                }
                if (!isExist) {
                  JOptionPane.showMessageDialog(null, "���ǷǷ��û�!");
                  
                }
                else {
                	MainFrame mainFrame = new MainFrame();
            		mainFrame.setSize(500,400);
            		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            		mainFrame.setLocation( (d.width - mainFrame.getSize().width) / 2,
        	                      (d.height - mainFrame.getSize().height) / 2);
            		mainFrame.show();
                  try {
                        rs.first();
                        mainFrame.setEnable(rs.getString("power").trim());  
                        //db.closeConnection();
                        this.dispose();                       
                      	} 
                  catch (SQLException sqle2) {
                    System.out.println(sqle2.toString());
                   }     
                    JOptionPane.showMessageDialog(null, "��½�ɹ�");
                    mainFrame.setEnable("����"); //���ӵ�ѧ����Ȩ�� 
                    this.dispose();                   
                }
		      }
		     
        }

    	if(e.getSource()==tuichu)
    	{
    		System.exit(0);
    		
    	}
		
	}
	public  static void DataBaseManager1() {
	    try {
	      String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=LibraryBase";
	      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");      
		  Connection con=DriverManager.getConnection(url,username,passwordSTR);//�������ݿ����
//	      stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
//	                                 ResultSet.CONCUR_UPDATABLE);
	    }
	    catch (SQLException sqle) {
	     // System.out.println(sqle.toString());
	    }
	    catch (ClassNotFoundException cnfex) {
	      cnfex.printStackTrace();
	    }
	  }
	public static void main(String args[])
	   {		
		new loginwindow();
				
	   }
}

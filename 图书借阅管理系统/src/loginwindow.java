
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
 	   super("图书管理系统登录");
 	   init();	  
 	   setSize(445,275);
 	   //登录窗口屏幕正中
 	   int windowWidth = this.getWidth(); //获得窗口宽
 	   int windowHeight = this.getHeight(); //获得窗口高
 	   Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
 	   Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
 	   int screenWidth = screenSize.width; //获取屏幕的宽
 	   int screenHeight = screenSize.height; //获取屏幕的高
 	   this.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示

 	   setResizable(false);
 	   setDefaultCloseOperation(EXIT_ON_CLOSE);
       setVisible(true);
    }
    public void init()
	{
		this.setLayout(null);
		
		Font font1=new Font("华文行楷",0,18);
		
		title_pic=new ImageIcon(getClass().getResource("/com/student/images/title.png"));
		
		this.setIconImage(title_pic.getImage());
	
		userlabel=new JLabel("用户名");
		passlabel=new JLabel("密    码");
		usertext=new JTextField("");
		password=new JPasswordField("");
		choicelabel=new JLabel("角    色");
		String[] ids = new String[]{"管理员","读者"};
		comboBox = new JComboBox(ids);
		
     	denglu=new JButton("登陆");
		tuichu=new JButton("退出");
		 

        label = new JLabel(background);  
         

        // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明  
        JPanel imagePanel = (JPanel)this.getContentPane();  
        imagePanel.setOpaque(false);  
        // 把背景图片添加到分层窗格的最底层作为背景  
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
		
		//添加监听对象
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
		//验证用户名、密码都没有输入的情况
			DataBaseManager1();
			 char[] password1 = password.getPassword();	      
			 username = usertext.getText().trim();
		      passwordSTR = new String(password1);
			  if (usertext.getText().trim().equals("")) 
		      {
		        JOptionPane.showMessageDialog(null, "用户名不可为空!");
		        return;
		      }
			if(!usertext.getText().trim().equals("")&&password.getPassword().equals(""))
		     {
				JOptionPane.showMessageDialog(getParent(), "请填写密码！",
					"信息提示框", JOptionPane.INFORMATION_MESSAGE);
				return ;
		     }
			if((usertext.getText()).equals("")&&!password.getPassword().equals(""))
		     {
				JOptionPane.showMessageDialog(getParent(), "请填写用户名！",
					"信息提示框", JOptionPane.INFORMATION_MESSAGE);
				return ;
		     }
			String choice=comboBox.getSelectedItem().toString();//选项
			if(choice.equals("管理员"))
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
                  JOptionPane.showMessageDialog(null, "您是非法用户!");  
                                                                        	  
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
                JOptionPane.showMessageDialog(null, "登陆成功");   //调出登录成功窗口  
                mainFrame.setEnable("系统管理员"); //连接到系统管理员的权限 
                this.dispose();
                }          	                
              }   
      
            if(choice.equals("读者"))
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
                  JOptionPane.showMessageDialog(null, "您是非法用户!");
                  
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
                    JOptionPane.showMessageDialog(null, "登陆成功");
                    mainFrame.setEnable("读者"); //连接到学生的权限 
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
		  Connection con=DriverManager.getConnection(url,username,passwordSTR);//连接数据库对象
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

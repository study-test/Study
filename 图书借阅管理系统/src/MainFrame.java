

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MainFrame extends JFrame
    implements ActionListener {
  /**
	 * ]
	 */
  private static final long serialVersionUID = 1L;
  private JPanel panel1;
  private JPanel panel2;
  private Container c;//����
  private JMenuBar menuB;
  JMenu  bookMGRMenu, borrowBookMenu, returnBookMenu,exitMenu,
      infoBrowseMenu, userMGRMenu,aboutMenu;
  JMenuItem adminLoginMenuItem, userLoginMenuItem, userAddMenuItem, userModifyMenuItem,
      userDeleteMenuItem, exitMenuItem, bookAddMenuItem, bookModifyMenuItem,
      bookDeleteMenuItem,
      borrowBookMenuItem, borrowInfoMenuItem, returnBookMenuItem,
      returnInfoMenuItem,
      bookListMenuItem, borrowBookListMenuItem, userListMenuItem,
      aboutMenuItem;
      
  public MainFrame() 
  {
    super("ͼ��ݹ���ϵͳ");
    //--���߲˵�--
   
    menuB = new JMenuBar();
    //systemMenu = new JMenu("ϵͳ����");
    userMGRMenu = new JMenu("���߹���");
    //adminLoginMenuItem = new JMenuItem("����Ա��¼");
    //userLoginMenuItem = new JMenuItem("�û���¼");
    userAddMenuItem = new JMenuItem("��Ӷ���");
    userModifyMenuItem = new JMenuItem("�޸�����");
    userDeleteMenuItem = new JMenuItem("ɾ������");
    exitMenu = new JMenu("�˳�");
    //systemMenu.add(adminLoginMenuItem);
    //systemMenu.add(userLoginMenuItem);
    userMGRMenu.add(userAddMenuItem);
    userMGRMenu.add(userModifyMenuItem);
    userMGRMenu.add(userDeleteMenuItem);
    //systemMenu.add(userMGRMenu);
    //systemMenu.add(exitMenu);
    //adminLoginMenuItem.addActionListener(this);
    //userLoginMenuItem.addActionListener(this);
    userAddMenuItem.addActionListener(this);
    userModifyMenuItem.addActionListener(this);
    userDeleteMenuItem.addActionListener(this);
    exitMenu.addActionListener(this);
    menuB.add(userMGRMenu);
    //menuB.add(systemMenu);
    //---�鼮����˵�--
    bookMGRMenu = new JMenu("�鼮����");
    bookAddMenuItem = new JMenuItem("����鼮");
    bookModifyMenuItem = new JMenuItem("�޸��鼮");
    bookDeleteMenuItem = new JMenuItem("ɾ���鼮");
    bookMGRMenu.add(bookAddMenuItem);
    bookMGRMenu.add(bookModifyMenuItem);
    bookMGRMenu.add(bookDeleteMenuItem);
    bookAddMenuItem.addActionListener(this);
    bookModifyMenuItem.addActionListener(this);
    bookDeleteMenuItem.addActionListener(this);
    menuB.add(bookMGRMenu);
    //--�������˵�--
    borrowBookMenu = new JMenu("�������");
    borrowBookMenuItem = new JMenuItem("�鼮����");
    //borrowInfoMenuItem = new JMenuItem("������Ϣ�޸�");
    borrowBookMenu.add(borrowBookMenuItem);
    //borrowBookMenu.add(borrowInfoMenuItem);
    borrowBookMenuItem.addActionListener(this);
   // borrowInfoMenuItem.addActionListener(this);
    menuB.add(borrowBookMenu);
    //--�������˵�--
    returnBookMenu = new JMenu("�������");
    returnBookMenuItem = new JMenuItem("�鼮����");
   // returnInfoMenuItem = new JMenuItem("�鼮������Ϣ�޸�");
    returnBookMenu.add(returnBookMenuItem);
    //returnBookMenu.add(returnInfoMenuItem);
    returnBookMenuItem.addActionListener(this);
    //returnInfoMenuItem.addActionListener(this);
    menuB.add(returnBookMenu);
    //--��Ϣһ���˵�--
    infoBrowseMenu = new JMenu("��Ϣ��ѯ");
    bookListMenuItem = new JMenuItem("�鼮��Ϣ��ѯ");
    borrowBookListMenuItem = new JMenuItem("������Ϣ��ѯ");
    userListMenuItem = new JMenuItem("�����б�");
    infoBrowseMenu.add(bookListMenuItem);
    infoBrowseMenu.add(borrowBookListMenuItem);
    infoBrowseMenu.add(userListMenuItem);
    bookListMenuItem.addActionListener(this);
    borrowBookListMenuItem.addActionListener(this);
    userListMenuItem.addActionListener(this);
    menuB.add(infoBrowseMenu);
    //--�����˳�--
    menuB.add(exitMenu);
    //--���ڲ˵�--
    aboutMenu=new JMenu("����");
    aboutMenuItem = new JMenuItem("����");
    aboutMenuItem.addActionListener(this);
    aboutMenu.add(aboutMenuItem);
    menuB.add(aboutMenu);
    //----------------------------------
    setJMenuBar(menuB);
    c = getContentPane();
    c.setLayout(new BorderLayout());
    panel1 = new JPanel();   
    panel1.setLayout(new BorderLayout());    
    c.add(panel1, BorderLayout.CENTER);   
    
    ImageIcon icon;
    icon=new ImageIcon("src/com/student/images/bj.jpg");//����һ��ImageIcon������
    JPanel panel2 = new JPanel();//����һ�����
    panel2 .setLayout(null);
    panel2 .setBounds(0, 0, 1000, 140);
    JLabel a = new JLabel(icon);//��ImageIcon���뵽һ��Jlable
    a.setBounds(0, 0, 500, 500);
    panel2.add(a,-1);
    c.add(panel2, BorderLayout.CENTER);
   
    //--���ó�ʼ����:--
    userMGRMenu.setEnabled(false);
    bookMGRMenu.setEnabled(false);
    borrowBookMenu.setEnabled(false);
    returnBookMenu.setEnabled(false);
    infoBrowseMenu.setEnabled(false);
    aboutMenu.setEnabled(false);
    exitMenu.setEnabled(false);
    
  }
  //--����ÿ���˵��������ֵĴ��ںʹ�����ʾ��λ��--
  public void actionPerformed(ActionEvent e) {
	
    if (e.getActionCommand() == "��Ӷ���") {
      UserAdd UserAddFrame = new UserAdd();
      Dimension FrameSize = UserAddFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      UserAddFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                               loc.x,
                               (MainFrameSize.height - FrameSize.height) / 2 +
                               loc.y);
      UserAddFrame.pack();
      UserAddFrame.show();
    }
    else if (e.getActionCommand() == "�޸�����") {
      UserModify UserModifyFrame = new UserModify();
      Dimension FrameSize = UserModifyFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      UserModifyFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                                  loc.x,
                                  (MainFrameSize.height - FrameSize.height) / 2 +
                                  loc.y);
      UserModifyFrame.pack();
      UserModifyFrame.show();
    }
    else if (e.getActionCommand() == "ɾ������") {
      UserDelete UserDeleteFrame = new UserDelete();
      Dimension FrameSize = UserDeleteFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      UserDeleteFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                                  loc.x,
                                  (MainFrameSize.height - FrameSize.height) / 2 +
                                  loc.y);
      UserDeleteFrame.pack();
      UserDeleteFrame.show();
    }
    else if (e.getActionCommand() == "����鼮") {
      BookAdd BookAddFrame = new BookAdd();
      Dimension FrameSize = BookAddFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      BookAddFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                               loc.x,
                               (MainFrameSize.height - FrameSize.height) / 2 +
                               loc.y);
      BookAddFrame.pack();
      BookAddFrame.show();
    }
    else if (e.getActionCommand() == "�޸��鼮") {
      BookModify BookModifyFrame = new BookModify();
      Dimension FrameSize = BookModifyFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      BookModifyFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                                  loc.x,
                                  (MainFrameSize.height - FrameSize.height) / 2 +
                                  loc.y);
      BookModifyFrame.pack();
      BookModifyFrame.show();
    }
    else if (e.getActionCommand() == "ɾ���鼮") {
      BookDelete BookDeleteFrame = new BookDelete();
      Dimension FrameSize = BookDeleteFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      BookDeleteFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                                  loc.x,
                                  (MainFrameSize.height - FrameSize.height) / 2 +
                                  loc.y);
      BookDeleteFrame.pack();
      BookDeleteFrame.show();
    }
    else if (e.getActionCommand() == "�鼮����") {
      BorrowBook BorrowBookFrame = new BorrowBook();
      Dimension FrameSize = BorrowBookFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      BorrowBookFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                                  loc.x,
                                  (MainFrameSize.height - FrameSize.height) / 2 +
                                  loc.y);
      BorrowBookFrame.pack();
      BorrowBookFrame.show();
    }
    
    else if (e.getActionCommand() == "�鼮����") {
      ReturnBook ReturnBookFrame = new ReturnBook();
      Dimension FrameSize = ReturnBookFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      ReturnBookFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                                  loc.x,
                                  (MainFrameSize.height - FrameSize.height) / 2 +
                                  loc.y);
      ReturnBookFrame.pack();
      ReturnBookFrame.show();
    }
    else if (e.getActionCommand() == "�鼮��Ϣ��ѯ") {
      BookList BookListFrame = new BookList();
      Dimension FrameSize = BookListFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      BookListFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                                loc.x,
                                (MainFrameSize.height - FrameSize.height) / 2 +
                                loc.y);
      BookListFrame.pack();
      BookListFrame.show();
    }
    else if (e.getActionCommand() == "������Ϣ��ѯ") {
      BorrowBookList BorrowBookListFrame = new BorrowBookList();
      Dimension FrameSize = BorrowBookListFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      BorrowBookListFrame.setLocation( (MainFrameSize.width - FrameSize.width) /
                                      2 + loc.x,
                                      (MainFrameSize.height - FrameSize.height) /
                                      2 + loc.y);
      BorrowBookListFrame.pack();
      BorrowBookListFrame.show();
    }
    else if (e.getActionCommand() == "�����б�") {
      UserList UserListFrame = new UserList();
      Dimension FrameSize = UserListFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      UserListFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                                loc.x,
                                (MainFrameSize.height - FrameSize.height) / 2 +
                                loc.y);
      UserListFrame.pack();
      UserListFrame.show();
    }
    else if (e.getActionCommand() == "����") {
      String str = "ͼ����Ĺ���ϵͳ�������Ϊ\n"+
          "      17������\n"+"���Ʒ� ������ �޺�Ȼ\n";
      JOptionPane.showMessageDialog(null, str, "����",
                                    JOptionPane.INFORMATION_MESSAGE);
    }
    else if (e.getActionCommand() == "�˳�") {
      this.dispose();
      System.exit(0);
    }
  }

  //--���õ�¼�û���Ȩ��--
  public void setEnable(String powerType) {
    if (powerType.trim().equals("ϵͳ����Ա")) {
      userMGRMenu.setEnabled(true);
      bookMGRMenu.setEnabled(true);
      borrowBookMenu.setEnabled(true);
      returnBookMenu.setEnabled(true);
      infoBrowseMenu.setEnabled(true);
      userListMenuItem.setEnabled(true);
      aboutMenu.setEnabled(true);
      exitMenu.setEnabled(true);
    }
   
    else if (powerType.trim().equals("����")) {
      userMGRMenu.setEnabled(false);
      bookMGRMenu.setEnabled(false);
      borrowBookMenu.setEnabled(true);
      returnBookMenu.setEnabled(true);
      infoBrowseMenu.setEnabled(true);
      userListMenuItem.setEnabled(false);
      aboutMenu.setEnabled(true);
      exitMenu.setEnabled(true);
    }
    else if (powerType.trim().equals("else")) {
      userMGRMenu.setEnabled(false);
      bookMGRMenu.setEnabled(false);
      borrowBookMenu.setEnabled(false);
      returnBookMenu.setEnabled(false);
      infoBrowseMenu.setEnabled(false);
      aboutMenu.setEnabled(false);
      exitMenu.setEnabled(true);
    }
  }
  public static void main(String[] args) {
	  try {
	      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }        	            
	    MainFrame mainFrame = new MainFrame();
	    mainFrame.setSize(500,400);
	    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	    mainFrame.setLocation( (d.width - mainFrame.getSize().width) / 2,
	                      (d.height - mainFrame.getSize().height) / 2);
	    mainFrame.show();
	}
}

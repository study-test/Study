

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class UserDelete
    extends JFrame
    implements ActionListener {
  DataBaseManager db = new DataBaseManager();
  ResultSet rs;
  JPanel panel1, panel2;
  Container c;
  JLabel userLabel, passwordLabel;
  JTextField userTextField;
  JPasswordField passwordTextField;
  JButton yesBtn, cancelBtn;
  public UserDelete() {
    super("ɾ������");
    c = getContentPane();
    c.setLayout(new BorderLayout());
    userLabel = new JLabel("������", JLabel.CENTER);
    passwordLabel = new JLabel("����", JLabel.CENTER);
    userTextField = new JTextField(25);
    passwordTextField = new JPasswordField(25);
    yesBtn = new JButton("ȷ��");
    cancelBtn = new JButton("ȡ��");
    yesBtn.addActionListener(this);
    cancelBtn.addActionListener(this);
    panel1 = new JPanel();
    panel1.setLayout(new GridLayout(2, 2));
    panel1.add(userLabel);
    panel1.add(userTextField);
    panel1.add(passwordLabel);
    panel1.add(passwordTextField);
    panel2 = new JPanel();
    panel2.add(yesBtn);
    panel2.add(cancelBtn);
    c.add(panel1, BorderLayout.CENTER);
    c.add(panel2, BorderLayout.SOUTH);
    setSize(300, 300);
  }

  public void actionPerformed(ActionEvent e) {
    try {
      if (e.getSource() == cancelBtn) {
        db.closeConnection();
        this.dispose();
      }
      else if (e.getSource() == yesBtn) {
        char[] password = passwordTextField.getPassword();
        String passwordSTR = new String(password);
        String strSQL = "select * from reader where readername='" +
            userTextField.getText().trim() + "' and Password='" +
            passwordSTR + "'";
        if (userTextField.getText().trim().equals("")) {
          JOptionPane.showMessageDialog(null, "����������Ϊ�գ�");
        }
        else if (passwordTextField.equals("")) {
          JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�");
        }
        else if(!(userTextField.getText().trim().equals(""))&&!passwordTextField.equals(""))
        {
        	String StrSQL1 = "select borrownumber from reader where readername='" +
        			userTextField.getText().trim() + "'";
                rs = db.getResult(StrSQL1);
                rs.first();
                int count = rs.getInt(1);
                if (! (count == 0))
                {
                  JOptionPane.showMessageDialog(null, "�˶��߻�����û�й黹��\n���ڻ����ܴ�������ɾ������");
                  this.dispose();
                }
                 else if (db.getResult(strSQL).first()) 
                 {
                	 strSQL = "delete reader where readername='" +
                			 userTextField.getText().trim() + "'";
                	 // if (db.updateSql(strSQL)>0) {
                	 db.getResult(strSQL);
                	 JOptionPane.showMessageDialog(null, "ɾ���ɹ�!");
                	 this.dispose();
                 
                  }
       

        //  }
        //  else {
        //    JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
        //    this.dispose();
        //  }
          db.closeConnection();
          
        }
        else {
          JOptionPane.showMessageDialog(null, "�����ڴ��û������������");
        }
      }
    }
    catch (SQLException sqle) {
      System.out.println(sqle.toString());
    }
    catch (Exception ex) {
      System.out.println(ex.toString());
    }
  }
}

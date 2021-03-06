

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class UserModify
    extends JFrame
    implements ActionListener {
  DataBaseManager db = new DataBaseManager();
  ResultSet rs;
  JPanel panel1, panel2;
  Container c;
  JLabel UserLabel, PasswordLabel, NewPasswordLabel, PasswordConfirmLabel,ageLabel,jobLabel;
  JTextField UserTextField,ageTextField,jobTextField;
  JPasswordField PasswordTextField, NewPasswordTextField,
      PasswordConfirmTextField;
  JButton UpdateBtn, CancelBtn;
  public UserModify() {
    super("更改密码");
    c = getContentPane();
    c.setLayout(new BorderLayout());
    UserLabel = new JLabel("用户名", JLabel.CENTER);
    PasswordLabel = new JLabel("原密码", JLabel.CENTER);
    NewPasswordLabel = new JLabel("新密码", JLabel.CENTER);
    PasswordConfirmLabel = new JLabel("确认新密码", JLabel.CENTER);
    ageLabel = new JLabel("年龄", JLabel.CENTER);
    jobLabel = new JLabel("职业", JLabel.CENTER);
    UserTextField = new JTextField(10);
    ageTextField = new JTextField(5);
    jobTextField = new JTextField(20);
    PasswordTextField = new JPasswordField(20);
    NewPasswordTextField = new JPasswordField(20);
    PasswordConfirmTextField = new JPasswordField(10);
    UpdateBtn = new JButton("更新");
    CancelBtn = new JButton("取消");
    UpdateBtn.addActionListener(this);
    CancelBtn.addActionListener(this);
    panel1 = new JPanel();
    panel1.setLayout(new GridLayout(6, 2));
    panel1.add(UserLabel);
     panel1.add(UserTextField);
    panel1.add(ageLabel);
    panel1.add(ageTextField);
    panel1.add(jobLabel);
    panel1.add(jobTextField);
   
    panel1.add(PasswordLabel);
    panel1.add(PasswordTextField);
    panel1.add(NewPasswordLabel);
    panel1.add(NewPasswordTextField);
    panel1.add(PasswordConfirmLabel);
    panel1.add(PasswordConfirmTextField);
    panel2 = new JPanel();
    panel2.add(UpdateBtn);
    panel2.add(CancelBtn);
    c.add(panel1, BorderLayout.CENTER);
    c.add(panel2, BorderLayout.SOUTH);
    setSize(300, 300);

  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == CancelBtn) {
      db.closeConnection();
      this.dispose();
    }
    else if (e.getSource() == UpdateBtn) {
      try {
        char[] password = PasswordTextField.getPassword();
        String passwordSTR = new String(password);
        char[] newPassword = NewPasswordTextField.getPassword();
        String newPasswordSTR = new String(newPassword);
        char[] confirmPassword = PasswordConfirmTextField.getPassword();
        String confirmPasswordSTR = new String(confirmPassword);
        String strSQL = "select * from reader where readername='" +
            UserTextField.getText().trim() + "'and Password='" +
            passwordSTR + "'";
        if (UserTextField.getText().trim().equals("")) {
          JOptionPane.showMessageDialog(null, "用户名不能为空！");
        }
        else if (passwordSTR.equals("")) {
          JOptionPane.showMessageDialog(null, "原密码不能为空！");
        }
        else if (!newPasswordSTR.equals(confirmPasswordSTR)) {
          JOptionPane.showMessageDialog(null, "两次输入的新密码不一致！");
        }
        else {
          if (!db.getResult(strSQL).first()) {
            JOptionPane.showMessageDialog(null, "此用户不存在或者原密码不正确！");

          }
          else {
            strSQL = "update reader set Password='" +
                newPasswordSTR + "',readerage='"+ ageTextField.getText().trim()+"',readerjob='"+jobTextField.getText().trim()+"'  where readername='" + UserTextField.getText()
                .trim() + "'";
           // if (db.updateSql(strSQL)>0) {
              db.getResult(strSQL);
              JOptionPane.showMessageDialog(null, "修改读者信息成功！");
              this.dispose();
           // }
          //  else {
          //    JOptionPane.showMessageDialog(null, "更新密码失败！");
          //    this.dispose();
          //  }
            db.closeConnection();
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
}

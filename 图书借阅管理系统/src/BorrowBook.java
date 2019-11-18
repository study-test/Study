

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class BorrowBook
    extends JFrame
    implements ActionListener {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  DataBaseManager db = new DataBaseManager();
  ResultSet rs;
  JPanel panel1, panel2;
  Container c;
  JLabel borrowedBook_Student_ID_Label,borrowedBookStudentLabel,
      borrowedBook_Book_ID_Label, borrowedBookNameLabel,
      borrowedDateLabel, borrowednumber;
  JTextField borrowedBook_Student_ID_TextField, borrowedBookStudentTextField,
  borrowedBook_Book_ID_TextField, borrowedDateTextField, borrowednumberTextField;
  JButton clearBtn, yesBtn, cancelBtn;
  JComboBox bookNameComboBox = new JComboBox();
  public BorrowBook() {
    super("书籍出借");
    c = getContentPane();
    c.setLayout(new BorderLayout());
    borrowedBook_Student_ID_Label = new JLabel("借阅者ID", JLabel.CENTER);
    borrowedBookStudentLabel = new JLabel("借阅者姓名", JLabel.CENTER);
    borrowedBook_Book_ID_Label = new JLabel("书籍ID", JLabel.CENTER);
    borrowedBookNameLabel = new JLabel("书名", JLabel.CENTER);
    borrowedDateLabel = new JLabel("日期", JLabel.CENTER);
    borrowednumber = new JLabel("数量", JLabel.CENTER);
    borrowedBookStudentTextField = new JTextField(25);
    borrowedDateTextField = new JTextField(25);
    borrowedBook_Student_ID_TextField = new JTextField(25);
    borrowedBook_Book_ID_TextField = new JTextField(25);
    borrowednumberTextField = new JTextField(25);
    try {
      String strSQL =
          "select BookName from books where books_count>=borrowed_count";
      rs = db.getResult(strSQL);
      while (rs.next()) {
        bookNameComboBox.addItem(rs.getString(1).trim());
      }
    }
    catch (SQLException sqle) {
      System.out.println(sqle.toString());
    }
    catch (Exception ex) {
      System.out.println(ex.toString());
    }
    panel1 = new JPanel();
    panel1.setLayout(new GridLayout(6, 2));
    panel1.add(borrowedBook_Student_ID_Label);
    panel1.add(borrowedBook_Student_ID_TextField);
    panel1.add(borrowedBookStudentLabel);
    panel1.add(borrowedBookStudentTextField);
    panel1.add(borrowedBook_Book_ID_Label);
    panel1.add(borrowedBook_Book_ID_TextField);
    panel1.add(borrowedBookNameLabel);
    panel1.add(bookNameComboBox);
    panel1.add(borrowedDateLabel);
    panel1.add(borrowedDateTextField);
    panel1.add(borrowednumber);
    panel1.add(borrowednumberTextField);
    c.add(panel1, BorderLayout.CENTER);
    panel2 = new JPanel();
    panel2.setLayout(new GridLayout(1, 3));
    clearBtn = new JButton("清空");
    yesBtn = new JButton("确定");
    cancelBtn = new JButton("取消");
    clearBtn.addActionListener(this);
    yesBtn.addActionListener(this);
    cancelBtn.addActionListener(this);
    panel2.add(clearBtn);
    panel2.add(yesBtn);
    panel2.add(cancelBtn);
    c.add(panel2, BorderLayout.SOUTH);

  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == cancelBtn) {
      this.dispose();
    }
    else if (e.getSource() == clearBtn) {
      borrowedBookStudentTextField.setText("");
      borrowedBook_Student_ID_TextField.setText("");
      borrowedBook_Book_ID_TextField.setText("");
      borrowedDateTextField.setText("");
      borrowednumberTextField.setText("");

    }
    else if (e.getSource() == yesBtn) {
    	String s=borrowednumberTextField.getText().trim();
    	int i=Integer.parseInt(s);//字符串型转为整型 
    	/*String sql = "select borrowednumber from bookBrowse where StudentName='"+borrowedBookStudentTextField.getText().trim()+"'";
    	rs=db.getResult(sql);     	
			rs.next();		    	
			String c = rs.getString(1);	
			int j=Integer.parseInt(c);
    	if(j+i>5) {    		
    		JOptionPane.showMessageDialog(null, "请输入借阅者的姓名。。。");
    	}*/
      if (borrowedBookStudentTextField.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "请输入借阅者的姓名。。。");
      }
      else if (i>5) {    	  
         JOptionPane.showMessageDialog(null, "对不起最多只能借5本");
        }
      else if (bookNameComboBox.getSelectedItem().equals("")) {
        JOptionPane.showMessageDialog(null, "对不起，现在书库里没有书，\n你现在不能借书!");
      }
      else {
        try {
          String strSQL = "insert into bookBrowse(readerid,readername,BookID,BookName,BorrowDate,Borrowednumber,Is_Returned) values('" +
        	  borrowedBook_Student_ID_TextField.getText().trim() + "','" +
        	  borrowedBookStudentTextField.getText().trim() + "','" +
        	  borrowedBook_Book_ID_TextField.getText().trim() + "','" +
              bookNameComboBox.getSelectedItem() + "','" +
              borrowedDateTextField.getText().trim() + "','" +
              borrowednumberTextField.getText().trim() + "','" +
              "否" + "')";
            db.getResult(strSQL);
            
            for(int n=0;n<i;n++)
            {
            	String strSQL1 = "update books set borrowed_count=borrowed_count+1  where BookName='" +
            bookNameComboBox.getSelectedItem() + "'";
                db.getResult(strSQL1);
                String strSQL2 = "update books set books_count = books_count-1 where BookName = '"+bookNameComboBox.getSelectedItem()+"'";
                db.getResult(strSQL2);
                String strSQL3 = "update reader set borrownumber = borrownumber+1 where readername = '"+ 
                		        	  borrowedBookStudentTextField.getText().trim() + "'";
                db.getResult(strSQL3);
            }                        
            JOptionPane.showMessageDialog(null, "借阅完成！");
            this.dispose();
            db.closeConnection();            
          
//          else {
//            JOptionPane.showMessageDialog(null, "借阅失败！");
//            db.closeConnection();
//            this.dispose();
//          }
        }
        catch (Exception ex) {
          System.out.println(ex.toString());
        }
      }
    }

  }
}

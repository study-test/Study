

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class ReturnBook
    extends JFrame
    implements ActionListener {
  DataBaseManager db = new DataBaseManager();
  ResultSet rs;
  JPanel panel1, panel2;
  Container c;
  JLabel returnedBookStudentLabel, returnedBookNameLabel,
      returnedDateLabel, returnednumberLabel;
  JTextField returnedBookStudentTextField,
      returnedDateTextField, returnednumberTextField;
  JButton clearBtn, yesBtn, cancelBtn;
  JComboBox bookNameComboBox = new JComboBox();
  public ReturnBook() {
    super("�鼮����");
    c = getContentPane();
    c.setLayout(new BorderLayout());
    returnedBookStudentLabel = new JLabel("����������", JLabel.CENTER);
    returnedBookNameLabel = new JLabel("����", JLabel.CENTER);
    returnedDateLabel = new JLabel("����", JLabel.CENTER);
    returnednumberLabel = new JLabel("����", JLabel.CENTER);
    returnedBookStudentTextField = new JTextField(15);
    returnedDateTextField = new JTextField(15);
    returnednumberTextField = new JTextField(15);
    try {
     // String s = "";
      String strSQL = "select bookName from bookBrowse where is_returned='��'";
      rs = db.getResult(strSQL);
      while (rs.next()) {
        bookNameComboBox.addItem(rs.getString(1));
      }
    }
    catch (SQLException sqle) {
      System.out.println(sqle.toString());
    }
    catch (Exception ex) {
      System.out.println(ex.toString());
    }
    panel1 = new JPanel();
    panel1.setLayout(new GridLayout(4, 2));
    panel1.add(returnedBookStudentLabel);
    panel1.add(returnedBookStudentTextField);
    panel1.add(returnedBookNameLabel);
    panel1.add(bookNameComboBox);
    panel1.add(returnedDateLabel);
    panel1.add(returnedDateTextField);
    panel1.add(returnednumberLabel);
    panel1.add(returnednumberTextField);
    c.add(panel1, BorderLayout.CENTER);
    panel2 = new JPanel();
    panel2.setLayout(new GridLayout(1, 3));
    clearBtn = new JButton("���");
    yesBtn = new JButton("ȷ��");
    cancelBtn = new JButton("ȡ��");
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
      returnedBookStudentTextField.setText("");
      returnedDateTextField.setText("");
      returnednumberTextField.setText("");

    }
    else if (e.getSource() == yesBtn) {
      if (returnedBookStudentTextField.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "�����뻹���ߵ�����������");
      }
      else if (bookNameComboBox.getSelectedItem().equals("")) {
        JOptionPane.showMessageDialog(null, "ͼ���û�г�����飡");
      }
    
      else {
    	  String s=returnednumberTextField.getText().trim();
          	int i=Integer.parseInt(s);//�ַ�����תΪ���� 
        try {
          String strSQL = "update bookBrowse set returnDate='" +
              returnedDateTextField.getText().trim() + "',borrowednumber='" +
              i+
              "',is_returned='��' where readername='" +
              returnedBookStudentTextField.getText().trim() + "'and bookName='" +
              bookNameComboBox.getSelectedItem() + "'and is_returned='��'";
              db.getResult(strSQL);              
            for(int n=0;n<i;n++)
            {
            	strSQL = "update books set borrowed_count=borrowed_count-1 where bookname='" +
                bookNameComboBox.getSelectedItem() + "'";
            	db.getResult(strSQL);
            	String strSQL2 = "update books set books_count = books_count+1 where BookName = '"+bookNameComboBox.getSelectedItem()+"'";
            	db.getResult(strSQL2); 
            	String strSQL3 = "update reader set borrownumber = borrownumber-1 where readername = '"+returnedBookStudentTextField.getText().trim()+"'";
            	db.getResult(strSQL3); 
            }         	
            JOptionPane.showMessageDialog(null, "������ɣ�");           
            this.dispose();
            db.closeConnection();            
           }
            // else {
           //  JOptionPane.showMessageDialog(null, "����ʧ�ܣ����������������������ϻ����ݿ����ʧ�ܣ�");
           //   db.closeConnection();
           // this.dispose();
           //}
        
        catch (Exception ex) {
          System.out.println(ex.toString());
        } 
        try
        {   String SQL1 = "update bookBrowse set days= datediff(day,BorrowDate,ReturnDate)";
        	db.getResult(SQL1); 
        	String SQL2 = "select days from bookBrowse where readername = '"+returnedBookStudentTextField.getText().trim()+"'";   
        	rs=db.getResult(SQL2); 	  	
        	rs.last();
        	int count = rs.getInt(1);	
        	if(count >30)
        	{
        		double money;
        		money = (count-30)*0.5;
        		JOptionPane.showMessageDialog(null, "Ӧ���ɷ���"+money);        	
        	}
        		
        	else
        	{
        		JOptionPane.showMessageDialog(null, "����Ҫ���ɷ��");
        	}
         }
        	catch (Exception ex) {
        		System.out.println(ex.toString());
        	} 		  					 
      }
    }
  }
}

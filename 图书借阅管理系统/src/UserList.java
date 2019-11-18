

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.Vector;

public class UserList
    extends JFrame {
  DataBaseManager db = new DataBaseManager();
  ResultSet rs;
  Container c;
  JTable table = null;
  DefaultTableModel defaultModel = null;
  public UserList() {
    super("读者列表一览！");
    c = getContentPane();
    c.setLayout(new BorderLayout());
    String[] name = {
        "读者编号","读者名", "性别","年龄","职业","借书量","罚金"};
    String[][] data = new String[0][0];
    defaultModel = new DefaultTableModel(data, name);
    table = new JTable(defaultModel);
    table.setPreferredScrollableViewportSize(new Dimension(500, 100));
    JScrollPane s = new JScrollPane(table);
    c.add(s);
    try {
      String strSql = "select readerid,readername,readersex,readerage,readerjob,borrownumber,money from reader";
      rs = db.getResult(strSql);
      while (rs.next()) {
        Vector insertRow = new Vector();
        insertRow.addElement(rs.getString(1));//获取结果集当前行的第1列数据
        insertRow.addElement(rs.getString(2));//获取结果集当前行的第2列数据
        insertRow.addElement(rs.getString(3));//获取结果集当前行的第3列数据
        insertRow.addElement(rs.getString(4));//获取结果集当前行的第4列数据
        insertRow.addElement(rs.getString(5));//获取结果集当前行的第5列数据
        insertRow.addElement(rs.getString(6));//获取结果集当前行的第6列数据
        insertRow.addElement(rs.getString(7));//获取结果集当前行的第7列数据
        defaultModel.addRow(insertRow);
      }
      table.revalidate();
    }
    catch (SQLException sqle) {
      System.out.println(sqle.toString());
    }
    catch (Exception ex) {
      System.out.println(ex.toString());
    }
  }
}

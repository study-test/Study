

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
    super("�����б�һ����");
    c = getContentPane();
    c.setLayout(new BorderLayout());
    String[] name = {
        "���߱��","������", "�Ա�","����","ְҵ","������","����"};
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
        insertRow.addElement(rs.getString(1));//��ȡ�������ǰ�еĵ�1������
        insertRow.addElement(rs.getString(2));//��ȡ�������ǰ�еĵ�2������
        insertRow.addElement(rs.getString(3));//��ȡ�������ǰ�еĵ�3������
        insertRow.addElement(rs.getString(4));//��ȡ�������ǰ�еĵ�4������
        insertRow.addElement(rs.getString(5));//��ȡ�������ǰ�еĵ�5������
        insertRow.addElement(rs.getString(6));//��ȡ�������ǰ�еĵ�6������
        insertRow.addElement(rs.getString(7));//��ȡ�������ǰ�еĵ�7������
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

import java.sql.*;
public class DataBaseManager {
  Connection con = null;
  ResultSet rs = null;
  Statement stmt;

  
  public DataBaseManager() {
    try {
      String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=LibraryBase";
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      //String url = "jdbc:odbc:LibraryBase";
      //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      //con = DriverManager.getConnection(url);
	  String user="sa";
	  String password="314159";
	  Connection con=DriverManager.getConnection(url,user,password);//连接数据库对象
      stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                 ResultSet.CONCUR_UPDATABLE);
    }
    catch (SQLException sqle) {
      System.out.println(sqle.toString());
    }
    catch (ClassNotFoundException cnfex) {
      cnfex.printStackTrace();
    }
  }

 
  
  public ResultSet getResult(String strSQL) {
    try {
      rs = stmt.executeQuery(strSQL);//查询返回的结果集
      return rs;
    }
    catch (SQLException sqle) {
      System.out.println(sqle.toString());
      return null;
    }
  }
 
  public int updateSql(String strSQL) {
    try {   
     int i = stmt.executeUpdate(strSQL);
      con.commit();  //保存到缓存里 
      return i;
    }
    catch (SQLException sqle) {
      System.out.println(sqle.toString());
      return -1;
    }
  }
  public void closeConnection() {
    try {
      con.close();
    }
    catch (SQLException sqle) {
      System.out.println(sqle.toString());
    }
  }
}


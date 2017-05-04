import java.sql.*;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by zhf on 2017/1/5.
 */
public class UserDAO {
    private String JDriver = "com.mysql.jdbc.Driver";// SQL数据库引擎

    private String connectDB = "jdbc:mysql://localhost:3306/shiro";// 数据源

    private String user = "zhf";

    private String password = "zhf";

    public String getPassword(String username) {

        try {

            Class.forName(JDriver);// 加载数据库引擎，返回给定字符串名的类

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

            System.out.println("加载数据库引擎失败");

            System.exit(0);

        }
        Connection con = null;
      //  System.out.println("数据库驱动成功");
        try {
            con = DriverManager.getConnection(connectDB, user, password);// 连接数据库对象

//            System.out.println("连接数据库成功");
            String queryPsd = "select password from user where username=" + "'" + username + "';";

          //  System.out.println(queryPsd);

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(queryPsd);
            if (rs.next()) {
                String psd = rs.getString("password");
                return psd;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList getPermissions(String username){

        try {

            Class.forName(JDriver);// 加载数据库引擎，返回给定字符串名的类

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

            System.out.println("加载数据库引擎失败");

            System.exit(0);

        }
        Connection con = null;
        ArrayList<String> arrayList=new ArrayList<String>();
        //  System.out.println("数据库驱动成功");
        try {
            con = DriverManager.getConnection(connectDB, user, password);// 连接数据库对象

//            System.out.println("连接数据库成功");
            String queryPsd = "select satellite_id,permission_id from user_permission where username=" + "'" + username + "';";

            //  System.out.println(queryPsd);

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(queryPsd);
            while (rs.next()) {
                String satelliteId = rs.getString("satellite_id");
                String permissionId=rs.getString("permission_id");
                arrayList.add(satelliteId+":"+permissionId);
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;

    }
}

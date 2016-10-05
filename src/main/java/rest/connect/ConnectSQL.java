package rest.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectSQL {

	public static final Map<String, String> users = new HashMap<String, String>();
	
	public ConnectSQL() {
		Connection conn = null;
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test1", "postgres", "njkmrj17538");
		    Statement statement = null;		
		    statement = conn.createStatement();
		    ResultSet re;
		    re = statement.executeQuery("select login, pass from client");
		    while (re.next()) {
		    	users.put(re.getString("login"), re.getString("pass"));
			}
		    conn.close();
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> getList() {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
	    try {
	       Class.forName("org.postgresql.Driver");
	       conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test1", "postgres", "njkmrj17538");
		   Statement statement = null;		
		   statement = conn.createStatement();
		   ResultSet re;
		   re = statement.executeQuery("select login, pass, id_client, name_client, role from client");
		   while (re.next()) {
			   list.add("Доступ - " + re.getString("role") + "; Логин - " + re.getString("login")
			  		+ "; Имя - " + re.getString("name_client")+ "; Id - " + re.getString("id_client") + "; Пароль - " + re.getString("pass"));
		   }		       
		   conn.close();
	    } catch (ClassNotFoundException e) {
	       e.printStackTrace();
		} catch (SQLException e) {
		   e.printStackTrace();
		}
		return list;
	}
}

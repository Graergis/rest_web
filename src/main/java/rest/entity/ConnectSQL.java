package rest.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class ConnectSQL {

	public ConnectSQL(String string, String string2, String string3, String name1, String author, String comment) {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/repository", "postgres", "njkmrj17538");
			Statement statement = null;
			statement = conn.createStatement();
			if (name1 != "") {
				String query = "INSERT INTO documents (name, date_creation, author, comment, id) VALUES ('" + name1 + "','" + new java.util.Date ().toString () + "', '"
					+ author + "','" + comment + "','" +"9" + "');";
				statement.executeUpdate(query);
			}
			int i = 16;
			System.out.println("string - " + string + "; string2 - " + string2 + "; string3 " + string3);
			if (string != "error") {
				String query = "INSERT INTO files (id, id_document, name, path) VALUES ('" + i++ + "','" + "9" + "', '"
					+ "name" + "','" + string + "');";
				statement.executeUpdate(query);
			}
			if (string2 != "error") {
				String query = "INSERT INTO files (id, id_document, name, path) VALUES ('" + i++ + "','" + "9" + "', '"
					+ "name" + "','" + string2 + "');";
				statement.executeUpdate(query);
			}
			if (string3 != "error") {
				String query = "INSERT INTO files (id, id_document, name, path) VALUES ('" + i++ + "','" + "9" + "', '"
					+ "name" + "','" + string3 + "');";
				statement.executeUpdate(query);
			}
			conn.close();
			
		} catch (ClassNotFoundException e) {
	    	   e.printStackTrace();
	    } catch (SQLException e) {
				e.printStackTrace();
		} 
	}

	public static String connec(String type) throws SQLException {
		Connection conn = null;
		ResultSet re;
		String s = "";
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/repository", "postgres", "njkmrj17538");
			Statement statement = null;
			statement = conn.createStatement();
			if (type != "") {
				String query = "select path from files where id = " + type + ";";
				re = statement.executeQuery(query);
				while (re.next()) {
					s = re.getString("path");
				}
			}
			conn.close();
		} catch (ClassNotFoundException e) {
	    	   e.printStackTrace();
	    } catch (SQLException e) {
				e.printStackTrace();
		} 
		return s;
	}

	public static List<DocumentEntity> getList() {
		List<String> list = new ArrayList<String>();
		List <DocumentEntity> list1 = new ArrayList<>();
		Connection conn = null;
	    try {
	       Class.forName("org.postgresql.Driver");
	       conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/repository", "postgres", "njkmrj17538");
		   Statement statement = null;		
		   statement = conn.createStatement();
		   ResultSet re;
		   re = statement.executeQuery("select files.id, documents.name, author, comment, path from documents inner join files on documents.id = files.id_document");
		   while (re.next()) {
			   list1.add(new DocumentEntity(re.getLong("id"),re.getString("name"),"12.10.2016",re.getString("author"),re.getString("comment")));
			   list.add("Документ - " + re.getString("name") + "; Автор - " + re.getString("author")
			  		+ "; Комментарий - " + re.getString("comment")+ "; Путь - " + re.getString("path"));
		   }		       
		   conn.close();
	    } catch (ClassNotFoundException e) {
	       e.printStackTrace();
		} catch (SQLException e) {
		   e.printStackTrace();
		}
		return list1;
	}
}

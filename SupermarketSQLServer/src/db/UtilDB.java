package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class UtilDB 
{
	public static int getLastElement (String tableName) 
	{
		Database db = Database.getKonekcijaNaBazu();
		int last = 0;
		try
		{
			String query = "SELECT * FROM " + tableName;
			PreparedStatement prepS = db.conn.prepareStatement(query);
			ResultSet res = prepS.executeQuery();
			ResultSetMetaData rsmd = (ResultSetMetaData) res.getMetaData();
			String firstColumn = rsmd.getColumnName(1);
			
			
			String query2 = "SELECT " + firstColumn + " FROM " + tableName + " ORDER BY " + firstColumn + " DESC LIMIT 0,1";
			PreparedStatement prepS2 = db.conn.prepareStatement(query2);
			ResultSet res2 = prepS2.executeQuery();
			
			if (res2.next()) 
				last = res2.getInt(1);
				
			return last;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return -1;
		}
	}
}

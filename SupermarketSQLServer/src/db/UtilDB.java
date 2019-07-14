package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class UtilDB 
{
	
	
	public static int vratiPoslednjiID (String tabela, String imePolja)
	{
		Database db = Database.getKonekcijaNaBazu();
		int poslednji = 0;
		String upit = "select MAX(" + imePolja + ") as max from " + tabela;
		db.otvoriKonekciju();
		try 
		{
			Statement s = db.conn.createStatement();
			ResultSet rs = s.executeQuery(upit);
			
			while(rs.next())
			{
				poslednji = rs.getInt("max");
			}
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
			return -1;
		}
		return poslednji;
		
	}
	
	public static Timestamp dajTrenutnoTimestampVreme ()
	{
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String trenutnoVreme = sdf.format(dt);
		return Timestamp.valueOf(trenutnoVreme);
	}
}

package util;

import java.sql.Timestamp;

public class Util 
{
	public static String trenutnoVreme = new Timestamp(System.currentTimeMillis()).toString().substring(0, 19);
}

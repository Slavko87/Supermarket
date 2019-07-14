package util;

import java.sql.Timestamp;

public class Konstante 
{
	public static String trenutnoVreme = new Timestamp(System.currentTimeMillis()).toString().substring(0, 19);
	public static String do2100godine = "2100-12-31 23:59:59";
	public static String od1900godine = "1900-12-31 00:00:00";
}

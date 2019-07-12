package serverskiDeo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PokreniServer extends Thread 
{
	public static boolean serverRadi = true;
	@Override
	public void run() {
		try 
		{
			ServerSocket ss = new ServerSocket(9000);
			System.out.println("Server je pokrenut!");
			while (serverRadi)
			{
				Socket klijent = ss.accept();
				System.out.println("Klijent se povezao");
				ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(klijent);
				okz.start();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
}

package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class KomunikacijaSaServerom 
{
	private static KomunikacijaSaServerom instanca;
	private Socket socket;
	
	private KomunikacijaSaServerom() {
		try 
		{
			socket = new Socket("localhost", 9000);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static KomunikacijaSaServerom getInstanca()
	{
		if (instanca == null)
			instanca = new KomunikacijaSaServerom();
		return instanca;
	}
	
	public void posaljiZahtev(KlijentskiZahtev kz)
	{
		try 
		{
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(kz);
			oos.flush();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public ServerskiOdgovor primiOdgovor()
	{
		ServerskiOdgovor so = new ServerskiOdgovor();
		try 
		{
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			so = (ServerskiOdgovor) ois.readObject();
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		return so;
	}
}

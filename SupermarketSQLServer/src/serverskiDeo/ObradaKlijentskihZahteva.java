package serverskiDeo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import db.Kontroler;
import komunikacija.KlijentskiZahtev;
import komunikacija.ServerskiOdgovor;
import konstante.Operacije;
import supermarket.Artikal;
import supermarket.Magacin;
import supermarket.StavkaZaRacun;

public class ObradaKlijentskihZahteva extends Thread
{
	private Socket socket;
	private boolean kraj = false;

	public ObradaKlijentskihZahteva(Socket socket) 
	{
		super();
		this.socket = socket;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		while (!kraj)
		{
			KlijentskiZahtev kz = primiZahtev();
			ServerskiOdgovor so = new ServerskiOdgovor();
			switch (kz.getOperacija())
			{
				case Operacije.POPUNI_LISTU_ARTIKLA:
					ArrayList<Artikal> lista = Kontroler.getInstanca().dajListuArtikla((Integer) kz.getObjekat());
					so.setObjekat(lista);
					break;
				case Operacije.POPUNI_LISTU_MAGACINA:
					ArrayList<Magacin> listaMagacina = Kontroler.getInstanca().dajListuMagacina();
					so.setObjekat(listaMagacina);
					break;
				case Operacije.SMANJI_ZALIHE_U_MAGACINU:
					boolean umanjeno;
					umanjeno = Kontroler.getInstanca().umanjiBrojArtiklaUMagacinu((List<StavkaZaRacun>) kz.getObjekat());
					so.setObjekat(umanjeno);
					break;
			}
			posaljiOdgovorKlijentu(so);
		}
	}

	private void posaljiOdgovorKlijentu(ServerskiOdgovor so) 
	{
		try 
		{
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(so);
		} 
		catch (IOException e) 
		{
			//e.printStackTrace();
		}
	}

	private KlijentskiZahtev primiZahtev() 
	{
		KlijentskiZahtev kz = new KlijentskiZahtev();
		try 
		{
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			kz = (KlijentskiZahtev) ois.readObject();
		} 
		catch (IOException e) 
		{
			//e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		return kz;
	}
	
}

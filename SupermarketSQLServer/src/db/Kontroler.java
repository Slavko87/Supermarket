package db;

import java.util.ArrayList;
import java.util.List;

import supermarket.Artikal;
import supermarket.Magacin;
import supermarket.Racun;
import supermarket.StavkaZaRacun;

public class Kontroler 
{
	private static Kontroler instanca;
	private Database db = Database.getKonekcijaNaBazu();
	
	private Kontroler() {}
	
	public static Kontroler getInstanca() 
	{
		if (instanca == null)
			instanca = new Kontroler();
		return instanca;
	}

	public ArrayList<Artikal> dajListuArtikla(Integer broj) 
	{
		db.otvoriKonekciju();
		ArrayList<Artikal> lista = db.dajListuArtikla(broj);
		db.zatvoriKonekciju();
		return lista;
	}
	
	public ArrayList<Magacin> dajListuMagacina()
	{
		db.otvoriKonekciju();
		ArrayList<Magacin> lista = db.dajListuMagacina();
		db.zatvoriKonekciju();
		return lista;
	}

	public void umanjiBrojArtiklaUMagacinu(List<StavkaZaRacun> listaStavki) 
	{
		db.otvoriKonekciju();
		db.umanjiBrojArtiklaUMagacinu(listaStavki);
		db.zatvoriKonekciju();
	}
	
	public ArrayList<Racun> dajListuRacuna (String vreme)
	{
		db.otvoriKonekciju();
		ArrayList<Racun> lista = db.dajListuRacuna(vreme);
		db.zatvoriKonekciju();
		return lista;
	}

}

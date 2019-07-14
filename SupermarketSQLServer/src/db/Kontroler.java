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

	public boolean umanjiBrojArtiklaUMagacinu(List<StavkaZaRacun> listaStavki) 
	{
		boolean b;
		db.otvoriKonekciju();
		b = db.umanjiBrojArtiklaUMagacinu(listaStavki);
		db.zatvoriKonekciju();
		return b;
	}
	
	public ArrayList<Racun> dajListuRacuna (String vremeOD, String vremeDO)
	{
		db.otvoriKonekciju();
		ArrayList<Racun> lista = db.dajListuRacuna(vremeOD, vremeDO);
		db.zatvoriKonekciju();
		return lista;
	}

	public boolean ubaciNovArtikal(Artikal a) 
	{
		boolean ubacen;
		db.otvoriKonekciju();
		ubacen = db.ubaciNovArtikal(a);
		db.zatvoriKonekciju();
		return ubacen;
	}

}

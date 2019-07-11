package supermarket;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class Racun implements Serializable
{
	private int idRacuna;
	private HashMap<Artikal, Integer> listaStavki = new HashMap<>();
	private double ukupanIznosRacuna;
	private Date datumKreiranjaRacuna;
	
	public Racun() {super();}

	public Racun(int idRacuna, HashMap<Artikal, Integer> listaStavki, double ukupanIznosRacuna, Date datumKreiranjaRacuna) 
	{
		super();
		this.idRacuna = idRacuna;
		this.listaStavki = listaStavki;
		this.ukupanIznosRacuna = ukupanIznosRacuna;
		this.datumKreiranjaRacuna = new Date();
	}
	
	public boolean dodajArtikalNaRacun(Artikal artikal, Integer kolicina, Magacin magacin)
	{
		boolean dodatArtikal = false;
		Artikal zeljeniArtikal = null;
		
		for (Map.Entry<Artikal, Integer> e : magacin.getListaArtikla().entrySet())
		{
			if (e.getKey().equals(artikal))
			{
				zeljeniArtikal = artikal;
				if (e.getValue() >= kolicina)
				{
					this.listaStavki.put(zeljeniArtikal, kolicina);
					e.setValue(e.getValue() - kolicina);
					dodatArtikal = true;
				}
			}
		}
		return dodatArtikal;
	}
	
	public double ukupanRacun ()
	{
		double ukupanRacun = 0;
		for (Map.Entry<Artikal, Integer> e : listaStavki.entrySet()) 
			ukupanRacun += e.getValue();
		
		return ukupanRacun;
	}

	public int getIdRacuna() {return idRacuna;}
	public void setIdRacuna(int idRacuna) {this.idRacuna = idRacuna;}
	public double getUkupanIznosRacuna() {return ukupanIznosRacuna;}
	public void setUkupanIznosRacuna(double ukupanIznosRacuna) {this.ukupanIznosRacuna = ukupanIznosRacuna;}
	public Date getDatumKreiranjaRacuna() {return datumKreiranjaRacuna;}
	public void setDatumKreiranjaRacuna(Date datumKreiranjaRacuna) {this.datumKreiranjaRacuna = datumKreiranjaRacuna;}
	public HashMap<Artikal, Integer> getListaStavki() {return listaStavki;}
	public void setListaStavki(HashMap<Artikal, Integer> listaStavki) {this.listaStavki = listaStavki;}
	
}

package supermarket;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StavkaZaRacun implements Serializable
{
	private Artikal artikal;
	private int kolicina;
	private double ukupnaCena;
	private int idMagacina;
	
	public StavkaZaRacun() {super();}
	
	public StavkaZaRacun(Artikal artikal, int kolicina, int idMagacina) 
	{
		super();
		this.artikal = artikal;
		this.kolicina = kolicina;
		this.idMagacina = idMagacina;
		this.ukupnaCena = this.artikal.getCena() * kolicina;
	}
	
	public Artikal getArtikal() {return artikal;}
	public void setArtikal(Artikal artikal) {this.artikal = artikal;}
	public int getKolicina() {return kolicina;}
	public void setKolicina(int kolicina) {this.kolicina = kolicina;}
	public double getUkupnaCena() {return ukupnaCena;}
	public void setUkupnaCena(double ukupnaCena) {this.ukupnaCena = ukupnaCena;}
	public int getIdMagacina() {return idMagacina;}
	public void setIdMagacina(int idMagacina) {this.idMagacina = idMagacina;}

	//potrebno za ispis tabele kod klijenta-kase
	public int getSifraArtikla() {return artikal.getSifraArtikla();}
	public String getNazivArtikla() {return artikal.getNazivArtikla();}
	public double getCenaArtikla() {return artikal.getCena();}

	@Override
	public boolean equals(Object obj) 
	{
		if ((obj instanceof StavkaZaRacun) != true)
			return false;
		
		StavkaZaRacun szr = (StavkaZaRacun) obj;
		
		if (szr.getArtikal().getSifraArtikla() == this.getArtikal().getSifraArtikla() && szr.idMagacina == this.idMagacina)
			return true;
		else
			return false;
	}
	
}

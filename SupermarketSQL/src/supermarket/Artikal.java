package supermarket;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Artikal implements Serializable
{
	private int sifraArtikla;
	private String barKodArtikla;
	private String nazivArtikla;
	private double cena; 
	
	public Artikal() {super();}
	
	public Artikal(int sifraArtikla, String barKodArtikla, String nazivArtikla, double cena)
	{
		super();
		this.sifraArtikla = sifraArtikla;
		this.barKodArtikla = barKodArtikla;
		this.nazivArtikla = nazivArtikla;
		this.cena = cena;
	}
	
	public void ubaciArtikalUMagacin (Magacin magacin, int kolicina)
	{
		magacin.dodajArtikalUMagacin(this, kolicina);
	}
	
	public int getSifraArtikla() {return sifraArtikla;}
	public void setSifraArtikla(int sifraArtikla) {this.sifraArtikla = sifraArtikla;}
	public String getBarKodArtikla() {return barKodArtikla;}
	public void setBarKodArtikla(String barKodArtikla) {this.barKodArtikla = barKodArtikla;}
	public String getNazivArtikla() {return nazivArtikla;}
	public void setNazivArtikla(String nazivArtikla) {this.nazivArtikla = nazivArtikla;}
	public double getCena() {return cena;}
	public void setCena(double cena) {this.cena = cena;}

	@Override
	public String toString() 
	{
		return this.sifraArtikla + " " + this.nazivArtikla + " " + this.barKodArtikla;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if ((obj instanceof Artikal) != true)
			return false;
		
		Artikal artikal = (Artikal) obj;
		
		if (artikal.getBarKodArtikla() == this.getBarKodArtikla() && artikal.getSifraArtikla() == this.getSifraArtikla())
			return true;
		else
			return false;
	}
}

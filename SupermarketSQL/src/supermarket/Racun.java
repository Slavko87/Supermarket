package supermarket;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;

@SuppressWarnings("serial")
public class Racun implements Serializable
{
	private int idRacuna;
	private ArrayList<StavkaZaRacun> listaStavki = new ArrayList<>();
	private double ukupanIznosRacuna;
	private Timestamp datumKreiranjaRacuna;
	private ComboBox<String> comboStavke = new ComboBox<>();
	
	public Racun() {super();}

	public Racun(int idRacuna, ArrayList<StavkaZaRacun> listaStavki, double ukupanIznosRacuna, Timestamp datumKreiranjaRacuna) 
	{
		super();
		this.idRacuna = idRacuna;
		this.listaStavki = listaStavki;
		this.ukupanIznosRacuna = ukupanIznosRacuna;
		this.datumKreiranjaRacuna = datumKreiranjaRacuna;
		popuniComboStavke();
	}
	
	private void popuniComboStavke()
	{
		for (StavkaZaRacun stavkaZaRacun : listaStavki) 
		{
			String s = stavkaZaRacun.getNazivArtikla() + " -kol: " + stavkaZaRacun.getKolicina();
			comboStavke.getItems().add(s);
		}
	}
	
	public double ukupanRacun ()
	{
		double ukupanRacun = 0;
		for (StavkaZaRacun stavkaZaRacun : listaStavki) 
			ukupanRacun += stavkaZaRacun.getCenaArtikla() * stavkaZaRacun.getKolicina();
		
		return ukupanRacun;
	}
	
	@Override
	public String toString() 
	{
		return "ID racuna " + this.idRacuna + ", iznos: " + this.ukupanIznosRacuna + ", datum " + this.datumKreiranjaRacuna.toString().substring(0, 19);
	}

	public int getIdRacuna() {return idRacuna;}
	public void setIdRacuna(int idRacuna) {this.idRacuna = idRacuna;}
	public double getUkupanIznosRacuna() {return ukupanIznosRacuna;}
	public void setUkupanIznosRacuna(double ukupanIznosRacuna) {this.ukupanIznosRacuna = ukupanIznosRacuna;}
	public Timestamp getDatumKreiranjaRacuna() {return datumKreiranjaRacuna;}
	public void setDatumKreiranjaRacuna(Timestamp datumKreiranjaRacuna) {this.datumKreiranjaRacuna = datumKreiranjaRacuna;}
	public ComboBox<String> getComboStavke() {return comboStavke;}
	public ArrayList<StavkaZaRacun> getListaStavki() {return listaStavki;}
	public void setListaStavki(ArrayList<StavkaZaRacun> listaStavki) {this.listaStavki = listaStavki;}
	public void setComboStavke(ComboBox<String> comboStavke) {this.comboStavke = comboStavke;}

	//za tabelu potrebno
	public int getBrojStavkiNaRacunu() {return listaStavki.size();}
	
}

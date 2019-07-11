package supermarket;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;

@SuppressWarnings("serial")
public class Magacin implements Serializable
{
	private int idMagacina;
	private String naziv;
	private HashMap<Artikal, Integer> listaArtikla = new HashMap<>();

	public Magacin() {super();}

	public Magacin(int idMagacina, String naziv, HashMap<Artikal, Integer> listaArtikla) 
	{
		super();
		this.idMagacina = idMagacina;
		this.naziv = naziv;
		this.listaArtikla = listaArtikla;
	}
	
	public void dodajArtikalUMagacin(Artikal artikal, Integer kolicina)
	{
		this.listaArtikla.put(artikal, kolicina);
	}
	//ako artikal ne postoji vraca isto 0
	public int proveriArtikalUMagacinu(Artikal artikal)
	{
		int kolicina = 0;
		for (Entry<Artikal, Integer> entry : listaArtikla.entrySet())
		{
			//kada se stavi equals nece da radi...
			if (entry.getKey().getSifraArtikla() == artikal.getSifraArtikla() && entry.getKey().getBarKodArtikla().equals(artikal.getBarKodArtikla()))
			{
				kolicina = entry.getValue();
			}
		}
		return kolicina;
	}
	

	public int getIdMagacina() {return idMagacina;}
	public void setIdMagacina(int idMagacina) {this.idMagacina = idMagacina;}
	public String getNaziv() {return naziv;}
	public void setNaziv(String naziv) {this.naziv = naziv;}
	public HashMap<Artikal, Integer> getListaArtikla() {return listaArtikla;}
	public void setListaArtikla(HashMap<Artikal, Integer> listaArtikla) {this.listaArtikla = listaArtikla;}
	
	@Override
	public String toString() 
	{
		return "ID magacina " + this.idMagacina + " - " + this.naziv + " - broj artikla: " + this.listaArtikla.size();
	}
}

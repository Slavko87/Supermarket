package forme;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import supermarket.StavkaZaRacun;

public class TabelaArtikla extends TableView<StavkaZaRacun>
{
	
	private List<StavkaZaRacun> listaStavki = new ArrayList<>();
	
	public TabelaArtikla (List<StavkaZaRacun> listaStavki)
	{
		this.listaStavki = listaStavki;
		srediTabelu();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void srediTabelu()
	{
		TableColumn kolonaSifraArtikla = new TableColumn("Sifra");
		kolonaSifraArtikla.setMaxWidth(46);
		kolonaSifraArtikla.setMinWidth(46);
		kolonaSifraArtikla.setCellValueFactory(new PropertyValueFactory<StavkaZaRacun, Integer>("SifraArtikla"));
		
		TableColumn kolonaNazivArtikla = new TableColumn("Naziv artikla");
		kolonaNazivArtikla.setMaxWidth(310);
		kolonaNazivArtikla.setMinWidth(310);
		kolonaNazivArtikla.setCellValueFactory(new PropertyValueFactory<StavkaZaRacun, Integer>("NazivArtikla"));
		
		TableColumn kolonaCena = new TableColumn("Cena artikla");
		kolonaCena.setMaxWidth(90);
		kolonaCena.setMinWidth(90);
		kolonaCena.setCellValueFactory(new PropertyValueFactory<StavkaZaRacun, Integer>("CenaArtikla"));
		
		TableColumn kolonaKolicina = new TableColumn("Kol");
		kolonaKolicina.setMaxWidth(45);
		kolonaKolicina.setMinWidth(45);
		kolonaKolicina.setCellValueFactory(new PropertyValueFactory<Integer, Integer>("Kolicina"));
		
		TableColumn kolonaUkupnaCena = new TableColumn("UCena");
		kolonaUkupnaCena.setMaxWidth(58);
		kolonaUkupnaCena.setMinWidth(58);
		kolonaUkupnaCena.setCellValueFactory(new PropertyValueFactory<StavkaZaRacun, Integer>("UkupnaCena"));
		
		this.getColumns().addAll(kolonaSifraArtikla, kolonaNazivArtikla, kolonaCena, kolonaKolicina, kolonaUkupnaCena);
		this.getItems().addAll(listaStavki);
	}
}

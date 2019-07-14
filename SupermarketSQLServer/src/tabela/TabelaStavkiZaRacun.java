package tabela;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import supermarket.Racun;
import supermarket.StavkaZaRacun;

public class TabelaStavkiZaRacun extends TableView <StavkaZaRacun>
{
	
	private Racun racun;
	
	public TabelaStavkiZaRacun (Racun racun)
	{
		this.racun = racun;
		srediTabelu();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void srediTabelu()
	{
		TableColumn idArtikla = new TableColumn("Sifra artikla");
		idArtikla.setMaxWidth(70);
		idArtikla.setMinWidth(70);
		idArtikla.setCellValueFactory(new PropertyValueFactory<StavkaZaRacun, Integer>("sifraArtikla"));
		
		TableColumn nazivArtikla = new TableColumn("Naziv artikla");
		nazivArtikla.setMaxWidth(160);
		nazivArtikla.setMinWidth(160);
		nazivArtikla.setCellValueFactory(new PropertyValueFactory<StavkaZaRacun, String>("nazivArtikla"));
		
		TableColumn cenaArtikla = new TableColumn("Cena artikla");
		cenaArtikla.setMaxWidth(90);
		cenaArtikla.setMinWidth(90);
		cenaArtikla.setCellValueFactory(new PropertyValueFactory<StavkaZaRacun, Double>("cenaArtikla"));
		
		TableColumn kolicina = new TableColumn("Kolicina");
		kolicina.setMaxWidth(75);
		kolicina.setMinWidth(75);
		kolicina.setCellValueFactory(new PropertyValueFactory<StavkaZaRacun, Integer>("kolicina"));
		
		this.getColumns().addAll(idArtikla, nazivArtikla, cenaArtikla, kolicina);
		if (racun != null)
			this.getItems().addAll(racun.getListaStavki());
	}
}

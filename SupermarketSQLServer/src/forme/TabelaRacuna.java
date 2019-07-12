package forme;

import java.sql.Timestamp;
import java.util.ArrayList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import supermarket.Racun;

public class TabelaRacuna extends TableView<Racun>
{
	private ArrayList<Racun> listaRacuna = new ArrayList<>();
	
	public TabelaRacuna (ArrayList<Racun> listaRacuna)
	{
		this.listaRacuna = listaRacuna;
		srediTabelu();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void srediTabelu()
	{
		TableColumn idRacuna = new TableColumn("ID");
		idRacuna.setMaxWidth(40);
		idRacuna.setMinWidth(40);
		idRacuna.setCellValueFactory(new PropertyValueFactory<Racun, Integer>("idRacuna"));
		
		TableColumn brojStavki = new TableColumn("Broj stavki");
		brojStavki.setMaxWidth(70);
		brojStavki.setMinWidth(70);
		brojStavki.setCellValueFactory(new PropertyValueFactory<Racun, Integer>("brojStavkiNaRacunu"));
		
		TableColumn ukupanIznos = new TableColumn("Ukupan iznos");
		ukupanIznos.setMaxWidth(90);
		ukupanIznos.setMinWidth(90);
		ukupanIznos.setCellValueFactory(new PropertyValueFactory<Racun, Integer>("ukupanIznosRacuna"));
		
		TableColumn vreme = new TableColumn("Vreme izdavanja racuna");
		vreme.setMaxWidth(200);
		vreme.setMinWidth(200);
		vreme.setCellValueFactory(new PropertyValueFactory<Racun, Timestamp>("datumKreiranjaRacuna"));
		
		this.getColumns().addAll(idRacuna, brojStavki, ukupanIznos, vreme);
		this.getItems().addAll(listaRacuna);
	}
}

package forme;

import java.util.ArrayList;
import javax.swing.JFrame;

import db.Kontroler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import supermarket.Racun;
import tabela.TabelaStavkiZaRacun;
import util.Konstante;
import util.Metode;

@SuppressWarnings("serial")
public class PregledRacuna extends JFrame 
{
	
	private Stage stage = new Stage();
	private Scene scene;
	private ArrayList<Racun> listaRacuna = Kontroler.getInstanca().dajListuRacuna(Konstante.od1900godine, Konstante.do2100godine);
	private ComboBox<Racun> comboListaRacuna = Metode.napuniComboBox(listaRacuna);
	private Racun racun = null;
	private TabelaStavkiZaRacun tabela = new TabelaStavkiZaRacun(racun);
	private TextField idRacuna = new TextField();

	public PregledRacuna ()
	{
		super("Pregled racuna");
		stage.setTitle("Pregled racuna");
		VBox glavniBox = new VBox(10);
		glavniBox.setPadding(new Insets(10, 10, 10, 10));
		
		comboListaRacuna.setMinWidth(400);
		glavniBox.getChildren().add(comboListaRacuna);
		
		HBox hbox = new HBox(10);
		
		idRacuna.setMinWidth(40);
		idRacuna.setMaxWidth(40);
		idRacuna.setOnKeyReleased(e -> {
			if (idRacuna.getText().equals("") == false)
				filtrirajPoIDracuna(Integer.valueOf(idRacuna.getText()));
			else
				ucitajRacun();
		});
		hbox.getChildren().add(idRacuna);
		
		Button ucitaj = new Button("UCITAJ");
		ucitaj.setMinWidth(150);
		ucitaj.setMaxWidth(150);
		hbox.getChildren().add(ucitaj);
		ucitaj.setOnAction(e -> ucitajRacun());
				
		glavniBox.getChildren().add(hbox);
		glavniBox.getChildren().add(tabela);
		
		scene = new Scene(glavniBox, 420, 400);
		stage.setScene(scene);
		stage.show();
	}
	
	private void ucitajRacun()
	{
		racun = comboListaRacuna.getSelectionModel().getSelectedItem();
		
		if (racun != null)
		{
			tabela.getItems().clear();
			tabela.getItems().addAll(racun.getListaStavki());
			tabela.refresh();
		}
	}
	
	private void filtrirajPoIDracuna(int filter)
	{
		comboListaRacuna.getItems().clear();
		for (Racun racun : listaRacuna) 
		{
			if (racun.getIdRacuna() == filter)
			{
				comboListaRacuna.getItems().add(racun);
				comboListaRacuna.getSelectionModel().selectFirst();
			}
		}
	}
	
	public Scene getScene() {return scene;}
	
}

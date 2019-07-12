package forme;

import java.util.ArrayList;
import javax.swing.JFrame;
import db.Kontroler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import serverskiDeo.PokreniServer;
import supermarket.Racun;
import util.Util;

@SuppressWarnings("serial")
public class ServerskaForma extends JFrame
{
	
	private Scene scena;
	private PokreniServer ps;
		
	private Button dodajArtikal = new Button("Dodaj nov artikal");
	private Button prikaziArtikle = new Button("Prikazi artikle");
	
	private Button dodajMagacin = new Button("Dodaj nov magacin");
	private Button stanjeMagacina = new Button("Stanje magacina");
	private Button dodajArtikleNaStanje = new Button ("Dodaj artikal na stanje");
	
	private Button pregledRacuna = new Button("Pregled racuna");
	private Button pregledPazara = new Button("Pregled pazara");
	
	private ArrayList<Racun> listaRacuna = new ArrayList<>();
	private TabelaRacuna tabelaRacuna;
	
	private String trenutnoVreme = Util.trenutnoVreme;
	
	public ServerskaForma()
	{
		super("SERVER");
		ps = new PokreniServer();
		ps.start();
		
		tabelaRacuna = new TabelaRacuna(listaRacuna);
		tabelaRacuna.autosize();
		
		OsveziTabelu ot = new OsveziTabelu(this);
		ot.start();
		
		VBox glavniBox = new VBox(10);
		glavniBox.setPadding(new Insets(10, 10, 10, 10));
		
		HBox artikalBox = new HBox(10);
		Label artikal = new Label("ARTIKAL");
		artikal.setStyle("-fx-text-fill: RED; -fx-background-color: rgb(244,244,244); -fx-translate-y: -19; -fx-translate-x: 252");
		artikalBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
		artikalBox.setPadding(new Insets(10, 10, 10, 10));
		dodajArtikal.setMinWidth(150);
		prikaziArtikle.setMinWidth(150);
		dodajArtikal.setStyle("-fx-translate-x: 10");
		prikaziArtikle.setStyle("-fx-translate-x: 113");
		artikalBox.getChildren().addAll(artikal, dodajArtikal, prikaziArtikle);
		glavniBox.getChildren().add(artikalBox);
		
		HBox magacinBox = new HBox(10);
		Label magacin = new Label("MAGACIN");
		magacin.setStyle("-fx-text-fill: RED; -fx-background-color: rgb(244,244,244); -fx-translate-y: -19; -fx-translate-x: 245");
		magacinBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
		magacinBox.setPadding(new Insets(10, 10, 10, 10));
		dodajMagacin.setMinWidth(130);
		stanjeMagacina.setMinWidth(130);
		dodajArtikleNaStanje.setMinWidth(130);
		magacinBox.getChildren().addAll(magacin, dodajMagacin, stanjeMagacina, dodajArtikleNaStanje);
		glavniBox.getChildren().add(magacinBox);
		
		HBox racunBox = new HBox(10);
		Label racun = new Label("RACUN");
		racun.setStyle("-fx-text-fill: RED; -fx-background-color: rgb(244,244,244); -fx-translate-y: -19; -fx-translate-x: 252");
		racunBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
		racunBox.setPadding(new Insets(10, 10, 10, 10));
		pregledRacuna.setMinWidth(150);
		pregledPazara.setMinWidth(150);
		pregledRacuna.setStyle("-fx-translate-x: 10");
		pregledPazara.setStyle("-fx-translate-x: 113");
		racunBox.getChildren().addAll(racun, pregledRacuna, pregledPazara);
		glavniBox.getChildren().add(racunBox);
		
		VBox racunUzivoBox = new VBox(10);
		racunUzivoBox.setMaxWidth(400);
		
		Label racunUzivo = new Label("RACUNI UZIVO");
		racunUzivo.setStyle("-fx-text-fill: RED; -fx-background-color: rgb(244,244,244); -fx-translate-y: 0; -fx-translate-x: 165");
		racunUzivoBox.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
		tabelaRacuna.setMinHeight(330);
		tabelaRacuna.setMaxHeight(330);
		tabelaRacuna.autosize();
		racunUzivoBox.getChildren().addAll(racunUzivo, tabelaRacuna);
		VBox.setMargin(racunUzivoBox, new Insets(0, 0, 0, 80));
		glavniBox.getChildren().add(racunUzivoBox);
		
		scena = new Scene(glavniBox, 590, 600);
	}
	
	public ArrayList<Racun> popuniListuRacuna(String vreme)
	{
		ArrayList<Racun> lista = Kontroler.getInstanca().dajListuRacuna(vreme);
		return lista;
	}
	
	public void refresujTabeluSaRacunima ()
	{
		listaRacuna = popuniListuRacuna(trenutnoVreme);
		tabelaRacuna.getItems().clear();
		tabelaRacuna.getItems().addAll(listaRacuna);
		tabelaRacuna.refresh();
	}

	public Scene getScena() {return scena;}

}

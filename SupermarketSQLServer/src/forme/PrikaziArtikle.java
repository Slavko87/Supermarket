package forme;

import javax.swing.JFrame;

import db.Kontroler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import supermarket.Artikal;
import util.Metode;

@SuppressWarnings("serial")
public class PrikaziArtikle extends JFrame
{

	private Stage stage = new Stage();
	private Scene scene;
	
	private Button ucitajArtikal = new Button("Ucitaj artikal");
	private ComboBox<Artikal> listaArtikla = new ComboBox<>();
	private Artikal izabranArtikal;
	
	public PrikaziArtikle()
	{
		super("Pregled artikla");
		stage.setTitle("Pregled artikla");
		VBox glavniBox = new VBox(10);
		glavniBox.setPadding(new Insets(10, 10, 10, 10));
		
		ucitajArtikal.setMinWidth(110);
		ucitajArtikal.setMaxWidth(110);
		
		listaArtikla = Metode.napuniComboBox(Kontroler.getInstanca().dajListuArtikla(0));
		
		HBox artikal = new HBox(10);
		artikal.getChildren().add(listaArtikla);
		artikal.getChildren().add(ucitajArtikal);
		glavniBox.getChildren().add(artikal);
		
		ucitajArtikal.setOnAction(e -> {
			izabranArtikal = listaArtikla.getSelectionModel().getSelectedItem();
			otvoriArtikal();
		});
		
//		HBox artikalBox = new HBox(10);
//		TextField sifraArtikla = new TextField();
//		TextField barKodArtikla = new TextField();
//		TextField NazivArtikla = new TextField();
//		TextField CenaArtikla = new TextField();
//		sifraArtikla.setEditable(false);
		
		scene = new Scene(glavniBox, 425, 60);
		stage.setScene(scene);
		stage.show();
	}
	
	private void otvoriArtikal()
	{
		DodajNovArtikal dna = new DodajNovArtikal(true);
		dna.getSifraTF().setEditable(false);
		dna.getDodaj().setText("Zapamti");
		if (izabranArtikal != null)
		{
			dna.getSifraTF().setText(String.valueOf(izabranArtikal.getSifraArtikla()));
			dna.getBarKodArtiklaTF().setText(izabranArtikal.getBarKodArtikla());
			dna.getNazivArtiklaTF().setText(izabranArtikal.getNazivArtikla());
			dna.getCenaTF().setText(String.valueOf(izabranArtikal.getCena()));
		}
		
	}

	public Scene getScene() {return scene;}
	
}

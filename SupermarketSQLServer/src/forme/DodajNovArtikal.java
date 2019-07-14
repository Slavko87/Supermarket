package forme;

import javax.swing.JFrame;
import db.Kontroler;
import db.UtilDB;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import supermarket.Artikal;
import util.Regex;

@SuppressWarnings("serial")
public class DodajNovArtikal extends JFrame
{
	private Stage stage = new Stage();
	private Scene scene;
	
	private TextField sifraTF = new TextField();
	private TextField barKodArtiklaTF = new TextField();
	private TextField nazivArtiklaTF = new TextField();
	private TextField cenaTF = new TextField();
	
	private Button dodaj = new Button("Dodaj");
	
	public DodajNovArtikal ()
	{
		super("Dodaj nov artikal");
		stage.setTitle("Dodaj nov artikal");
		VBox glavniBox = new VBox(10);
		glavniBox.setPadding(new Insets(10, 10, 10, 10));
		
		Label sifraL = new Label("Sifra artikla");
		sifraL.setMinWidth(100);
		sifraTF.setMinWidth(50);
		sifraTF.setMaxWidth(50);
		
		Label barKodArtiklaL = new Label("Barkod artikla");
		barKodArtiklaL.setMinWidth(100);
		barKodArtiklaTF.setMinWidth(200);
		
		Label nazivArtiklaL = new Label("Naziv artikla");
		nazivArtiklaL.setMinWidth(100);
		nazivArtiklaTF.setMinWidth(200);
		
		Label cenaL = new Label("Cena");
		cenaL.setMinWidth(100);
		cenaTF.setMinWidth(100);
		cenaTF.setMaxWidth(100);
		
		dodaj.setMinWidth(150);
		dodaj.setMinHeight(30);
		VBox.setMargin(dodaj, new Insets(0, 0, 0, 100));
		dodaj.setOnAction(e -> dodajUBazu());
		
		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);
		gp.add(sifraL, 0, 0);
		gp.add(sifraTF, 1, 0);
		gp.add(barKodArtiklaL, 0, 1);
		gp.add(barKodArtiklaTF, 1, 1);
		gp.add(nazivArtiklaL, 0, 2);
		gp.add(nazivArtiklaTF, 1, 2);
		gp.add(cenaL, 0, 3);
		gp.add(cenaTF, 1, 3);
		
		glavniBox.getChildren().add(gp);
		glavniBox.getChildren().add(dodaj);
		
		scene = new Scene(glavniBox, 350, 200);
		stage.setScene(scene);
		stage.show();
	}
	
	public void dodajUBazu()
	{
		
		if (sifraTF.getText().isEmpty() == false && sifraTF.getText().matches(Regex.regZaBrojeve) == false)
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setHeaderText("Niste ispravno uneli sifru artikla");
			alert.showAndWait();
		}
		else if (nazivArtiklaTF.getText().isEmpty())
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setHeaderText("Niste uneli naziv artikla");
			alert.showAndWait();
		}
		else if (cenaTF.getText().isEmpty())
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setHeaderText("Niste uneli cenu artikla");
			alert.showAndWait();
		}
		else if (cenaTF.getText().matches(Regex.regZaDouble) == false)
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setHeaderText("Niste ispravno uneli cenu artikla");
			alert.showAndWait();
		}
		else
		{
			Artikal a;
			if (sifraTF.getText().equals(""))
			{
				int id = UtilDB.vratiPoslednjiID("artikal", "sifraArtikla") + 1;
				a = new Artikal(id, barKodArtiklaTF.getText(), nazivArtiklaTF.getText(), Double.valueOf(cenaTF.getText()));
			}
			else
				a = new Artikal(Integer.valueOf(sifraTF.getText()), barKodArtiklaTF.getText(), nazivArtiklaTF.getText(), Double.valueOf(cenaTF.getText()));
				
			boolean ubacen = Kontroler.getInstanca().ubaciNovArtikal(a);
			if (ubacen == true)
			{
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setHeaderText("Nov artikal je dodat");
				alert.showAndWait();
			}
			else
			{
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setHeaderText("Doslo je do greske sa bazom prilikom dodavanja artikla. Artikal nije dodat!");
				alert.showAndWait();
			}
		}
	}

	public Scene getScene() {return scene;}
}

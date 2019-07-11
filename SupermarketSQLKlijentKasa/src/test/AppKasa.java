package test;

import forme.KlijentskaForma;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppKasa extends Application
{
	
	KlijentskaForma kf = new KlijentskaForma();

	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception 
	{
		try
		{
			stage.setMaxHeight(900);
			stage.setMaxWidth(590);
			stage.setMinHeight(600);
			stage.setMinWidth(590);
			stage.setTitle("KASA");
			stage.setScene(kf.getScena());
			stage.show();
			
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}

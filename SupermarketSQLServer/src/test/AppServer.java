package test;

import forme.ServerskaForma;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppServer extends Application
{
	ServerskaForma sf = new ServerskaForma();

	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception 
	{
		try
		{
			stage.setMaxHeight(500);
			stage.setMaxWidth(590);
			stage.setMinHeight(500);
			stage.setMinWidth(590);
			stage.setTitle("SERVER");
			stage.setScene(sf.getScena());
			stage.show();
			
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}

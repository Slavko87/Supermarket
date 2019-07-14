package util;

import java.util.ArrayList;

import javafx.scene.control.ComboBox;

public class Metode 
{

	@SuppressWarnings("unchecked")
	public static <T> ComboBox<T> napuniComboBox(ArrayList<T> lista)
	{
		ComboBox<T> comboLista = new ComboBox<>();
		for (Object object : lista) 
		{
			comboLista.getItems().add((T) object);
		}
		return comboLista;
	}
}

package komunikacija;

import java.io.Serializable;

@SuppressWarnings("serial")
public class KlijentskiZahtev implements Serializable
{
	private int operacija;
	private Object objekat;
	
	public KlijentskiZahtev() {super();
	}
	public KlijentskiZahtev(int operacina, Object objekat) 
	{
		super();
		this.operacija = operacina;
		this.objekat = objekat;
	}
	
	public int getOperacija() {return operacija;}
	public void setOperacija(int operacija) {this.operacija = operacija;}
	public Object getObjekat() {return objekat;}
	public void setObjekat(Object objekat) {this.objekat = objekat;}
	
}

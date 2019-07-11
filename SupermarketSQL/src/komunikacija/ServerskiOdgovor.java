package komunikacija;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ServerskiOdgovor implements Serializable
{
	private String poruka;
	private Object objekat;
	
	public ServerskiOdgovor() {super();}
	
	public ServerskiOdgovor(String poruka, Object objekat) 
	{
		super();
		this.poruka = poruka;
		this.objekat = objekat;
	}
	
	public String getPoruka() {return poruka;}
	public void setPoruka(String poruka) {this.poruka = poruka;}
	public Object getObjekat() {return objekat;}
	public void setObjekat(Object objekat) {this.objekat = objekat;}
	
}

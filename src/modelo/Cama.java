package modelo;

public class Cama
{
	private static final String SOFACAMA = "sofacama";
	private static final String INFANTIL = "infantil";
	private static final String SENCILLA = "sencilla";
	private static final String DOBLE = "doble";
	private String tipo;
	private int capacidadAdultos;
	private int capacidadNinios;
	
	public Cama()
	{
		
	}
	
	public Cama(String tipo)
	{
		this.tipo = tipo;
		
		if (this.tipo == DOBLE)
		{
			capacidadAdultos = 2;
			capacidadNinios = 3;
		}
		else if (this.tipo == SENCILLA)
		{
			capacidadAdultos = 1;
			capacidadNinios = 2;
		}
		else if (this.tipo == INFANTIL)
		{
			capacidadAdultos = 0;
			capacidadNinios = 1;
		}
		else if (this.tipo == SOFACAMA)
		{
			capacidadAdultos = 1;
			capacidadNinios = 1;
		}	
	}

	public String getTipo()
	{
		return tipo;
	}

	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	public int getCapacidadAdultos()
	{
		return capacidadAdultos;
	}

	public void setCapacidadAdultos(int capacidadAdultos)
	{
		this.capacidadAdultos = capacidadAdultos;
	}

	public int getCapacidadNinios()
	{
		return capacidadNinios;
	}

	public void setCapacidadNinios(int capacidadNinios)
	{
		this.capacidadNinios = capacidadNinios;
	}
	
	public static String getSofacama()
	{
		return SOFACAMA;
	}

	public static String getInfantil()
	{
		return INFANTIL;
	}

	public static String getSencilla()
	{
		return SENCILLA;
	}

	public static String getDoble()
	{
		return DOBLE;
	}

	@Override
	public String toString()
	{
		return tipo + ", " + capacidadAdultos + " adultos" + ", " + capacidadNinios + " niños";
	}

}

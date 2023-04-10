package modelo;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Habitacion
{
	private String tipo;
	private boolean tieneCocina;
	private boolean tieneBalcon;
	private boolean tieneVista;
	private String torre;
	private int piso;
	private String id;
	private ArrayList<Cama> camas;
	private int capacidadAdultos;
	private int capacidadNinios;
	// private HashMap<String, Huesped> huespedes;
	// private Reserva reservaActual;
	private HashMap<String, Reserva> reservas;
	
	public Habitacion()
	{
		
	}
	
	
	public Habitacion(String tipo, boolean cocina, boolean balcon, boolean vista, String torre, int piso, String id)
	{
		this.tipo = tipo;
		this.tieneCocina = cocina;
		this.tieneBalcon = balcon;
		this.tieneVista = vista;
		this.torre = torre;
		this.piso = piso;
		this.id = id;
		this.reservas = new HashMap<String, Reserva>();
	}


	public String getTipo()
	{
		return tipo;
	}

	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	public boolean isTieneCocina()
	{
		return tieneCocina;
	}

	public void setTieneCocina(boolean tieneCocina)
	{
		this.tieneCocina = tieneCocina;
	}

	public boolean isTieneBalcon()
	{
		return tieneBalcon;
	}

	public void setTieneBalcon(boolean tieneBalcon)
	{
		this.tieneBalcon = tieneBalcon;
	}

	public boolean isTieneVista()
	{
		return tieneVista;
	}

	public void setTieneVista(boolean tieneVista)
	{
		this.tieneVista = tieneVista;
	}

	public String getTorre()
	{
		return torre;
	}

	public void setTorre(String torre)
	{
		this.torre = torre;
	}

	public int getPiso()
	{
		return piso;
	}

	public void setPiso(int piso)
	{
		this.piso = piso;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public ArrayList<Cama> getCamas()
	{
		return camas;
	}

	public void setCamas(ArrayList<Cama> camas)
	{
		this.camas = camas;
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

	public HashMap<String, Reserva> getReservas()
	{
		return reservas;
	}


	public void setReservas(HashMap<String, Reserva> reservas)
	{
		this.reservas = reservas;
	}

	public void addReserva(Reserva reserva)
	{
		this.reservas.put(reserva.getId(), reserva);
	}
	
	@Override
	public String toString()
	{
		String info = "Habitacion " + tipo + " " + id + ":";
		
		info += "\n\tTorre: " + torre;
		info += "\n\tPiso: " + piso;
		
		info += "\n\tCamas:";
		for (Cama cama : camas)
			info += "\n\t\t" + cama.toString();
		
		info += "\n\tCapacidad adultos: " + capacidadAdultos;
		info += "\n\tCapacidad Niños: " + capacidadNinios;
		
		info += "\n\tTiene cocina? ";
		if (tieneCocina)
			info += "Si";
		else
			info += "No"; 
		
		info += "\n\tTiene balcón? ";
		if (tieneBalcon)
			info += "Si";
		else
			info += "No"; 
		
		info += "\n\tTiene vista? ";
		if (tieneVista)
			info += "Si";
		else
			info += "No"; 
		
		return info;
	}	
	
}

package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import controlador.Controlador;
import salvador.SalvadorDeDatos;

public class CoordinadorPMS
{
	private static final String ESTANDAR = "estandar";
	private static final String SUITE = "suite";
	private static final String SUITEDOBLE = "suitedoble";
	private Controlador controlador;
	private SalvadorDeDatos salvador;
	private HashMap<String, TarifasHabitacion> tarifasHotel;
	private HashMap<String, Habitacion> mapaHabitaciones;

	public CoordinadorPMS(Controlador controlador)
	{
		this.controlador = controlador;
		this.salvador = new SalvadorDeDatos();
		this.tarifasHotel = new HashMap<String, TarifasHabitacion>();
		tarifasHotel.put(ESTANDAR, new TarifasHabitacion(ESTANDAR));
		tarifasHotel.put(SUITE, new TarifasHabitacion(SUITE));
		tarifasHotel.put(SUITEDOBLE, new TarifasHabitacion(SUITEDOBLE));

	}
	
	public HashMap<String, TarifasHabitacion> getTarifasHotel()
	{
		return tarifasHotel;
	}

	public void setTarifasHotel(HashMap<String, TarifasHabitacion> tarifasHotel)
	{
		this.tarifasHotel = tarifasHotel;
	}
	
	public void agregarTarifa(String tipo, int valor, ArrayList<Integer> fechaInicial, ArrayList<Integer> fechaFinal, ArrayList<Integer> diasTarifa)
	{
		TarifasHabitacion tarifas = tarifasHotel.get(tipo);
		tarifas.addTarifaEnRangoFechas(valor, fechaInicial, fechaFinal, diasTarifa);
		salvador.salvarTarifasHabitacion(tarifas);
	}
	
	public void eliminarTarifas(String tipo, ArrayList<Integer> fechaInicial, ArrayList<Integer> fechaFinal, ArrayList<Integer> diasTarifa)
	{
		TarifasHabitacion tarifas = tarifasHotel.get(tipo);
		tarifas.eliminarTarifasEnRangoFechas(fechaInicial, fechaFinal, diasTarifa);
	}
	
	public String getFechasSinTarifaStr()
	{
		String fechasSinTarifa = "FECHAS SIN TARIFA";
		
		for (Entry<String, TarifasHabitacion> entry : tarifasHotel.entrySet())
		{
			TarifasHabitacion tarifas = entry.getValue();
			String tipo = tarifas.getTipoHabitacion();
			fechasSinTarifa += "\n" + tipo.toUpperCase() + ": \n";
			
			fechasSinTarifa += tarifas.fechasSinTarifa();
		}
		
		return fechasSinTarifa;
	}
	
	public boolean existeHabitacion(String id)
	{
		return mapaHabitaciones.containsKey(id);
	}

	public void putHabitacion(String tipo, boolean cocina, boolean balcon, boolean vista, String torre, int piso,
			String id)
	{
		Habitacion hab;
		if (tipo.equals(ESTANDAR))
			hab = (Habitacion) new HabitacionEstandar(cocina, balcon, vista, torre, piso, id);
		else if (tipo.equals(SUITE))
			hab = (Habitacion) new HabitacionSuite(cocina, balcon, vista, torre, piso, id);
		else
			hab = (Habitacion) new HabitacionSuiteDoble(cocina, balcon, vista, torre, piso, id);
		mapaHabitaciones.put(id, hab);
	}

	public String infoHabitacion(String id)
	{
		Habitacion habitacion = mapaHabitaciones.get(id);
		salvador.salvarHabitacion(habitacion);
		return habitacion.toString();
	}

	public void setHabitaciones(HashMap<String, Habitacion> mapaHabitaciones)
	{
		this.mapaHabitaciones = mapaHabitaciones;		
	}

	public boolean eliminarHabitacion(String id)
	{
		if (mapaHabitaciones.containsKey(id))
		{
			mapaHabitaciones.remove(id);
			salvador.borrarHabitacion(id);
			return true;
		}
		else
			return false;
	}

	public HashMap<String, Habitacion> getHabitaciones()
	{
		return mapaHabitaciones;
	}

	public String catalogoHabitaciones()
	{
		String catalogo = "";
		if (mapaHabitaciones.isEmpty())
			catalogo = "No hay habitaciones";
		else
		{
			ArrayList<Habitacion> listaHabitaciones = new ArrayList<Habitacion>();
			for (Entry<String, Habitacion> entry : mapaHabitaciones.entrySet())
				listaHabitaciones.add(entry.getValue());
			
			Collections.sort(listaHabitaciones, new ComparadorHabitaciones());
			
			for (Habitacion hab : listaHabitaciones)
				catalogo += hab.toString();
		}

		return catalogo;
	}
}

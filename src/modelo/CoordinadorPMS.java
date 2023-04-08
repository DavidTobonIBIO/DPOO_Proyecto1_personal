package modelo;

import java.util.ArrayList;
import java.util.HashMap;

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
}

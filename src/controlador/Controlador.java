package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import autenticador.AutenticadorDeUsuarios;
import autenticador.Usuario;
import cargador.CargadorDeDatos;
import consola.InterfazPrincipal;
import modelo.CoordinadorPMS;
import modelo.Habitacion;
import modelo.TarifasHabitacion;

public class Controlador
{
	private static final ArrayList<String> TIPOS_HABITACION = new ArrayList<String>(Arrays.asList("estandar", "suite", "suitedoble"));
	

	private static final ArrayList<String> DIAS_SEMANA = new ArrayList<String>(Arrays.asList("L", "M", "I", "J", "V", "S", "D"));
	private static final String[] STRINGS_DIAS_SEMANA = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
	private static final String[] STRINGS_MESES = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	private static final HashMap<String, Integer> MAPA_DIAS_SEMANA = crearMapaDiasSemana();
	private static final int[] DIASXMES = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private InterfazPrincipal interfaz;
	private AutenticadorDeUsuarios autenticador;
	private CargadorDeDatos cargador;
	private CoordinadorPMS coordinadorPMS;
	

	public Controlador(InterfazPrincipal interfaz, AutenticadorDeUsuarios autenticador)
	{
		this.interfaz = interfaz;
		this.autenticador = autenticador;
		this.cargador = new CargadorDeDatos(this);
		this.coordinadorPMS = new CoordinadorPMS(this);
	}
	
	public static ArrayList<String> getTiposHabitacion()
	{
		return TIPOS_HABITACION;
	}
	
	private static HashMap<String, Integer> crearMapaDiasSemana()
	{
		HashMap<String, Integer> mapaDiasSemana = new HashMap<String, Integer>();
		
		for (int i=0; i<7; i++)
		{
			mapaDiasSemana.put(DIAS_SEMANA.get(i), i);
		}
		
		return mapaDiasSemana;
	}

	public String cargarDatos()
	{
		cargador.cargarDatos();
		
		String infoCarga = "";
		HashMap<String, Integer> dictInfoCarga = cargador.infoDeCarga();
		
		if (dictInfoCarga != null)
		{
			for (Entry<String, Integer> info : dictInfoCarga.entrySet())
			{
				infoCarga += info.getKey() + ": " + info.getValue() + "\n";
			}
		}
		else
			infoCarga = "No se encontraron datos del hotel.";
		
		return infoCarga;
	}
	
	public void setUsuarios(HashMap<String, Usuario> mapaUsuarios)
	{
		autenticador.setUsersMap(mapaUsuarios);
	}

	public String crearTarifa(String tipo, int valor, String fechaI, String fechaF, String dias)
	{
		try
		{
			if (TIPOS_HABITACION.contains(tipo))
			{
				String[] listaFechaI = fechaI.split("-");
				String[] listaFechaF = fechaF.split("-");
				
				ArrayList<Integer> fechaInicial = dateToIntArray(listaFechaI);
				ArrayList<Integer> fechaFinal = dateToIntArray(listaFechaF);
				
				if (fechaInicial == null || fechaFinal == null)
					return "Fecha inválida (dd-mm).";
				
				String[] listaDias = dias.split("-");
				ArrayList<Integer> diasTarifa = new ArrayList<Integer>();
				for (String dia : listaDias)
				{
					if (DIAS_SEMANA.contains(dia))
						diasTarifa.add(MAPA_DIAS_SEMANA.get(dia));
				}
				
				coordinadorPMS.agregarTarifa(tipo, valor, fechaInicial, fechaFinal, diasTarifa);
				return infoTarifa(tipo, valor, fechaInicial, fechaFinal, diasTarifa);
			}
			else
				return "Tipo de habitación debe ser \"estandar\" o \"suite\" o \"suite doble\".";
		}
		catch (NumberFormatException e)
		{
			return "El valor de la tarifa debe ser un valor numérico.";
		}
	}
	
	public String sobreescribirTarifa(String tipo, int valor, String fechaI, String fechaF, String dias)
	{
		try
		{
			if (TIPOS_HABITACION.contains(tipo))
			{
				String[] listaFechaI = fechaI.split("-");
				String[] listaFechaF = fechaF.split("-");
				
				ArrayList<Integer> fechaInicial = dateToIntArray(listaFechaI);
				ArrayList<Integer> fechaFinal = dateToIntArray(listaFechaF);
				
				if (fechaInicial == null || fechaFinal == null)
					return "Fecha inválida (dd-mm).";
				
				String[] listaDias = dias.split("-");
				ArrayList<Integer> diasTarifa = new ArrayList<Integer>();
				for (String dia : listaDias)
				{
					if (DIAS_SEMANA.contains(dia))
						diasTarifa.add(MAPA_DIAS_SEMANA.get(dia));
				}
				
				coordinadorPMS.eliminarTarifas(tipo, fechaInicial, fechaFinal, diasTarifa);
				coordinadorPMS.agregarTarifa(tipo, valor, fechaInicial, fechaFinal, diasTarifa);
				return infoTarifa(tipo, valor, fechaInicial, fechaFinal, diasTarifa);
			}
			else
				return "Tipo de habitación debe ser \"estandar\" o \"suite\" o \"suite doble\".";
		}
		catch (NumberFormatException e)
		{
			return "El valor de la tarifa debe ser un valor numérico.";
		}
	}
	
	
	private String infoTarifa(String tipo, int valor, ArrayList<Integer> fechaInicial, ArrayList<Integer> fechaFinal,
			ArrayList<Integer> diasTarifa)
	{
		String infoTarifa = "Nueva Tarifa:" + "\n\tTipo de habitacion: " + tipo + "\n\tValor:" + valor + "\n\tFechas de validez: ";
		infoTarifa += fechaToString(fechaInicial) + " hasta " + fechaToString(fechaFinal);
		infoTarifa += "\n\tDías: " + diasToString(diasTarifa);
		return infoTarifa;
	}

	private String fechaToString(ArrayList<Integer> fecha)
	{
		return (fecha.get(1)+1) + " de " + STRINGS_MESES[fecha.get(0)];
	}

	private String diasToString(ArrayList<Integer> diasTarifa)
	{
		String diasString = "";
		for (int i=0; i<diasTarifa.size(); i++)
		{
			diasString += STRINGS_DIAS_SEMANA[diasTarifa.get(i)];
			
			if (i != diasTarifa.size()-1)
				diasString += ", ";
			else
				diasString += ".";
		}
		return diasString;
	}

	private ArrayList<Integer> dateToIntArray(String[] lista)
	{
		ArrayList<Integer> listaFinal = new ArrayList<Integer>();
		
		int dia = Integer.parseInt(lista[0])-1;
		int mes = Integer.parseInt(lista[1])-1;
		
		if ((0 <= mes) && (mes < 12))
		{
			int diasMes = DIASXMES[mes];
			if ((0 <= dia) && (dia < diasMes))
			{
				listaFinal.add(mes);
				listaFinal.add(dia);
			}
			else
				return null;
		}
		else
			return null;
		
		return listaFinal;
	}

	public void setTarifas(HashMap<String, TarifasHabitacion> mapaTarifas)
	{
		coordinadorPMS.setTarifasHotel(mapaTarifas);
	}

	public String revisionTarifas365Dias()
	{
		return coordinadorPMS.getFechasSinTarifaStr();
	}

	public String agregarHabitacion(String tipo, boolean cocina, boolean balcon, boolean vista, String torre,
			int piso, String id)
	{
		if (coordinadorPMS.existeHabitacion(id))
			return "No se puede agregar porque ya hay una habitación con el id: " + id;
		else
		{
			coordinadorPMS.putHabitacion(tipo, cocina, balcon, vista, torre, piso, id);
			return "Habitación agregada" + coordinadorPMS.infoHabitacion(id);
		}	
	}
	
	public void setHabitaciones(HashMap<String, Habitacion> habitaciones)
	{
		coordinadorPMS.setHabitaciones(habitaciones);
	}

	public String eliminarHabitacion(String id)
	{
		if (coordinadorPMS.eliminarHabitacion(id))
			return "Habitación " + id + " eliminada.";
		else
			return "No se puede eliminar la habitacion " + id +  " porque no existe.";			
	}

	public String mostrarCatalogoHabitaciones()
	{
		return coordinadorPMS.catalogoHabitaciones();
	}

	public String getInfoHabitacionesDisponibles(LocalDate fecha, int numeroDeNoches)
	{
		String infoHabitacionesDisponibles = "";
		ArrayList<Habitacion> habitaciones = coordinadorPMS.getHabitacionesDisponibles(fecha, numeroDeNoches);
		int i = 1;
		if (habitaciones != null)
			for (Habitacion hab : habitaciones)
			{
				infoHabitacionesDisponibles += i + ". " + hab.toString() + "\n";
				i++;
			}
		else
			infoHabitacionesDisponibles = "No hay habitaciones para " + numeroDeNoches + " noches entre " + fecha.toString() + " y " + fecha.plusDays(numeroDeNoches).toString() + ".";
		return infoHabitacionesDisponibles;
	}

	public boolean existeHabitacion(String idHabitacion)
	{
		return coordinadorPMS.existeHabitacion(idHabitacion);
	}
}

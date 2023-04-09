package modelo;

import java.util.ArrayList;
import java.util.Arrays;

public class TarifasHabitacion
{
	private static final int MESES = 12;
	private static final int DIAS_EN_SEMANA = 7;
	private static final int[] DIASXMES = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static final ArrayList<String> DIAS_SEMANA = new ArrayList<String>(Arrays.asList("L", "M", "I", "J", "V", "S", "D"));
	private String tipoHabitacion;
	private ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> tarifas;
	
	public TarifasHabitacion()
	{
		
	}
	
	public TarifasHabitacion(String tipoHabitacion)
	{
		this.tipoHabitacion = tipoHabitacion;
		this.createTarifas();
	}

	private void createTarifas()
	{
		this.tarifas = new ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>>(MESES);
		
		for (int i=0; i<MESES; i++)
		{
			ArrayList<ArrayList<ArrayList<Integer>>> listaDiasDelMes = new ArrayList<ArrayList<ArrayList<Integer>>>(DIASXMES[i]);
			
			for (int j=0; j<DIASXMES[i]; j++)
			{
				ArrayList<ArrayList<Integer>> listaDiaDelMes = new ArrayList<ArrayList<Integer>>(DIAS_EN_SEMANA);
				
				for (int k=0; k<DIAS_EN_SEMANA; k++)
				{
					listaDiaDelMes.add(new ArrayList<Integer>());
				}
				listaDiasDelMes.add(listaDiaDelMes);
			}
			tarifas.add(listaDiasDelMes);
		}		
	}

	public String getTipoHabitacion()
	{
		return tipoHabitacion;
	}

	public void setTipoHabitacion(String tipoHabitacion)
	{
		this.tipoHabitacion = tipoHabitacion;
	}
	
	public ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> getTarifas()
	{
		return tarifas;
	}

	public void setTarifas(ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> tarifas)
	{
		this.tarifas = tarifas;
	}
	
	public void addTarifa(int valor, int mes, int diaMes, int diaSemana)
	{
		ArrayList<ArrayList<ArrayList<Integer>>> listaDiasDelMes = tarifas.get(mes);
		
		ArrayList<ArrayList<Integer>> listaDiaDeMes = listaDiasDelMes.get(diaMes);
		
		ArrayList<Integer> listaDiasSemana = listaDiaDeMes.get(diaSemana);
		
		listaDiasSemana.add(valor);
		listaDiasSemana.sort(null);
	}
	
	public void removeTarifa(int mes, int diaMes, int diaSemana)
	{
		ArrayList<ArrayList<ArrayList<Integer>>> listaDiasDelMes = tarifas.get(mes);
		
		ArrayList<ArrayList<Integer>> listaDiaDeMes = listaDiasDelMes.get(diaMes);
		
		ArrayList<Integer> listaDiasSemana = listaDiaDeMes.get(diaSemana);
		
		listaDiasSemana.clear();
	}

	public void addTarifaEnRangoFechas(int valor, ArrayList<Integer> fechaInicial, ArrayList<Integer> fechaFinal,
			ArrayList<Integer> diasTarifa)
	{
		if (fechaInicial.get(0).equals(fechaFinal.get(0)))
			addTarifaMismoMes(valor, fechaInicial, fechaFinal, diasTarifa);
		else
			addTarifaDifMes(valor, fechaInicial, fechaFinal, diasTarifa);
		System.out.println(tarifas);
	}
	
	private void addTarifaDifMes(int valor, ArrayList<Integer> fechaInicial, ArrayList<Integer> fechaFinal,
			ArrayList<Integer> diasTarifa) 
	{
		int mesI = fechaInicial.get(0);
		int diaI = fechaInicial.get(1);
		int mesF = fechaFinal.get(0);
		int diaF = fechaFinal.get(1);
		
		for (int diaMes=diaI; diaMes < DIASXMES[mesI]; diaMes++)
			for (int dia : diasTarifa)
				addTarifa(valor, mesI, diaMes, dia);
		
		for (int diaMes=0; diaMes<=diaF; diaMes++)
			for (int dia : diasTarifa)
				addTarifa(valor, mesF, diaMes, dia);
	}

	private void addTarifaMismoMes(int valor, ArrayList<Integer> fechaInicial, ArrayList<Integer> fechaFinal,
			ArrayList<Integer> diasTarifa) 
	{
		int mes = fechaInicial.get(0);
		int diaI = fechaInicial.get(1);
		int diaF = fechaFinal.get(1);
		
		for (int diaMes=diaI; diaMes<=diaF; diaMes++)
			for (int dia : diasTarifa)
				addTarifa(valor, mes, diaMes, dia);
	}

	public void eliminarTarifasEnRangoFechas(ArrayList<Integer> fechaInicial, ArrayList<Integer> fechaFinal,
			ArrayList<Integer> diasTarifa)
	{
		if (fechaInicial.get(0).equals(fechaFinal.get(0)))
			eliminarTarifaMismoMes(fechaInicial, fechaFinal, diasTarifa);
		else
			eliminarTarifaDifMes(fechaInicial, fechaFinal, diasTarifa);
	}
	

	private void eliminarTarifaDifMes(ArrayList<Integer> fechaInicial, ArrayList<Integer> fechaFinal, ArrayList<Integer> dias)
	{
		int mesI = fechaInicial.get(0);
		int diaI = fechaInicial.get(1);
		int mesF = fechaFinal.get(0);
		int diaF = fechaFinal.get(1);
		
		for (int diaMes=diaI; diaMes < DIASXMES[mesI]; diaMes++)
			for (int dia : dias)
				removeTarifa(mesI, diaMes, dia);
		
		for (int diaMes=0; diaMes<=diaF; diaMes++)
			for (int dia : dias)
				removeTarifa(mesF, diaMes, dia);
	}

	private void eliminarTarifaMismoMes(ArrayList<Integer> listaFechaI, ArrayList<Integer> listaFechaF, ArrayList<Integer> dias)
	{
		int mes = listaFechaI.get(0);
		int diaI = listaFechaI.get(1);
		int diaF = listaFechaF.get(1);
		
		for (int diaMes=diaI; diaMes<=diaF; diaMes++)
			for (int dia : dias)
				removeTarifa(mes, diaMes, dia);
	}
	
	public String fechasSinTarifa()
	{
		String respuesta = "";
		
		for (int mes=0; mes<MESES; mes++)
		{
			ArrayList<ArrayList<ArrayList<Integer>>> diasMes = tarifas.get(mes);
			
			for (int diaMes=0; diaMes<diasMes.size(); diaMes++)
			{
				ArrayList<ArrayList<Integer>> diasSemana = diasMes.get(diaMes);
				ArrayList<String> diasSemanaSinTarifa = new ArrayList<String>();
				
				for (int diaSemana=0; diaSemana<diasSemana.size(); diaSemana++)
				{
					ArrayList<Integer> listaDia = diasSemana.get(diaSemana);
					if (listaDia.isEmpty())
					{
						diasSemanaSinTarifa.add(DIAS_SEMANA.get(diaSemana));
					}
				}
				if (!diasSemanaSinTarifa.isEmpty())
				{
					respuesta += textoFechaSinTarifa(mes, diaMes, diasSemanaSinTarifa);
				}
			}
		}
		return respuesta;
	}
	
	private String textoFechaSinTarifa(int mes, int diaMes, ArrayList<String> diasSemana) 
	{
		return "\tMes: " + (mes+1) + " | Dia: " + (diaMes+1) + " | Dias de la semana: " + diasSemana + "\n";
	}
	
}

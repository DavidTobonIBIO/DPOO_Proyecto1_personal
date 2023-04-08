package consola;

import controlador.Controlador;

public class InterfazTarifas extends Interfaz
{
	private Controlador controlador;
	
	public InterfazTarifas( Controlador controlador)
	{
		this.controlador = controlador;
	}

	@Override
	public void iniciarInterfaz()
	{
		System.out.println("\nTarifas");
		
		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int seleccion = Integer.parseInt(input("Selecione una opción"));
				
				if (seleccion == 1)
					ejecutarCrearTarifa();
				else if (seleccion == 2)
					ejecutarSobreescribirTarifa();
				else if (seleccion == 3)
				{
					System.out.println();
					continuar = false;
				}
				else
				{
					System.out.println("\nPor favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}
	
	private void ejecutarCrearTarifa()
	{
		String tipo = input("Tipo de habitación (estandar / suite / suitedoble)").toLowerCase();
		int valor = Integer.parseInt(input("Valor de la tarifa"));
		String fechaInicial = input("Fecha inicial (dd-mm)");
		String fechaFinal = input("Fecha final (dd-mm)");
		String dias = input("Días de la semana (Formato: L-M-I-J-V-S-D)").toUpperCase();
		
		System.out.println(controlador.crearTarifa(tipo, valor, fechaInicial, fechaFinal, dias));
	}

	private void ejecutarSobreescribirTarifa()
	{
		String tipo = input("Tipo de habitación (estandar / suite / suitedoble)").toLowerCase();
		int valor = Integer.parseInt(input("Nuevo valor de la tarifa"));
		String fechaInicial = input("Fecha inicial (dd-mm)");
		String fechaFinal = input("Fecha final (dd-mm)");
		String dias = input("Dias de la semana (Formato: L-M-I-J-V-S-D)").toUpperCase();
		
		System.out.println(controlador.sobreescribirTarifa(tipo, valor, fechaInicial, fechaFinal, dias));
	}

	@Override
	protected void mostrarMenu()
	{
		System.out.println("Opciones\n");
		System.out.println("1. Crear nueva tarifa");
		System.out.println("2. Sobreescribir tarifa");
		System.out.println("3. Salir");
	}

}

package consola;

public class InterfazAdmin extends Interfaz
{
	private InterfazPrincipal interfazPrincipal;
	
	public InterfazAdmin(InterfazPrincipal interfazPrincipal)
	{
		this.interfazPrincipal = interfazPrincipal;
	}
	
	@Override
	public void iniciarInterfaz()
	{
		System.out.println("\nPortal de administración\n");
		
		System.out.println(ejecutarRevisionDeTarifas365Dias());
		
		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int seleccion = Integer.parseInt(input("Seleccione una opción"));
				
				if (seleccion == 1)
					ejecutarInterfazTarifas();
				else if (seleccion == 2)
					ejecutarInterfazHabitaciones();
				else if (seleccion == 3)
					goToInterfazRecepcion();
				else if (seleccion == 4)
					goToInterfazServicios();
				else if (seleccion == 5)
				{
					System.out.println("Cerrando sesión...\n");
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
	
	private String ejecutarRevisionDeTarifas365Dias()
	{
		return interfazPrincipal.ejecutarRevisionDeTarifas365Dias();
	}

	private void ejecutarInterfazTarifas()
	{
		interfazPrincipal.ejecutarInterfazTarifas();		
	}

	private void ejecutarInterfazHabitaciones()
	{
		interfazPrincipal.ejecutarInterfazHabitaciones();		
	}

	private void goToInterfazRecepcion()
	{
		interfazPrincipal.ejecutarInterfazRecepcion();
	}

	private void goToInterfazServicios()
	{
		interfazPrincipal.ejecutarInterfazServicios();
	}

	@Override
	protected void mostrarMenu()
	{
		System.out.println("Opciones\n");
		System.out.println("1. Agregar o editar tarifas");
		System.out.println("2. Agregar o eliminar habitaciones");
		System.out.println("3. Menú recepción");
		System.out.println("4. Menú servicios");
		System.out.println("5. Cerrar sesión");
	}
}
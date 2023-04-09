package consola;

import autenticador.AutenticadorDeUsuarios;
import controlador.Controlador;

public class InterfazPrincipal extends Interfaz
{
	private static final String EMPLEADO = "empleado";
	private static final String RECEPCIONISTA = "recepcionista";
	private static final String ADMIN = "admin";
	private Controlador controlador;
	private AutenticadorDeUsuarios autenticador;
	private InterfazAdmin interfazAdmin;
	private InterfazRecepcion interfazRecepcion;
	private InterfazServicios interfazServicios;
	private InterfazTarifas interfazTarifas;
	private InterfazHabitaciones interfazHabitaciones;
	
	public InterfazPrincipal()
	{
		this.autenticador = new AutenticadorDeUsuarios();
		this.controlador = new Controlador(this, autenticador);
		this.interfazAdmin = new InterfazAdmin(this);
		this.interfazRecepcion = new InterfazRecepcion(controlador);
		this.interfazServicios = new InterfazServicios(this);
		this.interfazTarifas = new InterfazTarifas(controlador);
		this.interfazHabitaciones = new InterfazHabitaciones(controlador);
		
	}

	@Override
	public void iniciarInterfaz()
	{
		System.out.println("\nProperty management system (PMS) - Hotel Villa Uniandes\n");
		System.out.println("Cargando información del hotel...\n");
		ejecutarCargarDatosHotel();
		
		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int seleccion = Integer.parseInt(input("Seleccione una opción"));
				
				if (seleccion == 1)
					ejecutarIniciarSesion();
				else if (seleccion == 2)
					ejecutarRegistrarEmpleado();
				else if (seleccion == 3)
				{
					continuar = false;
					System.out.println("\nAplicación cerrada.\n");
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
	
	@Override
	protected void mostrarMenu()
	{
		System.out.println("Opciones\n");
		System.out.println("1. Iniciar sesión");
		System.out.println("2. Registrar nuevo empleado");
		System.out.println("3. Salir de la aplicación");
	}
	
	private void ejecutarRegistrarEmpleado()
	{
		String tipoEmpleado = autenticador.registrarUsuario();
		if (tipoEmpleado != null)
			System.out.println("Usuario de tipo " + tipoEmpleado + " registrado.");
		else
			System.out.println("No se registró el usuario.");
	}

	private void ejecutarIniciarSesion()
	{
		String tipoEmpleado = autenticador.iniciarSesion();
		
		System.out.println("Empleado de tipo: " + tipoEmpleado + "\n");
		if (tipoEmpleado != null)
		{
			if (tipoEmpleado.equals(ADMIN))
				ejecutarInterfazAdmin();
			else if (tipoEmpleado.equals(RECEPCIONISTA))
				ejecutarInterfazRecepcion();
			else if (tipoEmpleado.equals(EMPLEADO))
				ejecutarInterfazServicios();
		}
	}

	private void ejecutarInterfazAdmin()
	{
		interfazAdmin.iniciarInterfaz();
	}
	
	public void ejecutarInterfazTarifas()
	{
		interfazTarifas.iniciarInterfaz();
	}
	
	public void ejecutarInterfazHabitaciones()
	{
		interfazHabitaciones.iniciarInterfaz();		
	}

	public void ejecutarInterfazRecepcion()
	{
		interfazRecepcion.iniciarInterfaz();
	}

	public void ejecutarInterfazServicios()
	{
		interfazServicios.iniciarInterfaz();
	}

	private void ejecutarCargarDatosHotel()
	{
		String datosCargados = controlador.cargarDatos();
		System.out.println(datosCargados);
	}

	public static void main(String[] args)
	{
		InterfazPrincipal interfaz = new InterfazPrincipal();
		interfaz.iniciarInterfaz();
	}

	public String ejecutarRevisionDeTarifas365Dias()
	{
		return controlador.revisionTarifas365Dias();	
	}
}

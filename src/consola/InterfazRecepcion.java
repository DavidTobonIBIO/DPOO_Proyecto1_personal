package consola;

import java.time.LocalDate;

import controlador.Controlador;

public class InterfazRecepcion extends Interfaz
{
	private Controlador controlador;
	
	public InterfazRecepcion(Controlador controlador)
	{
		this.controlador = controlador;
	}
	
	@Override
	public void iniciarInterfaz()
	{
		System.out.println("\nRecepcion");
		
		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int seleccion = Integer.parseInt(input("Selecione una opción"));
				
				if (seleccion == 1)
					ejecutarCheckIn();
				else if (seleccion == 2)
					ejecutarCheckOut();
				else if (seleccion == 3)
					ejecutarGetInfoHuesped();
				else if (seleccion == 4)
					ejecutarGetInfoHabitacion();
				else if (seleccion == 5)
					ejecutarGetHabitacionesDisponibles();
				else if (seleccion == 6)
					ejecutarReservar();
				else if (seleccion == 7)
					ejecutarCancelarReserva();
				else if (seleccion == 8)
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

	private void ejecutarCheckIn()
	{
		String esConReserva = input("El huesped ya tiene reserva? (si / no)").toLowerCase();
		
		if (esConReserva.equals("si"))
		{
			ejecutarCheckInConReserva();
		}
		else if (esConReserva.equals("no"))
		{
			ejecutarCheckInSinReserva();
		}
		else
			System.out.println("Opción no válida.");
		
	}

	private void ejecutarCheckInSinReserva()
	{
		LocalDate fechaHoy = LocalDate.now();
		try
		{
			String nombreHuespedResponsable = input("Nombre del huesped responsable");
			String apellidosHuespedResponsable = input("Apellidos del huesped responsable");
			String documentoHuespedResponsable = input("Documento de ID");
			String correoHuespedResponsable = input("Correo electronico");
			String celularHuespedResponsable = input("Número de celular");
			int numeroDeNoches = Integer.parseInt(input("¿Cuantas noches?"));
			int totalHuespedes = Integer.parseInt(input("Número de huéspedes"));
			int huespedesNoAsignados = totalHuespedes;
			
			String habitacionesDisponibles = controlador.getInfoHabitacionesDisponibles(fechaHoy, numeroDeNoches);
			System.out.println(habitacionesDisponibles);
			if (habitacionesDisponibles.startsWith("1"))
			{
				while (huespedesNoAsignados > 0)
				{
					System.out.println(habitacionesDisponibles);
					String idHabitacion = ("Escriba el id de la habitación seleccionada");
					boolean existeHabitacion = controlador.existeHabitacion(idHabitacion);
					if (existeHabitacion)
					{
						boolean continuar = true;
						while (continuar && controlador.habitacionNoLlena(idHabitacion) && huespedesNoAsignados > 0)
						{
							System.out.println("1. Agregar huéspued a la habitación");
							System.out.println("2. Finalizar configuración de la habitación");
							int seleccion = Integer.parseInt(input("Elija una opción"));
							if (seleccion == 1)
								ejecutarAgregarHuespedAHabitacion();
							else if (seleccion == 2)
								continuar = false;
							else
								
								
						}
					}
					else
						System.out.println("ID incorrecto.");
				}
			}
				
				
		}
		catch (NumberFormatException e)
		{
			System.out.println("Debe de usar valores numéricos enteros positivos y el número debe estar dentro de las opciones.");
		}
		
	}

	private void ejecutarCheckInConReserva()
	{
		// TODO Auto-generated method stub
		
	}

	private void ejecutarCheckOut()
	{
		// TODO Auto-generated method stub
		
	}

	private void ejecutarGetInfoHuesped()
	{
		// TODO Auto-generated method stub
		
	}

	private void ejecutarGetInfoHabitacion()
	{
		// TODO Auto-generated method stub
		
	}

	private void ejecutarGetHabitacionesDisponibles()
	{
		// TODO Auto-generated method stub
		
	}

	private void ejecutarReservar()
	{
		// TODO Auto-generated method stub
		
	}

	private void ejecutarCancelarReserva()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void mostrarMenu()
	{
		System.out.println("Opciones\n");
		System.out.println("1. Realizar Check-In");
		System.out.println("2. Realizar Check-Out");
		System.out.println("3. Consultar información de un huésped");
		System.out.println("4. Consultar información de una habitación");
		System.out.println("5. Ver habitaciones disponibles");
		System.out.println("6. Realizar una reserva");
		System.out.println("7. Cancelar una reserva");;
		System.out.println("8. Ir atrás (admin) / Cerrar sesión");
	}

}

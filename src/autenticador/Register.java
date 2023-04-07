package autenticador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Register
{
	private static final String EMPLEADO = "empleado";
	private static final String RECEPCIONISTA = "recepcionista";
	private static final String ADMIN = "admin";

	public String selectType()
	{
		boolean continuar = true;
		
		while (continuar)
		{
			try
			{
				menuTipoEmpleado();
				int seleccion = Integer.parseInt(input("Seleccione una opción"));
				
				if (seleccion == 1)
					return ADMIN;
				else if (seleccion == 2)
					return RECEPCIONISTA;
				else if (seleccion == 3)
					return EMPLEADO;
				else
					System.out.println("\nPor favor seleccione una opción válida.");
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
		return null;
	}
	
	private void menuTipoEmpleado()
	{
		System.out.println("1. Administrador");
		System.out.println("2. Recepcionista");
		System.out.println("3. Otro (Empleado)");
	}
	
	public String getLogin()
	{
		return input("Nombre de usuario (login)");
	}
	
	public String getPassword1()
	{
		return input("Contraseña (Password)");
	}
	
	public String getPassword2()
	{
		return input("Confirmar contraseña");
	}

	public String passwordMistake()
	{
		System.out.println("\nLAS CONTRASEÑAS DEBEN DE SER IGUALES\n");
		return null;
	}
	
	public String userExists()
	{
		System.out.println("\nNOMBRE DE USUARIO YA EXISTE\n");
		return null;
	}
	
	private String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
}

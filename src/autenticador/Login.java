package autenticador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Login
{
	public String getLogin()
	{
		return input("Nombre de usuario (login)");
	}
	
	public String getPassword()
	{
		return input("Contraseña (Password)");
	}
	
	public String incorrectPassword()
	{
		System.out.println("\nCONTRASEÑA INCORRECTA\n");
		return null;
	}
	
	public String userNotFound()
	{
		System.out.println("\nUSUARIO NO EXISTE\n");
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

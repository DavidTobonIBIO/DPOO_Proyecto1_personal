package consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public abstract class Interfaz
{
	public abstract void iniciarInterfaz();
	protected abstract void mostrarMenu();

	protected String input(String mensaje)
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

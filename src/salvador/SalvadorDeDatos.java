package salvador;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import autenticador.Usuario;
import modelo.Habitacion;
import modelo.TarifasHabitacion;

public class SalvadorDeDatos
{
	private String carpetaUsuarios;
	private String carpetaTarifas;
	private String carpetaHabitaciones;
	private String carpetaServicios;
	private String carpetaProductos;
	
	public SalvadorDeDatos()
	{
		this.carpetaUsuarios = "data/usuarios/";
		this.carpetaTarifas = "data/tarifas/";
		this.carpetaHabitaciones = "data/habitaciones/";
		this.carpetaServicios = "data/servicios/";
		this.carpetaProductos = "data/productos/";
	}
	
	public void salvarUsuario(Usuario usuario)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(carpetaUsuarios + usuario.getLogin() + ".xml");
			XMLEncoder encoder = new XMLEncoder(fos);
			encoder.writeObject(usuario);
			encoder.close();
			fos.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void salvarTarifasHabitacion(TarifasHabitacion tarifas)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(carpetaTarifas + tarifas.getTipoHabitacion() + ".xml");
			XMLEncoder encoder = new XMLEncoder(fos);
			encoder.writeObject(tarifas);
			encoder.close();
			fos.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}	
	}
	
	public void salvarHabitacion(Habitacion habitacion)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(carpetaHabitaciones + habitacion.getId() + ".xml");
			XMLEncoder encoder = new XMLEncoder(fos);
			encoder.writeObject(habitacion);
			encoder.close();
			fos.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void borrarHabitacion(String id)
	{
		File directorio = new File(carpetaHabitaciones);
		String[] hijos = directorio.list();
		if (hijos != null)
		{
			for (String archivo : hijos)
			{
				if (archivo.equals(id + ".xml"))
				{
					File archivoAEliminar = new File(carpetaHabitaciones + archivo);
					archivoAEliminar.delete();
				}
			}
		}		
	}
}

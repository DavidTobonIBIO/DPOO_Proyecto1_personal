package salvador;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import autenticador.Usuario;
import modelo.TarifasHabitacion;

public class SalvadorDeDatos
{
	private String carpetaUsuarios;
	private String carpetaTarifas;
	private String carpetaHabitacionesEstandar;
	private String carpetaHabitacionesSuite;
	private String carpetaHabitacionesSuiteDoble;
	private String carpetaServicios;
	private String carpetaProductos;
	
	public SalvadorDeDatos()
	{
		this.carpetaUsuarios = "data/usuarios/";
		this.carpetaTarifas = "data/tarifas/";
		this.carpetaHabitacionesEstandar = "data/habitaciones/estandar/";
		this.carpetaHabitacionesSuite = "data/habitaciones/suite/";
		this.carpetaHabitacionesSuiteDoble = "data/habitaciones/suitedoble/";
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
}

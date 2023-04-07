package salvador;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import autenticador.Usuario;

public class SalvadorDeDatos
{
	private String carpetaUsuarios;
	private String archivoTarifasEstandar;
	private String archivoTarifasSuite;
	private String archivoTarifasSuiteDoble;
	private String carpetaHabitacionesEstandar;
	private String carpetaHabitacionesSuite;
	private String carpetaHabitacionesSuiteDoble;
	private String carpetaServicios;
	private String carpetaProductos;
	
	public SalvadorDeDatos()
	{
		this.carpetaUsuarios = "data/usuarios/";
		this.archivoTarifasEstandar = "data/tarifas/estandar.xml";
		this.archivoTarifasSuite = "data/tarifas/suite.xml";
		this.archivoTarifasSuiteDoble = "data/tarifas/suitedoble.xml";
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
}

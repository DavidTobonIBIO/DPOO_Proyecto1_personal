package cargador;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import autenticador.Usuario;
import controlador.Controlador;
import modelo.TarifasHabitacion;

public class CargadorDeDatos
{
	private Controlador controlador;
	private String carpetaUsuarios;
	private String carpetaTarifas;
	private String carpetaHabitacionesEstandar;
	private String carpetaHabitacionesSuite;
	private String carpetaHabitacionesSuiteDoble;
	private String carpetaServicios;
	private String carpetaProductos;
	private HashMap<String, Usuario> mapaUsuarios;
	private HashMap<String, TarifasHabitacion> mapaTarifas;
	
	public CargadorDeDatos(Controlador controlador)
	{
		this.controlador = controlador;
		
		this.carpetaUsuarios = "data/usuarios/";
		this.carpetaTarifas = "data/tarifas/";
		this.carpetaHabitacionesEstandar = "data/habitaciones/estandar/";
		this.carpetaHabitacionesSuite = "data/habitaciones/suite/";
		this.carpetaHabitacionesSuiteDoble = "data/habitaciones/suitedoble/";
		this.carpetaServicios = "data/servicios/";
		this.carpetaProductos = "data/productos/";
		
		this.mapaUsuarios = new HashMap<String, Usuario>();
		this.mapaTarifas = new HashMap<String, TarifasHabitacion>();
	}
	
	public HashMap<String, Integer> infoDeCarga()
	{
		HashMap<String, Integer> dictInfoCarga = new HashMap<String, Integer>();
		
		dictInfoCarga.put("Usuarios", mapaUsuarios.size());
		
		return dictInfoCarga;
	}
	
	public void cargarDatos()
	{
		cargarUsuarios();
		cargarTarifasHabitacion();
	}
	
	private void cargarUsuarios()
	{
		File directorio = new File(carpetaUsuarios);
		String[] hijos = directorio.list();
		if (hijos != null)
		{
			for (String archivoUsuario : hijos)
			{
				try 
				{
					FileInputStream fis = new FileInputStream(carpetaUsuarios + archivoUsuario);
					XMLDecoder decoder = new XMLDecoder(fis);
					Object obj;

					while (true) {
						try {
							obj = decoder.readObject();

							if (obj instanceof Usuario) {
								Usuario usuario = (Usuario) obj;
								String login = usuario.getLogin();
								mapaUsuarios.put(login, usuario);
							} else {
								System.err.println("objecto inesperado en el archivo");
							}
						} catch (ArrayIndexOutOfBoundsException e3) {
							break;
						}
					}
					decoder.close();
					fis.close();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
			controlador.setUsuarios(mapaUsuarios);
		}
	}
	
	public void cargarTarifasHabitacion()
	{
		File directorio = new File(carpetaTarifas);
		String[] hijos = directorio.list();
		if (hijos != null)
		{
			for (String archivoUsuario : hijos)
			{
				try 
				{
					FileInputStream fis = new FileInputStream(carpetaTarifas + archivoUsuario);
					XMLDecoder decoder = new XMLDecoder(fis);
					Object obj;

					while (true) {
						try {
							obj = decoder.readObject();

							if (obj instanceof TarifasHabitacion) {
								TarifasHabitacion tarifas = (TarifasHabitacion) obj;
								String tipo = tarifas.getTipoHabitacion();
								mapaTarifas.put(tipo, tarifas);
							} else {
								System.err.println("objecto inesperado en el archivo");
							}
						} catch (ArrayIndexOutOfBoundsException e3) {
							break;
						}
					}
					decoder.close();
					fis.close();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
			controlador.setTarifas(mapaTarifas);
		}

	}
	
}

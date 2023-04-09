package cargador;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import autenticador.Usuario;
import controlador.Controlador;
import modelo.Habitacion;
import modelo.TarifasHabitacion;

public class CargadorDeDatos
{
	private Controlador controlador;
	private String carpetaUsuarios;
	private String carpetaTarifas;
	private String carpetaHabitaciones;
	private String carpetaServicios;
	private String carpetaProductos;
	private HashMap<String, Usuario> mapaUsuarios;
	private HashMap<String, TarifasHabitacion> mapaTarifas;
	private HashMap<String, Habitacion> mapaHabitaciones;
	
	public CargadorDeDatos(Controlador controlador)
	{
		this.controlador = controlador;
		
		this.carpetaUsuarios = "data/usuarios/";
		this.carpetaTarifas = "data/tarifas/";
		this.carpetaHabitaciones = "data/habitaciones/";
		this.carpetaServicios = "data/servicios/";
		this.carpetaProductos = "data/productos/";
		
		this.mapaUsuarios = new HashMap<String, Usuario>();
		this.mapaTarifas = new HashMap<String, TarifasHabitacion>();
		this.mapaHabitaciones = new HashMap<String, Habitacion>();
	}
	
	public HashMap<String, Integer> infoDeCarga()
	{
		HashMap<String, Integer> dictInfoCarga = new HashMap<String, Integer>();
		
		dictInfoCarga.put("Usuarios", mapaUsuarios.size());
		dictInfoCarga.put("Habitaciones", mapaHabitaciones.size());
		
		return dictInfoCarga;
	}
	
	public void cargarDatos()
	{
		cargarUsuarios();
		cargarTarifasHabitacion();
		cargarHabitaciones();
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
			for (String archivo : hijos)
			{
				try 
				{
					FileInputStream fis = new FileInputStream(carpetaTarifas + archivo);
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
	
	public void cargarHabitaciones()
	{
		File directorio = new File(carpetaHabitaciones);
		String[] hijos = directorio.list();
		if (hijos != null)
		{
			for (String archivo : hijos)
			{
				try 
				{
					FileInputStream fis = new FileInputStream(carpetaHabitaciones + archivo);
					XMLDecoder decoder = new XMLDecoder(fis);
					Object obj;

					while (true) {
						try {
							obj = decoder.readObject();

							if (obj instanceof Habitacion) {
								Habitacion hab = (Habitacion) obj;
								String id = hab.getId();
								mapaHabitaciones.put(id, hab);
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
			controlador.setHabitaciones(mapaHabitaciones);
		}
	}
}

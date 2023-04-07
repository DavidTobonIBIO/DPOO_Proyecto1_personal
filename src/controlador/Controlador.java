package controlador;

import java.util.HashMap;

import autenticador.AutenticadorDeUsuarios;
import autenticador.Usuario;
import cargador.CargadorDeDatos;
import consola.InterfazPrincipal;
import modelo.CoordinadorPMS;

public class Controlador
{
	private InterfazPrincipal interfaz;
	private AutenticadorDeUsuarios autenticador;
	private CargadorDeDatos cargador;
	private CoordinadorPMS coordinadorPMS;

	public Controlador(InterfazPrincipal interfaz,AutenticadorDeUsuarios autenticador)
	{
		this.interfaz = interfaz;
		this.autenticador = autenticador;
		this.cargador = new CargadorDeDatos(this);
		this.coordinadorPMS = new CoordinadorPMS(this);
	}

	public void cargarDatos()
	{
		cargador.cargarDatos();
	}
	
	public void setUsuarios(HashMap<String, Usuario> mapaUsuarios)
	{
		autenticador.setUsersMap(mapaUsuarios);
	}
	
	
}

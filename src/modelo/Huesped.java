package modelo;

import java.util.HashMap;

public class Huesped
{
	private String nombre;
	private String apellido;
	private String id;
	private String correo;
	private String celular;
	private HashMap<String, Reserva> reservas;
	private Habitacion habitacionActual;
	
	public Huesped()
	{
		
	}

	public Huesped(String nombre, String apellido, String id, String correo, String celular)
	{
		this.nombre = nombre;
		this.apellido = apellido;
		this.id = id;
		this.correo = correo;
		this.celular = celular;
		this.reservas = new HashMap<String, Reserva>();
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getApellido()
	{
		return apellido;
	}

	public void setApellido(String apellido)
	{
		this.apellido = apellido;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getCorreo()
	{
		return correo;
	}

	public void setCorreo(String correo)
	{
		this.correo = correo;
	}

	public String getCelular()
	{
		return celular;
	}

	public void setCelular(String celular)
	{
		this.celular = celular;
	}

	public HashMap<String, Reserva> getReservas()
	{
		return reservas;
	}

	public void setReservas(HashMap<String, Reserva> reservas)
	{
		this.reservas = reservas;
	}

	public Habitacion getHabitacionActual()
	{
		return habitacionActual;
	}

	public void setHabitacionActual(Habitacion habitacionActual)
	{
		this.habitacionActual = habitacionActual;
	}
	
	@Override
	public String toString()
	{
		return apellido + " " + nombre + ", ID: " + id + "\n\t" + correo + "\n\t" + celular + "\n\tHabitación: " + habitacionActual.getId();
	}
}

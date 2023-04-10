package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Reserva
{
	private Huesped reservador;
	private String cedulaReservador;
	private String id;
	private int cantidadHuespedes;
	private LocalDate fechaInicial;
	private LocalDate fechaFinal;
	private int noches;
	private int precioTotal;
	private ArrayList<Habitacion> habitaciones;
	
	public Reserva()
	{
		
	}

	public Reserva(Huesped reservador, int cantidadHuespedes, LocalDate fechaInicial, LocalDate fechaFinal, int noches,
			ArrayList<Habitacion> habitaciones)
	{
		this.reservador = reservador;
		this.cedulaReservador = reservador.getId();
		this.id = cedulaReservador + reservador.getReservas().size();
		this.cantidadHuespedes = cantidadHuespedes;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.noches = noches;
		this.habitaciones = habitaciones;
		this.precioTotal = calcularPrecioTotal();	
	}

	private int calcularPrecioTotal()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public Huesped getReservador()
	{
		return reservador;
	}

	public void setReservador(Huesped reservador)
	{
		this.reservador = reservador;
	}

	public String getCedulaReservador()
	{
		return cedulaReservador;
	}

	public void setCedulaReservador(String cedulaReservador)
	{
		this.cedulaReservador = cedulaReservador;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public int getCantidadHuespedes()
	{
		return cantidadHuespedes;
	}

	public void setCantidadHuespedes(int cantidadHuespedes)
	{
		this.cantidadHuespedes = cantidadHuespedes;
	}

	public LocalDate getFechaInicial()
	{
		return fechaInicial;
	}

	public void setFechaInicial(LocalDate fechaInicial)
	{
		this.fechaInicial = fechaInicial;
	}

	public LocalDate getFechaFinal()
	{
		return fechaFinal;
	}

	public void setFechaFinal(LocalDate fechaFinal)
	{
		this.fechaFinal = fechaFinal;
	}

	public int getNoches()
	{
		return noches;
	}

	public void setNoches(int noches)
	{
		this.noches = noches;
	}

	public int getPrecioTotal()
	{
		return precioTotal;
	}

	public void setPrecioTotal(int precioTotal)
	{
		this.precioTotal = precioTotal;
	}

	public ArrayList<Habitacion> getHabitaciones()
	{
		return habitaciones;
	}

	public void setHabitaciones(ArrayList<Habitacion> habitaciones)
	{
		this.habitaciones = habitaciones;
	}
}

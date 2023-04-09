package modelo;

import java.util.ArrayList;

public class HabitacionEstandar extends Habitacion
{
	private static final String TIPO = "estandar";
	private static final String[] TIPOS_CAMA = {Cama.getDoble()};
	
	public HabitacionEstandar()
	{
		
	}
	
	public HabitacionEstandar(boolean cocina, boolean balcon, boolean vista, String torre, int piso, String id)
	{
		super(TIPO, cocina, balcon, vista, torre, piso, id);

		ArrayList<Cama> camas = new ArrayList<Cama>();
		for (String tipoCama : TIPOS_CAMA)
			camas.add(new Cama(tipoCama));
		super.setCamas(camas);
		
		int capacidadAdultos = 0;
		int capacidadNinos = 0;
		for (Cama cama : camas)
		{
			capacidadAdultos += cama.getCapacidadAdultos();
			capacidadNinos += cama.getCapacidadNinios();
		}
		super.setCapacidadAdultos(capacidadAdultos);
		super.setCapacidadNinios(capacidadNinos);
	}

}

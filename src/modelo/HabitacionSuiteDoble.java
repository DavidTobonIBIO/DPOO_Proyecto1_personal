package modelo;

import java.util.ArrayList;

public class HabitacionSuiteDoble extends Habitacion
{
	private static final String TIPO = "suitedoble";
	private static final String[] TIPOS_CAMA = {Cama.getDoble(), Cama.getDoble(), Cama.getSofacama()};
	
	public HabitacionSuiteDoble()
	{
		
	}
	
	public HabitacionSuiteDoble(boolean cocina, boolean balcon, boolean vista, String torre, int piso, String id)
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

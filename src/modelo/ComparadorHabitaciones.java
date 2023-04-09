package modelo;

import java.util.Comparator;

public class ComparadorHabitaciones implements Comparator<Habitacion>
{

	public ComparadorHabitaciones()
	{

	}

	@Override
	public int compare(Habitacion hab1, Habitacion hab2)
	{
		int comparacionTipo = hab1.getTipo().compareTo(hab2.getTipo());
		if (comparacionTipo == 0)
		{
			int comparacionTorre = hab1.getTorre().compareTo(hab2.getTorre());
			if (comparacionTorre == 0)
			{
				int comparacionPiso = Integer.compare(hab1.getPiso(), hab2.getPiso());
				if (comparacionPiso == 0)
				{
					return hab1.getId().compareTo(hab2.getId());
				}
				else
					return comparacionPiso;
				
			}
			else
				return comparacionTorre;
		}
		else
			return comparacionTipo;
	}

}

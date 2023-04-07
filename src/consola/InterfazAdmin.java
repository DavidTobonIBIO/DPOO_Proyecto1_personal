package consola;

public class InterfazAdmin extends Interfaz
{
	private InterfazPrincipal interfazPrincipal;
	
	public InterfazAdmin(InterfazPrincipal interfazPrincipal)
	{
		this.interfazPrincipal = interfazPrincipal;
	}
	
	@Override
	public void iniciarInterfaz()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void mostrarMenu()
	{
		System.out.println("Opciones\n");
		System.out.println("1. Agregar o editar tarifas");
		System.out.println("2. Agregar o eliminar habitaciones");
		System.out.println("3. Menú recepción");
		System.out.println("4. Menú servicios");
		System.out.println("5. Cerrar sesión");
	}

}

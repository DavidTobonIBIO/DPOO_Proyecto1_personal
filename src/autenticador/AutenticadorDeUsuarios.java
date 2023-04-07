package autenticador;

import java.util.HashMap;
import java.util.Map.Entry;

import salvador.SalvadorDeDatos;


public class AutenticadorDeUsuarios
{
	private SalvadorDeDatos salvador;
	private Login login;
	private Register register;
	private HashMap<String, Usuario> usersMap;
	
	
	public AutenticadorDeUsuarios()
	{
		this.salvador = new SalvadorDeDatos();
		this.usersMap = new HashMap<String, Usuario>();
		this.login = new Login();
		this.register = new Register();
	}
	
	public AutenticadorDeUsuarios(HashMap<String, Usuario> usersMap)
	{
		this.salvador = new SalvadorDeDatos();
		this.usersMap = usersMap;
		this.login = new Login();
		this.register = new Register();
	}
	
	public String registrarUsuario()
	{
		String type = register.selectType();
		String userLogin = register.getLogin();
		String password1 = register.getPassword1();
		String password2 = register.getPassword2();
		
		boolean userExists = usersMap.containsKey(userLogin);
		
		if (!userExists)
		{
			if (password1.equals(password2))
			{
				Usuario newUser = new Usuario(userLogin, password1, type);
				usersMap.put(userLogin, newUser);
				salvador.salvarUsuario(newUser);
				return type;
			}
			else
				return register.passwordMistake();
		}
		else
			return register.userExists();
	}
	
	public String iniciarSesion()
	{
		String userLogin = login.getLogin();
		String password = login.getPassword();
		Usuario user = usersMap.get(userLogin);
		
		if (user != null)
		{
			String userPassword = user.getPassword();
			if (userPassword.equals(password))
				return user.getType();
			else
				return login.incorrectPassword();
		}
		else
			return login.userNotFound();
	}
	
	public void setUsersMap(HashMap<String, Usuario> usersMap)
	{
		this.usersMap = usersMap;
		if (usersMap != null)
		{
			for (Entry<String, Usuario> entrada : usersMap.entrySet())
			{
				System.out.println(entrada.getValue().toString());
			}
		}
		
	}
}

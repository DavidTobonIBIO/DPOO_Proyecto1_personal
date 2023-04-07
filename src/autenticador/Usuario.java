package autenticador;

public class Usuario
{
	
	private String login;
	private String password;
	private String type;
	
	public Usuario()
	{
		
	}
	
	public Usuario(String login, String password, String tipo)
	{
		this.login = login;
		this.password = password;
		this.type = tipo;
	}

	public String getLogin()
	{
		return login;
	}
	
	public String getPassword()
	{
		return password;
	}

	public String getType()
	{
		return type;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setType(String type)
	{
		this.type = type;
	}
	
	@Override
	public String toString()
	{
		return "Usuario: " + login + "\n\tTipo: " + type + "\n";
	}

}

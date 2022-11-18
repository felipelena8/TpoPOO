package modelo;


public class Usuario {
	private String username;
	private String password;
	private int id;
	private static int idCount = 0;

	public Usuario(String username, String password) {
		this.id = idCount++;
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getId() {
		return id;
	}
	
	public boolean validarCredenciales(String username, String password) {
		return username.equals(this.username) && password.equals(this.password);
	}

}

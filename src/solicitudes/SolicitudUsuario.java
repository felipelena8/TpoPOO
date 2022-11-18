package solicitudes;


public class SolicitudUsuario {
	
	private String username;
	
	private String password;
	
	private String dni;
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getDni() {
		return dni;
	}
	
	public SolicitudUsuario(String username, String password, String dni) {
		this.username = username;
		this.password = password;
		this.dni = dni;
	}

}

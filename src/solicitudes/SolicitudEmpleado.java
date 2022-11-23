package solicitudes;

import modelo.enums.Perfil;

public class SolicitudEmpleado {
	
	private String nombre;
	
	private String apellido;
	
	private String dni;
	
	private Perfil perfil;
	
	private String username;
	
	private String password;
	
	public SolicitudEmpleado(String nombre, String apellido, String dni, Perfil perfil, String username, String password) {
		this.nombre = nombre;
		this.dni = dni;
		this.apellido = apellido;
		this.perfil = perfil;
		this.username = username;
		this.password = password;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public String getDni() {
		return dni;
	}

	public String getApellido() {
		return apellido;
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

}

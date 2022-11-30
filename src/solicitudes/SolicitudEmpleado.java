package solicitudes;

import modelo.enums.Perfil;
import modelo.enums.Seniority;

public class SolicitudEmpleado {
	
	private String nombre;
	
	private String apellido;
	
	private String dni;
	
	private Perfil perfil;
	
	private String username;
	
	private String password;
	
	private Seniority seniority;
	
	public SolicitudEmpleado(String nombre, String apellido, String dni, Perfil perfil, String username, String password, Seniority seniority) {
		this.nombre = nombre;
		this.dni = dni;
		this.apellido = apellido;
		this.perfil = perfil;
		this.username = username;
		this.password = password;
		this.seniority = seniority;
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
	
	public Seniority getSeniority() {
		return seniority;
	}

}

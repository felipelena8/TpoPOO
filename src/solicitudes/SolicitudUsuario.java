package solicitudes;

import modelo.enums.Rol;
import modelo.enums.Seniority;
import modelo.Turno;

public class SolicitudUsuario {
	private Turno turno;
	private Seniority seniority;
	private String nombre, apellido, usuario, password;
	private Rol rol;

	public Seniority getSeniority() {
		return seniority;
	}

	public Rol getRol() {
		return rol;
	}

	public SolicitudUsuario(String nombre, String apellido, String usuario, String password, Turno turno,
			Seniority seniority, Rol rol) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.password = password;
		this.turno = turno;
		this.seniority = seniority;
		this.rol = rol;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getPassword() {
		return password;
	}

	public Turno getTurno() {
		return turno;
	}
}

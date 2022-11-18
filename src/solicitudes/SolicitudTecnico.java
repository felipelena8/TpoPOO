package solicitudes;

import modelo.enums.Perfil;
import modelo.enums.Seniority;
import modelo.Turno;

public class SolicitudTecnico {
	private Turno turno;
	private Seniority seniority;
	private String nombre, apellido, usuario, password;
	private Perfil rol;

	public Seniority getSeniority() {
		return seniority;
	}

	public Perfil getRol() {
		return rol;
	}

	public SolicitudTecnico(String nombre, String apellido, String usuario, String password, Turno turno,
			Seniority seniority, Perfil rol) {
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

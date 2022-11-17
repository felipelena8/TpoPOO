package modelo.usuarios;

import java.util.ArrayList;
import java.util.List;

import modelo.enums.Rol;
import modelo.enums.Seniority;
import modelo.Instalacion;
import modelo.Turno;

public class Tecnico extends Usuario {

	private double precioPorHora;
	private Turno turno;
	private Seniority seniority;
	private List<Instalacion> instalaciones;

	public Tecnico(String nombre, String apellido, String usuario, String password, Turno turno, Seniority seniority) {
		super(nombre, apellido, usuario, password);
		this.turno = turno;
		this.seniority = seniority;
		instalaciones = new ArrayList<Instalacion>();
	}

	public List<Instalacion> getInstalaciones() {
		return instalaciones;
	}

	public void asignarInstalacion(Instalacion instalacion) {

	}

	public void calcularPrecioPorHora() {
		this.precioPorHora = seniority.getSueldo();
	}

	public Rol getRol() {
		return Rol.TECNICO;
	}

}

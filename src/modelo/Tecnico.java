package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.enums.Perfil;

public class Tecnico extends Empleado{
	
	private Turno turno;
	
	private List<Instalacion> instalaciones;

	public Tecnico(String nombre, String apellido, String dni, Perfil perfil, Turno turno, String username, String password) {
		super(nombre, apellido, dni, perfil, username, password);
		this.turno = turno;
		this.instalaciones = new ArrayList<>();
		
	}
	
	public List<Instalacion> getInstalaciones(){
		return instalaciones;
	}
	
	public Turno getTurno() {
		return turno;
	}

}

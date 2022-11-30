package modelo;

import modelo.enums.Perfil;
import modelo.enums.Seniority;

public class Empleado extends Persona{
	
	private Perfil perfil;
	
	private Usuario usuario;
	
	private Seniority seniority;

	public Empleado(String nombre, String apellido, String dni, Perfil perfil, String username, String password, Seniority seniority) {
		super(nombre, apellido, dni);
		this.perfil = perfil;
		usuario = new Usuario(username, password);
		this.seniority = seniority;
	}
	
	public Perfil getPerfil() {
		return perfil;
	}
	
	public void asignarUsuario(Usuario usuario) {
		if(!usuarioAsignado()) {
			this.usuario = usuario;
		}else {
			System.out.println("El empleado " + this.getNombre() + " " + this.getApellido() + " ya tiene un usuario asignado");
		}
	}
	
	public boolean usuarioAsignado() {
		return this.usuario != null;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public Seniority getSeniority() {
		return this.seniority;
	}

}

package modelo.usuarios;

import modelo.Persona;

public class Usuario extends Persona {
	private String usuario;
	private String password;
	private int id;
	private static int idCount = 0;

	public Usuario(String nombre, String apellido, String usuario, String password) {
		super(nombre, apellido);
		this.usuario = usuario;
		this.id = idCount++;
		this.password = password;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getPassword() {
		return password;
	}

	public int getId() {
		return id;
	}

}

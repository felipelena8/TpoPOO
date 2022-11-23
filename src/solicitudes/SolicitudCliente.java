package solicitudes;

public class SolicitudCliente {
	private String nombre;
	private String apellido;
	private String dni;
	private String telefono;
	private String cuitCuil;
	private String correoElectronico;
	private String direccion;

	
	
	public SolicitudCliente(String nombre, String apellido, String dni, String telefono, String cuitCuil,
			String correoElectronico, String direccion) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = telefono;
		this.cuitCuil = cuitCuil;
		this.correoElectronico = correoElectronico;
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDni() {
		return dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getCuitCuil() {
		return cuitCuil;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public String getDireccion() {
		return direccion;
	}

}

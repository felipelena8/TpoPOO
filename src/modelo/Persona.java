package modelo;


public abstract class Persona {
private String nombre;
private String apellido;
private String dni;


public Persona(String nombre, String apellido, String dni) {
	this.nombre= nombre;
	this.apellido = apellido;
	this.dni = dni;
}
public String getApellido() {
	return apellido;
}
public String getNombre() {
	return nombre;
}

public String getDni() {
	return dni;
}

}

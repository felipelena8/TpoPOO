package modelo;

public abstract class Persona {
private String nombre;
private String apellido;
public Persona(String nombre, String apellido) {
	this.nombre= nombre;
	this.apellido = apellido;
}
public String getApellido() {
	return apellido;
}
public String getNombre() {
	return nombre;
}
}

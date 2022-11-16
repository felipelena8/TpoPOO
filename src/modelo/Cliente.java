package modelo;
import java.util.List;

public class Cliente extends Persona {

private String telefono;
private String cuitCuil;
private String correoElectronico;
private String direccion;
private List<Factura> facturas;

public Cliente(String nombre, String apellido) {
	super(nombre, apellido);
	
}
public Cliente(String nombre, String apellido, String telefono, String cuitCuil, String correoElectronico,
		String direccion) {
	super(nombre, apellido);
	this.telefono = telefono;
	this.cuitCuil = cuitCuil;
	this.correoElectronico = correoElectronico;
	this.direccion = direccion;
}

public void agregarFactura(Factura factura) {
	this.facturas.add(factura);
}

public boolean soyCliente(String cc) {
	return cc == cuitCuil;
}
public String getTelefono() {
	return telefono;
}
public String getCorreoElectronico() {
	return correoElectronico;
}
public String getDireccion() {
	return direccion;
}

}

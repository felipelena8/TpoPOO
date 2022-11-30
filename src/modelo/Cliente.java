package modelo;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {

private String telefono;
private String cuitCuil;
private String correoElectronico;
private String direccion;
private List<Factura> facturas;

public Cliente(String nombre, String apellido, String dni, String telefono, String cuitCuil, String correoElectronico,
		String direccion) {
	super(nombre, apellido, dni);
	this.telefono = telefono;
	this.cuitCuil = cuitCuil;
	this.correoElectronico = correoElectronico;
	this.direccion = direccion;
	facturas = new ArrayList<Factura>();
}

public void agregarFactura(Factura factura) {
	this.facturas.add(factura);
}

public boolean soyCliente(String cc) {
	return cc.equals(this.cuitCuil);
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

public String getCuitCuil() {
	return cuitCuil;
}
}

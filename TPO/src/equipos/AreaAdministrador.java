package equipos;

import java.util.ArrayList;
import java.util.List;

import excepciones.FacturaException;
import modelo.Cliente;
import modelo.Factura;
import solicitudes.SolicitudFactura;
import usuarios.Usuario;

public class AreaAdministrador {
	private List<Usuario> usuarios;
	private List<Factura> facturas;
	public AreaAdministrador() {
		usuarios = new ArrayList<Usuario>();
		facturas = new ArrayList<Factura>();
	}
	public Factura buscarFactura(long nro) throws FacturaException {
		for(Factura factura: facturas) {
			if(factura.soyFactura(nro)) {
				return factura;
			}
		}
		throw new FacturaException("La factura numero: "+ nro + " no existe");
	}
	public List<Factura> listarFacturas(){
		return facturas;
	}
	
	public void agregarUsuario(Usuario user) {
		usuarios.add(user);
	}
	
	public void enviarFacturaPorCorreo(Factura factura, Cliente cliente) {
		
	}
	
	public void emitirFactura(SolicitudFactura soli) {
		
	}
	

}

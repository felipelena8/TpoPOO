package modelo.equipos;

import java.util.List;
import excepciones.FacturaException;
import modelo.Cliente;
import modelo.Factura;
import solicitudes.SolicitudFactura;

public class AreaAdministrador extends EquipoTrabajo{
	
	
	public AreaAdministrador(Double sueldoJunior, Double sueldoSemisenior, Double sueldoSenior) {
		super(sueldoJunior, sueldoSemisenior, sueldoSenior);
		// TODO Auto-generated constructor stub
	}

	private List<Factura> facturas;



	public Factura buscarFactura(long nro) throws FacturaException {
		for (Factura factura : facturas) {
			if (factura.soyFactura(nro)) {
				return factura;
			}
		}
		throw new FacturaException("La factura numero: " + nro + " no existe");
	}

	public List<Factura> listarFacturas() {
		return facturas;
	}


	public void enviarFacturaPorCorreo(Factura factura, Cliente cliente) {

	}

	public void emitirFactura(SolicitudFactura soli) {

	}


}

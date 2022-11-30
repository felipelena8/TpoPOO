package modelo.equipos;

import java.util.ArrayList;
import java.util.List;
import excepciones.FacturaException;
import modelo.Cliente;
import modelo.Factura;
import solicitudes.SolicitudFactura;

public class AreaAdministrador extends EquipoTrabajo {

	private List<Factura> facturas;

	public AreaAdministrador(Double sueldoJunior, Double sueldoSemisenior, Double sueldoSenior) {
		super(sueldoJunior, sueldoSemisenior, sueldoSenior);
		facturas = new ArrayList<Factura>();
	}

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

	public void emitirFactura(Factura f) {
		facturas.add(f);
		f.getCliente().agregarFactura(f);
	}

}

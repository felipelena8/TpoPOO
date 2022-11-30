package solicitudes;

import java.util.List;

import modelo.Cliente;
import modelo.Item;
import modelo.enums.TipoFactura;

public class SolicitudFactura {
	private TipoFactura tipo;
	public SolicitudFactura(TipoFactura tipo) {
		this.tipo = tipo;
	}
	public TipoFactura getTipo() {
		return tipo;
	}
}

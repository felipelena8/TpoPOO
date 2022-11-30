import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import modelo.Sistema;
import modelo.enums.DescripcionArticulo;
import modelo.enums.Perfil;
import modelo.enums.TipoFactura;
import solicitudes.SolicitudCliente;
import solicitudes.SolicitudEmpleado;
import solicitudes.SolicitudFactura;
import vista.PantallaLogin;

public class Main {

	public static void main(String[] args) {
		ControladorPantalla controladorPantalla = ControladorPantalla.getInstance();
		ControladorSistema controladorSistema = ControladorSistema.getInstance();
		SolicitudEmpleado solicitudEmpleado = new SolicitudEmpleado("Juan", "Perez", "1", Perfil.ADMINISTRADOR, "admin", "admin"); 
		controladorSistema.agregarCliente(new SolicitudCliente("Lucas", "Munoz", "19232232", "1158229501","20192322321","lucasmunoz@gmail.com","Calle 412"));
		controladorSistema.agregarCliente(new SolicitudCliente("Felipe", "Costa", "44967716", "1167081366", "204496771602", "felipelena8@gmail.com", "calle 1234"));
		controladorSistema.agregarEmpleado(solicitudEmpleado);
		controladorSistema.emitirFactura(new SolicitudFactura(TipoFactura.A),"lucasmunoz@gmail.com");
		controladorSistema.emitirFactura(new SolicitudFactura(TipoFactura.B), "felipelena8@gmail.com");
		controladorPantalla.mostrarPantallaChica(new PantallaLogin());
		
		controladorSistema.agregarArticulo(DescripcionArticulo.CONDENSADORA, 5);
		controladorSistema.agregarArticulo(DescripcionArticulo.EVAPORADORA, 10);
		controladorSistema.agregarArticulo(DescripcionArticulo.KIT_DE_INSTALACION, 12);
		controladorSistema.agregarArticulo(DescripcionArticulo.SOPORTE_PARED, 6);
		controladorSistema.agregarArticulo(DescripcionArticulo.CONDENSADORA, 4);
		
		controladorSistema.agregarItemDetalle(0, DescripcionArticulo.EVAPORADORA, 20);
		controladorSistema.agregarItemDetalle(0, DescripcionArticulo.CONDENSADORA,5);
		controladorSistema.agregarItemDetalle(0, DescripcionArticulo.CONDENSADORA,4);
		controladorSistema.agregarItemDetalle(0, DescripcionArticulo.SOPORTE_PARED, 3);
		controladorSistema.agregarItemDetalle(0, DescripcionArticulo.KIT_DE_INSTALACION, 6);
		controladorSistema.agregarItemDetalle(1, DescripcionArticulo.EVAPORADORA, 10);
		controladorSistema.agregarItemDetalle(1, DescripcionArticulo.CONDENSADORA, 3);
		
	}

}

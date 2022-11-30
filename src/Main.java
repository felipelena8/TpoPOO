import java.util.ArrayList;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import modelo.Sistema;
import modelo.Tecnico;
import modelo.Turno;
import modelo.enums.DescripcionArticulo;
import modelo.enums.Perfil;
import modelo.enums.Seniority;
import modelo.enums.TipoFactura;
import solicitudes.SolicitudCliente;
import solicitudes.SolicitudEmpleado;
import solicitudes.SolicitudFactura;
import solicitudes.SolicitudTecnico;
import vista.PantallaLogin;
import vista.PantallaTecnicos;

public class Main {

	public static void main(String[] args) {
		ControladorPantalla controladorPantalla = ControladorPantalla.getInstance();
		ControladorSistema controladorSistema = ControladorSistema.getInstance();
		SolicitudEmpleado solicitudEmpleado = new SolicitudEmpleado("Juan", "Perez", "1", Perfil.ADMINISTRADOR, "admin", "admin", Seniority.SENIOR); 
		SolicitudEmpleado solicitudEmpleado2 = new SolicitudEmpleado("Juan1", "Perez2", "12", Perfil.ADMINISTRADOR_SISTEMA, "system", "system", Seniority.SENIOR); 
		controladorSistema.agregarCliente(new SolicitudCliente("Lucas", "Munoz", "19232232", "1158229501","20192322321","lucasmunoz@gmail.com","Calle 412"));
		controladorSistema.agregarCliente(new SolicitudCliente("Felipe", "Costa", "44967716", "1167081366", "204496771602", "felipelena8@gmail.com", "calle 1234"));
		controladorSistema.agregarCliente(new SolicitudCliente("Marcos", "Picon", "44321923", "1167622361", "204432192302", "marcospicon@gmail.com", "por alla 3213"));
		controladorSistema.emitirFactura(new SolicitudFactura(TipoFactura.A),"lucasmunoz@gmail.com");
		controladorSistema.emitirFactura(new SolicitudFactura(TipoFactura.B), "felipelena8@gmail.com");
		controladorSistema.agregarEmpleado(new SolicitudEmpleado("Juan", "Perez", "1", Perfil.ADMINISTRADOR, "admin", "admin", Seniority.SENIOR));
		controladorSistema.agregarEmpleado(new SolicitudEmpleado("Pedro", "Gonzales", "2", Perfil.ADMINISTRADOR_SISTEMA, "sistema", "sistema",  Seniority.SENIOR));
		controladorSistema.agregarEmpleado(new SolicitudEmpleado("Esteban", "Quito", "3", Perfil.OPERADOR, "operador", "operador", Seniority.SENIOR));
		
		
		controladorSistema.crearTecnico(new SolicitudTecnico("Elsa", "Pato", "elsapato", "elsapato","12312", Turno.MANANA, Seniority.SENIOR, Perfil.TECNICO));
		controladorSistema.crearTecnico(new SolicitudTecnico("Elsa", "cacorchos", "elca", "elsaca","1235312", Turno.TARDE, Seniority.SEMISENIOR, Perfil.TECNICO));
		controladorSistema.crearTecnico(new SolicitudTecnico("Elsa", "catangas", "elsaca", "elsacatangas","1222", Turno.MANANA, Seniority.JUNIOR, Perfil.TECNICO));
		controladorSistema.agregarEmpleado(solicitudEmpleado);
		controladorSistema.agregarEmpleado(solicitudEmpleado2);
		
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

		controladorPantalla.mostrarPantallaChica(new PantallaLogin());
	}

}

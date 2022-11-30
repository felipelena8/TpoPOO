package modelo.equipos;

import modelo.Articulo;
import modelo.Sistema;
import modelo.enums.Seniority;

public class AreaSistema extends EquipoTrabajo{

	public AreaSistema(Double sueldoJunior, Double sueldoSemisenior, Double sueldoSenior) {
		super(sueldoJunior, sueldoSemisenior, sueldoSenior);
		
	}
	
	public void actualizarSueldoTecnicos(Seniority seniority, double nuevoSueldo) {
		EquipoTecnico equipoTecnico = Sistema.getInstance().getEquipoTecnico();
		switch (seniority) {
		case JUNIOR:
			equipoTecnico.setSueldoJunior(nuevoSueldo);
			break;
		case SEMISENIOR:
			equipoTecnico.setSueldoSemisenior(nuevoSueldo);
			break;
		case SENIOR:
			equipoTecnico.setSueldoSenior(nuevoSueldo);
			break;
		}
	}
	
	public void actualizarStock(Articulo articulo, int nuevoStock, double nuevoPrecio) {
		articulo.setStock(nuevoStock);
		articulo.setPrecio(nuevoPrecio);
	}


}

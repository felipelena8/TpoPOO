package modelo.equipos;

import java.util.ArrayList;
import java.util.List;

import modelo.Empleado;
import modelo.enums.Seniority;

public abstract class EquipoTrabajo {
	
	private Double sueldoJunior;
	private Double sueldoSemisenior;
	private Double sueldoSenior;
	private List<Empleado> equipo;
	
	public EquipoTrabajo(Double sueldoJunior, Double sueldoSemisenior, Double sueldoSenior) {
		 this.equipo = new ArrayList<>();
		 this.sueldoJunior = sueldoJunior;
		 this.sueldoSemisenior = sueldoSemisenior;
		 this.sueldoSenior = sueldoSenior;
		
	}
	
	
	public Double obtenerSueldo(Seniority seniority) {
		switch(seniority) {
		case JUNIOR:
			return sueldoJunior;
		case SEMISENIOR:
			return sueldoSemisenior;
		case SENIOR:
			return sueldoSenior;
		default:
			return 0.0;
			
		}	
	}
	
	protected void setSueldoJunior(Double sueldo) {
		this.sueldoJunior = sueldo;
	}
	
	protected void setSueldoSemisenior(Double sueldo) {
		this.sueldoSemisenior = sueldo;
	}

	protected void setSueldoSenior(Double sueldo) {
		this.sueldoSenior = sueldo;
	}
	
	public List<Empleado> getEquipo(){
		return equipo;
	}
	
	public void agregarIntegrante(Empleado empleado) {
		equipo.add(empleado);
	}


}

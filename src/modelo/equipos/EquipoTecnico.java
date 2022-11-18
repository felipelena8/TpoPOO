package modelo.equipos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import modelo.Instalacion;
import modelo.Tecnico;
import modelo.Turno;

public class EquipoTecnico extends EquipoTrabajo{
	
	private List<Tecnico> tecnicos;

	public EquipoTecnico(Double sueldoJunior, Double sueldoSemisenior, Double sueldoSenior) {
		super(sueldoJunior, sueldoSemisenior, sueldoSenior);
		this.tecnicos = new ArrayList<>();
	}
	
	public void asignarInstalacion(Instalacion instalacion) {
		
		for (Tecnico t : tecnicos) {
			
		}
		
	}
	
	public List<Tecnico> getTecnicos(){
		return tecnicos;
	}
	
	protected void agregarTecnico(Tecnico tecnico) {
		this.tecnicos.add(tecnico);
		this.agregarIntegrante(tecnico);
	}
	
	private Turno calcularTurno(LocalDateTime inicio, LocalDateTime fin) {
		return Turno.TARDE;
	}
	
}

package modelo.equipos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import modelo.Instalacion;
import modelo.Sistema;
import modelo.Tecnico;
import modelo.Turno;
import modelo.enums.Seniority;
import solicitudes.SolicitudInstalacion;

public class EquipoTecnico extends EquipoTrabajo {

	private List<Tecnico> tecnicos;

	public EquipoTecnico(Double sueldoJunior, Double sueldoSemisenior, Double sueldoSenior) {
		super(sueldoJunior, sueldoSemisenior, sueldoSenior);
		this.tecnicos = new ArrayList<>();
	}

	public void asignarInstalacion(Tecnico tecnico, SolicitudInstalacion solicitudInstalacion) {
		Instalacion instalacion = calcularInstalacion(tecnico, solicitudInstalacion);
		Sistema.getInstance().agregarInstalacion(instalacion);
		tecnico.asignarInstalacion(instalacion);
	}

	public List<Tecnico> getTecnicos() {
		return tecnicos;
	}

	public void agregarTecnico(Tecnico tecnico) {
		this.tecnicos.add(tecnico);
		this.agregarIntegrante(tecnico);
	}

	private Turno calcularTurno(LocalDateTime inicio, LocalDateTime fin) {

		if (inicio.getHour() >= 6 && fin.getHour() <= 13) {
			return Turno.MANANA;
		}

		if (inicio.getHour() >= 13 && fin.getHour() <= 20) {
			return Turno.TARDE;
		}

		return Turno.AMBOS;
	}

	public List<Tecnico> obtenerTecnicosDisponibles(LocalDateTime inicio, LocalDateTime fin) {

		List<Tecnico> disponibles = new ArrayList<>();

		Turno turnoInstalacion = calcularTurno(inicio, fin);

		for (Tecnico t : tecnicos) {

			if (turnoInstalacion.equals(t.getTurno()) || turnoInstalacion.equals(Turno.AMBOS)) {

				if (validarDisponibilidadTecnico(t, inicio, fin)) {
					disponibles.add(t);
				}

			}
		}

		return disponibles;
	}

	private boolean validarDisponibilidadTecnico(Tecnico t, LocalDateTime inicio, LocalDateTime fin) {
		Integer horasTrabajo = obtenerHorasPorInstalacion(t.getSeniority());
		if ((fin.getHour() - inicio.getHour()) < horasTrabajo
				|| inicio.plusHours(horasTrabajo).getHour() > t.getTurno().getFin()) {
			return false;
		}

		return true;
	}

	private Integer obtenerHorasPorInstalacion(Seniority seniority) {
		switch (seniority) {
		case JUNIOR:
			return 4;
		case SEMISENIOR:
			return 2;
		case SENIOR:
			return 1;
		default:
			return 4;

		}
	}

	private Instalacion calcularInstalacion(Tecnico tecnico, SolicitudInstalacion req) {

		LocalDateTime inicio = req.getFechaInicio();
		LocalDateTime fin = req.getFechaFin();
		Integer horasTrabajo = obtenerHorasPorInstalacion(tecnico.getSeniority());

		if (!tecnico.getInstalaciones().isEmpty()) {

			for (int i = 0; i < tecnico.getInstalaciones().size(); i++) {
				Instalacion instalacion = tecnico.getInstalaciones().get(i);
				if (tecnico.getInstalaciones().get(i).getFechaFin().isBefore(req.getFechaInicio())) {

					if (((instalacion.getFechaFin().getHour() + horasTrabajo) <= tecnico.getTurno().getFin()
							|| (i < tecnico.getInstalaciones().size() - 1 && 
									tecnico.getInstalaciones().get(i + 1).getFechaInicio().isAfter(req.getFechaInicio())))) {
						inicio = instalacion.getFechaFin();
						fin = inicio.plusHours(horasTrabajo);
						return new Instalacion(inicio, fin, req.getCliente());
					}

				}
				if (i == tecnico.getInstalaciones().size() - 1) {
				
					if(inicio.getHour() < tecnico.getTurno().getInicio()) {
						inicio = inicio.minusHours(inicio.getHour()).minusMinutes(inicio.getMinute());
						inicio = inicio.plusHours(tecnico.getTurno().getInicio());
						
						
					}else {
						inicio = instalacion.getFechaFin();
						
					}
					
					fin = inicio.plusHours(horasTrabajo);
					return new Instalacion(inicio, fin, req.getCliente());
				}

			}

		}
		fin = req.getFechaInicio().plusHours(horasTrabajo);
		return new Instalacion(inicio, fin, req.getCliente());
	}

	public Tecnico buscarTecnicoDni(String dni) {
		for (Tecnico t : tecnicos) {
			if (t.soyTecnico(dni)) {
				return t;
			}
		}
		return null;
	}

}

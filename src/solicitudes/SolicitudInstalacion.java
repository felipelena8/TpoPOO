package solicitudes;

import java.time.LocalDateTime;

import modelo.Cliente;

public class SolicitudInstalacion {
	

	private LocalDateTime fechaInicio; 
	private LocalDateTime fechaFin;
	private Cliente cliente;

	
	public SolicitudInstalacion(LocalDateTime fechaInicio, LocalDateTime fechaFin, Cliente cliente) {
		this.fechaFin = fechaFin;
		this.fechaInicio = fechaInicio;
		this.cliente = cliente;
	}
	
	
	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}
	public LocalDateTime getFechaFin() {
		return fechaFin;
	}
	public Cliente getCliente() {
		return cliente;
	}
}

package modelo;

import java.time.LocalDateTime;

import modelo.costos.Almuerzo;
import modelo.costos.Costo;
import modelo.enums.EstadoInstalacion;
import modelo.usuarios.Tecnico;

public class Instalacion {
private static long idCount;
private long id;
private EstadoInstalacion estado;
private LocalDateTime fechaInicio;
private LocalDateTime fechaFin;
private Tecnico tecnico;
private Cliente cliente;
private Costo costo;
private Almuerzo almuerzo;

public Instalacion(LocalDateTime fechaInicio, LocalDateTime fechaFin, Tecnico tecnico, Cliente cliente) {
	this.fechaFin=fechaFin;
	this.fechaInicio=fechaInicio;
	this.tecnico = tecnico;
	this.cliente = cliente;
	estado = EstadoInstalacion.PROGRAMADA;
	id=idCount++;
}

public EstadoInstalacion getEstado() {
	return estado;
}
public void setEstado(EstadoInstalacion estado) {
	this.estado = estado;
}
public LocalDateTime getFechaInicio() {
	return fechaInicio;
}

public long getId() {
	return id;
}

public LocalDateTime getFechaFin() {
	return fechaFin;
}

public void calcularTiempoTrabajado(LocalDateTime fechaIni, LocalDateTime fechaFin) {
	costo.calcularTiempoTrabajado(fechaIni, fechaFin);
}
public void modificarFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
	this.fechaInicio=fechaInicio;
	this.fechaFin=fechaFin;
}

//desarrollar este metodo
public void finalizarInstalacion() {
	this.estado=EstadoInstalacion.FINALIZADA;
	costo.calcularTiempoTrabajado(fechaInicio, fechaFin);
}

}

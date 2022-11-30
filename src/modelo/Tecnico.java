package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import modelo.enums.Perfil;
import modelo.enums.Seniority;

public class Tecnico extends Empleado{
	
	private Turno turno;
	
	private List<Instalacion> instalaciones;

	public Tecnico(String nombre, String apellido, String dni, Perfil perfil, Turno turno, String username, String password, Seniority seniority) {
		super(nombre, apellido, dni, perfil, username, password, seniority);
		this.turno = turno;
		this.instalaciones = new ArrayList<>();
		
	}
	
	public List<Instalacion> getInstalaciones(){
		return instalaciones;
	}
	
	public Turno getTurno() {
		return turno;
	}
	
	public boolean soyTecnico(String dni) {
		return dni.equals(getDni());
	}
	
	public void asignarInstalacion(Instalacion instalacion) {
		this.getInstalaciones().add(instalacion);
		instalacion.reasignarTecnico(this);
		Collections.sort(this.getInstalaciones(), new Comparator<Instalacion>() {
			  @Override
			  public int compare(Instalacion i1, Instalacion i2) {
			    return i1.getFechaInicio().compareTo(i2.getFechaInicio());
			  }
			});
	}

	public DefaultTableModel informacionInstalacionesTecnico() {
		DefaultTableModel modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelo.setColumnIdentifiers("ID-Fecha Inicio-Fecha Fin-Cliente DNI-Estado".split("-"));
		for(Instalacion i: instalaciones) {
			modelo.addRow(new Object[] {i.getId(), i.getFechaInicio(), i.getFechaFin(), i.getCliente().getDni(), i.getEstado()});
		}
		return modelo;
	}

}

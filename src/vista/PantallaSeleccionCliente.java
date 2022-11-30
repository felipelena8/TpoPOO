package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import solicitudes.SolicitudInstalacion;

public class PantallaSeleccionCliente extends JFrame {
	JTable tablaClientes;
	private ControladorPantalla controladorPantalla;
	private ControladorSistema controladorSistema;

	public PantallaSeleccionCliente(LocalDateTime fechaIni, LocalDateTime fechaFin) {
		controladorPantalla = ControladorPantalla.getInstance();
		controladorSistema = ControladorSistema.getInstance();
		JPanel panel = new JPanel();
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		Container containerTabla = new Container();
		tablaClientes=  new JTable(controladorSistema.informacionClientes());
		JScrollPane tableScrollPane = new JScrollPane(tablaClientes);
		tableScrollPane.setPreferredSize(new Dimension(600, 200));
		containerTabla.setLayout(new GridLayout(1, 2, 5, 5));
		containerTabla.add(tableScrollPane);
		panel.add(containerTabla);
		JFrame pantalla = this;		
		tablaClientes.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tablaClientes.rowAtPoint(e.getPoint());
				String dni = (String) tablaClientes.getValueAt(fila, 0);
				SolicitudInstalacion soli = new SolicitudInstalacion(fechaIni, fechaFin, controladorSistema.buscarClientePorDni(dni));
				controladorPantalla.mostrarPantallaGrande(new PantallaTecnicos(controladorSistema.tecnicosDisponibles(soli),soli));
				controladorPantalla.cerrarPantalla(pantalla);
			}
		});
		
		
		this.add(panel, BorderLayout.CENTER);
	}
}

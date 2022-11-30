package vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import modelo.Instalacion;
import modelo.Tecnico;
import modelo.enums.EstadoInstalacion;

public class PantallaInstalacionesTecnico extends JFrame {
	private JTable tablaInstalaciones;

	private JComboBox<EstadoInstalacion> comboEstado;

	private ControladorPantalla controladorPantalla;

	private ControladorSistema controladorSistema;

	private static Instalacion instalacion = null;

	public PantallaInstalacionesTecnico(Tecnico tecnico) {
		super("Listado de instalaciones");
		controladorPantalla = ControladorPantalla.getInstance();
		controladorSistema = ControladorSistema.getInstance();
		comboEstado = new JComboBox<EstadoInstalacion>(EstadoInstalacion.values());
		JPanel panel = new JPanel();
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		Container containerTabla = new Container();

		tablaInstalaciones = new JTable(tecnico.informacionInstalacionesTecnico());
		JScrollPane tableScrollPane = new JScrollPane(tablaInstalaciones);
		tableScrollPane.setPreferredSize(new Dimension(700, 200));
		containerTabla.setLayout(new GridLayout(1, 2, 5, 5));
		containerTabla.add(tableScrollPane);
		panel.add(containerTabla);
		this.add(panel);
		Container containerForm = new Container();
		containerForm.setLayout(new GridLayout(2, 2, 1, 1));
		JButton btnGuardar = new JButton("Guardar");
		containerForm.add(comboEstado);
		containerForm.add(btnGuardar);
		this.add(containerForm);
	
		JButton btnAtras = new JButton("Volver");
		this.add(btnAtras);
		
		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrarVentana();
				controladorPantalla.mostrarPantallaChica(new PantallaMenu());
			}
		});
		
		tablaInstalaciones.addMouseListener(new MouseListener() {

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
				int row = tablaInstalaciones.rowAtPoint(e.getPoint());
				long id = Integer.parseInt(tablaInstalaciones.getValueAt(row, 0).toString());
				instalacion = controladorSistema.buscarInstalacion(id);
				if (instalacion.getEstado().equals(EstadoInstalacion.FINALIZADA)) {
					comboEstado.setEnabled(false);
					btnGuardar.setEnabled(false);
				} else {
					comboEstado.setEnabled(true);
					btnGuardar.setEnabled(true);
				}
			}
		});

		btnGuardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (instalacion != null) {
					EstadoInstalacion estado = (EstadoInstalacion) comboEstado.getSelectedItem();
					instalacion.setEstado(estado);
					if (estado.equals(EstadoInstalacion.FINALIZADA)) {
						controladorPantalla.mostrarPantallaGrande(new PantallaFinalizarInstalacion(instalacion));
					}
				}
				tablaInstalaciones.setModel(tecnico.informacionInstalacionesTecnico());
			}
		});

	}
	public void cerrarVentana() {
		this.dispose();
	}
}

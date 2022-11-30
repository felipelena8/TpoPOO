package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import modelo.Tecnico;
import solicitudes.SolicitudInstalacion;

public class PantallaTecnicos extends JFrame {
	private JTable tablaTecnicos;

	private ControladorPantalla controladorPantalla;
	private ControladorSistema controladorSistema;
	private List<Tecnico> tecnicos;

	public PantallaTecnicos(List<Tecnico> tecnicos, SolicitudInstalacion soli) {
		super("Listado de tecnicos disponibles");
		this.tecnicos = tecnicos;
		controladorPantalla = ControladorPantalla.getInstance();

		controladorSistema = ControladorSistema.getInstance();

		JPanel panel = new JPanel();

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		Container containerTabla = new Container();

		tablaTecnicos = new JTable(informacionTecnicos());

		JScrollPane tableScrollPane = new JScrollPane(tablaTecnicos);
		tableScrollPane.setPreferredSize(new Dimension(600, 200));
		containerTabla.setLayout(new GridLayout(1, 2, 5, 5));
		containerTabla.add(tableScrollPane);
		panel.add(containerTabla);

		JButton btnAtras = new JButton("Volver");
		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controladorPantalla.mostrarPantallaGrande(new PantallaInstalaciones());
				cerrarVentana();
			}
		});

		Container containerAtras = new Container();
		containerAtras.setBounds(0, 0, 300, 100);
		containerAtras.setLayout(new FlowLayout(FlowLayout.LEFT));
		containerAtras.add(btnAtras);

		panel.add(containerAtras);

		tablaTecnicos.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tablaTecnicos.rowAtPoint(e.getPoint());
				Tecnico tecnico = controladorSistema.buscarTecnicoDni(tablaTecnicos.getValueAt(row, 3).toString());
				controladorSistema.asignarInstalacion(tecnico, soli);
				cerrarVentana();
				
			}
		});

		this.add(panel, BorderLayout.CENTER);

	}

	public DefaultTableModel informacionTecnicos() {
		DefaultTableModel modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelo.setColumnIdentifiers("ID-Nombre-Apellido-DNI-Turno-Usuario-Seniority".split("-"));
		for (Tecnico t : tecnicos) {
			modelo.addRow(new Object[] { t.getUsuario().getId(), t.getNombre(), t.getApellido(), t.getDni(),
					t.getTurno(), t.getUsuario().getUsername(), t.getSeniority()});
		}
		return modelo;
	}

	private void cerrarVentana() {
		controladorPantalla.cerrarPantalla(this);
	}
}

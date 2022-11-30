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
import java.time.LocalDateTime;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.optionalusertools.DateTimeChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateTimeChangeEvent;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import modelo.Instalacion;
import modelo.enums.EstadoInstalacion;
import modelo.enums.Perfil;
import modelo.enums.TipoFactura;
import solicitudes.SolicitudFactura;

public class PantallaInstalaciones extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTable tablaInstalaciones;

	private JComboBox<EstadoInstalacion> comboEstado;

	private ControladorPantalla controladorPantalla;

	private ControladorSistema controladorSistema;

	private DateTimePicker dateTimePickerFechaInicio, dateTimePickerFechaFin;

	private Instalacion instalacionSeleccionada;

	public PantallaInstalaciones() {

		super("Listado de instalaciones");

		controladorPantalla = ControladorPantalla.getInstance();

		controladorSistema = ControladorSistema.getInstance();

		JPanel panel = new JPanel();

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		Container containerTabla = new Container();
		tablaInstalaciones = new JTable(controladorSistema.informacionInstalaciones());
		JScrollPane tableScrollPane = new JScrollPane(tablaInstalaciones);
		tableScrollPane.setPreferredSize(new Dimension(700, 200));
		containerTabla.setLayout(new GridLayout(1, 2, 5, 5));
		containerTabla.add(tableScrollPane);
		panel.add(containerTabla);

		Container containerBotones = new Container();
		containerBotones.setLayout(new GridLayout(4, 1));

		JButton btnActualizar = new JButton("Actualizar");
		JButton btnEliminar = new JButton("Eliminar");
		JButton btnAtras = new JButton("Volver");

		containerBotones.add(btnActualizar);
		containerBotones.add(btnEliminar);
		containerBotones.add(btnAtras);

		panel.add(containerBotones);

		Container containerFormulario = new Container();
		containerFormulario.setLayout(new GridLayout(4, 1));
		containerFormulario.setBounds(100, 300, 300, 100);

		JLabel labelFechaInicio = new JLabel("Fecha de Inicio:");
		dateTimePickerFechaInicio = new DateTimePicker();
		containerFormulario.add(labelFechaInicio);
		containerFormulario.add(dateTimePickerFechaInicio);

		JLabel labelFechaFin = new JLabel("Fecha de Fin:");
		dateTimePickerFechaFin = new DateTimePicker();
		containerFormulario.add(labelFechaFin);
		containerFormulario.add(dateTimePickerFechaFin);

		JButton btnAgregar = new JButton("Agregar");

		JButton btnEmitirFactura = new JButton("Emitir factura");
		JComboBox<TipoFactura> tipoFacturaCombo = new JComboBox<TipoFactura>(TipoFactura.values());
		JLabel labelTipoFacutura = new JLabel("Tipo Factura: ");

		if (controladorSistema.getEmpleadoLogueado().getPerfil().equals(Perfil.ADMINISTRADOR)) {
			btnAgregar.setEnabled(false);
		}

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
				instalacionSeleccionada = controladorSistema.buscarInstalacion(Integer.parseInt(
						tablaInstalaciones.getValueAt(tablaInstalaciones.rowAtPoint(e.getPoint()), 0).toString()));

			}
		});

		btnEmitirFactura.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (instalacionSeleccionada == null) {
					JOptionPane.showMessageDialog(null, "No hay ninguna factura seleccionada");
					return;
				}
				if (instalacionSeleccionada.getEstado().equals(EstadoInstalacion.FINALIZADA)) {
					controladorSistema.emitirFactura(
							new SolicitudFactura((TipoFactura) tipoFacturaCombo.getSelectedItem()),
							instalacionSeleccionada);
					JOptionPane.showMessageDialog(null, "Se ha emitido la factura");
				} else {
					JOptionPane.showMessageDialog(null,
							"La instalacion aun no ha finalizado. No se puede emitir una factura");
				}

			}
		});

		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LocalDateTime fIni = dateTimePickerFechaInicio.getDateTimeStrict();
				LocalDateTime fFin = dateTimePickerFechaFin.getDateTimeStrict();
				if (6 <= fIni.getHour() && fIni.getHour() <= 19 && 7 <= fFin.getHour() && fFin.getHour() <= 20
						&& fFin.getHour() >= fIni.getHour() + 1) {

					if (fIni != null && fFin != null) {
						controladorPantalla.mostrarPantallaGrande(new PantallaSeleccionCliente(fIni, fFin));
						actualizarTabla();
					} else {
						JOptionPane.showMessageDialog(null, "Debes seleccionar dos fechas");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"El rango horario de trabajo es desde 6:00 hasta las 20:00 horas. Y la hora de fin debe ser al menos una hora mayor que la hora de inicio");
				}
			}
		});

		btnActualizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarTabla();

			}
		});

		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controladorPantalla.mostrarPantallaChica(new PantallaMenu());
				cerrarVentana();
			}
		});

		btnEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controladorSistema.eliminarInstalacion(instalacionSeleccionada);
				actualizarTabla();
			}
		});

		Container containerAgregar = new Container();
		containerAgregar.setBounds(0, 0, 300, 100);
		containerAgregar.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		
		Container containerFactura = new Container();
		containerFactura.setLayout(new GridLayout(2, 2, 1, 1));
		panel.add(containerFactura);
		
		Container contFacturaBtn = new Container();
		contFacturaBtn.setLayout(new GridLayout(2,1));
		contFacturaBtn.add(containerFactura);
		contFacturaBtn.add(btnEmitirFactura);
		containerFactura.add(labelTipoFacutura);
		containerFactura.add(tipoFacturaCombo);
		containerAgregar.add(btnAgregar);

		panel.add(containerFormulario);
		panel.add(containerAgregar);
		panel.add(contFacturaBtn);

		this.add(panel, BorderLayout.CENTER);
	}

	public void actualizarTabla() {
		tablaInstalaciones.setModel(controladorSistema.informacionInstalaciones());
	}

	private void cerrarVentana() {
		controladorPantalla.cerrarPantalla(this);
	}

}

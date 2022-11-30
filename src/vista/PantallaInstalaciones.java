package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import modelo.enums.EstadoInstalacion;

public class PantallaInstalaciones extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTable tablaInstalaciones;

	private JComboBox<EstadoInstalacion> comboEstado;

	private DateTimePicker dateTimePickerFechaInicio, dateTimePickerFechaFin;

	private ControladorPantalla controladorPantalla;

	private ControladorSistema controladorSistema;

	public PantallaInstalaciones() {

		super("Listado de instalaciones");

		controladorPantalla = ControladorPantalla.getInstance();

		controladorSistema = ControladorSistema.getInstance();

		JPanel panel = new JPanel();

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		Container containerTabla = new Container();
		tablaInstalaciones = new JTable(controladorSistema.informacionInstalaciones());
		JScrollPane tableScrollPane = new JScrollPane(tablaInstalaciones);
		tableScrollPane.setPreferredSize(new Dimension(500, 200));
		containerTabla.setLayout(new GridLayout(1, 2, 5, 5));
		containerTabla.add(tableScrollPane);
		panel.add(containerTabla);

		Container containerBotones = new Container();
		containerBotones.setLayout(new GridLayout(4, 1));

		JButton btnSeleccionar = new JButton("Seleccionar");
		JButton btnActualizar = new JButton("Actualizar");
		JButton btnEliminar = new JButton("Eliminar");
		JButton btnAtras = new JButton("Volver");

		containerBotones.add(btnSeleccionar);
		containerBotones.add(btnActualizar);
		containerBotones.add(btnEliminar);
		containerBotones.add(btnAtras);

		panel.add(containerBotones);

		Container containerFormulario = new Container();
		containerFormulario.setLayout(new GridLayout(4, 1));
		containerFormulario.setBounds(100, 300, 300, 100);

		JLabel labelEstado = new JLabel("Estado:");
		comboEstado = new JComboBox<EstadoInstalacion>(EstadoInstalacion.values());
		containerFormulario.add(labelEstado);
		containerFormulario.add(comboEstado);

		JLabel labelFechaInicio = new JLabel("Fecha de Inicio:");
		dateTimePickerFechaInicio = new DateTimePicker();
		containerFormulario.add(labelFechaInicio);
		containerFormulario.add(dateTimePickerFechaInicio);

		JLabel labelFechaFin = new JLabel("Fecha de Fin:");
		dateTimePickerFechaFin = new DateTimePicker();
		containerFormulario.add(labelFechaFin);
		containerFormulario.add(dateTimePickerFechaFin);

		panel.add(containerFormulario);
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LocalDateTime fIni = dateTimePickerFechaInicio.getDateTimeStrict();
				LocalDateTime fFin = dateTimePickerFechaFin.getDateTimeStrict();
				if (6 <= fIni.getHour() && fIni.getHour() <= 19 && 7 <= fFin.getHour() && fFin.getHour() <= 20) {

					if (fIni != null && fFin != null) {
						controladorPantalla.mostrarPantallaGrande(new PantallaSeleccionCliente(fIni, fFin));
						actualizarTabla();
					} else {
						JOptionPane.showMessageDialog(null, "Debes seleccionar dos fechas");
					}
				}else {
					JOptionPane.showMessageDialog(null, "El rango horario de trabajo es desde 6:00 hasta las 20:00 horas");
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

		Container containerAgregar = new Container();
		containerAgregar.setBounds(0, 0, 300, 100);
		containerAgregar.setLayout(new FlowLayout(FlowLayout.LEFT));
		containerAgregar.add(btnAgregar);

		panel.add(containerAgregar);

		this.add(panel, BorderLayout.CENTER);
	}

	public void actualizarTabla() {
		tablaInstalaciones.setModel(controladorSistema.informacionInstalaciones());
	}

	private void cerrarVentana() {
		controladorPantalla.cerrarPantalla(this);
	}

}

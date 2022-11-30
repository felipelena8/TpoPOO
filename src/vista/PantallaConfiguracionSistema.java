package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import modelo.Articulo;
import modelo.enums.DescripcionArticulo;
import modelo.enums.Seniority;
import modelo.equipos.EquipoTecnico;

public class PantallaConfiguracionSistema extends JFrame {

	private JTable tablaStock, tablaSeniority;
	private ControladorPantalla controladorPantalla;
	private ControladorSistema controladorSistema;

	private JTextField stockField, precioField, sueldoField;
	private JComboBox<DescripcionArticulo> comboArticulos;
	private JComboBox<Seniority> comboSeniority;

	public PantallaConfiguracionSistema() {

		super("Configuracion Sistema");

		controladorPantalla = ControladorPantalla.getInstance();
		controladorSistema = ControladorSistema.getInstance();

		JPanel panel = new JPanel();

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		Container containerTablaStock = new Container();
		tablaStock = new JTable(controladorSistema.informacionStock());
		JScrollPane tableScrollPaneStock = new JScrollPane(tablaStock);
		tableScrollPaneStock.setPreferredSize(new Dimension(500, 87));
		containerTablaStock.setLayout(new GridLayout(1, 2, 5, 5));
		containerTablaStock.add(tableScrollPaneStock);
		panel.add(containerTablaStock);

		Container containerUpdateStock = new Container();
		containerUpdateStock.setLayout(new FlowLayout(FlowLayout.LEFT));

		comboArticulos = new JComboBox<DescripcionArticulo>(DescripcionArticulo.values());
		containerUpdateStock.add(comboArticulos);

		Container containerTextboxStock = new Container();
		containerTextboxStock.setLayout(new BoxLayout(containerTextboxStock, BoxLayout.Y_AXIS));
		JLabel labelNuevoStock = new JLabel("Nuevo Stock");
		containerTextboxStock.add(labelNuevoStock);
		stockField = new JTextField();
		containerTextboxStock.add(stockField);
		containerUpdateStock.add(containerTextboxStock);

		Container containerTextboxPrecio = new Container();
		containerTextboxPrecio.setLayout(new BoxLayout(containerTextboxPrecio, BoxLayout.Y_AXIS));
		JLabel labelNuevoPrecio = new JLabel("Nuevo Precio");
		containerTextboxPrecio.add(labelNuevoPrecio);
		precioField = new JTextField();
		containerTextboxPrecio.add(precioField);
		containerUpdateStock.add(containerTextboxPrecio);

		JButton btnActualizarArticulo = new JButton("Actualizar Articulo");
		containerUpdateStock.add(btnActualizarArticulo);

		panel.add(containerUpdateStock);

		actualizarTextfieldsArticulos();

		comboArticulos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarTextfieldsArticulos();
			}
		});

		btnActualizarArticulo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controladorSistema.actualizarStock(comboArticulos.getSelectedItem().toString(), Integer.parseInt(stockField.getText()),
						Double.parseDouble(precioField.getText()));
				actualizarTablaStock();
			}
		});

		stockField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					stockField.setEditable(true);
				} else {
					stockField.setEditable(false);
				}
			}
		});

		precioField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE
						|| ke.getKeyCode() == KeyEvent.VK_PERIOD) {
					precioField.setEditable(true);
				} else {
					precioField.setEditable(false);
				}
			}
		});

		Container containerTablaTecnicos = new Container();
		tablaSeniority = new JTable(controladorSistema.informacionTecnicos());
		JScrollPane tableScrollPaneTecnicos = new JScrollPane(tablaSeniority);
		tableScrollPaneTecnicos.setPreferredSize(new Dimension(500, 71));
		containerTablaTecnicos.setLayout(new GridLayout(1, 2, 5, 5));
		containerTablaTecnicos.add(tableScrollPaneTecnicos);
		panel.add(containerTablaTecnicos);

		Container containerUpdateTecnicos = new Container();
		containerUpdateTecnicos.setLayout(new FlowLayout(FlowLayout.LEFT));

		comboSeniority = new JComboBox<Seniority>(Seniority.values());
		containerUpdateTecnicos.add(comboSeniority);

		Container containerUpdateSueldo = new Container();
		containerUpdateSueldo.setLayout(new BoxLayout(containerUpdateSueldo, BoxLayout.Y_AXIS));
		JLabel labelNuevoSueldo = new JLabel("Nuevo Sueldo");
		containerUpdateSueldo.add(labelNuevoSueldo);
		sueldoField = new JTextField();
		containerUpdateSueldo.add(sueldoField);
		containerUpdateTecnicos.add(containerUpdateSueldo);

		JButton btnActualizarSueldo = new JButton("Actualizar Sueldo");
		containerUpdateTecnicos.add(btnActualizarSueldo);
		actualizarTextfieldTecnicos();

		panel.add(containerUpdateTecnicos);

		comboSeniority.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarTextfieldTecnicos();
			}
		});

		sueldoField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE
						|| ke.getKeyCode() == KeyEvent.VK_PERIOD) {
					sueldoField.setEditable(true);
				} else {
					sueldoField.setEditable(false);
				}
			}
		});

		btnActualizarSueldo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Seniority seniority : Seniority.values()) {
					Double nuevoSueldo = Double.parseDouble(sueldoField.getText());
					if (seniority.name() == comboSeniority.getSelectedItem().toString()) {
						controladorSistema.actualizarSueldoTecnicos(seniority, nuevoSueldo);
						actualizarTablaTecnicos();
					}
				}
			}
		});

		Container containerAtras = new Container();
		containerAtras.setLayout(new BoxLayout(containerAtras, BoxLayout.Y_AXIS));

		JButton btnAtras = new JButton("Volver");
		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controladorPantalla.mostrarPantallaChica(new PantallaMenu());
				cerrarVentana();
			}
		});

		containerAtras.setPreferredSize(new Dimension(500, 50));

		containerAtras.add(btnAtras);

		panel.add(containerAtras);

		this.add(panel, BorderLayout.CENTER);
	}

	private void actualizarTextfieldsArticulos() {
		for (Articulo articulo : ControladorSistema.getSistema().getArticulos()) {
			if (articulo.getDescripcion().name() == comboArticulos.getSelectedItem().toString()) {
				stockField.setText(Integer.toString(articulo.getStock()));
				precioField.setText(Double.toString(articulo.getPrecio()));
			}
		}
	}

	private void actualizarTextfieldTecnicos() {
		for (Seniority seniority : Seniority.values()) {
			EquipoTecnico equipoTecnico = ControladorSistema.getSistema().getEquipoTecnico();
			if (seniority.name() == comboSeniority.getSelectedItem().toString()) {
				sueldoField.setText(equipoTecnico.obtenerSueldo(seniority).toString());
			}
		}
	}

	public void actualizarTablaStock() {
		tablaStock.setModel(controladorSistema.informacionStock());
	}

	public void actualizarTablaTecnicos() {
		tablaSeniority.setModel(controladorSistema.informacionTecnicos());
	}

	private void cerrarVentana() {
		controladorPantalla.cerrarPantalla(this);
	}

}
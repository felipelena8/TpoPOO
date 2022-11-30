package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class PantallaConfiguracionSistema extends JFrame {

	private JTable tablaStock, tablaSeniority;
	private ControladorPantalla controladorPantalla;
	private ControladorSistema controladorSistema;

	private JTextField stockField, precioField;
	private JComboBox<DescripcionArticulo> comboArticulos;

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

		actualizarTextfieldsArticuloes();

		comboArticulos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarTextfieldsArticuloes();
			}
		});
		
		btnActualizarArticulo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Articulo articulo : ControladorSistema.getSistema().getArticulos()) {
					if (articulo.getDescripcion().name() == comboArticulos.getSelectedItem().toString()) {
						articulo.setStock(Integer.parseInt(stockField.getText()));
						articulo.setPrecio(Double.parseDouble(precioField.getText()));
						actualizarTablaStock();
					}
				}
			}
		});

		Container containerTablaTecnicos = new Container();
		tablaSeniority = new JTable(controladorSistema.informacionTecnicos());
		JScrollPane tableScrollPaneTecnicos = new JScrollPane(tablaSeniority);
		tableScrollPaneTecnicos.setPreferredSize(new Dimension(500, 75));
		containerTablaTecnicos.setLayout(new GridLayout(1, 2, 5, 5));
		containerTablaTecnicos.add(tableScrollPaneTecnicos);
		panel.add(containerTablaTecnicos);

		this.add(panel, BorderLayout.CENTER);
	}

	private void actualizarTextfieldsArticuloes() {
		for (Articulo articulo : ControladorSistema.getSistema().getArticulos()) {
			if (articulo.getDescripcion().name() == comboArticulos.getSelectedItem().toString()) {
				stockField.setText(Integer.toString(articulo.getStock()));
				precioField.setText(Double.toString(articulo.getPrecio()));
			}
		}
	}

	public void actualizarTablaStock() {
		tablaStock.setModel(controladorSistema.informacionStock());
	}

	private void cerrarVentana() {
		controladorPantalla.cerrarPantalla(this);
	}

}
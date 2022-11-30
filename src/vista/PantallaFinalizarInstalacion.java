package vista;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import modelo.Articulo;
import modelo.Instalacion;
import modelo.Item;
import modelo.enums.DescripcionArticulo;
import modelo.enums.TipoFactura;
import solicitudes.SolicitudFactura;

public class PantallaFinalizarInstalacion extends JFrame {
	private ControladorPantalla controladorPantalla;
	private ControladorSistema controladorSistema;

	public PantallaFinalizarInstalacion(Instalacion i) {
		controladorPantalla = ControladorPantalla.getInstance();
		controladorSistema = ControladorSistema.getInstance();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2, 1, 1));
		JComboBox<DescripcionArticulo> comboArticulo = new JComboBox<DescripcionArticulo>(DescripcionArticulo.values());
		panel.add(comboArticulo);

		Container contAddCantidad = new Container();
		contAddCantidad.setLayout(new GridLayout(1, 2));
		JLabel labelCantidad = new JLabel("Cantidad: ");
		JTextField fieldCantidad = new JTextField();
		JButton btnGuardar = new JButton("Guardar");
		contAddCantidad.add(labelCantidad);
		contAddCantidad.add(fieldCantidad);
		panel.add(contAddCantidad);
		panel.add(btnGuardar);
		this.add(panel);

		fieldCantidad.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					fieldCantidad.setEditable(true);
				} else {
					fieldCantidad.setEditable(false);
				}
			}
		});

		btnGuardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				i.agregarArticulo(new Item(new Articulo((DescripcionArticulo) comboArticulo.getSelectedItem(), 0),
						Integer.parseInt(fieldCantidad.getText())));
			}
		});

	}
}

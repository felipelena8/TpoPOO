package vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;

public class PantallaClientes extends JFrame {
	JTable tablaClientes;
	private ControladorPantalla controladorPantalla;
	private ControladorSistema controladorSistema;
	public PantallaClientes() {
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
		
		JButton btnAgregar = new JButton("Agregar nuevo cliente");
		JButton btnActualizar = new JButton("Actualizar");
		
		btnActualizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tablaClientes.setModel(controladorSistema.informacionClientes());
			}
		});
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controladorPantalla.mostrarPantallaGrande(new PantallaAgregarCliente());
			}
		});
		panel.add(btnAgregar);
		panel.add(btnActualizar);
		this.add(panel);
		
	}
}

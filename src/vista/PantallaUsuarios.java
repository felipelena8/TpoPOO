package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

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

public class PantallaUsuarios extends JFrame {
	private JTable tablaUsuarios;

	private JComboBox<String> comboEstado;

	private ControladorPantalla controladorPantalla;
	private ControladorSistema controladorSistema;
	public PantallaUsuarios() {


		super("Listado de instalaciones");
		
		controladorPantalla = ControladorPantalla.getInstance();
		
		controladorSistema = ControladorSistema.getInstance(); 
		
		JPanel panel = new JPanel();
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		Container containerTabla = new Container();
	    
	    tablaUsuarios = new JTable(controladorSistema.informacionUsuarios());
	    
		JScrollPane tableScrollPane = new JScrollPane(tablaUsuarios);
		tableScrollPane.setPreferredSize(new Dimension(600, 200));
		containerTabla.setLayout(new GridLayout(1, 2, 5, 5));
		containerTabla.add(tableScrollPane);
		panel.add(containerTabla);
		
		Container containerBotones = new Container();
		containerBotones.setLayout(new GridLayout(4, 1));
		
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		JButton btnActualizar = new JButton("Actualizar");
		JButton btnEliminar = new JButton("Eliminar");
		JButton btnAgregar = new JButton("Agregar");
	
		
		containerBotones.add(btnSeleccionar);
		containerBotones.add(btnActualizar);
		containerBotones.add(btnEliminar);
		containerBotones.add(btnAgregar);
		

		panel.add(containerBotones);
		
		
		Container containerFormulario  = new Container();
		containerFormulario.setLayout(new GridLayout(4, 1));
		containerFormulario.setBounds(100, 300, 300, 100);
		
		panel.add(containerFormulario);
		this.add(panel, BorderLayout.CENTER);
		
	}
	public void actualizarTabla() {
		tablaUsuarios.setModel(controladorSistema.informacionInstalaciones());
	}
}

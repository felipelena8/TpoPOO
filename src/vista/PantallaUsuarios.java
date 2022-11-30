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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import modelo.Empleado;

public class PantallaUsuarios extends JFrame {
	private JTable tablaUsuarios;

	private JComboBox<String> comboEstado;

	private ControladorPantalla controladorPantalla;
	private ControladorSistema controladorSistema;
	private int idEmpleado = -1;
	
	public PantallaUsuarios() {

		super("Listado de usuarios");

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

		Container containerFormulario = new Container();
		containerFormulario.setLayout(new GridLayout(4, 1));
		containerFormulario.setBounds(100, 300, 300, 100);

		panel.add(containerFormulario);

		JButton btnAtras = new JButton("Volver");
		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controladorPantalla.mostrarPantallaChica(new PantallaMenu());
				cerrarVentana();
			}
		});
		
		btnAgregar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controladorPantalla.mostrarPantallaGrande(new PantallaAgregarEmpleado());
			}
		});

		btnActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarTabla();
			}
		});
		
		tablaUsuarios.addMouseListener(new MouseListener() {
			
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
				int row = tablaUsuarios.rowAtPoint(e.getPoint());
				idEmpleado = Integer.parseInt(tablaUsuarios.getValueAt(row, 0).toString());
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(controladorSistema.eliminarEmpleado(idEmpleado)) {
					JOptionPane.showMessageDialog(null, "Se ha eliminado el empleado");
					actualizarTabla();
				}else {
					JOptionPane.showMessageDialog(null, "No se ha podido eliminar el empleado");
				}
			}
		});

		Container containerAtras = new Container();
		containerAtras.setBounds(0, 0, 300, 100);
		containerAtras.setLayout(new FlowLayout(FlowLayout.LEFT));
		containerAtras.add(btnAtras);

		panel.add(containerAtras);

		this.add(panel, BorderLayout.CENTER);

	}

	public void actualizarTabla() {
		tablaUsuarios.setModel(controladorSistema.informacionUsuarios());
	}

	private void cerrarVentana() {
		controladorPantalla.cerrarPantalla(this);
	}
}

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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import modelo.Factura;

public class PantallaFacturas extends JFrame {
	JTable tabla = new JTable();
	private ControladorPantalla controladorPantalla;
	private ControladorSistema controladorSistema;

	public PantallaFacturas() {
		super("Listado de facturas");
		controladorPantalla = ControladorPantalla.getInstance();
		controladorSistema = ControladorSistema.getInstance(); 
		tabla.setModel(controladorSistema.informacionFacturas());
		JPanel panel = new JPanel();
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		Container containerTabla = new Container();
		JScrollPane tableScrollPane = new JScrollPane(tabla);
		tableScrollPane.setPreferredSize(new Dimension(500, 200));
		containerTabla.setLayout(new GridLayout(1, 2, 5, 5));
		containerTabla.add(tableScrollPane);
		panel.add(containerTabla);
		
		this.add(panel, BorderLayout.CENTER);
		
		JButton btnAtras = new JButton("Volver");
		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controladorPantalla.mostrarPantallaChica(new PantallaMenu());
				cerrarVentana();
			}
		});
		
		
		Container containerAtras = new Container();
		containerAtras.setBounds(0, 0, 300, 100);
		containerAtras.setLayout(new FlowLayout(FlowLayout.LEFT));
		containerAtras.add(btnAtras);
		
		panel.add(containerAtras);
		
		
		tabla.addMouseListener(new MouseListener() {
			
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
				int fila = tabla.rowAtPoint(e.getPoint());
				Factura f = controladorSistema.buscarFactura((long) tabla.getValueAt(fila, 0));
				controladorPantalla.mostrarPantallaGrande(new PantallaInformacionFactura(f));
			}
		});
	}
	
	private void cerrarVentana() {
		controladorPantalla.cerrarPantalla(this);
	}
}

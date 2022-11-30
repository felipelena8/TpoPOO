package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import modelo.Factura;
import modelo.enums.DescripcionArticulo;
import modelo.enums.TipoFactura;

public class PantallaInformacionFactura extends JFrame {
	JTable tablaItems;
	private ControladorPantalla controladorPantalla;
	private ControladorSistema controladorSistema;
	private DescripcionArticulo descrSelec;
	public PantallaInformacionFactura(Factura f) {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		controladorPantalla = ControladorPantalla.getInstance();
		controladorSistema = ControladorSistema.getInstance();
		JPanel panel = new JPanel();
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		Container containerTabla = new Container();
		tablaItems=  new JTable(f.informacionItems());
		JScrollPane tableScrollPane = new JScrollPane(tablaItems);
		tableScrollPane.setPreferredSize(new Dimension(600, 200));
		containerTabla.setLayout(new GridLayout(1, 2, 5, 5));
		containerTabla.add(tableScrollPane);
		Container containerInfo = new Container();
		containerInfo.setLayout(new GridLayout(4,3,2,2));
		
		JLabel ltotal = new JLabel("Total: "+ (f.getTipo() == TipoFactura.A ? f.calcularTotalFactura(): f.calcularTotalFactura() + f.calcularTotalFactura()*Factura.getIva()) + " IVA incluido");
		JLabel lcliente = new JLabel("DNI Cliente: " + f.getCliente().getDni());
		JLabel lnumero = new JLabel("Numero factura: " + f.getNumero());
		JLabel ltipo = new JLabel("Tipo de Factura: " + f.getTipo());
		JButton btnGuardar = new JButton("Guardar cambios");
		
		containerInfo.add(ltotal);
		containerInfo.add(lcliente);
		containerInfo.add(lnumero);
		containerInfo.add(ltipo);
		
		
		JLabel cantidadLabel = new JLabel("Cantidad: ");
		JLabel warningLabel = new JLabel("* Solamente se permiten caracteres numéricos");
		
		warningLabel.setForeground(Color.red);
		warningLabel.setVisible(false);
		JTextField cantidadField = new JTextField();
		
		cantidadField.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	             String value = cantidadField.getText();
	             int l = value.length();
	             if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
	            	 cantidadField.setEditable(true);
	            	 warningLabel.setVisible(false);
	             } else {
	            	 cantidadField.setEditable(false);
	            	 warningLabel.setVisible(true);
	             }
	          }
	       });
	
		containerInfo.add(cantidadLabel);
		containerInfo.add(cantidadField);
		containerInfo.add(warningLabel);
		containerInfo.add(btnGuardar);
		
		panel.add(containerTabla);	
		panel.add(containerInfo);
		this.add(panel);
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cantidadField.isEditable()) {
					f.setItemDetalle(descrSelec ,Integer.parseInt(cantidadField.getText()));
					tablaItems.setModel(f.informacionItems());
				}
			}
		});
		
		tablaItems.addMouseListener(new MouseListener() {
			
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
				cantidadField.setText(tablaItems.getValueAt(tablaItems.rowAtPoint(e.getPoint()), 1).toString());
				descrSelec = (DescripcionArticulo) tablaItems.getValueAt(tablaItems.rowAtPoint(e.getPoint()), 0);
			}
		});
	}
}

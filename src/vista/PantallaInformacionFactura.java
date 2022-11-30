package vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import modelo.Factura;
import modelo.enums.TipoFactura;

public class PantallaInformacionFactura extends JFrame {
	JTable tablaItems;
	private ControladorPantalla controladorPantalla;
	private ControladorSistema controladorSistema;
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
		containerInfo.setLayout(new GridLayout(2,3,1,1));
		
		JLabel ltotal = new JLabel("Total: "+ (f.getTipo() == TipoFactura.A ? f.calcularTotalFactura(): f.calcularTotalFactura() + f.calcularTotalFactura()*Factura.getIva()) + " IVA incluido");
		JLabel lcliente = new JLabel("DNI Cliente: " + f.getCliente().getDni());
		JLabel lnumero = new JLabel("Numero factura: " + f.getNumero());
		JLabel ltipo = new JLabel("Tipo de Factura: " + f.getTipo());
		JButton btnGuardar = new JButton("Guardar cambios");
		
		containerInfo.add(ltotal);
		containerInfo.add(lcliente);
		containerInfo.add(lnumero);
		containerInfo.add(ltipo);
		containerInfo.add(btnGuardar);
		
		panel.add(containerTabla);	
		panel.add(containerInfo);
		this.add(panel);
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < tablaItems.getRowCount(); i++) {
					f.getItemsDetalle().get(i).setCantidad(Integer.parseInt((String) tablaItems.getModel().getValueAt(i, 1)));
					System.out.println(tablaItems.getValueAt(i, 1));
					System.out.println(f.getItemsDetalle().get(i).getCantidad());
				}
				tablaItems.setModel(f.informacionItems());
			}
		});
	}
}

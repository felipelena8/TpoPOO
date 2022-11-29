package vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import modelo.Factura;

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
		containerInfo.setLayout(new GridLayout(2,2,1,1));
		JLabel ltotal = new JLabel("Total: " + f.calcularTotalFactura());
		JLabel lcliente = new JLabel("DNI Cliente: " + f.getCliente().getDni());
		JLabel lnumero = new JLabel("Numero factura: " + f.getNumero());
		JLabel ltipo = new JLabel("Tipo de Factura: " + f.getTipo());
		containerInfo.add(ltotal);
		containerInfo.add(lcliente);
		containerInfo.add(lnumero);
		containerInfo.add(ltipo);
		panel.add(containerTabla);	
		panel.add(containerInfo);
		this.add(panel);
	}
}

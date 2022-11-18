package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controlador.ControladorPantalla;
import controlador.ControladorSistema;
import modelo.enums.EstadoInstalacion;

public class PantallaInstalaciones extends JFrame{
	
	private static final long serialVersionUID = 1L;

	/*
	 * private long id;
private EstadoInstalacion estado;
private LocalDateTime fechaInicio;
private LocalDateTime fechaFin;
private Tecnico tecnico;
private Cliente cliente;
	 * 
	 */
	
    
	
	private DefaultTableModel tableModel;
	
	private JTable tablaInstalaciones;
	
	private JComboBox<String> comboEstado;
	
	private JDateChooser dateChooserFechaInicio, dateChooserFechaFin;
	
	private ControladorPantalla controladorPantalla;
	
	private ControladorSistema controladorSistema;
	
	
		public PantallaInstalaciones() {
			
			super("Listado de camiones");
			
			controladorPantalla = ControladorPantalla.getInstance();
			
			controladorSistema = ControladorSistema.getInstance(); 
			
			JPanel panel = new JPanel();
			
			this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
			Container containerTabla = new Container();
			String[] columnas = {"ID", "Estado", "Fecha Inicio", "Fecha Fin", "Técnico", "Cliente"};
			
			tableModel = new DefaultTableModel(null, columnas){

		        private static final long serialVersionUID = 1L;

				@Override
		        public boolean isCellEditable(int row, int column)
		        {
		            return false;
		        }
		    };
		    
		    tablaInstalaciones = new JTable(tableModel);
		   
		    
			
		
			JScrollPane tableScrollPane = new JScrollPane(tablaInstalaciones);
			tableScrollPane.setPreferredSize(new Dimension(500, 200));
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
			
			JLabel labelEstado = new JLabel("Estado:");
			comboEstado = new JComboBox<String>();
			cargarComboEstado(comboEstado);
			containerFormulario.add(labelEstado);
			containerFormulario.add(comboEstado);
			
			JLabel labelFechaInicio = new JLabel("Fecha de Inicio:");
			dateChooserFechaInicio = new JDateChooser();
			dateChooserFechaInicio.setDateFormatString("dd/MM/yyyy");
			containerFormulario.add(labelFechaInicio);
			containerFormulario.add(dateChooserFechaInicio);
			
			
			JLabel labelFechaFin = new JLabel("Fecha de Fin:");
			dateChooserFechaFin = new JDateChooser();
			dateChooserFechaFin.setDateFormatString("dd/MM/yyyy");
			containerFormulario.add(labelFechaFin);
			containerFormulario.add(dateChooserFechaFin);
			
			
			panel.add(containerFormulario);
			
			
			//rellenarTabla();
			
			this.add(panel, BorderLayout.CENTER);
		}
		
		private void cargarComboEstado(JComboBox<String> comboEstado) {
			comboEstado.addItem(EstadoInstalacion.PROGRAMADA.name());
			comboEstado.addItem(EstadoInstalacion.CANCELADA.name());
			comboEstado.addItem(EstadoInstalacion.EN_CURSO.name());
			comboEstado.addItem(EstadoInstalacion.FINALIZADA.name());
			
		}
		
		/*
		public void rellenarTabla() {
			
			tableModel.setRowCount(0);
			
			List<Camion> camiones = sistema.getCamiones();
			
			Collections.sort(camiones, new Comparator<Camion>() {
				  @Override
				  public int compare(Camion c1, Camion c2) {
				    return c1.getId().compareTo(c2.getId());
				  }
				});
			
			for (Camion c : camiones) {
				String data[] = {Integer.toString(c.getId()), c.getTipoCarga(), Integer.toString(c.getCapacidad())};
				tableModel.addRow(data);
			}
			
			
			table.setRowSelectionInterval(0, 0);
			rellenarFormulario();
			
		}
		

		
		*/
		/*
		private void rellenarFormulario() {
			
			idField.setText(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
			comboCarga.setSelectedItem(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
			capacidadField.setText(table.getModel().getValueAt(table.getSelectedRow(), 2).toString());
			
			
		}
		
		private void eliminarCamion() {
			sistema.getCamiones().remove(table.getSelectedRow());
		}
		
		private void modificarCamion() {
			
			Integer index = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
			sistema.getCamiones().get(index).setCapacidad(Integer.parseInt(capacidadField.getText()));
			sistema.setCarga(sistema.getCamiones().get(index), comboCarga.getSelectedItem().toString());
			
		}
		
	*/

}

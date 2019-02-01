package miniProj1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class Intro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtItem;
	private JTextField txtPrice;
	private JTextField txtBusiness;
	private JTextField txtAddress;
	private JTable table_first;

	BillManager bill;
	Intro dialog;
	PrintWriter writer;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Intro dialog = new Intro();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		   System.out.println("in main intro");
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}

	/**
	 * Create the dialog.
	 */
	public Intro() {
		setTitle("Information");
		
		setBounds(100, 100, 748, 538);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 195, 304, 146);
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.add(panel);
		
		JLabel lbl_item = new JLabel("Item Name");
		lbl_item.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_item.setBounds(12, 13, 121, 27);
		panel.add(lbl_item);
		
		JLabel lbl_price = new JLabel("Price");
		lbl_price.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_price.setBounds(12, 68, 110, 23);
		panel.add(lbl_price);
		
		JButton btnAddItem = new JButton("Add Item");
		
		btnAddItem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddItem.setBounds(127, 108, 138, 25);
		panel.add(btnAddItem);
		
		txtItem = new JTextField();
		txtItem.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtItem.setColumns(10);
		txtItem.setBounds(127, 16, 138, 27);
		panel.add(txtItem);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtPrice.setColumns(10);
		txtPrice.setBounds(127, 68, 138, 27);
		panel.add(txtPrice);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 75, 706, 96);
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.add(panel_1);
		
		JLabel lbl_bussiness = new JLabel("Bussiness Name");
		lbl_bussiness.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_bussiness.setBounds(12, 13, 121, 27);
		panel_1.add(lbl_bussiness);
		
		txtBusiness = new JTextField();
		txtBusiness.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtBusiness.setColumns(10);
		txtBusiness.setBounds(159, 16, 521, 22);
		panel_1.add(txtBusiness);
		
		JLabel lbl_address = new JLabel("Address");
		lbl_address.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_address.setBounds(12, 53, 144, 26);
		panel_1.add(lbl_address);
		
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtAddress.setColumns(10);
		txtAddress.setBounds(159, 56, 521, 22);
		panel_1.add(txtAddress);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					writer=new PrintWriter(bill.introfile);
					writer.append(txtBusiness.getText().toString());
					writer.println();
					writer.append(txtAddress.getText().toString());
					writer.println();
					 writer.flush();
					 writer.close();
				      ArrayList<String> al=new ArrayList<String>();
				      int nrow=table_first.getRowCount();
				      int ncol=table_first.getColumnCount();
				      writer=new PrintWriter(bill.itemfile);
				      for(int i=0;i<nrow;i++) {
				    	  
				    		
				    		 writer.append((String)table_first.getValueAt(i, 0));
				    		 writer.println();
				    	  
				      }
				      writer.close();
				      writer=new PrintWriter(bill.pricefile);
				      for(int i=0;i<nrow;i++) {
				    	  writer.append((String)table_first.getValueAt(i, 1));
				    		 writer.println();
				    		
				      }
				      
				  
				     writer.close();
				     Intro.this.dispose();
				     
				   bill=new BillManager();
					bill.loadvalues();
	
			
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		btnOk.setBounds(621, 418, 97, 25);
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPanel.add(btnOk);
		
		JLabel lblIntro = new JLabel("Please provide the necessary Information");
		lblIntro.setBounds(12, 29, 706, 33);
		lblIntro.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntro.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPanel.add(lblIntro);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(328, 195, 390, 146);
		contentPanel.add(scrollPane);
		
		table_first = new JTable();
		table_first.setFont(new Font("Tahoma", Font.BOLD, 13));
		table_first.setBackground(Color.WHITE);
		scrollPane.setViewportView(table_first);
		table_first.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item Name", "Price"
			}
		));
		table_first.getColumnModel().getColumn(0).setPreferredWidth(174);
		
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) table_first.getModel();
				model.addRow(new Object[]{txtItem.getText().toString(),txtPrice.getText().toString()});
			}
		});
	}
}

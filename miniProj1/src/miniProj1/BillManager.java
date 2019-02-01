package miniProj1;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Window;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.util.Scanner;
//import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;
import javax.swing.JSpinner;
import java.util.*;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;


public class BillManager  {
	public static File introfile,itemfile,pricefile;
	private  JFrame frmBillManager;
	private JTextField txtAddress;
	private JTable table_Item;
	private JTextField txtSubTotal;
	private JTextField txtVat;
	private JTextField txtTotal;
	private static JTextField txtName;
	static String bussinessName;
	private static JComboBox cbox_Item;
	private JSpinner spin_Quantity;
	static ArrayList<Integer> price;
    static BillManager window;
    private JTextArea txt_bill;

	public static void main(String[] args) {
 		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				     introfile = new File("d:\\introfile.txt");
				     itemfile=new File("d:\\itemfile.txt");
			    	  pricefile=new File("d:\\pricefile.txt");
				      if (introfile.createNewFile()){
				    		Intro intro=new Intro();
				    	 	intro.setVisible(true);	
				      }else{
				    	  window = new BillManager();
				    	  loadvalues();
				    	  window.frmBillManager.setVisible(true);
				      }
			    	} catch (IOException e) {
				      e.printStackTrace();
				}
			}
		});
	}
	public BillManager() {
		initialize();
	}
	
	private void initialize() {
		frmBillManager = new JFrame();
		frmBillManager.setTitle("Bill Manager");
		frmBillManager.setBounds(100, 100, 733, 639);
		frmBillManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBillManager.getContentPane().setLayout(null);
		frmBillManager.setVisible(true);
		JPanel panel_cstInfo = new JPanel();
		panel_cstInfo.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_cstInfo.setBounds(12, 13, 350, 125);
		frmBillManager.getContentPane().add(panel_cstInfo);
		panel_cstInfo.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(12, 13, 82, 28);
		panel_cstInfo.add(lblName);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtName.setBounds(78, 17, 260, 22);
		panel_cstInfo.add(txtName);
		txtName.setColumns(10);
		txtName.setText(bussinessName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddress.setBounds(12, 57, 82, 28);
		panel_cstInfo.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtAddress.setColumns(10);
		txtAddress.setBounds(78, 61, 260, 22);
		panel_cstInfo.add(txtAddress);
		
		JScrollPane scrollPane_CartInfo = new JScrollPane();
		scrollPane_CartInfo.setOpaque(false);
		scrollPane_CartInfo.setBounds(12, 353, 686, 114);
		frmBillManager.getContentPane().add(scrollPane_CartInfo);
		
		table_Item = new JTable();
		table_Item.setFont(new Font("Tahoma", Font.BOLD, 13));
		table_Item.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item Name", "Quantity", "Cost per Item", "Price"
			}
		));
		table_Item.getColumnModel().getColumn(0).setPreferredWidth(186);
		table_Item.getColumnModel().getColumn(1).setPreferredWidth(62);
		table_Item.getColumnModel().getColumn(2).setPreferredWidth(100);
		scrollPane_CartInfo.setViewportView(table_Item);
		
		JPanel panel_Cart = new JPanel();
		panel_Cart.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_Cart.setBounds(12, 151, 350, 192);
		frmBillManager.getContentPane().add(panel_Cart);
		panel_Cart.setLayout(null);
		
		JLabel lblChoose = new JLabel("Choose an Item");
		lblChoose.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoose.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChoose.setBounds(12, 13, 119, 28);
		panel_Cart.add(lblChoose);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuantity.setBounds(193, 13, 119, 28);
		panel_Cart.add(lblQuantity);
		
		cbox_Item = new JComboBox();
		cbox_Item.setFont(new Font("Tahoma", Font.BOLD, 13));
		cbox_Item.setEditable(true);
		cbox_Item.setBounds(12, 54, 119, 28);
		panel_Cart.add(cbox_Item);
		
		JButton btnAdd = new JButton("Add Item");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int table_price=0;
				table_price=(int) spin_Quantity.getValue()*price.get(cbox_Item.getSelectedIndex());
				DefaultTableModel model = (DefaultTableModel) table_Item.getModel();
				model.addRow(new Object[]{cbox_Item.getSelectedItem().toString(),spin_Quantity.getValue(),price.get(cbox_Item.getSelectedIndex()),table_price});
				cbox_Item.setSelectedIndex(0);
				spin_Quantity.setValue(0);
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdd.setBounds(106, 142, 119, 37);
		panel_Cart.add(btnAdd);
		
		spin_Quantity = new JSpinner();
		spin_Quantity.setFont(new Font("Tahoma", Font.BOLD, 13));
		spin_Quantity.setBounds(174, 54, 124, 28);
		panel_Cart.add(spin_Quantity);
		
		JPanel panel_Bill = new JPanel();
		panel_Bill.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_Bill.setBounds(374, 13, 324, 330);
		frmBillManager.getContentPane().add(panel_Bill);
		panel_Bill.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Bill Information");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(12, 13, 300, 26);
		panel_Bill.add(lblNewLabel_2);
		
		JLabel lblSubTotal = new JLabel("Sub Total");
		lblSubTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSubTotal.setBounds(12, 110, 82, 47);
		panel_Bill.add(lblSubTotal);
		
		JLabel lblVat = new JLabel("VAT");
		lblVat.setHorizontalAlignment(SwingConstants.CENTER);
		lblVat.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVat.setBounds(12, 170, 82, 42);
		panel_Bill.add(lblVat);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotal.setBounds(12, 225, 82, 47);
		panel_Bill.add(lblTotal);
		
		txtSubTotal = new JTextField();
		txtSubTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtSubTotal.setEditable(false);
		txtSubTotal.setColumns(10);
		txtSubTotal.setBounds(106, 110, 152, 45);
		panel_Bill.add(txtSubTotal);
		
		txtVat = new JTextField();
		txtVat.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtVat.setEditable(false);
		txtVat.setColumns(10);
		txtVat.setBounds(110, 172, 152, 45);
		panel_Bill.add(txtVat);
		
		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);
		txtTotal.setBounds(110, 227, 152, 45);
		panel_Bill.add(txtTotal);

		JButton btnTotal = new JButton("Total");
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row_count=table_Item.getRowCount();
				double subTotal=0;
				for(int i=0;i<row_count;i++) {
					subTotal=subTotal+Integer.parseInt(table_Item.getValueAt(i, 3).toString());
				}
				DecimalFormat df=new DecimalFormat("#.00");
				double vat=subTotal*0.13;
				txtSubTotal.setText(df.format(subTotal));
				txtVat.setText(df.format(vat));
				txtTotal.setText(df.format(vat+subTotal));
			}
		});
		btnTotal.setBounds(12, 496, 109, 37);
		frmBillManager.getContentPane().add(btnTotal);
		
		JButton btnRecipt = new JButton("Recipt");
		btnRecipt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRecipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_bill.setText("");
				
				frmBillManager.setBounds(100, 100, 1257, 639);
				String items_name = "";
				String quantity="";
				String cost="";
				txt_bill.setText("\n-----------------------------------------------------------");
				txt_bill.append("\n\t\t    *****BILL MANAGER****");				
				txt_bill.append("\n-----------------------------------------------------------\n\n");
				txt_bill.append("\tItem\t\tQuantity\t\tPrice ");
				txt_bill.append("\n-----------------------------------------------------------\n");
				for(int i=0;i<table_Item.getRowCount();i++) {
					
						items_name=(String) table_Item.getValueAt(i, 0);
						quantity=table_Item.getValueAt(i, 1).toString();
						cost=table_Item.getValueAt(i, 3).toString();
						txt_bill.append("\n\n\t"+items_name+"\t\t"+quantity+"\t\t\t"+cost+"\n");
				}
				txt_bill.append("\n\t\t\t\t---------------");
				txt_bill.append("\n\n\t\t\t\t  SubTotal:"+" "+txtSubTotal.getText()+"\n\n\t\t\t\t  Vat:"+" "+txtVat.getText()
				+"\n\n\t\t\t\t  Total:"+" "+txtTotal.getText());
				txt_bill.append("\n\t\t\t\t---------------");
				
				
			}
		});
		btnRecipt.setBounds(147, 496, 109, 37);
		frmBillManager.getContentPane().add(btnRecipt);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBillManager.setBounds(100, 100, 733, 639);
				txtName.setText("");
				txtAddress.setText("");
				txtSubTotal.setText("");
				txtTotal.setText("");
				txtVat.setText("");
				txt_bill.setText("");
				DefaultTableModel model=(DefaultTableModel) table_Item.getModel();
				model.setRowCount(0);
				
			}
		});
		btnReset.setBounds(282, 496, 109, 37);
		frmBillManager.getContentPane().add(btnReset);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savetoDB();
			}
		});
		btnSave.setBounds(417, 496, 109, 37);
		frmBillManager.getContentPane().add(btnSave);
		
		JButton btnRecords = new JButton("View Records");
		btnRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Records rec=new Records();
				rec.setVisible(true);
			}
		});
		btnRecords.setBounds(555, 496, 143, 37);
		frmBillManager.getContentPane().add(btnRecords);
		
		
		txt_bill = new JTextArea();
		txt_bill.setBorder(new LineBorder(new Color(0, 0, 0)));
		txt_bill.setFont(new Font("Monospaced", Font.BOLD, 14));
		txt_bill.setBounds(761, 13, 470, 520);
		txt_bill.setEditable(false);
		frmBillManager.getContentPane().add(txt_bill);
		
		JScrollPane scrollPane_bill = new JScrollPane(txt_bill);
		scrollPane_bill.setBounds(761, 13, 470, 520);
		frmBillManager.getContentPane().add(scrollPane_bill);
		
		
		
		
		
	}

	 static void loadvalues() {
		 
		Scanner scan;
		ArrayList<String> item=new ArrayList<String>();
		price=new ArrayList<Integer>();
		Iterator<String> itemItr= item.iterator();
		try {
			scan=new Scanner(itemfile);
			while (scan.hasNext()) {
				item.add(scan.nextLine());
			}
			scan.close();
			String itemArr[]=new String[item.size()];
			itemArr=item.toArray(itemArr);
			for(int i=0;i<itemArr.length;i++) {
				cbox_Item.addItem(itemArr[i]);
			}
			
			scan=new Scanner(pricefile);
			while(scan.hasNext()) {
				price.add(Integer.parseInt(scan.nextLine()));
			}
			scan.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	 
	 public void savetoDB() {
			int j=0;
			String item ="";
			String qty = "";
		 Connection con = null;
		 String conUrl = "jdbc:sqlserver://localhost; databaseName=demo; integratedSecurity=true;";
		 try {
			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			 con = DriverManager.getConnection(conUrl);
			    Statement stmt=con.createStatement();  
				ResultSet rs;
				PreparedStatement p = null;
				String customerName=txtName.getText();
				for(int i=0;i<table_Item.getRowCount();i++) {
					item=item+(String)table_Item.getValueAt(i, j)+"  ";
					qty=qty+table_Item.getValueAt(i, j+1)+"  ";
				}
				String subTotal=txtSubTotal.getText();
				String vat=txtVat.getText();
				String total=txtTotal.getText();
				String address=txtAddress.getText();
				
				 p=con.prepareStatement("Insert into bill(Customer_name,Address,item_name,quantity,subtotal,vat,total) values (?,?,?,?,?,?,?)");
				 p.setString(1, customerName);
				 p.setString(2, address);
				 p.setString(3, item);
				 p.setString(4, qty);
				 p.setString(5, subTotal);
				 p.setString(6,vat);
				 p.setString(7, total);
				 p.executeUpdate();
		 }
		 catch(Exception e) {
			 
			 e.printStackTrace();
		 }	
		}
}
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.sql.*;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;

public class AdminPage extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private boolean flag=true;
	private DbConnection c = new DbConnection();
	private JPanel contentPane;
	private JTextField hotelField;
	private JTextField priceField;
	private JTable table;
	private JTable table_1;
	private JTextField eventTypeTf;
	private JTextField eventNameTf;
	private JTextField eventPriceTf;
	private JTextField detailsTextField;
	private int mouseX;
	private int mouseY;
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/*
	 * when we open the application we get informations from database
	 * and set these informations to tables and arraylists
	 */
	
	public void setValues() {
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

		try {
			con = c.conndb();				
		
			stm= con.createStatement();//we will use handle it using a reference of statement 
			
			rs = stm.executeQuery("select * from hotels");//creating rs we are gettin informations from database
			while(rs.next()) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.addRow(new Object[] {
						rs.getString(1),
						rs.getInt(2)				
				});
				
				//Hotel h = new Hotel(rs.getString(1),"Medium", rs.getInt(2));
				//SystemClass.add(h);					
			}
			
			rs2 = stm.executeQuery("select * from Events");
			while(rs2.next()) {
				DefaultTableModel model2 = (DefaultTableModel)table_1.getModel();
				model2.addRow(new Object[] {
						rs.getString(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getString(4)
										
				});
				//Hotel h = new Hotel(rs.getString(1),"Medium", rs.getInt(2));
				//SystemClass.add(h);					
			}
	
			con.close();
			stm.close();
			rs.close();
			rs2.close();			
			
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
	}
	
	public void setImmage1(String source,JLabel label) {
		ImageIcon i = new ImageIcon(getClass().getResource(source));
		Image img = i.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon i2 = new ImageIcon(img);
		label.setIcon(i2);
	}	
	
	public void dragUndercorated() {
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				
			}
		});
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(getX()+e.getX()-mouseX,getY()+e.getY()-mouseY);
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public AdminPage() {
		
		setTitle("ADMIN");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 970, 677);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(205, 216, 223));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		dragUndercorated();
		
		JLabel lblNewLabel = new JLabel("HotelName:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(34, 26, 110, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrice.setBounds(34, 94, 110, 32);
		contentPane.add(lblPrice);
		
		hotelField = new JTextField();
		hotelField.setBounds(177, 32, 132, 26);
		contentPane.add(hotelField);
		hotelField.setColumns(10);
		
		priceField = new JTextField();
		priceField.setColumns(10);
		priceField.setBounds(177, 100, 132, 26);
		contentPane.add(priceField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 238, 338, 229);
		contentPane.add(scrollPane);
		
		table = new JTable();
		JTableHeader Theader = table.getTableHeader();
		Theader.setBackground(new Color(224,217,237));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"HotelName", "Price"
			}
		) {
			private static final long serialVersionUID = 1L;
		});
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(487, 227, 442, 257);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		JTableHeader Theader2 = table_1.getTableHeader();
		Theader2.setBackground(new Color(224,217,237));
		
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"EventType", "EventName", "Price", "Details"
			}
		));
		scrollPane_1.setViewportView(table_1);
		setValues();
		
		JLabel hotelWarningLabel = new JLabel("");
		hotelWarningLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		hotelWarningLabel.setBounds(216, 158, 176, 39);
		contentPane.add(hotelWarningLabel);

		JButton addButton = new JButton("ADD");
		
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(priceField.getText().isEmpty() || priceField.getText().isEmpty()) {
					hotelWarningLabel.setText("Pls enter price and name!!");
				}
				else {				
				hotelWarningLabel.setText("");							
					try {
						int price = Integer.parseInt(priceField.getText());//we are checking is the entering value is integer
						String name=hotelField.getText();					//if it is not integer we are warning admin
						price = Integer.parseInt(priceField.getText());
						
						try {
							Connection con = c.conndb();			
							name=hotelField.getText();
							price = Integer.parseInt(priceField.getText());
							
							PreparedStatement stm = con.prepareStatement("insert into hotels values (?,?)");
							stm.setString(1, name);
							stm.setInt(2,price);
							stm.executeUpdate();
							
							Hotel h = new Hotel(name,150);
							SystemClass.add(h);
							
							Statement stmm = con.createStatement();
							
							ResultSet rs = stmm.executeQuery("select * from hotels");
							
							while(rs.next()) {
								//System.out.print(rs.getString(1));
								//System.out.println(rs.getInt(2));
								
						}
						stm.close();
						stmm.close();
						rs.close();
						} catch (Exception e2) {
							System.out.println(e2);
						}
						
						DefaultTableModel model = (DefaultTableModel)table.getModel();
						model.addRow(new Object[] {
								name,
								price
						});
					     
					  hotelField.setText("");
					  priceField.setText("");
					}
					catch (Exception E) {
						hotelWarningLabel.setText("Enter an integer value for price");						
					}				
				}
			}
		});
		
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addButton.setBounds(34, 136, 160, 39);
		contentPane.add(addButton);
										
				JButton deleteButton = new JButton("Delete");
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DefaultTableModel model = (DefaultTableModel)table.getModel();
						if(table.getSelectedRow() == -1) {							
							hotelWarningLabel.setText("Select Row");
						}
						else {
							hotelWarningLabel.setText("");
							String hotel=(String)model.getValueAt(table.getSelectedRow(), 0);
							model.removeRow(table.getSelectedRow());
							try {
								Connection con = c.conndb();
								Statement stm = con.createStatement();
								stm.executeUpdate("delete from hotels where hotelName='"+hotel+"';");
							
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
				}
				});
				deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				deleteButton.setBounds(34, 185, 157, 32);
				contentPane.add(deleteButton);
				
				
				JLabel eventNameLabel = new JLabel("EventName:");
				eventNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
				eventNameLabel.setBounds(499, 70, 110, 32);
				contentPane.add(eventNameLabel);
				
				JLabel eventTypeLabel = new JLabel("EventType:");
				eventTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
				eventTypeLabel.setBounds(499, 28, 110, 32);
				contentPane.add(eventTypeLabel);
				
				JLabel eventPriceLabel = new JLabel("EventPrice:");
				eventPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
				eventPriceLabel.setBounds(499, 114, 110, 32);
				contentPane.add(eventPriceLabel);
				
				eventTypeTf = new JTextField();
				eventTypeTf.setBounds(619, 35, 96, 19);
				contentPane.add(eventTypeTf);
				eventTypeTf.setColumns(10);
				
				eventNameTf = new JTextField();
				eventNameTf.setColumns(10);
				eventNameTf.setBounds(619, 77, 96, 19);
				contentPane.add(eventNameTf);
				
				eventPriceTf = new JTextField();
				eventPriceTf.setColumns(10);
				eventPriceTf.setBounds(619, 123, 96, 19);
				contentPane.add(eventPriceTf);
				
				JLabel eventWarningLabel = new JLabel("");
				eventWarningLabel.setBounds(647, 185, 226, 32);
				contentPane.add(eventWarningLabel);
				
				JButton addEventButton = new JButton("ADD");
				addEventButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(eventPriceTf.getText().isEmpty() || eventTypeTf.getText().isEmpty() || eventNameTf.getText().isEmpty() || detailsTextField.getText().isEmpty()) {
							eventWarningLabel.setText("Enter all blanks!!");
						}
						else {
							eventWarningLabel.setText("");
							try {
								Connection con = null;
								PreparedStatement stm = null;
								Statement stmm = null;
								ResultSet rs = null;
								int price = Integer.parseInt(eventPriceTf.getText());
								String type = eventTypeTf.getText();
								String name = eventNameTf.getText();
								String details = detailsTextField.getText();
								
								try {
									con = c.conndb();			
									stm = con.prepareStatement("insert into Events values (?,?,?,?)");
									stm.setString(1, type);
									stm.setString(2, name);
									stm.setInt(3,price);
									stm.setString(4,details);	
									stm.executeUpdate();
									
									Event ev = new Event(type, name, price,details);
									SystemClass.add(ev);
									
									stmm = con.createStatement();
									
									rs = stmm.executeQuery("select * from Events");
									
									while(rs.next()) {
										//System.out.print(rs.getString(1));
										//System.out.print(rs.getString(2));
										//System.out.println(rs.getInt(3));
										//System.out.println(rs.getString(4));
										
										
								}							
									stm.close();
									stmm.close();
									rs.close();
													
								} catch (Exception e2) {
									System.out.println(e2);
								}
								
								DefaultTableModel model = (DefaultTableModel)table_1.getModel();
								model.addRow(new Object[] {
										name,
										type,
										price,
										details
								});
								
								
							} catch (Exception e2) {
								eventWarningLabel.setText("Enter integer value for price");
							}
							eventNameTf.setText("");
							eventPriceTf.setText("");
							eventTypeTf.setText("");
							detailsTextField.setText("");
							
						}		
					}
				});
				addEventButton.setBounds(499, 147, 96, 28);
				contentPane.add(addEventButton);
				
				JButton deleteEventButton = new JButton("DELETE");
				deleteEventButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DefaultTableModel model = (DefaultTableModel)table_1.getModel();
						if(table_1.getSelectedRow() == -1) {
							eventWarningLabel.setText("Select Row");
						}
						else {
							eventWarningLabel.setText("");
							String eventName=(String)model.getValueAt(table_1.getSelectedRow(),1);
							model.removeRow(table_1.getSelectedRow());
							try {
								Connection con = c.conndb();
								Statement stm = con.createStatement();
								stm.executeUpdate("delete from Events where EventName='"+eventName+"';");
								stm.close();
							
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
						
					}
				});
				deleteEventButton.setBounds(499, 189, 96, 28);
				contentPane.add(deleteEventButton);					
				
				JLabel lblNewLabel_1 = new JLabel("Details:");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblNewLabel_1.setBounds(794, 10, 96, 26);
				contentPane.add(lblNewLabel_1);
				
				detailsTextField = new JTextField();
				detailsTextField.setBounds(761, 46, 168, 129);
				contentPane.add(detailsTextField);
				detailsTextField.setColumns(10);
				
				JLabel exitLabel = new JLabel("X");
				exitLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						try {
							LoginFrame f = new LoginFrame();
							f.setVisible(true);
							dispose();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				exitLabel.setForeground(Color.RED);
				exitLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
				exitLabel.setBounds(937, 3, 23, 32);
				contentPane.add(exitLabel);
				
				JLabel bgroundLabel = new JLabel("New label");
				bgroundLabel.setBounds(0, 0, 970, 677);
				contentPane.add(bgroundLabel);
				setImmage1("bground.jpg", bgroundLabel);

			}
	}
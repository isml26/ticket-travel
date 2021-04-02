import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.CardLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseMotionAdapter;


@SuppressWarnings("serial")
public class ShoppingFrame extends JFrame {
	private DbConnection c = new DbConnection();
	private JPanel contentPane;
	private JTable table;
	private JLabel idLabel = new JLabel("\"\"");//create id label
	private JTextField deleteTextField;
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
					MainFrame frame = new MainFrame();
					//frame.setUndecorated(true);
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
	public void setValuesCustomer() {
		try {
			Connection con = c.conndb();				
		
			Statement stm= con.createStatement();
			
			ResultSet rs = stm.executeQuery("select * from hotels");
		
			while(rs.next()) {
				Hotel h = new Hotel(rs.getString(1),rs.getInt(2));
				SystemClass.add(h);									
			}	
			ResultSet rs2 = stm.executeQuery("select * from Events");
			while(rs2.next()) {
				Event e = new Event(rs2.getString(1),rs2.getString(2), rs2.getInt(3),rs.getString(4));
				SystemClass.add(e);									
			}
			stm.close();
			rs.close();
			rs2.close();
			//System.out.println(SystemClass.getEvents());
		} catch (Exception e2) {
			// TODO: handle exception
		}	
	}
	//when we rearrage table we must clear and we set values again
	public static void clear(final DefaultTableModel model) {
	    for( int i = model.getRowCount() - 1; i >= 0; i-- ) {
	        model.removeRow(i);
	    }
	}
	//using this function we can set image to label properly
	public void setImmage1(String source,JLabel label) {
		ImageIcon i = new ImageIcon(getClass().getResource(source));
		Image img = i.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon i2 = new ImageIcon(img);
		label.setIcon(i2);
	}
	
	//using this function we can set image to checkbox properly
	public void setImmage2(String source,JCheckBox cb) {
		ImageIcon i = new ImageIcon(getClass().getResource(source));
		Image img = i.getImage().getScaledInstance(cb.getWidth(), cb.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon i2 = new ImageIcon(img);
		cb.setIcon(i2);
	}
	//here we are trying to get user id because 
	//when we login customerframe we take this name and searching in customerarraylist
	//we set current customer 
	public JLabel getLabel() {
		return idLabel;
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ShoppingFrame() {
		
		SystemClass.setTrips();
		setTitle("Ticket and Travel Ankara");
		setValuesCustomer();//here when we open frame we are getting values from databese and setting to arraylists
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 1095, 769);
		contentPane = new JPanel();
		
		setUndecorated(true);
		dragUndercorated();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ButtonGroup group = new ButtonGroup();
		
		JRadioButton rdbtn = new JRadioButton("",false);
		rdbtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtn.setBackground(new Color(139, 168, 172));
		rdbtn.setBounds(62, 120, 23, 39);
		contentPane.add(rdbtn);
		group.add(rdbtn);
		
		
		JRadioButton rdbtn1 = new JRadioButton("",false);
		rdbtn1.setBackground(new Color(132, 161, 165));
		rdbtn1.setBounds(231, 120, 23, 39);
		contentPane.add(rdbtn1);
		group.add(rdbtn1);
		
		
		JRadioButton rdbtn2 = new JRadioButton("",false);
		rdbtn2.setBounds(407, 119, 23, 39);
		rdbtn2.setBackground(new Color(126, 155, 161));
		contentPane.add(rdbtn2);
		group.add(rdbtn2);
		
		JPanel panel = new JPanel();
		panel.setBounds(28, 192, 730, 523);
		contentPane.add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(153, 153, 204));
		panel1.setBorder(new TitledBorder(null, "Events", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel1, "name_5019060641814100");
		panel1.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 21, 716, 470);
		panel1.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		tabbedPane.addTab("Theater", null, panel_1, null);
		panel_1.setLayout(null);		
		
		JCheckBox selectBox = new JCheckBox("1");
		selectBox.setBackground(new Color(0, 0, 0));
		selectBox.setBounds(29, 150, 61, 50);
		panel_1.add(selectBox);
		setImmage2("empty.png", selectBox);
		
		JButton buyTheaterButton = new JButton("BUY");
		buyTheaterButton.setEnabled(false);
		selectBox.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectBox.isSelected()) {
					
					setImmage2("full.png", selectBox);	
					buyTheaterButton.setEnabled(true);
				}
				else {
					
					setImmage2("empty.png", selectBox);
					buyTheaterButton.setEnabled(false);					
				}								
			}
		});
		
		JCheckBox selectBox_1 = new JCheckBox("1");
		selectBox_1.setBackground(new Color(0, 0, 0));
		selectBox_1.setBounds(124, 150, 61, 50);
		panel_1.add(selectBox_1);
		setImmage2("empty.png", selectBox_1);
		selectBox_1.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
			if(selectBox_1.isSelected()) {setImmage2("full.png", selectBox_1);	buyTheaterButton.setEnabled(true);}
				else {setImmage2("empty.png", selectBox_1);buyTheaterButton.setEnabled(false);}	}});
		
		
		JCheckBox selectBox_2 = new JCheckBox("1");
		selectBox_2.setBackground(new Color(0, 0, 0));
		selectBox_2.setBounds(220, 150, 61, 50);
		panel_1.add(selectBox_2);
		setImmage2("empty.png", selectBox_2);
		selectBox_2.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
			if(selectBox_2.isSelected()) {setImmage2("full.png", selectBox_2);	buyTheaterButton.setEnabled(true);}
				else {setImmage2("empty.png", selectBox_2);buyTheaterButton.setEnabled(false);}	}});
		
		JCheckBox selectBox_3 = new JCheckBox("1");
		selectBox_3.setBackground(new Color(0, 0, 0));
		selectBox_3.setBounds(355, 150, 61, 50);
		panel_1.add(selectBox_3);
		setImmage2("empty.png", selectBox_3);
		selectBox_3.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
			if(selectBox_3.isSelected()) {setImmage2("full.png", selectBox_3);	buyTheaterButton.setEnabled(true);}
				else {setImmage2("empty.png", selectBox_3);buyTheaterButton.setEnabled(false);}	}});
		
		JCheckBox selectBox_4 = new JCheckBox("1");
		selectBox_4.setBackground(new Color(0, 0, 0));
		selectBox_4.setBounds(462, 150, 61, 50);
		panel_1.add(selectBox_4);
		setImmage2("empty.png", selectBox_4);
		selectBox_4.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
			if(selectBox_4.isSelected()) {setImmage2("full.png", selectBox_4);	buyTheaterButton.setEnabled(true);}
				else {setImmage2("empty.png", selectBox_4);buyTheaterButton.setEnabled(false);}	}});
		
		JCheckBox selectBox_5 = new JCheckBox("1");
		selectBox_5.setBackground(new Color(0, 0, 0));
		selectBox_5.setBounds(31, 223, 61, 50);
		panel_1.add(selectBox_5);
		setImmage2("empty.png", selectBox_5);
		selectBox_5.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
			if(selectBox_5.isSelected()) {setImmage2("full.png", selectBox_5);	buyTheaterButton.setEnabled(true);}
				else {setImmage2("empty.png", selectBox_5);buyTheaterButton.setEnabled(false);}	}});
		
		JCheckBox selectBox_6 = new JCheckBox("1");
		selectBox_6.setBackground(new Color(0, 0, 0));
		selectBox_6.setBounds(125, 223, 61, 50);
		panel_1.add(selectBox_6);
		setImmage2("empty.png", selectBox_6);
		selectBox_6.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
			if(selectBox_6.isSelected()) {setImmage2("full.png", selectBox_6);	buyTheaterButton.setEnabled(true);}
				else {setImmage2("empty.png", selectBox_6);buyTheaterButton.setEnabled(false);}	}});
		
		JCheckBox selectBox_7 = new JCheckBox("1");
		selectBox_7.setBackground(new Color(0, 0, 0));
		selectBox_7.setBounds(218, 223, 61, 50);
		panel_1.add(selectBox_7);
		setImmage2("empty.png", selectBox_7);
		selectBox_7.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
			if(selectBox_7.isSelected()) {setImmage2("full.png", selectBox_7);	buyTheaterButton.setEnabled(true);}
				else {setImmage2("empty.png", selectBox_7);buyTheaterButton.setEnabled(false);}	}});
		
		JCheckBox selectBox_8 = new JCheckBox("1");
		selectBox_8.setBackground(new Color(0, 0, 0));
		selectBox_8.setBounds(357, 223, 61, 50);
		panel_1.add(selectBox_8);
		setImmage2("empty.png", selectBox_8);
		selectBox_8.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
			if(selectBox_8.isSelected()) {setImmage2("full.png", selectBox_8);	buyTheaterButton.setEnabled(true);}
				else {setImmage2("empty.png", selectBox_8);buyTheaterButton.setEnabled(false);}	}});
		
		JCheckBox selectBox_9 = new JCheckBox("1");
		selectBox_9.setBackground(new Color(0, 0, 0));
		selectBox_9.setBounds(462, 223, 61, 50);
		panel_1.add(selectBox_9);
		setImmage2("empty.png", selectBox_9);
		selectBox_9.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
			if(selectBox_9.isSelected()) {setImmage2("full.png", selectBox_9);	buyTheaterButton.setEnabled(true);}
				else {setImmage2("empty.png", selectBox_9);buyTheaterButton.setEnabled(false);}	}});
	
		
		ArrayList<JCheckBox> cb = new ArrayList<JCheckBox>();//creating checkbox arraylist so we can check true or false dynamically
		cb.add(selectBox);cb.add(selectBox_1);cb.add(selectBox_2);cb.add(selectBox_3);cb.add(selectBox_4);
		cb.add(selectBox_5);cb.add(selectBox_6);cb.add(selectBox_7);cb.add(selectBox_8);cb.add(selectBox_9);
		
		JLabel detailLabel = new JLabel("");
		detailLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		detailLabel.setForeground(Color.WHITE);
		detailLabel.setBounds(79, 314, 167, 50);
		panel_1.add(detailLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(530, 83, 119, 34);
		panel_1.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String eventName = (String)comboBox.getSelectedItem();
				Event ev = (Event)SystemClass.search(SystemClass.event, eventName);
				for(int i=0;i<cb.size();i++) {
					if(ev.getCheck().get(i).equals("0")) {
						cb.get(i).setEnabled(true);
						cb.get(i).setSelected(false);
						setImmage2("empty.png", cb.get(i));
					}
					else {
						cb.get(i).setEnabled(false);
						cb.get(i).setSelected(true);
						setImmage2("full.png", cb.get(i));
					}
				}
				if(eventName.equals(comboBox.getSelectedItem())) {
					detailLabel.setText(ev.getDetails());
					//System.out.println(ev.getDetails());
				}
			}
		});
		//when we add event if event type is theater we set this theater names to the combobox;
		for(int i=0;i<SystemClass.event.size();i++){
			Event e = (Event)SystemClass.event.get(i);
			if(e.getType().equalsIgnoreCase("Theater") ) {
				comboBox.addItem(e.getName());
			}
		}
		ArrayList<Integer> a = new ArrayList<Integer>();
		for(int i=0;i<comboBox.getItemCount();i++) {
			a.add(i,0);
		}
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		infoPanel.setBounds(818, 178, 238, 555);
		contentPane.add(infoPanel);
		infoPanel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 10, 202, 263);
		infoPanel.add(scrollPane_1);
		
		JTextPane textPane = new JTextPane();
		scrollPane_1.setViewportView(textPane);
		textPane.setBackground(new Color(143, 188, 143));
		textPane.setEditable(false);
		
		
		JLabel successLabel = new JLabel("Successful!!");
		successLabel.setBounds(86, 323, 100, 23);
		infoPanel.add(successLabel);
		successLabel.setVisible(false);

		
		JScrollPane orderScrollPane = new JScrollPane();
		orderScrollPane.setBounds(523, 10, 178, 142);
		contentPane.add(orderScrollPane);
		
		JTextPane orderTextPane = new JTextPane();
		orderTextPane.setEditable(false);
		orderTextPane.setBackground(new Color(192, 192, 192));
		orderScrollPane.setViewportView(orderTextPane);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(728, 66, 221, 92);
		contentPane.add(scrollPane_2);
		
		JTextPane messagePane = new JTextPane();
		messagePane.setEditable(false);
		messagePane.setBackground(Color.LIGHT_GRAY);
		scrollPane_2.setViewportView(messagePane);
		scrollPane_2.setVisible(false);
		
		JLabel messageLabel = new JLabel("New label");
		messageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					if(scrollPane_2.isVisible()) {
						scrollPane_2.setVisible(false);
					}
					else {
						scrollPane_2.setVisible(true);
						setImmage1("mail2.png", messageLabel);
					}
					
				}
			}
		});
		messageLabel.setBounds(818, 10, 45, 45);
		contentPane.add(messageLabel);
		setImmage1("mail2.png", messageLabel);
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			Timer t;
			int k=0;
			String mail="";
			public void actionPerformed(ActionEvent e) {
				if(textPane.getText().length()<2) {
					successLabel.setText("No item yet!!");
					successLabel.setVisible(true);				
				}
				else {
				Customer c = Person.getCustomer(idLabel.getText());
				c.setOrder(c.toString());
				textPane.setText("");
				//here i am using timer class thus i am trying to send mail from hotel after a while
				if(c.isHotelEmpty() != 0) {//if there is a hotel we can send a mail to mail box 
					System.out.println("asdas");
					
					t = new Timer(1000,new ActionListener() {
						String s = "";
					@Override
					public void actionPerformed(ActionEvent e) {k++;if(k == 3) {setImmage1("mail.png", messageLabel);			
					for(int i=0;i<c.getHotels().size();i++) {
						s += "Thank you for your purchase from "+c.getHotels().get(i).getName() +
								c.getHotels().get(i).toString()+"\nContact 0216-253-55"+"\n";
					}	
					//System.out.println(s);
					mail += s+"\n";
					messagePane.setText(mail);
					c.clearHotels();//we have to clear events becase it is connected to current shopping cart
					t.stop();
					k=0;
					}
					System.out.println(k);		
					}
						});
					t.start();	
				}
					c.clearEvents();//we have to clear events because it is connected to current shopping cart
					
					orderTextPane.setText(c.getOrders().toString());
					successLabel.setText("Success");
					successLabel.setVisible(true);
					
				}
					
			}
		});
		confirmButton.setForeground(new Color(143, 188, 143));
		confirmButton.setBounds(20, 283, 85, 30);
		infoPanel.add(confirmButton);
		
		JPanel deletePanel = new JPanel();
		deletePanel.setBounds(10, 342, 218, 112);
		infoPanel.add(deletePanel);
		deletePanel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("EventName:");
		lblNewLabel_4.setBounds(0, 10, 78, 29);
		deletePanel.add(lblNewLabel_4);
		
		deleteTextField = new JTextField();
		deleteTextField.setBounds(75, 10, 88, 29);
		deletePanel.add(deleteTextField);
		deleteTextField.setColumns(10);
		
		JLabel deleteWrongLabel = new JLabel("");
		JButton delItemButton = new JButton("");
		delItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textPane.getText().equals("")) {
					String eventName = (String)deleteTextField.getText();
					Event ev = (Event)SystemClass.search(SystemClass.event, eventName);
					Person.getCustomer(idLabel.getText()).disEnroll(ev);
					if(Person.getCustomer(idLabel.getText()).getExpenses() == 0) {
						textPane.setText("");
					}
					else {
						textPane.setText(Person.getCustomer(idLabel.getText()).toString());
					}
					deleteWrongLabel.setVisible(false);
				}
				else {
					deleteWrongLabel.setText("There is no item in shopping cart");
				}
				deleteTextField.setText("");
			}
		});
		delItemButton.setBounds(168, 9, 50, 30);
		deletePanel.add(delItemButton);
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		deletePanel.setVisible(false);
		
		ImageIcon ia = new ImageIcon(getClass().getResource("delete.png"));
		Image img = ia.getImage().getScaledInstance(delItemButton.getWidth(), delItemButton.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon i2 = new ImageIcon(img);
		delItemButton.setIcon(i2);
		
		JLabel lblNewLabel_7 = new JLabel("Just Delete Event!");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_7.setBounds(0, 63, 93, 13);
		deletePanel.add(lblNewLabel_7);
		
		
		deleteWrongLabel.setBounds(0, 86, 176, 23);
		deletePanel.add(deleteWrongLabel);
		deleteWrongLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton deleteItemButton = new JButton("Delete");
		deleteItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(deletePanel.isVisible()) {
					deletePanel.setVisible(false);
				}
				else {
					deletePanel.setVisible(true);
				}
				
			}
		});
		deleteItemButton.setForeground(new Color(255, 0, 0));
		deleteItemButton.setBounds(127, 283, 85, 30);
		infoPanel.add(deleteItemButton);			
		
		JLabel shopGifLabel = new JLabel("");
		shopGifLabel.setBounds(64, 449, 96, 96);
		infoPanel.add(shopGifLabel);
		shopGifLabel.setBackground(Color.WHITE);
		shopGifLabel.setIcon(new ImageIcon("C:\\Users\\Dell\\Desktop\\Java Project\\0-Ticket\\gifs\\shopping.gif"));
	
		buyTheaterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			for(int i=0;i<cb.size();i++) {
				if(cb.get(i).isSelected() && cb.get(i).isEnabled()) {
					System.out.println(i);
					int index = i;
					String theaterName = (String)comboBox.getSelectedItem();
					Event ev = (Event)SystemClass.search(SystemClass.event, theaterName);
					
					if(cb.get(i).isEnabled())//is enabled we add event if we cannot check so when we gel more than 2 ticket it take previous ticked again
					Person.getCustomer(idLabel.getText()).enrol(ev);
				
					buyTheaterButton.setEnabled(false);//after buying we set disable		
					cb.get(i).setEnabled(false);
					ev.setCheck(index, "1");//define this seat is full or not
					System.out.println(ev.getCheck().toString());
					System.out.println(ev.getName());
					textPane.setText(Person.getCustomer(idLabel.getText()).toString());					
				}}
			
			}
			});
		buyTheaterButton.setBounds(520, 328, 98, 36);
		panel_1.add(buyTheaterButton);
		
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(48, 10, 392, 87);
		panel_1.add(lblNewLabel_5);
		setImmage1("theater.jpg", lblNewLabel_5);
		
		JLabel faceGifLabel = new JLabel("");
		faceGifLabel.setIcon(new ImageIcon("gifs\\face2.gif"));
		faceGifLabel.setBounds(530, 10, 119, 63);
		panel_1.add(faceGifLabel);
		
			
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(51, 0, 51));
		tabbedPane.addTab("Concert", null, panel_1_1, null);
		panel_1_1.setLayout(null);
		
		JLabel concertDetailsLabel = new JLabel("");
		concertDetailsLabel.setForeground(Color.WHITE);
		concertDetailsLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		concertDetailsLabel.setBounds(98, 135, 319, 79);
		panel_1_1.add(concertDetailsLabel);
		
		JComboBox concertBox = new JComboBox();
		concertBox.setBounds(205, 20, 148, 33);
		panel_1_1.add(concertBox);
		concertBox.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				String eventName = (String)concertBox.getSelectedItem();
				Event ev = (Event)SystemClass.search(SystemClass.event, eventName);
				if(eventName.equals(concertBox.getSelectedItem())) {
					concertDetailsLabel.setText(ev.getDetails());
				}
				
			}
		});
		
		JLabel musicLabelGif = new JLabel("New label");
		musicLabelGif.setIcon(new ImageIcon("gifs\\music2.gif"));
		musicLabelGif.setBounds(-17, 306, 173, 127);
		panel_1_1.add(musicLabelGif);
		
		JButton buyConcertButton = new JButton("BUY\r\n");
		buyConcertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eventName = (String)concertBox.getSelectedItem();
				Event ev = (Event)SystemClass.search(SystemClass.event, eventName);
				Person.getCustomer(idLabel.getText()).enrol(ev);
				textPane.setText(Person.getCustomer(idLabel.getText()).toString());
				
			}
		});
		buyConcertButton.setBackground(new Color(0, 153, 204));
		buyConcertButton.setBounds(463, 151, 100, 33);
		panel_1_1.add(buyConcertButton);
		
		JLabel concertLabel = new JLabel("New label");
		concertLabel.setBounds(384, 282, 212, 118);
		panel_1_1.add(concertLabel);
		setImmage1("concert.jpg", concertLabel);
		
		
		for(int i=0;i<SystemClass.event.size();i++){
			Event e = (Event)SystemClass.event.get(i);
			if(e.getType().equalsIgnoreCase("Music")) {
				concertBox.addItem(e.getName());
			}
		}
			
		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(153, 204, 204));
		panel2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Hotels", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.add(panel2, "name_5019060656562900");
		panel2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 43, 610, 274);
		panel2.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Hotel", "Price"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
	
		for(int k=0;k<SystemClass.hotel.size();k++) {
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			Hotel h = (Hotel)SystemClass.hotel.get(k);
			model.addRow(new Object[] {
					h.getName(),
					h.getPrice()
							
			});
		}
		
		JPanel confirmPanel = new JPanel();
		confirmPanel.setBackground(new Color(153, 204, 204));
		confirmPanel.setBounds(121, 459, 398, 54);
		panel2.add(confirmPanel);
		confirmPanel.setLayout(null);
		
		JButton yesButton = new JButton("YES");
		yesButton.setBackground(new Color(51, 204, 153));
		yesButton.setBounds(106, 11, 85, 21);
		confirmPanel.add(yesButton);
		
		JButton noButton = new JButton("NO");
		noButton.setBackground(new Color(255, 0, 102));
		noButton.setBounds(287, 11, 85, 21);
		confirmPanel.add(noButton);
		
		JLabel walletLabel = new JLabel("");
		walletLabel.setBounds(198, 10, 79, 33);
		confirmPanel.add(walletLabel);
		
		setImmage1("wallet.jpg", walletLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Confirm?");
		lblNewLabel_3.setBounds(10, 11, 62, 20);
		confirmPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("PARTNER HOTELS");
		lblNewLabel_2.setBounds(274, 12, 117, 38);
		panel2.add(lblNewLabel_2);
		
		JLabel eventsLabel = new JLabel("Events");
		eventsLabel.setBounds(91, 114, 45, 45);
		contentPane.add(eventsLabel);
		
		JPanel hotelDetailsPanel = new JPanel();
		hotelDetailsPanel.setBackground(new Color(153, 204, 204));
		hotelDetailsPanel.setBounds(461, 327, 210, 122);
		panel2.add(hotelDetailsPanel);
		hotelDetailsPanel.setLayout(null);
		
		JLabel enterDayLabel = new JLabel("Check In ");
		enterDayLabel.setBounds(55, 10, 101, 20);
		hotelDetailsPanel.add(enterDayLabel);
		
		JComboBox chooseDayBox = new JComboBox();
		chooseDayBox.setBounds(26, 77, 117, 20);
		hotelDetailsPanel.add(chooseDayBox);
		for(int i=0;i<5;i++) {
			chooseDayBox.addItem(i+1);
		}
		
		JLabel lblNewLabel_1 = new JLabel("DAY");
		lblNewLabel_1.setBounds(153, 77, 47, 20);
		hotelDetailsPanel.add(lblNewLabel_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(26, 40, 149, 19);
		hotelDetailsPanel.add(dateChooser);
		
	
		JScrollPane hotelDetailsScrollPane = new JScrollPane();
		hotelDetailsScrollPane.setBounds(54, 322, 241, 127);
		panel2.add(hotelDetailsScrollPane);
		
		JTextPane txtPanelHotelDetails = new JTextPane();
		txtPanelHotelDetails.setBackground(Color.LIGHT_GRAY);
		txtPanelHotelDetails.setText("--General--\r\n-Wifi\r\n-7/24 Open Reception\r\n-Cafe\r\n-Restaurant\r\n-TV Room\r\n--Meals--\r\n-Self Services\r\n-Buffet Breakfast\r\n-Continental Breakfast\r\n-Pub\r\n-Jakuzi\r\n");
		hotelDetailsScrollPane.setViewportView(txtPanelHotelDetails);
		
		
		hotelDetailsPanel.setVisible(false);
		confirmPanel.setVisible(false);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					hotelDetailsPanel.setVisible(true);
					confirmPanel.setVisible(true);
				}				
			}
		});
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBackground(new Color(192, 192, 192));
		textPane_1.setEditable(false);
		textPane_1.setBounds(345, 355, 74, 38);
		panel2.add(textPane_1);
		textPane_1.setVisible(false);
		
		yesButton.addActionListener(new ActionListener() {		
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					String d1  = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
					if(d1.equals("")) {
						textPane_1.setVisible(true);
						textPane_1.setText("Select Date!!");
					}
					else {
						System.out.println(d1);
						textPane_1.setVisible(false);
						int day =(Integer)chooseDayBox.getSelectedItem();
						DefaultTableModel model = (DefaultTableModel)table.getModel();
						//System.out.print(model.getValueAt(table.getSelectedRow(), 0));
						//System.out.println(model.getValueAt(table.getSelectedRow(), 1));
						
						String hotel=(String)model.getValueAt(table.getSelectedRow(), 0);
						int price = (Integer)model.getValueAt(table.getSelectedRow(), 1);
						Hotel h = new Hotel(hotel,price);
						h.setDate(d1);						
						Person.getCustomer(idLabel.getText()).enrol(h);
						
						for(int i=0;i<day-1;i++) {
							Person.getCustomer(idLabel.getText()).setExpenses(price);
						}
						//System.out.println("Successful");
							
						//System.out.println(Person.getCustomer(ýdLabel.getText()).toString());
						textPane.setText(Person.getCustomer(idLabel.getText()).toString());	
						
						confirmPanel.setVisible(false);					
					}
			
			}
		});
		noButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmPanel.setVisible(false);
				
			}
		});
				
		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(204, 204, 204));
		panel3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Trips", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel3.setLayout(null);
		panel.add(panel3, "name_5019060670573900");
		
		JPanel panelTrip = new JPanel();
		panelTrip.setBounds(31, 55, 668, 446);
		panel3.add(panelTrip);
		panelTrip.setLayout(new CardLayout(0, 0));
		
		JPanel dailyPanel = new JPanel();
		dailyPanel.setBackground(new Color(153, 204, 204));
		dailyPanel.setBorder(new TitledBorder(null, "Daily", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTrip.add(dailyPanel, "name_3902119767800");
		dailyPanel.setLayout(null);
		
		JLabel dailyImgLabel1 = new JLabel("");
		dailyImgLabel1.setBounds(23, 192, 236, 122);
		dailyPanel.add(dailyImgLabel1);
		setImmage1("yedigoller.jpg", dailyImgLabel1);
		
		JLabel dailyImgLabel2 = new JLabel("");
		dailyImgLabel2.setBounds(384, 192, 236, 122);
		dailyPanel.add(dailyImgLabel2);
		setImmage1("Cappadociasunrise.jpg", dailyImgLabel2);
		
		JLabel lblNewLabel_6 = new JLabel("Yedig\u00F6ller");
		lblNewLabel_6.setBounds(48, 369, 123, 13);
		dailyPanel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("Kapadokya");
		lblNewLabel_6_1.setBounds(415, 369, 123, 13);
		dailyPanel.add(lblNewLabel_6_1);	
		
		JComboBox dailyComboBox = new JComboBox();
		dailyComboBox.setBounds(92, 29, 114, 39);
		dailyPanel.add(dailyComboBox);
		
		JButton buyDailyButton = new JButton("Enroll");
		buyDailyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tripName = (String)dailyComboBox.getSelectedItem();
				myTrip trip = (myTrip)SystemClass.search(SystemClass.trip,tripName);
				Person.getCustomer(idLabel.getText()).enrol(trip);
				textPane.setText(Person.getCustomer(idLabel.getText()).toString());
				
			}
		});
		buyDailyButton.setBounds(468, 104, 114, 39);
		dailyPanel.add(buyDailyButton);
		
		JLabel dailyLabel = new JLabel("");
		dailyLabel.setBounds(23, 93, 402, 59);
		dailyPanel.add(dailyLabel);
		
		//System.out.println(SystemClass.trip.size());
		for(int i=0;i<SystemClass.trip.size();i++){
			myTrip e = (myTrip)SystemClass.trip.get(i);
			if(e.getType().equals("Daily")) {
				dailyComboBox.addItem(e.getName());
			}
		}
		
		dailyComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String tripName = (String)dailyComboBox.getSelectedItem();
				myTrip trip = (myTrip)SystemClass.search(SystemClass.trip, tripName);
				if(tripName.equals(dailyComboBox.getSelectedItem())) {				
					dailyLabel.setText(trip.toString());
				}
				
			}
		});
		
		JPanel accPanel = new JPanel();
		accPanel.setBackground(new Color(153, 153, 204));
		accPanel.setBorder(new TitledBorder(null, "Accommodation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTrip.add(accPanel, "name_3921787193300");
		accPanel.setLayout(null);
		
		JLabel accLabelImg1 = new JLabel("New label");
		accLabelImg1.setBounds(50, 208, 220, 124);
		accPanel.add(accLabelImg1);
		setImmage1("gap.jpg",accLabelImg1);
		
		JLabel accLabelImg2 = new JLabel("New label");
		accLabelImg2.setBounds(419, 208, 201, 124);
		accPanel.add(accLabelImg2);
		setImmage1("canakkale.jpg",accLabelImg2);
		
		JLabel lblNewLabel_8 = new JLabel("GAP Tour");
		lblNewLabel_8.setBounds(50, 353, 166, 13);
		accPanel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_8_1 = new JLabel("\u00C7anakkale Tour");
		lblNewLabel_8_1.setBounds(418, 353, 133, 13);
		accPanel.add(lblNewLabel_8_1);
		
		JLabel accLabel = new JLabel("");
		accLabel.setBounds(10, 101, 395, 79);
		accPanel.add(accLabel);
		
		JComboBox accComboBox = new JComboBox();
		accComboBox.setBounds(92, 29, 114, 39);
		accPanel.add(accComboBox);
		accComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String tripName = (String)accComboBox.getSelectedItem();
				myTrip trip = (myTrip)SystemClass.search(SystemClass.trip, tripName);
				if(tripName.equals(accComboBox.getSelectedItem())) {				
					accLabel.setText(trip.toString());
				}
				
			}
		});
		
		JButton butAccButton = new JButton("Enroll");
		butAccButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tripName = (String)accComboBox.getSelectedItem();
				myTrip trip = (myTrip)SystemClass.search(SystemClass.trip,tripName);
				Person.getCustomer(idLabel.getText()).enrol(trip);
				textPane.setText(Person.getCustomer(idLabel.getText()).toString());
			}
		});
		butAccButton.setBounds(468, 101, 114, 39);
		accPanel.add(butAccButton);
		


		for(int i=0;i<SystemClass.trip.size();i++){
			myTrip e = (myTrip)SystemClass.trip.get(i);
			if(e.getType().equals("Accommodation")) {
				accComboBox.addItem(e.getName());
			}
		}
		accComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String tripName = (String)accComboBox.getSelectedItem();
				@SuppressWarnings("unused")
				myTrip trip = (myTrip)SystemClass.search(SystemClass.trip, tripName);
				if(tripName.equals(accComboBox.getSelectedItem())) {				
					//System.out.println(trip.price);
				}
				
			}
		});
		
		panelTrip.setVisible(false);
		dailyPanel.setVisible(false);
		accPanel.setVisible(false);
		
		JRadioButton tripRdbtn1 = new JRadioButton("Daily");
		tripRdbtn1.setBackground(new Color(204, 204, 204));
		tripRdbtn1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tripRdbtn1.setBounds(110, 22, 136, 27);
		panel3.add(tripRdbtn1);
		
		tripRdbtn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == tripRdbtn1) {
					panelTrip.setVisible(true);
					dailyPanel.setVisible(true);
					accPanel.setVisible(false);
				}
				
			}
		});
				
		JRadioButton tripRdbtn2 = new JRadioButton("Accommodation");
		tripRdbtn2.setBackground(new Color(204, 204, 204));
		tripRdbtn2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tripRdbtn2.setBounds(349, 22, 136, 27);
		panel3.add(tripRdbtn2);
		tripRdbtn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == tripRdbtn2) {
					panelTrip.setVisible(true);
					dailyPanel.setVisible(false);
					accPanel.setVisible(true);
				}
			}
		});
		group.add(tripRdbtn1);
		group.add(tripRdbtn2);
		
		idLabel.setBounds(282, 25, 122, 51);
		contentPane.add(idLabel);
		
		JLabel welcomeLabel = new JLabel("");
		welcomeLabel.setIcon(new ImageIcon("gifs\\welcome22.gif"));
		welcomeLabel.setBounds(28, 25, 238, 71);
		contentPane.add(welcomeLabel);
				
		
		orderScrollPane.setVisible(false);
		JButton btnNewButton = new JButton("Orders");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(orderScrollPane.isVisible()) {
					orderScrollPane.setVisible(false);
				}
				else {
					orderScrollPane.setVisible(true);
				}
				
			}
		});
		btnNewButton.setBounds(407, 25, 106, 30);
		contentPane.add(btnNewButton);
		
			
		JLabel dateLabel = new JLabel("New label");
		dateLabel.setForeground(new Color(0, 0, 51));
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateLabel.setBounds(959, 66, 96, 30);
		contentPane.add(dateLabel);
		
		dateLabel.setText(java.time.LocalDate.now().toString());
		
		JLabel calendarLabel = new JLabel("Calendar");
		calendarLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		calendarLabel.setBounds(966, 10, 55, 39);
		contentPane.add(calendarLabel);
		setImmage1("calendar.png",calendarLabel);
		
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
		exitLabel.setBounds(1062, 10, 23, 30);
		contentPane.add(exitLabel);
		
		JLabel hotelsLabel = new JLabel("Hotels");
		hotelsLabel.setBounds(260, 119, 55, 40);
		contentPane.add(hotelsLabel);
		
		JLabel tripsLabel = new JLabel("Trips");
		tripsLabel.setBounds(436, 120, 63, 39);
		contentPane.add(tripsLabel);
		
		JLabel bgroundLabel = new JLabel("New label");
		bgroundLabel.setBounds(0, 0, 1095, 769);
		contentPane.add(bgroundLabel);
		setImmage1("bground.jpg",bgroundLabel);
		
		
				
		panel.setVisible(false);
		panel1.setVisible(false);
		panel2.setVisible(false);
		panel3.setVisible(false);
		rdbtn2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if(e.getSource() == rdbtn2) {					
					panel.setVisible(true);
					panel1.setVisible(false);
					panel2.setVisible(false);
					panel3.setVisible(true);
					tripRdbtn1.setSelected(true);
					panelTrip.setVisible(true);
					dailyPanel.setVisible(true);			
				}									
			}
		});
		rdbtn1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if(e.getSource() == rdbtn1) {
					panel.setVisible(true);
					panel1.setVisible(false);
					panel2.setVisible(true);
					panel3.setVisible(false);
				}									
			}
		});
				
		rdbtn.addActionListener(new ActionListener() {			
		@Override
		public void actionPerformed(ActionEvent e) {
		
			if(e.getSource() == rdbtn) {
				panel.setVisible(true);
				panel1.setVisible(true);
				panel2.setVisible(false);
				panel3.setVisible(false);
			}								
		}
		});
	}
}
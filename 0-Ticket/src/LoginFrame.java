import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import keeptoo.KGradientPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DbConnection cc = new DbConnection();
	private JTextField idField;
	private JLabel warningLabel;
	private JButton signButton;
	private JPasswordField pwField;
	private int mouseX;
	private int mouseY;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//when we at the login screen we set informations to customerlist from database 
	//so we can check is current customer is signed up.
	public void setValuesCustomer() {
		try {
			Connection con = cc.conndb();				
		
			Statement stm= con.createStatement();
			
			ResultSet rs = stm.executeQuery("select * from userList");
			
			while(rs.next()) {
				Customer c = new Customer(rs.getString(1), rs.getString(2));
				Person.add(c);										
			}					
			stm.close();
			rs.close();
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
	
	public void setImmage2(String source,JCheckBox cb) {
		ImageIcon i = new ImageIcon(getClass().getResource(source));
		Image img = i.getImage().getScaledInstance(cb.getWidth(), cb.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon i2 = new ImageIcon(img);
		cb.setIcon(i2);
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
	 * @throws Exception 
	 */
	public LoginFrame() throws Exception {
		setTitle("LOGIN");
		setValuesCustomer();//set values to arraylist from database
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 983, 690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		setUndecorated(true);
		dragUndercorated();

		KGradientPanel gradientPanel = new KGradientPanel();
		gradientPanel.kGradientFocus = 250;
		gradientPanel.kStartColor = new Color(133, 168, 172);
		gradientPanel.kEndColor = new Color(153, 102, 204);
		contentPane.add(gradientPanel, "name_272520748034300");
		gradientPanel.setLayout(null);
		
		idField = new JTextField();
		idField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		idField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) SystemColor.text));
		idField.setOpaque(false);
		idField.setBounds(355, 249, 194, 27);
		gradientPanel.add(idField);
		idField.setColumns(10);
		
		pwField = new JPasswordField();
		pwField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pwField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) SystemColor.text));
		pwField.setOpaque(false);
		pwField.setBounds(355, 360, 194, 39);
		gradientPanel.add(pwField);
		
		JButton loginButton = new JButton("S\u0131gn In");
		loginButton.setBackground(new Color(51, 153, 204));
		loginButton.setBounds(288, 469, 143, 60);
		gradientPanel.add(loginButton);
		
		signButton = new JButton("S\u0131gn Up");
		signButton.setBackground(new Color(153, 153, 255));
		signButton.setBounds(511, 476, 114, 47);
		gradientPanel.add(signButton);
		
		warningLabel = new JLabel("");
		warningLabel.setBounds(396, 545, 130, 39);
		gradientPanel.add(warningLabel);
		
		JLabel lblNewLabel = new JLabel("Ticket and Travel Ankara");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Cooper Black", Font.PLAIN, 24));
		lblNewLabel.setBounds(303, 72, 333, 80);
		gradientPanel.add(lblNewLabel);
		
		JLabel ticketLabel = new JLabel("New label");
		ticketLabel.setBounds(98, 54, 130, 60);
		gradientPanel.add(ticketLabel);
		
		setImmage1("ticket.png", ticketLabel);
		
		JLabel hideShowLabel = new JLabel("show/hide");
		hideShowLabel.setBounds(561, 373, 47, 39);
		gradientPanel.add(hideShowLabel);
		setImmage1("show.jpg", hideShowLabel);
		
		JLabel pwLabel = new JLabel("New label");
		pwLabel.setBounds(306, 360, 39, 39);
		gradientPanel.add(pwLabel);
		setImmage1("pww.png", pwLabel);
		
		JLabel userLabel = new JLabel("New label");
		userLabel.setBounds(306, 249, 39, 39);
		gradientPanel.add(userLabel);
		setImmage1("user.png", userLabel);
		
		JLabel exitLabel = new JLabel("X");
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		exitLabel.setForeground(Color.RED);
		exitLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
		exitLabel.setBounds(921, 10, 26, 39);
		gradientPanel.add(exitLabel);
		//setImmage1("exit.png", exitLabel);
		
		hideShowLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() % 1 == 0){
					if(pwField.getEchoChar() == 0) {
						setImmage1("show.jpg", hideShowLabel);
						pwField.setEchoChar((char)'*');
					}
					else {
						setImmage1("hide.jpg", hideShowLabel);
						pwField.setEchoChar((char)0);
					}
				}			
			}
		});
		
		signButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Customer c = new Customer(idField.getText(), pwField.getText());
				ArrayList<Customer> a = new ArrayList<Customer>();
				a = Person.getAll();
				if(idField.getText().equals("") || pwField.getText().equals("")) 
					warningLabel.setText("Enter id and pw!!!");
			else {		
				if(Person.indexOf(c)!=-1) {//check already signed or not
						warningLabel.setText("Already Signed");
					}							
					else {						
						try {
							String id=idField.getText();
							String pw = pwField.getText();
							if(id.equalsIgnoreCase("admin")) {
							warningLabel.setText("Cannot use");
							}
							else {
								Connection con = cc.conndb();
								PreparedStatement stm = con.prepareStatement("insert into userList values (?,?)");
								stm.setString(1, id);
								stm.setString(2,pw);
								stm.executeUpdate();
								Person.add(c);
								System.out.println(a.size());
								warningLabel.setText("Succsessful");
							}
						
						} catch (Exception e2) {
							// TODO: handle exception
						}}
				} 							
			}			
		});
		loginButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Customer c = new Customer(idField.getText(), pwField.getText());//get fields and create customer object
				//ArrayList<Customer> a = new ArrayList<Customer>();
				//a = Person.getAll();
				//String pw = Person.getCustomer(idField.getText()).getPw();
				if(idField.getText().equals("") || pwField.getText().equals("")) 
					warningLabel.setText("Enter id and pw!!!");
			else {					
			if(idField.getText().equals("admin") && pwField.getText().equals("admin")) {
				AdminPage admin = new AdminPage();
				admin.setVisible(true);
				dispose();
			}
			else {
				if(Person.indexOf(c)==-1) {//it means there is no customer that previously created, therefore need to sign
					warningLabel.setText("Pls Sign Up");
					}				
					else if(Person.getCustomer(idField.getText()).getPw().equals(pwField.getText())){
						ShoppingFrame m = new ShoppingFrame();
						m.setVisible(true);
						m.getLabel().setText(idField.getText());
						dispose();//close login panel
						
					}
					else
						warningLabel.setText("Wrong id or pw!!");
				
			}}
			}
		});
	}
}
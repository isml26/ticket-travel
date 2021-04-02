import java.sql.*;
public class DbConnection {
	Connection con = null;
	public DbConnection() {}
	public Connection conndb() throws Exception{
		//in org.sqlite in jdbc.jar we have JDBC class 
		//when we use class.forname we are creating object of JDBC class
		Class.forName("org.sqlite.JDBC");//invoking a driver
		//we create the object of a statement using connection	
		con = DriverManager.getConnection("jdbc:sqlite:database\\datas.db");//establish connection
		return con;
	}

}

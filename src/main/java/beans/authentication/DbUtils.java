package beans.authentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;



public class DbUtils {
	
	private static DbUtils instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/devconnectors";
	
	public static DbUtils getInstance() throws Exception{
		if(instance == null) {
			instance = new DbUtils();
		}
		
		return instance;
	}
	
	private DbUtils() throws Exception{
		dataSource = getDataSource();
	}
	
	private DataSource getDataSource() throws NamingException{
		Context context = new InitialContext();
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		return theDataSource;
	}
	
	//ADD USERS || REGISTER USER
	public void addUser(User theUser) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		String password = (String)theUser.getPassword();
		String confirmPassword =(String) theUser.getConfirmPassword();
		try {
				checkEmail(theUser.getEmail());
				if(password.equals(confirmPassword)) {
					myConn = getConnection();
					String sql = "INSERT INTO user(name, email, password) value(?,?,?)";
					myStmt = myConn.prepareStatement(sql);
					//Set the parameters
					myStmt.setString(1, theUser.getName());
					myStmt.setString(2, theUser.getEmail());
					myStmt.setString(3, theUser.getPassword());
					myStmt.execute();
				}else {
					throw new Exception("The passwords do not match !");
				}
	
		}
		finally{
			close(myConn, myStmt);
		}
	}
	
	//CHECK EMAIL
	public void checkEmail(String email) throws Exception{
		Connection myConn = null ;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();
			String sql = "SELECT * FROM user WHERE email=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, email);
			myRs = myStmt.executeQuery();
			if(myRs.next()) {
				throw new Exception("A user with this email already exist!");
			}
		}finally {
			close(myConn, myStmt, myRs);
		}
		
		
	}
	
	// LOGGIN USER
	public User loginUser(User theUser) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();
			String sql = "SELECT * FROM user WHERE email=? AND password=?";
			myStmt = myConn.prepareStatement(sql);
			//SET PARAMETERS
			myStmt.setString(1, theUser.getEmail());
			myStmt.setString(2, theUser.getPassword());
			
			myRs = myStmt.executeQuery();
			
			User user = null;
			if(myRs.next()) {
				int id = myRs.getInt("id");
				String name = myRs.getString("name");
				String email = myRs.getString("email");
				String avatar = myRs.getString("avatar");

				user = new User(id, name, email, avatar);
			}else {
				throw new Exception("Your email or your password is incorrect !");
			}
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.setAttribute("uemail",user.getEmail());
			session.setMaxInactiveInterval(15*60);
			return user;
		}
		finally {
			close(myConn, myStmt, myRs);
		}
	}
	
	//GET THE CONNECTED USER
	public User getUser(String email) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();
			String sql = "SELECT * FROM user WHERE email=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, email);
			myRs = myStmt.executeQuery();
			User theUser = null;
			if(myRs.next()) {
				int id = myRs.getInt("id");
				String name = myRs.getString("name");
				String mail = myRs.getString("email");
				String avatar = myRs.getString("avatar");
				
				theUser = new User(id, name, mail, avatar);
			}else {
				throw new Exception("Could not find user email: " + email);
			}
			
			return theUser;
		}
		finally{
			close(myConn, myStmt, myRs);
		}
	}
	
	//GET THE CONNECTION
	private Connection getConnection() throws Exception{
		Connection theConn = dataSource.getConnection();
		return theConn;
	}
	
	//CLOSE THE CONNECTION
	private void close(Connection theConn, Statement theStmt) {
		close(theConn, theStmt, null);
	}
	
	private void close(Connection theConn, Statement theStmt, ResultSet theRs) {
		try {
			if(theRs != null) {
				theRs.close();
			}
			if(theStmt != null) {
				theStmt.close();
			}
			if(theConn != null) {
				theConn.close();
			}
		}catch(Exception exc) {
			exc.printStackTrace();
		}
	}
}

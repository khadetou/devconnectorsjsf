package beans.profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import beans.authentication.User;

public class ProfileDbUtil {

		private static ProfileDbUtil instance;
		private DataSource dataSource;
		private String jndiName = "java:comp/env/jdbc/devconnectors";
		
		
		//GET INSTANCE
		public static ProfileDbUtil getInstance()throws Exception{
			
			if(instance == null) {
				instance = new ProfileDbUtil();
			}
			return instance;
		}
		
		//CONSTRUCTOR
		private ProfileDbUtil() throws Exception{
			dataSource = getDataSource();
		}
		
		//SETTING THE DATA SOURCE
		private DataSource getDataSource() throws NamingException{
			Context context = new InitialContext();
			DataSource theDataSource = (DataSource) context.lookup(jndiName);
			return theDataSource;
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
		
		//GET THE CONNECTED PROFILE USER
		public Profile getCurrentProfile(String email) throws Exception{
			Connection myConn = null;
			PreparedStatement myStmt = null;
			ResultSet myRs = null;
			
			try {
				myConn = getConnection();
				String sql = "SELECT * FROM profile WHERE email=?";
				
				myStmt = myConn.prepareStatement(sql);
				
				myStmt.setString(1, email);
				myRs = myStmt.executeQuery();
				Profile theProfile = null;
				if(myRs.next()) {
					//RETRIEVE DATA FROM RESULT SET ROW
					int id = myRs.getInt("id");
					int user_id = myRs.getInt("user_id");
					String company = myRs.getString("company");
					String website = myRs.getString("website");
					String location = myRs.getString("location");
					String status = myRs.getString("status");
					String skills = myRs.getString("skills");
					String bio = myRs.getString("bio");
					String githubusername = myRs.getString("githubusername");
					
					theProfile = new Profile(id, user_id, company, status, website, location, skills, bio, githubusername);
				}else {
					throw new Exception("Could not find user email: " + email);
				}
				
				return theProfile;
			}
			finally{
				close(myConn, myStmt, myRs);
			}
		}
		
		//CREATE A PROFILE 
			
		public boolean createProfile(Profile theProfile) throws Exception {
			Connection myConn = null;
			PreparedStatement myStmt = null;
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			String email = (String) session.getAttribute("uemail");
			try {
				myConn = getConnection();
				
				String sql = "INSERT INTO profile (user_id, company, website, location, status, skills, bio, githubusername) values(?, ?, ?, ?, ?, ?, ?, ?)";
				
				myStmt = myConn.prepareStatement(sql);
				
				//SET PARAMS
				myStmt.setInt(1, getUser(email).getId());
				myStmt.setString(2, theProfile.getCompany());
				myStmt.setString(3, theProfile.getWebsite());
				myStmt.setString(4, theProfile.getLocation());
				myStmt.setString(5, theProfile.getStatus());
				myStmt.setString(6, theProfile.getSkills());
				myStmt.setString(7, theProfile.getBio());
				myStmt.setString(8, theProfile.getGithubusername());
				
				myStmt.execute();
				
				return true;
			}
			finally {
				close(myConn, myStmt);
			}
		}
		
		//CREATE SOCIALS
		public void createSocials(Socials theSocials) throws Exception {
			Connection myConn = null;
			PreparedStatement myStmt = null;
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			String email = (String) session.getAttribute("uemail");
			try {
				myConn = getConnection();
				
				String sql = "INSERT INTO profile (user_id, company, website, location, status, skills, bio, githubusername) values(?, ?, ?, ?, ?, ?, ?, ?)";
				
				myStmt = myConn.prepareStatement(sql);
				
				//SET PARAMS
				myStmt.setInt(1, getCurrentProfile(email).getId());
				myStmt.setString(2, theSocials.getYoutube());
				myStmt.setString(3, theSocials.getTwitter());
				myStmt.setString(4, theSocials.getFacebook());
				myStmt.setString(5, theSocials.getLinkedin());
				myStmt.setString(6, theSocials.getInstagram());
				
				myStmt.execute();
			}
			finally {
				close(myConn, myStmt);
			}
		}
		
		//GET ALL PROFILES 
		//GET ALL PROFILES
		public	List<Profile> getProfiles() throws Exception{
			List<Profile> profiles = new ArrayList<>();
			
			Connection myConn =null;
			Statement myStmt = null;
			ResultSet myRs = null;
			
			try {
				myConn = getConnection();
				
				String sql = "SELECT * FROM profile";
				
				myStmt = myConn.createStatement();
				myRs = myStmt.executeQuery(sql);
				
				//PROCESS RESULT SET
				while(myRs.next()) {
					//RETRIEVE DATA FROM RESULT SET ROW
					int id = myRs.getInt("id");
					int user_id = myRs.getInt("user_id");
					String company = myRs.getString("company");
					String website = myRs.getString("website");
					String location = myRs.getString("location");
					String status = myRs.getString("status");
					String skills = myRs.getString("skills");
					String bio = myRs.getString("bio");
					String githubusername = myRs.getString("githubusername");
					
					//CREATE A NEW PROFILE
					Profile tempProfile = new Profile(id, user_id, company, status, website, location, skills, bio, githubusername);
					//ADD IT TO THE LIST OF PROFILES
					profiles.add(tempProfile);
				}
				return profiles;
			}finally {
				close(myConn, myStmt, myRs);
			}
		}
		
		//CREATE AN EXPERIENCE PROFILE
		
		//CREATE AN EDUCATION PROFILE 
		
		//GET USER REPOS FROM GITHUB
		
		//DELETE PROFILE
		
		//DELETE EXPERIENCE
		
		//DELETE EDUCATION
		
		//UPDATE PROFILE
		
		
		
		//GET CONNECTION
		private Connection getConnection() throws Exception {

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

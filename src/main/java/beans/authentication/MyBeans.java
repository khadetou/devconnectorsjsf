package beans.authentication;


import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class MyBeans {
	private User userInfo;
	private DbUtils userDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public MyBeans() throws Exception{
		userInfo = new User();
		userDbUtil = DbUtils.getInstance();
	}
	
	
	
	//ADD USER || REGISTER USER
	public String addUser(User theUser) {
		logger.info("Registering: "+theUser);
		try {
			//add user to the database
			userDbUtil.addUser(theUser);
		}catch(Exception exc) {
			//Send this to the server logs
			logger.log(Level.SEVERE, "Error adding User", exc);
			addErrorMessage(exc);
			return null;
		}
		return "login.xhtml?faces-redirect=true";
	}
	
	//LOGGIN USER && GET THE USER
	public String logginAccount(User theUser) {
		logger.info("Login as user: "+theUser);
		try {
			//get user, logging user
			userDbUtil.loginUser(theUser);

		}catch(Exception exc) {
			//Send this to the server logs
			logger.log(Level.SEVERE, "Error getting user login ");
			addErrorMessage(exc);
			return null;
		}
		return "auth/dashboard.xhtml?faces-redirect=true";
	}
	
	public User getUser() {
		return userInfo;
	}
	
	public void loadUser() {
		logger.info("Loading user");
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		try {
			//get user, logging user
			if(session != null) {
				String email = (String) session.getAttribute("uemail");
				userInfo = userDbUtil.getUser(email);
			}

		}catch(Exception exc) {
			//Send this to the server logs
			logger.log(Level.SEVERE, "Error getting user login ");
			addErrorMessage(exc);
		}
	}
	
	public boolean isLoggedIn() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if(session != null) {
			String username = (String) session.getAttribute("uemail");
			
			if(username != null) {
				return true;
			}
		}
		return false;
	}
	
	public String logoutUser() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if(session != null) {
			session.invalidate();
		}
		return "/index.xhtml?faces-redirect=true";
	}
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: "+ exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}

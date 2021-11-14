package beans.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ProfileController {
	private List<Profile> profiles;
	private ProfileDbUtil profileDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	//CONSTRUCTOR
	public ProfileController() throws Exception {
		profiles = new ArrayList<>();
		profileDbUtil = ProfileDbUtil.getInstance();
	}
	
	//CREATE PROFILE
	public String createProfile(Profile theProfile) {
		logger.info("Adding profile: "+ theProfile);
		try {
			//ADD PROFILE TO THE DATABASE
			profileDbUtil.createProfile(theProfile);
		}catch(Exception exc) {
			//SEND THIS TO SERVER LOGS
			logger.log(Level.SEVERE, "Error adding profile", exc);
			
			//ADD ERROR MESSAGE FOR JSF PAGE
			addErrorMessage(exc);
			
			return null;
		}
		
		return "auth/dashboard.xhtml?faces-redirect=true";
	}
	
	
	//GET PROFILES
	public List<Profile> getProfiles(){
		return profiles;
	}
	
	public void loadProfile() {
		logger.info("Loading profiles");
		profiles.clear();
		
		try {
			
			//GET ALL PROFILES
			profiles = profileDbUtil.getProfiles();
			
		}catch(Exception exc) {
			//SEND THIS TO SERVER LOGS
			logger.log(Level.SEVERE, "Error loading profiles", exc);
			
			//ADD ERROR MESSAGE FOR JSF PAGE
			addErrorMessage(exc);
		}
	}
	
	//ERROR MESSAGE HANDLER
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: "+ exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}

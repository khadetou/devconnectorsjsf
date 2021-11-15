package beans.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
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
	public String createProfile(Profile theProfile, Socials theSocials) {
		logger.info("Adding profile: "+ theProfile);
		try {
			//ADD PROFILE TO THE DATABASE
			boolean result = profileDbUtil.createProfile(theProfile);
			if(result) {
				profileDbUtil.createSocials(theSocials);
			}
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
	
	public void loadProfiles() {
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
	
	
	//GET A SINGLE PROFILE
	public String loadProfile(int profileId) {
		//GET PROFILE AND PUT IT IN THE REQUEST
		logger.info("loading profile: "+profileId);
		try { 
			//Get profile form database
			Profile  theProfile = profileDbUtil.getProfile(profileId);
			
			//Put in the request attribute 
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			
			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("profile", theProfile);
			
		}catch(Exception exc) {
			//Send this to server logs
			logger.log(Level.SEVERE, "Error loading profile id:" + profileId);
			//Add error message for JSF page
			addErrorMessage(exc);
			return null;
		}
		
		return "view-profile.xhtml";
	}
	
	
	//ERROR MESSAGE HANDLER
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: "+ exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}

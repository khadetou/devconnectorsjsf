package beans.profile;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Socials {
	private int id;
	private int profile_id;
	private String youtube;
	private String twitter;
	private String facebook;
	private String linkedin;
	private String instagram;
	
	public Socials() {
		
	}
	public Socials(int id, int profile_id, String youtube, String twitter, String facebook, String linkedin, String instagram) {
		this.setId(id);
		this.setProfile_id(profile_id);
		this.setYoutube(youtube);
		this.setTwitter(twitter);
		this.setFacebook(facebook);
		this.setLinkedin(linkedin);
		this.setInstagram(instagram);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProfile_id() {
		return profile_id;
	}
	public void setProfile_id(int profile_id) {
		this.profile_id = profile_id;
	}
	public String getYoutube() {
		return youtube;
	}
	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getLinkedin() {
		return linkedin;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	public String getInstagram() {
		return instagram;
	}
	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}
}

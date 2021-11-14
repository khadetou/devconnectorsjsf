package beans.profile;

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
		this.id = id;
		this.profile_id = profile_id;
		this.youtube = youtube;
		this.twitter = twitter;
				
	}
}

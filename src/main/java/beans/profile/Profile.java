package beans.profile;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Profile {
	private int id;
	private int user_id;
	private int social_id;
	private String name;
	private String email;
	private String avatar;
	private String status;
	private String website;
	private String location;
	private String company;
	private String skills;
	private String githubusername;
	private String bio;
	private String twitter;
	private String facebook;
	private String youtube;
	private String linkedin;
	private String instagram;
	
	
	public Profile() {
		
	}
	
	public Profile(int id, int user_id, int social_id, String name, String email, String avatar, String company, String status, String website, String location, String skills, String bio, String githubusername, String twitter, String youtube, String facebook, String instagram, String linkedin  ) {
		this.id = id;
		this.user_id = user_id;
		this.social_id = social_id;
		this.name = name;
		this.email = email;
		this.avatar = avatar;
		this.status = status;
		this.website = website;
		this.location = location;
		this.skills = skills;
		this.bio = bio;
		this.githubusername = githubusername;
		this.twitter = twitter;
		this.youtube = youtube;
		this.facebook = facebook;
		this.linkedin = linkedin;
		this.instagram = instagram;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getGithubusername() {
		return githubusername;
	}

	public void setGithubusername(String githubUsername) {
		this.githubusername = githubUsername;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
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

	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
  

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getSocial_id() {
		return social_id;
	}

	public void setSocial_id(int social_id) {
		this.social_id = social_id;
	}
	
	
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", social_id=" + social_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", status='" + status + '\'' +
                ", website='" + website + '\'' +
                ", location='" + location + '\'' +
                ", company='" + company + '\'' +
                ", skills='" + skills + '\'' +
                ", githubusername='" + githubusername + '\'' +
                ", bio='" + bio + '\'' +
                ", twitter='" + twitter + '\'' +
                ", facebook='" + facebook + '\'' +
                ", youtube='" + youtube + '\'' +
                ", linkedin='" + linkedin + '\'' +
                ", instagram='" + instagram + '\'' +
                '}';
    }

}

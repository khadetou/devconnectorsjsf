package beans.profile;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Experience {
	   private int id;
	    private int profiel_id;
	    private String title;
	    private String company;
	    private String location;
	    private String fromstart;
	    private String toend;
	    private String current;
	    private String description;
	    
	    
	    public Experience() {
	    	
	    }

	    public Experience(int id, int profiel_id, String title, String company, String location, String fromstart, String toend, String current, String description) {
	        this.id = id;
	        this.profiel_id = profiel_id;
	        this.title = title;
	        this.company = company;
	        this.location = location;
	        this.fromstart = fromstart;
	        this.toend = toend;
	        this.current = current;
	        this.description = description;
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public int getProfiel_id() {
	        return profiel_id;
	    }

	    public void setProfiel_id(int profiel_id) {
	        this.profiel_id = profiel_id;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getCompany() {
	        return company;
	    }

	    public void setCompany(String company) {
	        this.company = company;
	    }

	    public String getLocation() {
	        return location;
	    }

	    public void setLocation(String location) {
	        this.location = location;
	    }

	    public String getFromstart() {
	        return fromstart;
	    }

	    public void setFromstart(String fromstart) {
	        this.fromstart = fromstart;
	    }

	    public String getToend() {
	        return toend;
	    }

	    public void setToend(String toend) {
	        this.toend = toend;
	    }

	    public String getCurrent() {
	        return current;
	    }

	    public void setCurrent(String current) {
	        this.current = current;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    @Override
	    public String toString() {
	        return "Experience{" +
	                "id=" + id +
	                ", profiel_id=" + profiel_id +
	                ", title='" + title + '\'' +
	                ", company='" + company + '\'' +
	                ", location='" + location + '\'' +
	                ", fromstart='" + fromstart + '\'' +
	                ", toend='" + toend + '\'' +
	                ", current='" + current + '\'' +
	                ", description='" + description + '\'' +
	                '}';
	    }
	    
}

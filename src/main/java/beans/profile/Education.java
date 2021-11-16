package beans.profile;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Education {
    private int id;
    private int profile_id;
    private String school;
    private String degree;
    private String fieldofstudy;
    private String fromStart;
    private String toEnd;
    private String current;
    private String description;
    
    public Education() {
    	
    }

    public Education(int id, int profile_id, String school, String degree, String fieldofstudy, String fromStart, String toEnd, String current, String description) {
        this.id = id;
        this.profile_id = profile_id;
        this.school = school;
        this.degree = degree;
        this.fieldofstudy = fieldofstudy;
        this.fromStart = fromStart;
        this.toEnd = toEnd;
        this.current = current;
        this.description = description;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getFieldofstudy() {
        return fieldofstudy;
    }

    public void setFieldofstudy(String fieldofstudy) {
        this.fieldofstudy = fieldofstudy;
    }

    public String getFromStart() {
        return fromStart;
    }

    public void setFromStart(String fromStart) {
        this.fromStart = fromStart;
    }

    public String getToEnd() {
        return toEnd;
    }

    public void setToEnd(String toEnd) {
        this.toEnd = toEnd;
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
        return "Education{" +
                "id=" + id +
                ", profile_id=" + profile_id +
                ", school='" + school + '\'' +
                ", degree='" + degree + '\'' +
                ", fieldofstudy='" + fieldofstudy + '\'' +
                ", fromStart='" + fromStart + '\'' +
                ", toEnd='" + toEnd + '\'' +
                ", current='" + current + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

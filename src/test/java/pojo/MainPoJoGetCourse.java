package pojo;

/**
 * refer pojo.json in same package We have nested json and array of json in
 * single json course is json inside json (child json) Inject Mini json in json
 * 
 * Solution: Create a pojo class for mini json and return type will be courses
 * 
 * @author AKSHAY
 * 
 *         If you have [] then there is array of json
 *         
 *         MainPojoGetCourse
 *          ->Courseschild json -> seperate class is created for it.
 *
 */

public class MainPoJoGetCourse {

	// varaibles names should be exactly as in response error else getMethod will reuturn null
	private String services;
	private String experties;
	private Courses courses; // Course class is created since it has sub nodes
	private String instructor;
	private String linkedIn;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getExperties() {
		return experties;
	}

	public void setExperties(String experties) {
		this.experties = experties;
	}

	public pojo.Courses getCourses() {
		return courses;
	}

	public void setCourses(pojo.Courses courses) {
		this.courses = courses;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

}

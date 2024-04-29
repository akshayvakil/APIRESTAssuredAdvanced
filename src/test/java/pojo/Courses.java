package pojo;

import java.util.List;

public class Courses {
	
	//List is created since each course returns two fixed attributes
	// seperate class is created for each attributes course title and price
	private List< WebAutomation> webAutomation;
	private List <api> api;
	private List <Mobile> mobile;
	
	
	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<api> getApi() {
		return api;
	}
	public void setApi(List<api> api) {
		this.api = api;
	}
	public List<Mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}
	


}

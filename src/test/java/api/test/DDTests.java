package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.StudentEndPoints;
import api.payload.Student;
import io.restassured.response.Response;

public class DDTests {
	
	Student userPayload;
	
	@Test(priority=2, dataProvider="Data", dataProviderClass=api.utilities.Dataproviders.class)
	public void testUpdateStudents(String id, String name, String location, String phone, String courses) {
		
		userPayload = new Student();
		Integer num = Integer.parseInt(id);
	    userPayload.setId(num);
		userPayload.setName(name);
		userPayload.setLocation(location);
		userPayload.setPhone(phone);
		
		String[]arrcourses = courses.split(",");
		userPayload.setCourses(arrcourses);
		
		Response res = StudentEndPoints.updateStudent(this.userPayload,this.userPayload.getId());
		System.out.println(id);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		//System.out.println(res.jsonPath().getString("message"));
		
	}
	
	@Test(priority=1, dataProvider="StudentId", dataProviderClass=api.utilities.Dataproviders.class)
	public void testGetUser(int id) {
		
		Response res = StudentEndPoints.getStudent(id);
		System.out.println(id);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		//System.out.println(res.jsonPath().getString("message"));
		
	}
	
//	
//	@Test(priority=3, dependsOnMethods = {"testPostUsers"}, dataProvider="UserNames", dataProviderClass=api.utilities.Dataproviders.class)
//	public void testDeleteUser(String userName) {
//		
//		Response res = UserEndPoints.deleteUser(userName);
//		System.out.println(userName);
//		res.then().log().all();
//		Assert.assertEquals(res.getStatusCode(),200);
//		//System.out.println(res.jsonPath().getString("message"));
//		
//	}
	

}

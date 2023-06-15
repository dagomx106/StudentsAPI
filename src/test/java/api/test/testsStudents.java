package api.test;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import api.endpoints.StudentEndPoints;
import api.payload.Student;
import io.restassured.response.Response;

public class testsStudents {

	Student studentPayload;
	Faker fk;
	public Logger logg;

	@BeforeClass
	void setup() {
		//add data
		studentPayload = new Student();
		fk = new Faker();

		studentPayload.setName(fk.name().firstName());
		studentPayload.setLocation(fk.country().name());
		studentPayload.setPhone(fk.phoneNumber().cellPhone());
		String[] courses= {fk.programmingLanguage().name(),fk.programmingLanguage().name()};
		studentPayload.setCourses(courses);
		
		logg = LogManager.getLogger(this.getClass());

	}

	@Test (priority =1)
	void testPostStudent(ITestContext con) {
				logg.info("-----Inicio----");

		Response res = StudentEndPoints.createStudent(this.studentPayload);

		System.out.println(res.getBody().asPrettyString());
		System.out.println(res.jsonPath().getInt("id"));
		Assert.assertEquals(res.getStatusCode(), 201);
		con.getSuite().setAttribute("id",res.jsonPath().getInt("id"));
			logg.info("-----POST----");
	}

	@Test(priority= 2, dependsOnMethods ={"testPostStudent"})
	void testGetStudent(ITestContext con) {
			logg.info("-----GET----");
		System.out.println((int)con.getSuite().getAttribute("id"));
		int id = (int)con.getSuite().getAttribute("id");
		Response res = StudentEndPoints.getStudent(id);

		System.out.println(res.getBody().asString());
		System.out.println(res.jsonPath().getInt("id"));
		Assert.assertEquals(res.getStatusCode(), 200);

	}

	@Test(priority= 3, dependsOnMethods ={"testPostStudent"})
	void testPutStudent(ITestContext con) {
				logg.info("-----DATA UPDATE----");
		studentPayload.setName(fk.name().firstName());
		studentPayload.setLocation(fk.country().name());
		studentPayload.setPhone(fk.phoneNumber().cellPhone());
			logg.info("-----UPDATE----");
		Response res = StudentEndPoints.updateStudent(this.studentPayload, (int)con.getSuite().getAttribute("id"));
		System.out.println(res.getBody().asPrettyString());
		System.out.println(res.jsonPath().getInt("id"));
		Assert.assertEquals(res.getStatusCode(), 200);

	}

	@Test(priority= 4, dependsOnMethods ={"testPostStudent"})
	void testDeleteStudent(ITestContext con) {
			logg.info("-----DELETE----");
		Response res = StudentEndPoints.deleteStudent((int)con.getSuite().getAttribute("id"));

		System.out.println(res.getBody().asPrettyString());
		Assert.assertEquals(res.getStatusCode(), 200);

		logg.info("-----FIN----");
	}

}

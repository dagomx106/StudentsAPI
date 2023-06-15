package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import api.payload.Student;

public class StudentEndPoints2 {

	static ResourceBundle getURL() {
	ResourceBundle routes = ResourceBundle.getBundle("routes");
	return routes;
	}
	
	
	
	public static Response createStudent (Student payload) {

		Response response = given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(payload)
				
				.when()
				.post(getURL().getString("post_url"));

		return response;

	}

	public static Response getStudent (int id) {

		Response response = given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.pathParam("id", id)

				.when()
				.get(getURL().getString("get_url"));

		return response;

	}

	public static Response updateStudent (Student payload, int id) {

		Response response = given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(payload)
				.pathParam("id", id)
				
				.when()
				.put(getURL().getString("put_url"));

		return response;

	}

	public static Response deleteStudent (int id) {

		Response response = given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.pathParam("id", id)

				.when()
				.delete(getURL().getString("delete_url"));

		return response;

	}

}

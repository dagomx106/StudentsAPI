package api.endpoints;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import api.payload.Student;

public class StudentEndPoints {

	public static Response createStudent (Student payload) {

		Response response = given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(payload)
				
				.when()
				.post(Routes.post_url);

		return response;

	}

	public static Response getStudent (int id) {

		Response response = given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.pathParam("id", id)

				.when()
				.get(Routes.get_url);

		return response;

	}

	public static Response updateStudent (Student payload, int id) {

		Response response = given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(payload)
				.pathParam("id", id)
				
				.when()
				.put(Routes.put_url);

		return response;

	}

	public static Response deleteStudent (int id) {

		Response response = given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.pathParam("id", id)

				.when()
				.delete(Routes.delete_url);

		return response;

	}

}

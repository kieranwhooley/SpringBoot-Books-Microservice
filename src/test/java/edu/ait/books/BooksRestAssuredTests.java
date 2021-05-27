package edu.ait.books;

import edu.ait.books.config.TestConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

@SpringBootTest
class BooksRestAssuredTests extends TestConfig {

	//Test to check the application is up and running by confirming a 200 status code is returned
	@Test
	public void applicationIsUpAndRunning_APITest() {
		given().
				when().get("books").
				then().statusCode(200);
	}

	// GET - Test to confirm the correct status code and book information is returned for a specific book id
	@Test
	public void restAssured_getSpecificBook_APITest() {
		given().
				when().get("books/1").
				then()
				.statusCode(200)
				.body("title", equalTo("Atonement"))
				.body("author", equalTo("Ian McEwan"))
				.body("publishedYear", equalTo("2001"))
				.body("publisher", equalTo("Jonathan Cape"))
				.body("genre", equalTo("Metafiction"))
				.body("format", equalTo("Hardback"));
	}

	// GET - Test to confirm the correct code and error message is displayed if a user requests information for a non-existent book id
	@Test
	public void restAssured_gettingNonExistentBook_ReturnsErrorMessage_APITest() {
		given().
				when().get("books/10").
				then()
				.statusCode(404)
				.body("message", equalTo("There is no book with id 10 in the database"));
	}

	// DELETE - Test to delete a book
	@Test
	public void restAssured_deleteABook_APITest() {
		given().
				when().delete("books/2").
				then()
				.statusCode(200);
	}

	//DELETE - Test to confirm the correct code and error message is displayed if a user tries to delete a book with a non-existent id
	@Test
	public void restAssured_deletingNonExistentBook_ReturnsErrorMessage_APITest() {
		given().
				when().delete("books/10").
				then()
				.statusCode(404)
				.body("message", equalTo("Unable to delete as there is no book in the database with id 10"));
	}

	// POST - Test to confirm creation of a new book is working correctly
	@Test
	public void restAssured_createNewBook_APITest() {
		String requestBody = "{\n" +
				"    \"title\": \"A Tale of Two Cities\",\n" +
				"    \"author\": \"Charles Dickens\",\n" +
				"    \"publishedYear\": \"1859\",\n" +
				"    \"publisher\": \"Chapman & Hall\",\n" +
				"    \"genre\": \"Fiction\",\n" +
				"    \"format\": \"Hardback\"\n" +
				"}";

		Response response = given().
				body(requestBody).
				when().post("/books").
				then().
				extract().response();

		Assertions.assertEquals(200, response.statusCode());

	}

	// PUT - Test to confirm updating of an existing book is working correctly
	@Test
	public void restAssured_updateExistingBook_APITest() {
		String updatedBookBody = "{\n" +
				"    \"id\": "+ 5 + ",\n" +
				"    \"title\": \"A Tale of Two Cities\",\n" +
				"    \"author\": \"Charles Dickens\",\n" +
				"    \"publishedYear\": \"1890\",\n" +
				"    \"publisher\": \"Chapman & Hall\",\n" +
				"    \"genre\": \"Fiction\",\n" +
				"    \"format\": \"eBook\"\n" +
				"}";

		given().
				body(updatedBookBody).
				when().put("/books").
				then().statusCode(200);
	}

	// POST - Test to confirm the correct validation error messages are returned if a user submits invalid data in a POST request
	@Test
	public void restAssured_PostWithInvalidData_APITest() {
		String invalidBody = "{\n" +
				"    \"title\": \"\",\n" +
				"    \"author\": \"\",\n" +
				"    \"publishedYear\": \"99\",\n" +
				"    \"publisher\": \"\",\n" +
				"    \"genre\": \"\",\n" +
				"    \"format\": \"\"\n" +
				"}";

		given().
				body(invalidBody).
				when().post("/books").
				then().
				statusCode(400).
				body(containsString("The title cannot be left blank")).
				body(containsString("The author cannot be left blank")).
				body(containsString("The year must be 4 characters in length, for example 1898, 1957, 2003")).
				body(containsString("The publisher cannot be left blank")).
				body(containsString("The genre cannot be left blank")).
				body(containsString("The format cannot be left blank"));

	}

	// PUT - Test to confirm the correct validation error messages are returned if a user submits invalid data in a PUT request
	@Test
	public void restAssured_PutWithInvalidData_APITest() {
		String invalidBody = "{\n" +
				"    \"id\": "+ 1 + ",\n" +
				"    \"title\": \"\",\n" +
				"    \"author\": \"\",\n" +
				"    \"publishedYear\": \"99\",\n" +
				"    \"publisher\": \"\",\n" +
				"    \"genre\": \"\",\n" +
				"    \"format\": \"\"\n" +
				"}";

		given().
				body(invalidBody).
				when().put("/books").
				then().
				statusCode(400).
				body(containsString("The title cannot be left blank")).
				body(containsString("The author cannot be left blank")).
				body(containsString("The year must be 4 characters in length, for example 1898, 1957, 2003")).
				body(containsString("The publisher cannot be left blank")).
				body(containsString("The genre cannot be left blank")).
				body(containsString("The format cannot be left blank"));

	}
}

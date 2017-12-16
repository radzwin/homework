package collibra.restassured;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class CommentsRestTest extends FunctionalTest {
	
	@Test
	public void verifyCommentsEndPointResponse() {
		given().when().get().then()
				.statusCode(200)
				.body("size()", greaterThan(0))
				.body("email", hasItem(("Jayne_Kuhic@sydney.com")));
	}
}
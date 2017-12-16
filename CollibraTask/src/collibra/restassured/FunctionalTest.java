package collibra.restassured;

import org.junit.BeforeClass;
import com.jayway.restassured.RestAssured;

public class FunctionalTest {
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		RestAssured.basePath = "/comments/";
	}
}
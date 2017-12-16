package collibra.app;

import static com.jayway.restassured.RestAssured.when;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Application {
	
	private static String webURL = "https://jsonplaceholder.typicode.com/comments/";
	
	public static void main(String[] args) {

		String jsonAsString = getJSONasString();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			List<Comment> commentsBody = mapper.readValue(jsonAsString, new TypeReference<ArrayList<Comment>>(){});
			removeItemsWithPostIdDifferentThan(commentsBody, 1);
			removeItemsWhichDoNotContainTextInBody(commentsBody, "non");
			printCollection(commentsBody);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getJSONasString() {
		Response response = when().get(webURL).then().
	            contentType(ContentType.JSON).  
	            extract().response();

	    return response.asString();
	}
	
	private static void printCollection(List<Comment> collection) {
		Iterator<Comment> iterator = collection.iterator();
		while (iterator.hasNext())
		{
			Comment comment = iterator.next();
			System.out.println(comment.toString());
		}
	}
	
	private static void removeItemsWithPostIdDifferentThan(List<Comment> collection, int postId) {
		Iterator<Comment> iterator = collection.iterator();
		while (iterator.hasNext())
		{
			if (iterator.next().getPostId() != postId)
				iterator.remove();
		}
	}
	
	private static void removeItemsWhichDoNotContainTextInBody(List<Comment> collection, String stringToFilter) {
		Iterator<Comment> iterator = collection.iterator();
		while (iterator.hasNext())
		{
			if (!iterator.next().getBody().contains(stringToFilter))
				iterator.remove();
		}
	}
}

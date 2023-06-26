import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class StaticJsonPAyload {

	@Test
	public  void updateBook() {
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().headers("Content-Type","application/json").body(Payload.addBookUpdatePayload("344","badd"))
				                    
		.when()
		.post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath js=new JsonPath(response);
	//	JsonPath js=	Reusable.rawToJson(response);
		String id=js.get("ID");
		
		System.out.println("String id****** :"+id);
		
		
		
	}
	
	
	
}

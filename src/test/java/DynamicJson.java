import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import files.Payload;
import files.Reusable;


public class DynamicJson {
	
	public static void main(String[] args) {
		RestAssured.baseURI="http://216.10.245.166";
	String response=	given().headers("Content-Type","application/json")
		.body(Payload.addBook())
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
	   JsonPath js=Reusable.rawToJson(response);
	String id=   js.get("ID");
	System.out.println("New ID ********"+id);
		
	}

}

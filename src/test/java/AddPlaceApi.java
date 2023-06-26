import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import javax.sound.midi.SysexMessage;

import org.testng.Assert;

import files.Payload;
import files.Reusable;

public class AddPlaceApi {
	public static void main(String[] args) {
		// Validate taht add place api is working as expected
		// given - all input details
		// when - submit api resourcr and http
		// then - validate the response

		// preferences -java-editor-typing -escape to test when pasting

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String respone = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.addPlace())
				.when().post("maps/api/place/add/json")
				.then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).and().header("server", equalTo("Apache/2.4.41 (Ubuntu)"))
				.extract().response().asString();
		System.out.println("respone******* : " + respone);
		// Update Place
String newAddress="Summer walk America";
		JsonPath jsonPath = new JsonPath(respone);// for parsing sjon
		String PlaceId = jsonPath.getString("place_id");
		System.out.println("PlaceId  ****: " + PlaceId);

		// Update Place

		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n"
						+ "\"place_id\":\""+PlaceId+"\",\r\n"
						+ "\"address\":\""+newAddress+"\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n"
						+ "}\r\n"
						+ "")
				.when().put("/maps/api/place/update/json")
				.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
	//get 
		String getPlaceResponse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", PlaceId)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		//JsonPath js=new JsonPath(getPlaceResponse);
		JsonPath js=Reusable.rawToJson(getPlaceResponse);
		String actualAddress=js.getString("address");
		
		System.out.println("newAddress   :"+newAddress  +"***actualAddress"+actualAddress);
		Assert.assertEquals(newAddress, actualAddress);
		//Assert.assertEquals(newAddress, "Bihar");
		
		//https://jsoneditoronline.org/
	}

}

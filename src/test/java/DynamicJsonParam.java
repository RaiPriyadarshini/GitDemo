import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJsonParam {

	@Test(dataProvider="BooksData")
	public  void updateBook(String isbn,String aisle) {
		
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().headers("Content-Type","application/json").body(Payload.addBookUpdatePayload(isbn,aisle))
				                    
		.when()
		.post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath js=new JsonPath(response);
	//	JsonPath js=	Reusable.rawToJson(response);
		String id=js.get("ID");
		
		System.out.println("String id****** :"+id);
		
		
		
	}
	
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		//array = collection of elemnet
		//multidimentional array=collection of array
		//new Object [][] {array,array,array};
		//Below line is creating multidimensional aaray with all the element
	return	new Object[][] {{"bcda","567"},{"dfc","453"},{"gfd","987"}};
		
	}
}

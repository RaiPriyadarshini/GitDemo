import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
public class RequestSpecificationEx {

	public static void main(String[] args) {
		
		AddPlace p=new AddPlace();
		
		
		p.setAccuracy(50);
		p.setAddress("Pune");
		p.setLanguage("Marathi");
		
		p.setName("");
		p.setPhone_number("8999769822");
		
		p.setWebsite("www.rahulshetty.com");
		
		Location l=new Location();
		l.setLat(4333);
		l.setLng(8383);
		p.setLocation(l);
		
		ArrayList<String> typeList=new ArrayList();
		
		typeList.add("Shop");
		typeList.add("shoeShop");
		p.setTypes(typeList);
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		ResponseSpecification  resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification res=given().spec(req).body(p);
		
		Response response=res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();
		
		System.out.println("Our response **** "+response.asString());
		
	
	}
}

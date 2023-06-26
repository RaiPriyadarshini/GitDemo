import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
public class SerializationEx {

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
		RestAssured.baseURI="https://rahulshettyacademy.com";
		Response rs=given().queryParam("key", "qaclick123").log().all().body(p)
		.when().post("/maps/api/place/add/json")
		.then().extract().response();
		
		String res=rs.asString();
		
		System.out.println("New Response *** "+res);
		System.out.println("End");
	}
}

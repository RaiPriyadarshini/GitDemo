import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.LogInRequest;
import pojo.LogInResponse;
import pojo.OrderDetails;
import pojo.Orders;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EcomerceProject {

	// https://rahulshettyacademy.com/client/auth/login
	// tech.priyadarshini2209@gmail.com
	// Priya12345
	// Network -Fetch/HSR to fect network call
	
	//by pass ssl certificate   -- given().relaxedHTTPSValidation()

	public static void main(String[] args) {

		LogInRequest login = new LogInRequest();
		login.setUserEmail("tech.priyadarshini2209@gmail.com");
		login.setUserPassword("Priya12345");
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		RequestSpecification reqlogin = given().relaxedHTTPSValidation().spec(req).body(login);
		LogInResponse logInResponse = reqlogin.when().post("/api/ecom/auth/login").then().log().all().extract()
				.response().as(LogInResponse.class);
		String token = logInResponse.getToken();
		String userId = logInResponse.getUserId();

		// Add Product

		RequestSpecification reqAddPRoduct = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token).build();
		RequestSpecification reqadd = given().spec(reqAddPRoduct).param("productName", "Laptop")
				.param("productAddedBy", userId).param("productCategory", "fashion")
				.param("productSubCategory", "shirts").param("productPrice", "11500")
				.param("productDescription", "Lenova").param("productFor", "men")
				.multiPart("productImage", new File("E:\\Workspace\\laptop.png"));

		String addProductResponse = reqadd.when().post("/api/ecom/product/add-product").then().log().all().extract()
				.asString();
		JsonPath js = new JsonPath(addProductResponse);
		System.out.println("ProductId************   " + js.getString("productId"));
		String ProductID=js.getString("productId");
		
		
		//Create Order
		
		RequestSpecification reqspecCreateOrder=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).addHeader("authorization", token).build();

		OrderDetails orderDetails=new OrderDetails();
		orderDetails.setCountry("India");
		orderDetails.setProductOrderedId(ProductID);
List<OrderDetails>  OrderDetailList=new ArrayList<OrderDetails> ();
OrderDetailList.add(orderDetails);
		Orders orders =new Orders();
		orders.setOrders(OrderDetailList);
		RequestSpecification createOrderReq=given().spec(reqspecCreateOrder).body(orders);
		String res=createOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
		
		System.out.println("Order response    "+res);
		
		//Delete 
		RequestSpecification reqDelete=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization", token).setContentType(ContentType.JSON).build();
		
	RequestSpecification reqSpecDelete=given().spec(reqDelete).pathParam("productId", ProductID);
	
	String resDelet=reqSpecDelete.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().extract().response().asString();
	JsonPath jp=new JsonPath(resDelet);
	//;
	System.out.println("Delete response ****************"+jp.getString("message"));
	
	}
}

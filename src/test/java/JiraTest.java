import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraTest {

	//name=RestAssuredAutomation
	// key =RES
public static void main(String[] args) {
	RestAssured.baseURI="http://localhost:8080/";
	SessionFilter session=new SessionFilter();
	String response=given().header("Content-Type","application/json").
			body("{ \"username\": \"priya\", \"password\": \"12345\" }").log().all().filter(session)
			.when().post("rest/auth/1/session")
			.then().extract().response().asString();
	
	given().pathParam("key", "10009").log().all().header("Content-Type","application/json").body("{\r\n"
			+ "    \"body\": \"Comment from rest Assured*************\",\r\n"
			+ "    \"visibility\": {\r\n"
			+ "        \"type\": \"role\",\r\n"
			+ "        \"value\": \"Administrators\"\r\n"
			+ "    }\r\n"
			+ "}").filter(session).when().post("rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201);


//given().header("X-Atlassian-Token","no-check").filter(session).pathParam("key", "10009")
//.header("Content-Type","multipart/form-data")
//.multiPart("file",new File("E:\\Workspace_api\\ApiTest\\src\\test\\java\\files\\file.txt"))
//.when().post("/rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
//.assertThat().statusCode(200);

String responseall=given().filter(session).pathParam("key", 10009).log().all().queryParam("fields", "comment").
when().get("/rest/api/2/issue/{key}").
then().log().all().extract().response().asString();

System.out.println("SSSSSSSSSSSSSSSSS"+responseall);}
}

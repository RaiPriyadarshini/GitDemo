import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJson {
public static void main(String[] args) {
	JsonPath js= new JsonPath(Payload.coursePrice());
	int totalAmount=js.getInt("dashboard.purchaseAmount");
	System.out.println("totalAmount    *** "+totalAmount);
int count=	js.getInt("courses.size()");
	String titile=js.get("courses[0].title");
	System.out.println(count);
	System.out.println(titile);
	
	for(int i=0;i<count;i++) {
		
		String allCourse=js.get("courses["+i+"].title");
		System.out.println(allCourse);
		System.out.println(js.get("courses["+i+"].price"));
	}
}
}

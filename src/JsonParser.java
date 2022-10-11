import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonParser {

    public static StudentList parseJSOn(String url) throws ParseException {
       StudentList studentList = new StudentList();
        Client client = Client.create();
        WebResource webResource = client.resource(url);

        ClientResponse clientResponse = webResource.accept("application/json").get(ClientResponse.class);
        if (clientResponse.getStatus() != 200) {
            throw new RuntimeException("Failed"+ clientResponse.toString());
        }

        JSONArray jsonArray = (JSONArray) new  JSONParser().parse(clientResponse.getEntity(String.class));

        Iterator<Object> it = jsonArray.iterator();
        int id;
        String firstName;
        String gender;
        String email;
        double gpa;
        while (it.hasNext()){
            JSONObject jsonObject = (JSONObject)it.next();
            id = Integer.parseInt(jsonObject.get("id").toString());
            firstName = (String)jsonObject.get("first_name");
            gender = (String)jsonObject.get("gender");
            email = (String)jsonObject.get("email");
            gpa = Double.parseDouble(jsonObject.get("gpa").toString());
            studentList.addStudent(new Student(id, firstName, gpa,  email, gender));
        }

        return studentList;
    }

    public static void main(String[] args) throws ParseException {

      StudentList studentList = parseJSOn("https://hccs-advancejava.s3.amazonaws.com/student.json");

            System.out.println(studentList.getStudentList());
       
            System.out.println(studentList.searchGender("male"));
    }
}
package getRequests;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Class that contains the validation for each service.
 * @author Fernando Ayasta
 *
 */
public class TestResponses {

	@Before
    public void setup()
    {
        RestAssured.baseURI  = "https://restcountries.eu";
        RestAssured.basePath = "/rest/v2";
    }
	
	/**
	 * Validates the response when the Country is US
	 */
	 @Test
	    public void getCountryUS()
	    {
	        expect().statusCode( 200 )
	                .body( "alpha2Code", equalTo( "US" ) )
	                .when().get ( "/alpha/US" );
	    }

	 /**
	  * Validates the response when the Country is DE
	  */
    //https://restcountries.eu/rest/v2/
    @Test
    public void getCountryDE()
    {
        expect().statusCode( 200 )
                .body( "alpha2Code", equalTo( "DE" ) )
                .when().get ( "/alpha/DE" );
    }

    /**
     * Validates the response when the Country is GB
     */
    //https://restcountries.eu/rest/v2/
    @Test
    public void getCountryGB()
    {
        expect().statusCode( 200 )
                .body( "alpha2Code", equalTo( "GB" ) )
                .when().get ( "/alpha/GB" );
    }
   
    /**
     * Validates when user wants to reach a service that does not exist 
     * and validates the status code.
     */
    @Test
    public void getInexistingCountry(){
    	expect().statusCode( 400 )
        .when().get ( "/alpha/ThisCountryDoesNotExist" );
    }
    
    /**
     * POST CALL - This service is not created yet.
     * This method, sends the information required to create a new Country 
     * and validates the status code.
     */
    @Test
    public void postRegisterNewCountry(){
    	JSONObject requestParams = new JSONObject();
    	 requestParams.put("name", "Test Country"); 
    	 requestParams.put("alpha2_code", "TC");
    	 requestParams.put("alpha2_code", "TCY");
		try{
			RequestSpecBuilder builder = new RequestSpecBuilder();
			builder.setBody(requestParams.toJSONString());
			builder.setContentType("application/json; charset=UTF-8");
			RequestSpecification requestSpec = builder.build();
			given().spec(requestSpec).then().statusCode(201).when().post("/register");
		}catch(Exception ex){
			ex.printStackTrace();
		}
    }	
} 

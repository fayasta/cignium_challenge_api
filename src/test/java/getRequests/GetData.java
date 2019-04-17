package getRequests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

public class GetData {

    @Before
    public void setup()
    {
        RestAssured.baseURI  = "https://restcountries.eu";
        RestAssured.basePath = "/rest/v2";
    }
/*
    @Test
    public void testResponseCode(){
        Response response = RestAssured.get("https://restcountries.eu/rest/v2/all");
        int httpResponse = response.getStatusCode();
        System.out.println("My status is: "+httpResponse);
        System.out.println("The time was: "+response.getTime());
        Assert.assertEquals(httpResponse, 200);
    }


*/
    @Test
    public void testRead()
    {
        expect().statusCode( 200 )
                .when() .get ( "/all" );
        System.out.println("0");
    }



    @Test
    public void getCountryUS2()
    {
     /*   expect().statusCode( 200 )
                .body( "name", equalTo( "Peru" ) )
                .when() .get ( "/alpha/PE" );
        */
        get("/alpha/PE").then()
                .assertThat().body("name", equalTo("Peru"));
        System.out.println("1");
    }


    @Test
    public void getCountryUS()
    {
        expect().statusCode( 200 )
                .body( "alpha2Code", equalTo( "US" ) )
                .when() .get ( "/alpha/US" );
        System.out.println("2");
    }

    //https://restcountries.eu/rest/v2/
    @Test
    public void getCountryDE()
    {
        expect().statusCode( 200 )
                .body( "alpha2Code", equalTo( "DE" ) )
                .when() .get ( "/alpha/DE" );
        System.out.println("3");
    }

    //https://restcountries.eu/rest/v2/
    @Test
    public void getCountryGB()
    {
        expect().statusCode( 200 )
                .body( "alpha2Code", equalTo( "GB" ) )
                .when() .get ( "/alpha/GB" );
        System.out.println("4");
    }
}

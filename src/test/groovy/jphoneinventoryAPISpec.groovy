import com.example.jphoneinventoryAPI.Controllers.HelloController
import com.example.jphoneinventoryAPI.JphoneinventoryApiApplication
import com.example.jphoneinventoryAPI.Models.PhoneDetails
import com.github.tomakehurst.wiremock.WireMockServer
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.client.WireMock.*

@ContextConfiguration
class jphoneinventoryAPISpec extends Specification
{

    WireMockServer wm = new WireMockServer();
    CloseableHttpClient httpClient = HttpClients.createDefault();



    def "Check Welcome Endpoint"()
    {
        given: "Main Endpoint Exists"
        wm.start()
        configureFor("localhost", 8080);
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withBody("Greetings from Spring Boot!")));

        when: "Endpoint is called"
        HttpGet request = new HttpGet("http://localhost:8080/")
        HttpResponse httpResponse = httpClient.execute(request)


        then: "Status is 200"
        assert httpResponse.getStatusLine().getStatusCode() == 200

        and: "Body contains proper values"
        String responseString = convertResponseToString(httpResponse);
        assert responseString == "Greetings from Spring Boot!"
        wm.stop()

    }


    def "Check Phone Inventories Get Endpoint"()
    {
        def controller = new HelloController()

        given: "Phone Inventories Get Endpoint exists"
        wm.start()
        configureFor("localhost", 8080)
        PhoneDetails details = new PhoneDetails("Apple","IPhone 8", 699, 1)
        String response = controller.getPhoneInventories()
        stubFor(get(urlEqualTo("/phoneInventories")).willReturn(aResponse().withBody(response)))



        when: "Endpoint is called"
        HttpGet request = new HttpGet("http://localhost:8080/phoneInventories")
        HttpResponse httpResponse = httpClient.execute(request)


        then: "Status is 200"
        assert httpResponse.getStatusLine().getStatusCode() == 200

        and: "Body contains proper values"
        String responseString = convertResponseToString(httpResponse);
        assert responseString == response
        wm.stop()

    }

    private String convertResponseToString(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return responseString;
    }

}
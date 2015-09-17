package outreach;

import static org.junit.Assert.*;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

public class OutreachProspectTest {
    // NOTE: This needs to be changed between tests, since it expires after it's initially traded for a bearer token.
    private final String AUTHORIZATION_CODE = "1111111111111111111111111111111111111111111111111111111111111111";

    private Outreach outreach;

    @Before
    public void init() {
        /** @todo Populate these values with your OAuth application credentials. */
        Outreach.ApplicationCredentials app_creds = new Outreach.ApplicationCredentials(
              "1111111111111111111111111111111111111111111111111111111111111111", // App identifier
              "1111111111111111111111111111111111111111111111111111111111111111", // App secret key
              "https://www.example.com");                                         // App return-to URI
        outreach = new Outreach(app_creds, this.AUTHORIZATION_CODE);
    }

    @Test
    public void createProspectAllFields() {
    	JSONParser parser = new JSONParser();

        try {
            String prospect = parser.parse(
              "{\"data\": {"
            + "  \"attributes\": {"
            + "    \"address\": {"
            + "      \"city\": \"test-address-city\","
            + "      \"state\": \"test-address-state\","
            + "      \"country\": \"test-address-country\","
            + "      \"street\": [\"test-address-street-0\",\"test-address-street-1\"],"
            + "      \"zip\": 12345,"
            + "    },"
            + "    \"company\": {"
            + "      \"name\": \"test-company-name\","
            + "      \"type\": \"test-company-type\","
            + "      \"size\": \"test-company-size\","
            + "      \"locality\" :\"test-company-locality\""
            + "    },"
            + "    \"contact\": {"
            + "      \"timezone\": \"test-contact-timezone\","
            + "      \"email\" : {"
            + "        \"personal\": \"test-contact-email-personal@test.com\","
            + "        \"work\": \"test-contact-email-work@test.com\""
            + "      },"
            + "      \"phone\": {"
            + "        \"personal\": \"206-123-4567\","
            + "        \"work\": \"206-987-6543\""
            + "      }"
            + "    },"
            + "    \"personal\": {"
            + "      \"name\": {"
            + "        \"first\": \"Tester\","
            + "        \"last\": \"McTest\""
            + "      },"
            + "      \"gender\": \"male\","
            + "      \"occupation\": \"test-personal-occupation\","
            + "      \"title\": \"test-personal-title\""
            + "    },"
            + "    \"social\": {"
            + "      \"facebook\": \"test-social-facebook\","
            + "      \"linkedin\": \"test-social-linkedin\","
            + "      \"plus\": \"test-social-plus\","
            + "      \"quora\": \"test-social-quora\","
            + "      \"twitter\": \"test-social-twitter\""
            + "    }"
            + "  }"
            + "}}").toString();

            System.out.println(outreach.addProspect(prospect));
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	@Test
    public void assertion() {
        assertNotNull(null);
    }
}

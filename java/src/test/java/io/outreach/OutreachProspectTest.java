package io.outreach;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;

public class OutreachProspectTest {
    private static Outreach outreach;

    @BeforeClass
    public static void init() {
        try (FileInputStream propertiesFile = new FileInputStream("src/test/resources/application.properties")) {
            Properties appProperties = new Properties();
            appProperties.load(propertiesFile);

            Outreach.ApplicationCredentials app_creds = new Outreach.ApplicationCredentials(
                  appProperties.getProperty("app_identifier"),
                  appProperties.getProperty("app_secret_key"),
                  appProperties.getProperty("app_return_url"));
            outreach = new Outreach(app_creds, appProperties.getProperty("authorize_code"), TrustStore.get());
        } catch (IOException e) {
            assertTrue(false);
            return;
        }
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
            + "      \"timezone\": \"Pacific/Honolulu\","
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
            + "      \"facebook\": \"http://www.facebook.com/test\","
            + "      \"linkedin\": \"http://www.linkedin.com/test\","
            + "      \"plus\": \"http://plus.google.com/test\","
            + "      \"quora\": \"http://www.quora.com/test\","
            + "      \"twitter\": \"http://www.twitter.com/test\""
            + "    }"
            + "  }"
            + "}}").toString();

            // Example response: {"data":{"attributes":{"created":"2015-09-18T22:28:10.959Z","updated":"2015-09-18T22:28:10.959Z"},"id":48438,"type":"prospect"}}
            JSONObject response = outreach.addProspect(prospect);
            assertNotNull(response);

            JSONObject data = (JSONObject) response.get("data");
            assertNotNull(data);
            assertNotNull(data.get("id"));
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    @Test
    public void createProspectMinimal() {
        JSONParser parser = new JSONParser();

        try {
            String prospect = parser.parse(
              "{\"data\": {"
            + "  \"attributes\": {"
            + "    \"personal\": {"
            + "      \"name\": {"
            + "        \"first\": \"Tester\","
            + "        \"last\": \"McTest\""
            + "      }"
            + "    }"
            + "  }"
            + "}}").toString();

            // Example response: {"data":{"attributes":{"created":"2015-09-18T22:28:10.959Z","updated":"2015-09-18T22:28:10.959Z"},"id":48438,"type":"prospect"}}
            JSONObject response = outreach.addProspect(prospect);
            assertNotNull(response);

            JSONObject data = (JSONObject) response.get("data");
            assertNotNull(data);
            assertNotNull(data.get("id"));
        } catch (ParseException e) {
            assertTrue(false);
        }
    }

    @Test
    public void createProspectNoInput() {
        JSONParser parser = new JSONParser();

        try {
            String prospect = parser.parse(
              "{\"data\": {"
            + "  \"attributes\": {"
            + "  }"
            + "}}").toString();

            // Example response: {"data":{"attributes":{"created":"2015-09-18T22:28:10.959Z","updated":"2015-09-18T22:28:10.959Z"},"id":48438,"type":"prospect"}}
            JSONObject response = outreach.addProspect(prospect);
            assertNotNull(response);

            JSONObject error = (JSONObject) response.get("error");
            assertNotNull(error);
        } catch (ParseException e) {
            assertTrue(false);
        }
    }
}

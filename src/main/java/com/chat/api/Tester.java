package com.chat.api;

import com.chat.targeting.proto.UserProfileProto.UserProfile;
import com.services.rest.service.UserProfileService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.client.ClientConfig;
import com.services.restclient.ClientTest;

/*
 Tester class to test proto messages
 1> Test how to call a function to create a proto object, and read data from the object (Done)
 2> Test how to call api through web service and do the same test
*/
public class Tester {
  public static void debug(Object obj) {
    System.out.println(obj);
    System.out.println();
  }



  public static void testDirectFunctionCall() throws IOException {
    UserProfileService profileService = new UserProfileService();
    debug("Start creating user profile");
    UserProfile profile = profileService.createUserProfile();

    debug("Write proto to file");
    String fileName = "profile_output.proto";
    // Write the new address book back to disk.
    FileOutputStream output = new FileOutputStream(fileName);
    profile.writeTo(output);
    output.close();

    debug("Read from proto file");
    UserProfile userProfile = UserProfile.parseFrom(new FileInputStream(fileName));
    debug(userProfile);
  }

  public static void testAPICall() {
    ClientConfig config = new ClientConfig();
    Client client = ClientBuilder.newClient(config);
    WebTarget target = client.target(ClientTest.getBaseURI());

    System.out.println(
        target
            .path("rest")
            .path("hello")
            .request()
            .accept(MediaType.APPLICATION_JSON)
            .get(String.class));
  }

  public static void main(String[] args) throws IOException {
    testAPICall();
  }
}

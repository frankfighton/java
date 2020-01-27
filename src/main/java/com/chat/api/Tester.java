package com.chat.api;

import com.chat.targeting.proto.UserProfileProto;
import com.chat.targeting.proto.UserProfileProto.UserProfile;
import com.google.protobuf.InvalidProtocolBufferException;
import com.services.rest.providor.AlternateMediaType;
import com.services.rest.providor.ProtobufMessageReader;
import com.services.rest.providor.ProtobufMessageWriter;
import com.services.rest.providor.ProtobufferProvider;
import com.services.rest.service.UserProfileService;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
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

  public static void testAPICall() throws InvalidProtocolBufferException {
    Client client = ClientBuilder.newBuilder().register(new ProtobufferProvider()).build();


    WebTarget target = client.target(ClientTest.getBaseURI());

    Response response =
        target
            .path("service")
            .path("profile")
            .path("get_proto")
            .request()
            .accept(AlternateMediaType.APPLICATION_XPROTOBUF)
            .get();
    System.out.println("The response is ");
    byte[] profileStr = response.readEntity(byte[].class);
    UserProfile userProfile = UserProfile.parseFrom(profileStr);
    System.out.println("UserName = " + userProfile.getUserName());
    System.out.println("UserID = " + userProfile.getUserId());
  }

  public static void main(String[] args) throws IOException {
    testAPICall();
  }
}

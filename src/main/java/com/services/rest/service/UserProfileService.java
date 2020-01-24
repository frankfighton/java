package com.services.rest.service;

import com.chat.api.Tester;
import com.services.rest.entity.UserProfilePOJO;
import com.services.rest.providor.AlternateMediaType;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.chat.targeting.proto.UserProfileProto.UserProfile;
@Path("/profile")
public class UserProfileService {

  @GET
  @Path("ping")
  public String getServerTime() {
    System.out.println("RESTful Service 'UserProfileService' is running ==> ping");
    return "received ping on " + new Date().toString();
  }

  @GET
  @Path("all")
  @Produces({
    MediaType.APPLICATION_JSON
  }) // add MediaType.APPLICATION_XML if you want XML as well (don't forget @XmlRootElement)
  public List<UserProfilePOJO> getAllProfiles() throws Exception {

    List<UserProfilePOJO> UserProfiles = new ArrayList<>();
    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    int day = localDate.getDayOfMonth();
    UserProfilePOJO m = new UserProfilePOJO();
    Map<String, Object> creditCards = new HashMap<String, Object>();
    creditCards.put("MasterCard", "1234 1234 1234 1234");
    creditCards.put("Visa", "1234 1234 1234 1234");
    creditCards.put("dummy", true);
    m.setCreditCards(creditCards);

    m.setCitizenships(new String[] {"China", "US"});
    m.setFirstName("Nabi");
    m.setLastName("Zamani");
    m.setDateOfBirth("01/01/2000");
    m.setAge(day);
    UserProfiles.add(m);

    System.out.println(
        "getAllUserProfiles(): found " + UserProfiles.size() + " UserProfile(s) on DB");

    return UserProfiles; // do not use Response object because this causes issues when generating
                         // XML
    // automatically
  }

  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  @Produces({MediaType.APPLICATION_JSON})
  @Path("/post")
  public String postUserProfile(UserProfilePOJO msg) throws Exception {
    //Add a user profile to memory

    System.out.println("First Name = " + msg.getFirstName());
    System.out.println("Last Name  = " + msg.getLastName());
    //return userProfile.build();
    return "{'code':200,'message' : 'Success'}";
  }

  @GET
  @Consumes({AlternateMediaType.APPLICATION_XPROTOBUF})
  @Produces({AlternateMediaType.APPLICATION_XPROTOBUF})
  @Path("/get_proto")
  public UserProfile getUserProfileProto(int user_id) throws Exception {

    System.out.println("Calling getUserProfileProto for user id " + user_id);
    return this.createUserProfile();
    //return "{'code':200,'message' : 'Success'}";
  }


  public UserProfile createUserProfile() {
    UserProfile.Builder userProfile = UserProfile.newBuilder();
    userProfile.setGhostId("GhostId");
    userProfile.setSaid("Said-Snapchat Advistor ID");
    userProfile.setUserId("frankusc");
    userProfile.setUserName("Frank Rao");
    Tester.debug("Userprofile created!");
    return userProfile.build();
  }
}

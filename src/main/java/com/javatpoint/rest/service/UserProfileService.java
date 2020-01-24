package com.javatpoint.rest.service;

import com.javatpoint.rest.entity.UserProfile;
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
  public List<UserProfile> getAllProfiles() throws Exception {

    List<UserProfile> UserProfiles = new ArrayList<>();
    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    int day = localDate.getDayOfMonth();
    UserProfile m = new UserProfile();
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
  public String postUserProfile(UserProfile msg) throws Exception {

    System.out.println("First Name = " + msg.getFirstName());
    System.out.println("Last Name  = " + msg.getLastName());

    return "{'code':200,'message' : 'Success'}";
  }
}

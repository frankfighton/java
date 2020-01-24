package com.snapchat.api;

import com.snapchat.targeting.proto.UserProfileProto.UserProfile;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
  Tester class to test proto messages
  1> Test how to call a function to create a proto object, and read data from the object (Done)
  2> Test how to call api through web service and do the same test
 */
public class Tester {
  public static void debug(Object obj){
    System.out.println(obj);
    System.out.println();
  }
  public UserProfile createUserProfile(){
    UserProfile.Builder userProfile =  UserProfile.newBuilder();
    userProfile.setGhostId("GhostId");
    userProfile.setSaid("Said-Snapchat Advistor ID");
    userProfile.setUserId("frankusc");
    userProfile.setUserName("Frank Rao");
    debug("Userprofile created!");
    return userProfile.build();
  }
  public static void main(String[] args) throws IOException {
    Tester tester = new Tester();
    debug("Start creating user profile");
    UserProfile profile = tester.createUserProfile();

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
}

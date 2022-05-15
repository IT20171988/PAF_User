package com.user.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.*;

//For XML 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.user.model.*;


@Path("/Users")

public class userService {
	
	userModel userObj = new userModel();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUser() {
		return userObj.readUser();
	}
	
	// Insert User

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(
			@FormParam("UserNO")String UserNO,
			@FormParam("CusName")String CusName,
			@FormParam("Username")String Username,
			@FormParam("Password")String Password,
			@FormParam("Email")String Email,
			@FormParam("Phoneno")String Phoneno)
	{
		String output = userObj.insertUser(UserNO, CusName, Username, Password, Email, Phoneno );
		return output;
	}
	
	    //Update User
	
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateUser(String userData) {
			
			//Convert the input string to a JSON object 
			JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
				
			String UserID = userObject.get("UserID").getAsString();
			String UserNO = userObject.get("UserNO").getAsString();
			String CusName = userObject.get("CusName").getAsString();
			String Username = userObject.get("Username").getAsString();
			String Password = userObject.get("Password").getAsString();
			String Email = userObject.get("Email").getAsString();
			String Phoneno = userObject.get("Phoneno").getAsString();
			
			String output = userObj.updateUser(UserID, UserNO, CusName, Username, Password, Email, Phoneno);
			
			return output;
		}
	
		//	Delete User

		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteuser(String userData)
		{
			//Convert the input string to an XML document
			Document doc = Jsoup.parse(userData, "", Parser.xmlParser());
		
			//Read the value from the element <Account No>
			String UserID = doc.select("UserID").text();
			
			String output = userObj.deleteuser(UserID);
			return output;
		}

}

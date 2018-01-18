package edu.umbc.soc;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.GsonBuilder;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * REST Web Service
 *
 * @author Sushmitha
 */
@Path("/authentication")
public class Authentication {

@POST
@Path("/login")
//@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public Response authenticateUser(String json) {

	if (json == null)
		return Response.status(400).entity("Invalid form data").build();
	else{
		Credentials res = new GsonBuilder().create().fromJson(json.trim(), Credentials.class);
		System.out.println(res.getUsername());
		App.Test_Auth(res.getUsername(),res.getPassword());
		return Response.status(200)
			.entity("Username and password receieved successfully").build();
    // Authenticate the user, issue a token and return a response
   
}
}
}


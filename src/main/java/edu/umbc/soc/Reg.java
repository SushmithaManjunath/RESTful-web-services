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
@Path("/Reg")
public class Reg {

@POST
@Path("/reg1")
//@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public Response Insert_user(String json) {

	if (json == null)
		return Response.status(400).entity("Invalid form data").build();
	else{
		Reg_Cred res1 = new GsonBuilder().create().fromJson(json.trim(), Reg_Cred.class);
		
		App.Test_Reg(res1.getUsername(),res1.getEmail(),res1.getPassword());
		return Response.status(200)
			.entity("Username Credentials inserted successfully").build();
    // Authenticate the user, issue a token and return a response
   
}
}
}

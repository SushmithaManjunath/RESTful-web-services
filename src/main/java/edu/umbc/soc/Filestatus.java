package edu.umbc.soc;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * REST Web Service
 *
 * @author Sushmitha
 */
@Path("/status")
public class Filestatus {
	
	public Filestatus(){
    }

@Context
private UriInfo context;
	
	
	
	
	@GET
    @Path("/{id}")
   public Response getStatus(@PathParam("id") String id)
    {
    	System.out.println("Id - "+ id);
		// check if all form parameters are provided
    			if (id == null){
    				return Response.status(400).entity("Invalid id").build();
		
    				 }
		
			App.test_Status(id);
    			return Response.status(200)
    					.entity("File status retrived").build();
    		

    	
    }
  

}

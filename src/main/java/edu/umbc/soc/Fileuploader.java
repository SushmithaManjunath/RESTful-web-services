/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

import com.google.gson.GsonBuilder;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * REST Web Service
 *
 * @author Sushmitha
 */
@Path("/Upload")
public class Fileuploader {
    
    /** The path to the folder where we want to store the uploaded files */
	private static final String UPLOAD_FOLDER = "C:\\uploadedFiles\\";
	public Fileuploader(){
               }

    @Context
    private UriInfo context;
	/**
	 * Returns text response to caller containing uploaded file location
	 * 
	 * @return error response in case of missing parameters an internal
	 *         exception or success response if file has been stored
	 *         successfully
	 */
    
    

    /**
     * Retrieves representation of an instance of fileUpload.FileUpload
     * @return an instance of java.lang.String
     * 
     */
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
   public Response updateResult(String json)
    {
    	if (json == null)
			return Response.status(400).entity("Invalid form data").build();
    	else{
    		Result res = new GsonBuilder().create().fromJson(json.trim(), Result.class);
    		App.Test_updateresult(res.getId(),res.getResult());
    		return Response.status(200)
				.entity("File result updated successfully").build();
    	}
    	
    }
    
 /*   @GET
    @Path("/status")
   public Response getStatus(String id)
    {
    	System.out.println("Id - "+ id);
    	// check if all form parameters are provided
    			if (id == null){
    				return Response.status(400).entity("Invalid id").build();
		
    				 }
		
			App.test_Status(id);
    			return Response.status(200)
    					.entity("File status retrived").build();
    		

    	
    }    */
   
      
  
    
    @POST
    @Path("/file")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
   public Response fileUpload(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		// check if all form parameters are provided
		if (uploadedInputStream == null || fileDetail == null)
			return Response.status(400).entity("Invalid form data").build();
		// create our destination folder, if it not exists
		try {
			System.out.println("Before Folder Create");
			createFolderIfNotExists(UPLOAD_FOLDER);
			System.out.println("After Folder Create");
		} catch (SecurityException se) {
			return Response.status(500)
					.entity("Can not create destination folder on server")
					.build();
		}
		String uploadedFileLocation = UPLOAD_FOLDER + fileDetail.getFileName();
		//System.out.println(fileDetail.getFileName());
		try {
			saveToFile(uploadedInputStream, uploadedFileLocation);
			String uniqueKey = UUID.randomUUID().toString();
			
				  //generating unique id
				   // UUID uniqueKey = UUID.randomUUID();
				    System.out.println (uniqueKey);
				  
				
			App.test(uploadedFileLocation,uniqueKey);
		} catch (IOException e) {
			return Response.status(500).entity("Can not save file").build();
		}
		return Response.status(200)
				.entity("File saved to " + uploadedFileLocation).build();
	}
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
	/**
	 * Utility method to save InputStream data to target location/file
	 * 
	 * @param inStream
	 *            - InputStream to be saved
	 * @param target
	 *            - full path to destination file
	 */
	private void saveToFile(InputStream inStream, String target)
			throws IOException {
		OutputStream out = null;
		int read = 0;
		byte[] bytes = new byte[1024];
		out = new FileOutputStream(new File(target));
		while ((read = inStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
	}
	
	/**
	 * Creates a folder to desired location if it not already exists
	 * 
	 * @param dirName
	 *            - full path to the folder
	 * @throws SecurityException
	 *             - in case you don't have permission to create the folder
	 */
	private void createFolderIfNotExists(String dirName)
			throws SecurityException {
		File theDir = new File(dirName);
		if (!theDir.exists()) {
			theDir.mkdir();
		}
	}
}


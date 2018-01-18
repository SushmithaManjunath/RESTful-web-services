<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body bgcolor="#E6E6FA">
       <h1> Choose File to upload <br> </h1>
<form action="http://localhost:8080/Fileupload/rest/Upload/file" method="post" enctype="multipart/form-data">
	<input name="file" id="filename" type="file" /><br><br>
	<button name="submit" type="submit">Upload</button>
</form>
    </body>
</html>

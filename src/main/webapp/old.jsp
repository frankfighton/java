<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pojo to Json Serialization using Jersey with Jackson for Java REST Services</title>
         <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

        <script>
            var ctxPath = "<%=request.getContextPath() %>";
            $(function(){
                $("#postProfile").on("click", function(){
                    console.log("Start posting profile!");
                    $.ajax({
                        url:  ctxPath+"/service/profile/post",
                        type: "POST",
                        data: '{"firstName":"Michael", "lastName":"Jordan"}',
                        contentType: "application/json",
                        cache: false,
                        dataType: "json"
                    });
                });
            });
        </script>

    </head>

    <body>
       <h1>Pojo to Json Serialization using Jersey with Jackson for Java REST Services</h1>
       <ul>
           <li><a href="<%=request.getContextPath() %>/service/profile/all"><%=request.getContextPath() %>/service/profile/get</a></li>
           <li><button id="postProfile">Post Profile</button></li>
       </ul>
      <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js" integrity="sha384-6khuMg9gaYr5AxOqhkVIODVIvm9ynTT5J4V1cfthmT+emCG6yVmEZsRHdxlotUnm" crossorigin="anonymous"></script>
    </body>

</html>
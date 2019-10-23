<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
<style>
#static{
width:50px;
height:50px;
position:static;
}
#relative{
	position:relative;
	left:428px;
	top:387px;
	}
#relative2{
	position:relative;
	left:0 px;
	top:450px;
}
</style>

<head>
  <meta charset="UTF-8">
  <title>편의점 선택</title>
  
  
  
      <link rel="stylesheet" href="css/style3.css">

  
</head>

<body>

  <div onclick="location.href='GSALLpricetable.jsp'" style="cursor:pointer;" class="sun-container">
 <div id="static"></div>
 <div id="relative">
      <svg width="100" height="100" class="sun">
        <g class="group-1">
          <rect width="100" height="100" />
          <rect width="100" height="100" />
          <rect width="100" height="100" />
        </g>
        <g class="group-2">
          <rect width="100" height="100" />
          <rect width="100" height="100" />
          <rect width="100" height="100" />
        </g>
        
      </svg>
      </div>
   <div id="relative2">
    <img src="img/GS25.png" alt="My Image" width="956" height="350">
   </div>
    </div>
    
    
    <div onclick="location.href='CUALLpricetable.jsp'" style="cursor:pointer;" class="moon-container">
    <div id="static"></div>
    <div id="relative">
      <svg width="100" height="100" class="moon">
        <circle cx="50" cy="50" r="50" />
      </svg>
      </div>
      <div id="relative2">
      <img src="img/CU.png" alt="My Image" width="956" height="350">
      </div>
    </div>
  
  <%@include file="list.jsp" %>

</body>

</html>

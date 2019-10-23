<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>기본적인 세로 메뉴의 시작</title>
</head>
<style>
    body {
        margin-left:100px;
        margin-top:50px;
        padding: 0;
        font-family:"맑은 고딕";
        font-size:0.9em;
		
 	  	 float: left;
}

    ul#navi {
        width: 250px;
        text-indent: 10px;
}
    ul#navi, ul#navi ul {
        margin:0;
        padding:0;
        list-style:none;
}
    li.group {
        margin-bottom: 3px;
}
    li.group div.title {
        height: 45px;
        line-height: 45px;
        background:#9ab92e;
        cursor:pointer;
}
    ul.sub li {
        margin-bottom: 2px;
        height:45px;
        line-height:45px;
        background:#f4f4f4;
        cursor:pointer;
}
    ul.sub li a {
        display: block;
        width: 100%;
        height:100%;
        text-decoration:none;
        color:#000;
}
    ul.sub li:hover {
        background:#cf0;
}

#sidebar{
float:left;
}
#contents{
float:left;
width:200px;
height:300px;
border:1px soild green;


}
</style>
<body>
   
    <ul id="navi">
        <li class="group">
        <div class="title"><a href="index2.jsp">Home</a></div>
        <ul class="sub">
             <li><a href="selectshop.jsp">GS/CU</a></li>  
            </ul>
            <div class="title">GS분류1</div>
            <ul class="sub">
             <li><a href="GSALLpricetable.jsp">전체</a></li>  
              
            </ul>
        </li>
        <li class="group">
            <div class="title">GS분류2</div>
            <ul class="sub">
              <li><a href="GS음료.jsp">음료</a></li>
                <li><a href="GS과자.jsp">과자</a></li>
                <li><a href="GS인스턴스.jsp">인스턴스</a></li>  
                             
            </ul>
        </li>
        <li class="group">
            <div class="title">GS분류3</div>
            <ul class="sub">
                <li><a href="GS기타.jsp">기타</a></li>                
                 
            </ul>
        </li>        
    </ul>
   
</body>
</html>

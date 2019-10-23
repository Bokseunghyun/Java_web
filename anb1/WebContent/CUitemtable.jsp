<%@page import="org.web.memberDTO.ChartDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%


ArrayList<ChartDTO> chart = (ArrayList<ChartDTO>) request.getAttribute("chart");



String imge=(String)request.getAttribute("img");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

  
</head>
<body>
<div style="display:inline; min-width:1200px;">
<div style="display:inline;float:left;width:200px;padding-top:75px;">
<%@include file="CUbar.jsp" %>
</div>
<div style="display:inline;float:left;width:200px;padding-left:100px;">
<div id="bord" style="text-align:center;background:#eee;width:800px;padding:30px 0;margin:0 auto">
		<h1 style="">물품</h1>
		
		<img src="<%=imge %>" style="width:300px ;height:300px;">
		
		<table style="width: 100%" border="1">
			<tr>
				<th>물품명</th>
				<th>날짜</th>
				<th>지점</th>
				<th>가격</th>
				<th>변동</th>
			</tr>
			<%
				int b=0;
			    int c=0;
			    int pok=0;
				for (ChartDTO dto : chart) {
			%>
	
			<tr>
				<td><%=dto.getItem()%></td>
				<td><%=dto.getDate()%></td>
				<td><%=dto.getShop()%></td>
				<td><%=dto.getPrice()%></td>
				<%
				if(c>=1)
				{
					pok=Integer.parseInt(dto.getPrice())-b;
					
				}
				%>
				<td><%=pok %>
				<%
				if(pok>0)
				{
				%>
				<img src="img/up.png" style="heigth:15px;width:15px">
				<%
				}else if(pok<0){
				%>
				<img src="img/down.png" style="heigth:15px;width:15px">
				<%
				}else{
				%>
				<img src="img/same.png" style="heigth:15px;width:15px">
				<%
				}
				%>
				</td>
				<%
				b=Integer.parseInt(dto.getPrice());
				c++;
				%>
	
			</tr>
			<%
				}
			%>
		</table>
	</div>
	</div>
</div>

</body>
</html>
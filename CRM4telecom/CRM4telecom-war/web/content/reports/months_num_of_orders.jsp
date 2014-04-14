<%-- 
    Document   : home
    Created on : Apr 3, 2014, 9:59:25 AM
    Author     : Nastya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Number of orders per month</h2>
        <jsp:useBean id="greeter" class="com.crm4telecom.report.QueryExecutor">
            <%
                out.println(greeter.execute("select extract(year from order_date)||'_'||extract(month from order_date) as year_month, order_type, count(order_id)  NUM_OF_ORDERS from orders group by extract(year from order_date)||'_'||extract(month from order_date), order_type"));
            %>
        </jsp:useBean>
    </body>
</html>

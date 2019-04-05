<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title><fmt:message key="label.home" bundle="${lang}"/></title>

    <!-- Bootstrap -->
    <link href="<c:url value='/template/web/css/bootstrap.css'/>" rel="stylesheet">
    <link href="<c:url value='/template/web/css/bootstrap-responsive.css'/>" rel="stylesheet">
    <link href="<c:url value='/template/web/css/style.css' />" rel="stylesheet">

    <!--Font-->
    <link rel="stylesheet" href="<c:url value='/template/admin/font-awesome/4.5.0/css/font-awesome.min.css' />" />
    <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600' rel='stylesheet' type='text/css'>
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="<c:url value='/template/web/js/bootstrap.min.js'/> "></script>

    <dec:head />
</head>
<body>
    <!-- Header -->
    <%@ include file="/common/web/header.jsp" %>
    <!-- End Header -->

    <div class="container">
        <dec:body/>
    </div>

    <!-- Begin footer -->
    <%@ include file="/common/web/footer.jsp" %>
    <!-- end footer -->
</body>
</html>

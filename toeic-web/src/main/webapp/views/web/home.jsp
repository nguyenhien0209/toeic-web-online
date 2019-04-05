<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>

<html>
<head>
    <title>Home Web</title>
</head>
<body>
    <!--Carousel
      ==================================================-->
    <div id="myCarousel" class="carousel slide">
        <div class="carousel-inner">
            <div class="active item">
                <div class="container">
                    <div class="row">
                        <div class="span6">
                            <div class="carousel-caption">
                                <h1>Nguyễn Hiền</h1>
                                <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                                <a class="btn btn-large btn-primary" href="#">Sign up today</a>
                            </div>
                        </div>
                        <%--<div class="span6"> <img src="img/slide/slide1.jpg"></div>--%>
                    </div>
                </div>
            </div>

            <div class="item">
                <div class="container">
                    <div class="row">
                        <div class="span6">
                            <div class="carousel-caption">
                                <h1>Example headline</h1>
                                <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                                <a class="btn btn-large btn-primary" href="#">Sign up today</a>
                            </div>
                        </div>
                        <%--<div class="span6"> <img src="img/slide/slide2.jpg"></div>--%>
                    </div>
                </div>
            </div>
        </div>
        <!-- Carousel nav -->
        <a class="carousel-control left " href="#myCarousel" data-slide="prev"><i class="icon-chevron-left"></i></a>
        <a class="carousel-control right" href="#myCarousel" data-slide="next"><i class="icon-chevron-right"></i></a>
        <!-- /.Carousel nav -->
    </div>
    <div class="row feature-box">
        <div class="span4">
            <h2><fmt:message key="label.guideline.listen" bundle="${lang}" /> </h2>
            <a href="<c:url value="/danh-sach-huong-dan-nghe.html" />">Read More &rarr;</a>
        </div>
        <div class="span4">
            <h2><fmt:message key="label.exercise.listen" bundle="${lang}" /></h2>
            <c:url value="/danh-sach-bai-tap-nghe.html" var="exerciseListenUrl">
                <c:param name="pojo.type" value="listening" />
            </c:url>
            <a href="${exerciseListenUrl}">Read More &rarr;</a>
        </div>

        <div class="span4">
            <h2><fmt:message key="label.exercise.read" bundle="${lang}" /></h2>
            <a href="<c:url value="/danh-sach-huong-dan-nghe.html" />">Read More &rarr;</a>
        </div>

        <div class="span4">
            <h2><fmt:message key="label.examination" bundle="${lang}" /></h2>
            <a href="<c:url value="/danh-sach-bai-thi.html" />">Read More &rarr;</a>
        </div>
    </div>
</body>
</html>

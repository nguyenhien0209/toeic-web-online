<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url value="/admin-guideline-listen-edit.html" var="formUrl" />
<html>
<head>
    <title><fmt:message key="label.guideline.listen.edit" bundle="${lang}"  /></title>
    <script src="<c:url value='/template/admin/assets/js/jquery.2.1.1.min.js' />"></script>
    <script src="<c:url value='/template/admin/assets/js/jquery.validate.min.js' />"></script>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="admin-home.html"><fmt:message key="label.home" bundle="${lang}"/></a>
                </li>
                <li class="active"><fmt:message key="label.guideline.listen.edit" bundle="${lang}"/></li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class = "ace-icon fa fa-times"></i>
                            </button>
                                ${messageResponse}
                        </div>
                    </c:if>
                    <form action="${formUrl}" method="post" enctype="multipart/form-data" id="formEdit">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message bundle="${lang}" key="label.guideline.title" /></label>
                            <div class="col-sm-9">
                                <input type="text" name="pojo.title" id="title" value="${item.pojo.title}"  />
                            </div>
                        </div>
                        <br />
                        <br />
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message bundle="${lang}" key="label.grammarguideline.upload.image" /></label>
                            <div class="col-sm-9">
                                <input type="file" name="file" id="uploadImage"  />
                            </div>
                        </div>
                        <br />
                        <br />
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message bundle="${lang}" key="label.grammarguideline.upload.image.view" /></label>
                            <div class="col-sm-9">
                                <c:if test="${not empty item.pojo.image}">
                                    <%--/repository/ nhu la mot co de danh dau--%>
                                    <c:set value="/repository/${item.pojo.image}" var="image" />
                                </c:if>
                                <img src="${image}" id="viewImage" width="150px" height="150px" />
                            </div>
                        </div>
                        <br />
                        <br />
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message bundle="${lang}" key="label.guideline.content" /></label>
                        </div>
                        <br />
                        <br />
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty item.pojo.content}">
                                    <%--<c:set /> giong nhu khai bao bien--%>
                                    <c:set value="${item.pojo.content}" var="content" />
                                </c:if>
                                <textarea name="pojo.content" cols="80" rows="10" id="ListenguidelineContent"  >${content}</textarea>
                            </div>
                        </div>
                        <br />
                        <div class="form-group">
                            <div class="col-sm-12">
                                <input type="submit" class="btn btn-white btn-warning btn-bold" value="<fmt:message bundle="${lang}" key="label.done" />" />
                            </div>
                        </div>
                        <c:if test="${not empty item.pojo.listenGuidelineId}" >
                            <input type="hidden" name="pojo.listenGuidelineId" value="${item.pojo.listenGuidelineId}" />
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var listenGuidelineId = "";
    <c:if test="${not empty item.pojo.listenGuidelineId}" >
        listenGuidelineId = ${item.pojo.listenGuidelineId};
    </c:if>
    $(document).ready(function () {
        var editor = CKEDITOR.replace('ListenguidelineContent');
        // /ckfinder/ giong nhu mot decorator/ vi /admin tro den trang admin
        CKFinder.setupCKEditor( editor, '/ckfinder/' );
       validateData();
        $('#uploadImage').change(function () {
            readURL(this,'viewImage');
        });
    });

    function validateData() {
        $('#formEdit').validate({
            ignore: [],
            rules: [],
            messages: []
        });
        $('#title').rules("add",{
            required: true,
            messages: {
                required: '<fmt:message key="label.empty" bundle="${lang}" />'
            }
        });
        if(listenGuidelineId == '') {
            $('#uploadImage').rules("add",{
                required: true,
                messages: {
                    required: '<fmt:message key="label.empty" bundle="${lang}" />'
                }
            });
        }
        $('#ListenguidelineContent').rules("add",{
            required: function () {
                CKEDITOR.instances.ListenguidelineContent.updateElement();
            },
            messages: {
                required: '<fmt:message key="label.empty" bundle="${lang}" />'
            }
        });
    }

    function readURL(input, imageId) {
        if(input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#'+imageId).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>
</body>
</html>

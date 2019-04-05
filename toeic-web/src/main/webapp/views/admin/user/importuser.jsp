<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url value="/admin-user-import-validate.html" var="validateExcel" >
<c:url value="/admin-user-import.html" var="importExcel" />
</c:url>
<html>
<head>
    <title><fmt:message bundle="${lang}" key="label.user.import.excel" /></title>
</head>
<body>
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs', 'fixed')} catch (e) {}
                </script>
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#"><fmt:message bundle="${lang}" key="label.home"/></a>
                    </li>
                    <li><fmt:message bundle="${lang}" key="label.user.list"/></li>
                    <li class="active"><fmt:message bundle="${lang}" key="label.user.import.excel"/></li>
                </ul>
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <c:if test="${messageResponse != null}">
                            <div class="alert alert-block alert-${alert}">
                                <button type="button" class="close" data-dismiss="alert">
                                    <i class="ace-icon fa fa-times"></i>
                                </button>
                                    ${messageResponse}
                            </div>
                        </c:if>
                        <form action="${validateExcel}" method="POST" enctype="multipart/form-data" id="formImport">
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="col-sm-12">
                                        <input type="file" name="file" />
                                        <br />
                                        <button type="button" class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" id="validateData">
                                            <fmt:message key="label.file.validate.import" bundle="${lang}"/>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <br />
                            <br />
                            <c:if test="${not empty items.userImportDTOS}" >
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="table-responsive">
                                            <fmt:bundle basename="ApplicationResources">
                                                <display:table name="items.userImportDTOS" cellspacing="0" cellpadding="0" requestURI="${requestUrl}"
                                                               partialList="true" sort="external" size="${items.totalItems}" id="tableList" excludedParams="checkList"
                                                               pagesize="${items.maxPageItems}" export="false"
                                                               class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                                               style="margin: 3cm 0 1 5cm;">
                                                    <display:column headerClass="text-left" property="userName" titleKey="label.userName" />
                                                    <display:column headerClass="text-left" property="password" titleKey="label.password" />
                                                    <display:column headerClass="text-left" property="fullName" titleKey="label.fullName" />
                                                    <display:column headerClass="text-left" property="roleName" titleKey="label.roleName" />
                                                    <display:column headerClass="text-left" property="error" titleKey="label.import.error" />
                                                </display:table>
                                            </fmt:bundle>
                                        </div>
                                    </div>
                                </div>
                                <button type="button" class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" id="importData">
                                    <fmt:message bundle="${lang}" key="label.user.import" />
                                </button>
                            </c:if>
                            <input type="hidden" name="urlType" id="urlType">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script>
    $(document).ready( function () {
        $('#validateData').click( function () {
            $('#urlType').val('read_excel');
            $('#formImport').submit();
        });
        $('#importData').click(function () {
            $('#urlType').val('import_data');
            // $('#formImport').prop('enctype', false);
            $('#formImport').attr('enctype', 'application/x-www-form-urlencoded');
            $('#formImport').attr('action','${importExcel}');
            $('#formImport').submit();
        })
    });
</script>
</body>
</html>

<%@include file="/common/taglibs.jsp"%>
    <%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Add new contact</title>
            <%@include file="/common/scriptCss.jsp"%>
        </head>

        <body>
            <div align="left" class="container col-lg-6 col-lg-offset-3">
                <h3>Add new contact</h3>
                <form:form action="javascript:saveContact();" method="post" modelAttribute="phoneBook" class="form-group">
                    <div class="panel panel-primary">
                        <div class="panel-heading">Contact details</div>
                        <div class="panel-body">
                            <div class="form-group">
                                name
                                <form:input path="name" cssClass="form-control" />
                            </div>
                            <div class="form-group">
                                <form:input path="entries[0].name" cssClass="form-control" />
                                <form:input type="number" path="entries[0].phoneNumber" cssClass="form-control" />
                            </div>
                            <div class="form-group">
                                <form:input path="entries[1].name" cssClass="form-control" />
                                <form:input type="number" path="entries[1].phoneNumber" cssClass="form-control" />
                            </div>

                        </div>
                        <div class="panel-footer">
                            <div class="form-group">
                                <input type="button" class="btn btn-success" value="save" onclick="javascript:saveContact();">
                                <input type="button" class="btn btn-warning" value="quit" onclick="javascript:navigateToHome();">
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
            <script>
                function saveContact() {
                    document.forms[0].action = '<c:url value="/saveContact/"/>';
                    document.forms[0].submit();
                }

                function navigateToHome() {
                    document.forms[0].action = '<c:url value="/home/"/>';
                    document.forms[0].submit();
                }
            </script>
        </body>

        </html>
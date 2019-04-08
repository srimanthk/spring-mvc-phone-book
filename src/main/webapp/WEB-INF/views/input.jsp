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
                            <div class="form-group row">
                                <div class="col-xs-4">
                                    <label for="ex3">Name</label>
                                    <form:input path="name" cssClass="form-control" />
                                </div>
                            </div>

                            <div class="form-group">
                                <form:input path="entries[0].name" cssClass="col-xs-2" readonly="true" />
                                <form:input type="number" path="entries[0].phoneNumber" cssClass="col-xs-4" />
                            </div>

                            <div class="form-group">
                                <form:input path="entries[1].name" cssClass="col-xs-2" readonly="true" />
                                <form:input type="number" path="entries[1].phoneNumber" cssClass="col-xs-4" />
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
                    var contactName = document.getElementById("name").value;
                    var homeNumber = document.getElementById("entries0.phoneNumber").value;
                    var workNumber = document.getElementById("entries1.phoneNumber").value;
                    if (validName(contactName) && validPhoneNumber(homeNumber, "home") && validPhoneNumber(workNumber, "work") && checkBothEqual(homeNumber, workNumber)) {
                        document.forms[0].action = '<c:url value="/saveContact/"/>';
                        document.forms[0].submit();
                    }
                }

                function navigateToHome() {
                    document.forms[0].action = '<c:url value="/home/"/>';
                    document.forms[0].submit();
                }

                function validName(name) {
                    if (!name) {
                        alert("Name cannot be empty");
                        return false;
                    } else {
                        return true;
                    }
                }

                function checkBothEqual(homeNumber, workNumber) {
                    if (homeNumber === workNumber) {
                        alert("Home and work numbers cannot be same");
                        return false;
                    } else {
                        return true;
                    }
                }

                function validPhoneNumber(data, type) {
                    var phoneNo = /^\d{11}$/;
                    if (data && data.match(phoneNo)) {
                        return true;
                    } else {
                        alert("Not a valid " + type + " Phone Number");
                        return false;
                    }
                }
            </script>
        </body>

        </html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html><%@include file="/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>Phone Book</title><%@include file="/common/scriptCss.jsp"%>
		</head>
		<body>
			<div align="left" class="container col-lg-6 col-lg-offset-3">
				<h3>Phone Book</h3>
				<c:if test="${fn:length(phoneBooks) gt 0}">
						<table class="table table-striped table-bordered">
							<thead>
								<tr class="bg-success text-info">
									<th>All Contacts</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="contactVar" items="${phoneBooks}" varStatus="row">
									<tr>
										<td>
											<c:out value="${contactVar.name}" />
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					<a class="btn btn-primary" href="
						<c:url value="/addContact/"/>" role="button">Add new contact
					</a>
					<hr></hr>
					<div></div>
					<form:form action="home" method="post" modelAttribute="phoneBook">
						<div class="panel panel-primary">
							<div class="panel-heading">search</div>
							<div class="panel-body">
								<div class="form-group">
									<form:input path="name" cssClass="form-control" />
								</div>
							</div>
							<div class="panel-footer">
								<input type="button" value="search" onclick="javascript:searchContact();" class="btn btn-primary">
									<input type="button" value="refresh" onclick="javascript:resetSearchCriteria();" class="btn btn-warning">
									</div>
									<c:if test="${fn:length(entries) gt 0}">
										<div class="panel-heading">Results</div>
											<table class="table table-striped table-bordered">
												<thead>
													<tr class="bg-success text-info">
														<th>Contact Name</th>
														<th>Type</th>
														<th>Phone Number</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="entryVar" items="${entries}" varStatus="row">
														<tr>
															<td>
																<c:out value="${entryVar.phoneBook.name}" />
															</td>
															<td>
																<c:out value="${entryVar.name}" />
															</td>
															<td>
																<c:out value="${entryVar.phoneNumber}" />
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</c:if>
									</div>
								</form:form>
							</div>
							<script>
                function searchContact() {
                    document.forms[0].action = '<c:url value = "/searchContact/" />';
                    document.forms[0].submit();
                }

                function resetSearchCriteria() {
                    document.forms[0].action = '<c:url value = "/resetSearchCriteria/" />';
                    document.forms[0].submit();
                }

							</script>
						</body>
					</html>
<%-- <!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>
</head>
<body>
    <form:form modelAttribute="taskForm">
    	<p><form:input path="id"/></p>
        <p><form:input path="task"/></p>
        <p><form:input path="progress"/></p>
        <p><form:input path="priority"/></p>
        <input type="submit">
    </form:form>
</body>
</html>
 --%>


<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
    	<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
        <meta charset="utf-8">
        <title>Todoリスト</title>
        <link href="<c:url value="/resources/css/NewFile.css" />" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    </head>

    <div class="container">
	    <div class="row">
	    	<div class="col-md-6 ">
			    <body>
				    <form:form modelAttribute="formUpdate">
				    	<form:input path="task"/>
				        <form:select path="progress">
				        	<c:if test="${task.progress == 0}">
					        	<option value="0">未実施</option>
					        	<option value=1>実施済</option>
				        	</c:if>
				        	<c:if test="${task.progress == 1}">
					        	<option value=1>実施済</option>
					        	<option value="0">未実施</option>
				        	</c:if>
				        </form:select>
				        <form:select path="priority">
				        	<c:if test="${task.priority == 0}">
					        	<option value="0">高</option>
					        	<option value=1>中</option>
					        	<option value=2>低</option>
				        	</c:if>
				        	<c:if test="${task.priority == 1}">
					        	<option value=1>中</option>
					        	<option value="0">高</option>
					        	<option value=2>低</option>
				        	</c:if>
				        	<c:if test="${task.priority == 2}">
					        	<option value=2>低</option>
					        	<option value=1>中</option>
					        	<option value="0">高</option>
				        	</c:if>
				        </form:select>
				        <input type="submit" value="更新">
				    </form:form>
				</body>
			</div>
		</div>
	</div>


	<div class="container">
	    <div class="row">
	      <div class="col-md-6 ">
	        <table class= "table table-striped">
		    	<div class="row">
				    <thead>
				      <tr>
				        <th class="col-sm-1"></th>
				        <th class="col-sm-6">未実施タスク</th>
				        <th class="col-sm-1">優先</th>
				        <th class="col-sm-1">削除</th>
				      </tr>
				    </thead>
		   		</div>
		   		 <tbody>
			    	<c:forEach items="${tasks}" var="task">
				    	<c:if test="${task.progress== 0}">
					      <tr>
							<td><p>

							</p></td>
					        <td><p><a href="${pageContext.request.contextPath}/update/${task.id}/"><c:out value="${task.task}"></c:out></a></p></td>
					        <c:if test="${task.priority == 0}">
					        	<td><p>高</p></td>
							</c:if>
							<c:if test="${task.priority == 1}">
					        	<td><p>中</p></td>
							</c:if>
							<c:if test="${task.priority == 2}">
					        	<td><p>低</p></td>
							</c:if>
							<td>
								<form:form modelAttribute="taskForm" action="delete" method="post">
									<div hidden><textarea name="id" ><c:out value="${task.id}" /></textarea></div>
									<input type="submit" value="&#xf057" class="fas">
				    			</form:form>
							</td>
					      </tr>
					      </c:if>
					</c:forEach>
				</tbody>
	  		</table>
	  	</div>

	  	<div class="col-md-6 ">
	        <table class= "table table-striped">
		    	<div class="row">
				    <thead>
				      <tr>
				        <th class="col-sm-1"></th>
				        <th class="col-sm-6">実施済みタスク</th>
				        <th class="col-sm-1">優先</th>
				        <th class="col-sm-1">削除</th>
				      </tr>
				    </thead>
		   		</div>
		   		 <tbody>
			    	<c:forEach items="${tasks}" var="task">
				    	<c:if test="${task.progress== 1}">
					      <tr>
							<td><p>

							</p></td>
					        <td><p><a href="${pageContext.request.contextPath}/update/${task.id}/"><c:out value="${task.task}"></c:out></a></p></td>
					        <c:if test="${task.priority == 0}">
					        	<td><p>高</p></td>
							</c:if>
							<c:if test="${task.priority == 1}">
					        	<td><p>中</p></td>
							</c:if>
							<c:if test="${task.priority == 2}">
					        	<td><p>低</p></td>
							</c:if>
							<td>
								<form:form modelAttribute="taskForm" action="delete" method="post">
									<div hidden><textarea name="id" ><c:out value="${task.id}" /></textarea></div>
									<input type="submit" value="&#xf057" class="fas">
				    			</form:form>
							</td>
					      </tr>
					      </c:if>
					</c:forEach>
				</tbody>
	  		</table>
	  	</div>
	  </div>
	</div>

</html>
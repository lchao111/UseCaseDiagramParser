
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:forEach items="${xmiData.actors}" var="actor">
	Actor Name: ${actor.name} Actor ID: ${actor.id}<br/>
	</c:forEach>

	<c:forEach items="${xmiData.usecases}" var="usecase">
	usecase Name: ${usecase.name} UseCase ID: ${usecase.id} }<br/>
	</c:forEach>
	<c:forEach items="${xmiData.association}" var="assoc">
	Actor Id: ${assoc.actorId} =================UseCase ID: ${assoc.useCaseId} <br/>
	</c:forEach>
	<c:forEach items="${xmiData.includeAssocs}" var="include">
	From: ${include.baseUseCaseId} ================= To: ${include.addUseCaseId}<br/>
	</c:forEach>
	<c:forEach items="${xmiData.extendAssocs}" var="extend">
	From: ${extend.baseUseCaseId} ================= To: ${extend.extendUseCaseId}<br/>
	</c:forEach>

</body>
</html>
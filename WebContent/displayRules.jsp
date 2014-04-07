
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
<c:forEach items="${rules.mustHaveActors}" var="actors">
	mustHaveActors: ${actors.name} max UseCase Num:${actors.ucMaxNum} }<br/>
	</c:forEach>
	<c:forEach items="${rules.mustHaveUseCases}" var="usecase">
	mustHaveUseCase:${usecase.name} MAX Actor Num:${usecase.maxActNum} }<br/>
	</c:forEach>
	<c:forEach items="${rules.mustHaveAssocs}" var="assoc">
	mustHaveAssocs:${assoc.actorName}=====================${assoc.useCaseName}<br/>
	</c:forEach>
	<c:forEach items="${rules.mustNotHaveAssocs}" var="assoc">
	mustNotHaveAssocs:${assoc.actorName}=====================${assoc.useCaseName}<br/>
	</c:forEach>
	<c:forEach items="${rules.mustHaveIncludeAssociations}" var="includeAssoc">
	mustHaveIncludeAssociations:From:${includeAssoc.baseUseCaseName}==================To:${includeAssoc.addUseCaseName}<br/>
	</c:forEach>
	<c:forEach items="${rules.mustNotHaveIncludeAssociations}" var="includeAssoc">
	mustNotHaveIncludeAssociations:From:${includeAssoc.baseUseCaseName}==================To:${includeAssoc.addUseCaseName}<br/>
	</c:forEach>
	<c:forEach items="${rules.mustHaveExtendAssociations}" var="extendAssoc">
	mustHaveExtendAssociations:From:${extendAssoc.baseUseCaseName}==================To:${extendAssoc.extendUseCaseName}<br/>
	</c:forEach>
	<c:forEach items="${rules.mustNotHaveExtendAssociations}" var="extendAssoc">
	mustNotHaveExtendAssociations:From:${extendAssoc.baseUseCaseName}==================To:${extendAssoc.extendUseCaseName}<br/>	
	</c:forEach>
</body>
</html>
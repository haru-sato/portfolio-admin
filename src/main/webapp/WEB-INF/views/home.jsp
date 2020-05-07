<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<form method="get" action="<%=request.getContextPath()%>/upload">
	<!-- upload(URL)の取得をSkillControllerに要求　つまりuplpadを表示してくださいてこと -->
<button>button</button>
</form>

</body>
</html>

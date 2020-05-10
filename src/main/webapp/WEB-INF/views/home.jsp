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
<form method="get" action="<%=request.getContextPath()%>/skillupload">
	<!-- upload(URL)の取得をSkillControllerに要求　つまりuplpadを表示してくださいてこと -->
	<!-- uploadの部分を他のControllerのvalueで定義した部分に変えればそれを表示できる -->
<button>skill upload</button>
</form>

</body>
</html>

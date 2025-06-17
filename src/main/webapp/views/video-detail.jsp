<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>${video.name}</h1>
		
		<!-- lấy danh sách comment từ cái lits  commentEntities -->
		<!-- dùng for hiển thị danh sách comment  -->
		<!-- Sẽ có các thông tin: tên user comment và content  -->
		
		<c:forEach items="${video.commentEntities}" var="commentItem">
			<h4>Tên người dùng: ${commentItem.userEntity.name}</h4>
			<p class="ms-3">${commentItem.content}</p>
			<div class="d-flex row gap-3">
				<!-- Hiển thị danh sách hình ảnh bên trong comment-->
				<c:forEach items="${commentItem.commentImageEntities}" var="commentImageItem">
					<img src="${pageContext.request.contextPath}/assets/images/${commentImageItem.image}" style="width: 100px; height: 100px"/>
				</c:forEach>
			</div>
			<hr/>
		</c:forEach>
		
		<br/>
		
		<form method="POST"
			action="${pageContext.request.contextPath}/add-comment"
			enctype="multipart/form-data">
			<input type="hidden" name="videoId" value="${video.id}">
			<div class="mb-3 text-start">
			  <label class="form-label">Nội dung bình luận</label>
			  <input name="content" type="text" class="form-control">
			</div>
			
			<div class="mb-3 text-start">
				<label class="form-label">Ảnh bình luận</label>
				<input name="image" type="file" multiple class="form-control">
			</div>
			
			<button type="submit" class="btn btn-primary">Bình luận</button>
		</form>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
</body>
</html>
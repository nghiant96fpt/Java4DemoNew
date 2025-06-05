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
		<div class="col-6 offset-3 p-3 text-center">
			<form method="POST"
				action="${pageContext.request.contextPath}/video-form"
				enctype="multipart/form-data">
				<div class="mb-3 text-start">
				  <label class="form-label">Tên video </label>
				  <input value="${video.name}" name="name" type="text" class="form-control" id="exampleFormControlInput1">
				</div>
				<div class="mb-3 text-start">
				  <label class="form-label">Mô tả </label>
				  <textarea name="desc" class="form-control" id="exampleFormControlTextarea1" rows="5">${video.desc}</textarea>
				</div>
				<div class="mb-3 text-start">
				  <label class="form-label">Ảnh video</label>
				  <input name="image" type="file" class="form-control" id="exampleFormControlInput1">
				</div>
				<div class="mb-3 text-start">
				  <label class="form-label">URL</label>
				  <input value="${video.url}" name="url" type="text" class="form-control" id="exampleFormControlInput1">
				</div>
				
				<div class="mb-3 text-start">
				  <label class="form-label">Danh mục</label>
				  <select name="category" class="form-select" aria-label="Default select example">
					  <c:choose>
					  	<c:when test="${video == null}">
					  		<option selected value="-1">--------Chọn danh mục-------</option>
					  	</c:when>
					  	<c:otherwise>
					  		<option value="-1">--------Chọn danh mục-------</option>
					  	</c:otherwise>
					  </c:choose>
					  
					  <c:forEach items="${categories}" var="item">
					  	<c:choose>
						  	<c:when test="${video.category == item.id}">
						  		<option selected value="${item.id}">${item.name}</option>
						  	</c:when>
						  	<c:otherwise>
						  		<option value="${item.id}">${item.name}</option>
						  	</c:otherwise>
						  </c:choose>
					  </c:forEach>
					</select>
				 </div>
				
				<button type="submit" class="btn btn-primary">Thêm video</button>
			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
</body>
</html>










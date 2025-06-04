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
				action="${pageContext.request.contextPath}/register">
				<!-- Kiểm tra nếu id != null thì có thẻ input trong html  -->
				<!-- Ngược lại thì không có  -->
				<c:if test="${user.id != null}">
					<input type="hidden" name="id" value="${user.id}"/>
				</c:if>
				<div class="mb-3 text-start">
				  <label class="form-label">Tên tài khoản</label>
				  <input value="${user.username}" name="username" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Tên tài khoản">
				  <c:if test="${user.errors.errUsername != null}">
				  	<small class="text-danger">${user.errors.errUsername}</small>
				  </c:if>
				</div>
				<div class="mb-3 text-start">
				  <label class="form-label">Mật khẩu</label>
				  <input value="${user.password}" name="password" type="password" class="form-control" id="exampleFormControlInput1" placeholder="Mật khẩu">
				  <small class="text-danger">${user.errors.errPassword}</small>
				</div>
				<div class="mb-3 text-start">
				  <label class="form-label">Họ và tên</label>
				  <input value="${user.name}" name="name" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Họ và tên">
				  <small class="text-danger">${user.errors.errName}</small>
				</div>
				<div class="mb-3 text-start">
				  <label class="form-label">Email</label>
				  <input value="${user.email}" name="email" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Email">
				  <small class="text-danger">${user.errors.errEmail}</small>
				</div>
				
				<button type="submit" class="btn btn-primary">${user.id != null ? "Sửa thông tin" : "Đăng ký"}</button>
			</form>
			
			<div class="mt-3">
				<a href="${pageContext.request.contextPath}/login">Tôi đã có tài khoản</a>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
</body>
</html>
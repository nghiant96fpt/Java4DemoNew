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
		<a href="${pageContext.request.contextPath}/video-form"
			class="btn btn-primary mt-5">Thêm video</a>

		<div class="mt-5">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Tên</th>
						<th scope="col">Mô tả</th>
						<th scope="col">Hình ảnh</th>
						<th scope="col">URL</th>
						<th scope="col">Lượt xem</th>
						<th scope="col">Danh mục</th>
						<th scope="col">Trạng thái</th>
						<th scope="col">Hành động</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${videos}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td>${item.desc}</td>
						<td>
							<img src="${pageContext.request.contextPath}/assets/images/${item.image}" style="width: 100px; height: 100px"/>
						</td>
						<td>
							<!-- làm sao để khi click vào thẻ a sẽ mở 1 tab mới với url của href   -->
							<a href="${item.videoURL}" target="_blank">${item.videoURL}</a>
						</td>
						<td>${item.viewCount}</td>
						<td>${item.categoryEntity.name}</td>
						<td>${item.status == 0 ? 'Inactive' : 'Active'}</td>
					</tr>
				</c:forEach>
					<!-- Hiên thị  danh sách video theo theo các thông tin trên 
					ID, Tên, Mô tả, Hình ảnh (img), url (a), lượt xem, tên danh mục,
					trạng thái     -->
				</tbody>
			</table>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
</body>
</html>













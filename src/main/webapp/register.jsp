<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>할일등록</title>
<link rel="stylesheet" href="./css/register.css">
</head>
<body>
	<header>
		<h1>할일 등록</h1>
	</header>
	<form class="" action="register" method="post">
		<section class="mainsection">

			<div>어떤일인가요?</div>
			<input type="text" name="title" placeholder="swift 공부하기 (24자까지)"
				maxlength="24" class="todoinput" required>
			<div>누가 할일인가?</div>
			<input type="text" name="name" placeholder="홍길동" class="nameinput"
				required>
			<div>우선순위를 선택하세요</div>

			<ul>
				<li><input type="radio" name="sequence" value="1" class="radio"
					required><label>1순위</label></li>
				<li><input type="radio" name="sequence" value="2" class="radio"
					required><label>2순위</label></li>
				<li><input type="radio" name="sequence" value="3" class="radio"
					required><label>3순위</label></li>
			</ul>

		</section>
		<footer>
			<button type="button" class="clickbefore">&lt 이전</button>
			<section class="nonesection">
				<button type="submit" class="clicksubmit">제출</button>
				<button type="reset" class="clickerase">내용지우기</button>
			</section>

		</footer>
	</form>
</body>
<script src="./js/register.js"></script>
</html>
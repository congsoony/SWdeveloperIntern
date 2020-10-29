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
    <section>
      <div>어떤일인가요?</div>
      <input type="text" name="do_reg" value="swift 공부하기 (24자까지)" maxlength="24" class="todoinput">

      <div>누가 할일인가?</div>
      <input type="text" name="do_name" value="홍길동" class="nameinput">
      <div>우선순위를 선택하세요</div>

      <ul>
        <li><input type="radio" name="radio" value="1순위" class="radio"><label>1순위</label></li>
        <li><input type="radio" name="radio" value="2순위" class="radio"><label>2순위</label>
        </li>
        <li><input type="radio" name="radio" value="3순위" class="radio"><label>3순위</label>
        </li>
      </ul>
    </section>
    <footer>
      <button type="button" name="이전" class="clickbefore">< 이전</button>
      <bsection>

        <button type="button" name="이전" class="clicksubmit">제출</button>
        <button type="button" name="이전" class="clickerase">내용지우기</button>
      </bsection>

    </footer>
  </body>
</html>
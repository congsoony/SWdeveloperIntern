<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title></title>
  <link rel="stylesheet" href="./css/main.css">
</head>

<body>
  <header>
    <h1>나의 해야할 일들</h1>
    <button type="button" class="add">새로운 TODO 등록</button>
  </header>

    <section>

      <list class="TODO">
        <h2 class="firstline">TODO</h2>
        <c:forEach items="${todolist }" var="item">
        <article class="article_${item.getId()}">
          <h3 class="listclass">${item.getTitle() }
            <p>등록날짜:${item.getRegDate()} ,
              ${item.getName()},우선순위${item.getSequence() }</p>
            <button type="button" class="todoclick" onclick="moving('${ item.getId() }', '${ item.getType() }');">→</button>
          </h3>
          </article>
        </c:forEach>   
      </list>
      <list class="DOING">
        <h2 class="firstline">DOING</h2>
        
         <c:forEach items="${doinglist }" var="item">
          <article class="article_${item.getId()}">
          <h3 class="listclass">${item.getTitle() }
            <p>등록날짜:${item.getRegDate()} ,
              ${item.getName()},우선순위${item.getSequence() }</p>
            <button type="button" class="todoclick" onclick="moving('${ item.getId() }', '${ item.getType() }');">→</button>
          </h3>
          </article>
        </c:forEach>
      </list>
      <list class="DONE">
        <h2 class="firstline">DONE</h2>
         <c:forEach items="${donelist }" var="item">
         <article class="article_${item.getId()}">
          <h3 class="listclass">${item.getTitle() }
            <p>등록날짜:${item.getRegDate()} ,
              ${item.getName()},우선순위${item.getSequence() }</p>
            
          </h3>
          </article>
        </c:forEach>
      </list>
    </section>
    <script src="./js/main.js"></script>
</body>
</html>
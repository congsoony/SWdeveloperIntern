<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <button type="button" name="button" class="add">새로운 TODO 등록</button>
  </header>
  <section>
    <list>
      <h2 class="firstline">TODO </h2>
      <c:forEach items="${todolist }" var="item">
       <h3 class="listclass">${item.title }
        <p>등록날짜:${item.regDate },${dateString},우선순위${item.sequence }</p>
        <button type="button" name="button" class="todoclick">→</button>
      </h3>
		</c:forEach>
     
      
    </list>
    <list>
      <h2 class="firstline">DOING </h2>
     
    </list>
    <list>
      <h2 class="firstline">DONE </h2>
  
    </list>

</body>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="utf-8">
<title>食品登録</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
    <div class="main-contents">
		<div class="header">
			<h4>
				<a href="days">日別</a>
		        <a href="weeks">週別</a>
		        <a href="./">戻る</a>
	        </h4>
		</div>
	</div>
<body>
    <h1>食品登録！</h1>
    <form:form modelAttribute="foodForm">
    	※１００ｇ当たりの栄養素<br>
        食品名　　<form:input path="name" /><br>
        タンパク質<form:input path="protein" /><br>
        脂質　　　<form:input path="fat" /><br>
        糖質　　　<form:input path="carbo" /><br>
        <div hidden>量　　　　<form:input path="per" /><br></div>
        <input type="submit" value="追加する" class="button">
    </form:form>
</body>
</html>
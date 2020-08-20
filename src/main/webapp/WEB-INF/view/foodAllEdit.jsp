<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PFC計算アプリ</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="<c:url value="/resources/css/NewFile.css" />" rel="stylesheet">
    </head>

    <div class="main-contents">
		<div class="header">
			<h4>
				<a href="./">ホーム</a>
				<a href="food">食品登録</a>
		        <a href="days">戻る</a>
	        </h4>
		</div>
	</div>

    <a href="/PFCApp3/"><h1><p class="text-center">PFCAPP</p></h1></a>

	<div class="container">
	    <div class="row">
	    	<div class="col-md-6">
				<h3>${day}のデータ</h3>
				<h4>${user.name}さんの摂取カロリー　${calory}kcal</h4>
				<h4>　　　　　　　基礎代謝　●●kcal</h4>
				<h4>あと●●キロカロリー摂取しましょう！！</h4>
			</div>

	    	<div class="col-md-6">
				<h2>PFCバランス</h2>
				<h3>タンパク質　${proteinPer}%　(${protein}g)　　</h3>
				<h3>脂質　　　　${fatPer}%　(${fat}g)</h3>
				<h3>糖質　　　　${carboPer}%　(${carbo}g)</h3>
				<h5>※タンパク質は1g当たり4kcal、脂質は1g当たり9kcal、糖質は1g当たり4kcalで計算</h5>
			</div>
		</div>
	</div>
	<br><br>

	<div class="container">
	    <div class="row">
	    	<div class="col-md-6">
			<h3>記録する</h3>
				<form:form modelAttribute="dateMealForm">
					食品名<form:select path="id">
   							<form:options items="${foods}" itemLabel="name" itemValue="id" />
    					</form:select>
					量<form:input path="meal_quantity" />g<br>
					<div hidden><textarea name="date" ><c:out value="${day}" /></textarea></div>
			        <input type="submit" value="追加する" class="button" style="display: inline">
			    </form:form>
		</div>

	    	<div class="col-md-6">
				<h2>本日食べたもの</h2>
				<h3>
					<c:forEach items="${date_meals}" var="date_meal">
		            	<c:if test="${date_meal.date == day}">
		            		<c:forEach items="${foods}" var="food">
		            			<c:if test="${food.id == date_meal.food_id}">
		            				<c:out value="${food.name}"></c:out>　　　<c:out value="${date_meal.meal_quantity}"></c:out>g
								    <form:form modelAttribute="DateMealForm" action="delete" method="post" style="display: inline">
										<div hidden><textarea name="id" ><c:out value="${date_meal.id}" /></textarea></div>
										<input type="submit" value="削除する" class="button">
					    			</form:form>
					    			<br>
		            			</c:if>
		       				</c:forEach>
						</c:if>
		        	</c:forEach>
		        </h3>
			</div>
		</div>
	</div>
</html>
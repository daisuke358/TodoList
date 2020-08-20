<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="ja">
<head>
		<meta charset="utf-8">
		<title>日別データ</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="<c:url value="/resources/css/NewFile.css" />" rel="stylesheet">
</head>
    <div class="main-contents">
		<div class="header">
			<h4>
				<a href="food">食品登録</a>
		        <a href="./">戻る</a>
	        </h4>
		</div>
	</div>
<body>
  <h1 class="text-center">日別データ</h1>



	<div class="row">
	    <div class="col-md-1 col-md-offset-1">
		<a href="/PFCApp3/dayss?weekChange=-1">前の週</a>
	    </div>
	    <div class="col-md-1"><a href="/PFCApp3/edit?day=${sixDaysAgoA}">${sixDaysAgoB}</a></div>
	    <div class="col-md-1"><a href="/PFCApp3/edit?day=${fiveDaysAgoA}">${fiveDaysAgoB}</a></div>
	    <div class="col-md-1"><a href="/PFCApp3/edit?day=${fourDaysAgoA}">${fourDaysAgoB}</a></div>
	    <div class="col-md-1"><a href="/PFCApp3/edit?day=${threeDaysAgoA}">${threeDaysAgoB}</a></div>
	    <div class="col-md-1"><a href="/PFCApp3/edit?day=${twoDaysAgoA}">${twoDaysAgoB}</a></div>
	    <div class="col-md-1"><a href="/PFCApp3/edit?day=${oneDayAgoA}">${oneDayAgoB}</a></div>
	    <div class="col-md-1"><a href="/PFCApp3/edit?day=${thatdayA}">${thatDayB}</a></div>
	    <div class="col-md-1">
	    <a href="/PFCApp3/dayss?weekChange=1">次の週</a>
	    </div>
	</div>

  <canvas id="myLineChart"></canvas>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
  <script>
  var ctx = document.getElementById("myLineChart");
  var myLineChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: ['${sixDaysAgoB}', '${fiveDaysAgoB}', '${fourDaysAgoB}', '${threeDaysAgoB}', '${twoDaysAgoB}', '${oneDayAgoB}', '${thatDayB}'],
      datasets: [
        {
          label: 'カロリー',
          data: [${caloryG}, ${caloryF}, ${caloryE}, ${caloryD}, ${caloryC}, ${caloryB}, ${caloryA}],
          borderColor: "rgba(0,0,255,0.5)",
          backgroundColor: "rgba(0,0,0,0)"
        },
        {
            label: 'タンパク質',
            data: [${proteinG}, ${proteinF}, ${proteinE}, ${proteinD}, ${proteinC}, ${proteinB}, ${proteinA}],
            borderColor: "rgba(255,0,0,1)",
            backgroundColor: "rgba(0,0,0,0)"
          },
        {
	       label: '脂質',
	       data: [${fatG}, ${fatF}, ${fatE}, ${fatD}, ${fatC}, ${fatB}, ${fatA}],
	       borderColor: "rgba(247,238,54,1)",
	       backgroundColor: "rgba(0,0,0,0)"
        },
        {
 	       label: '糖質',
 	       data: [${carboG}, ${carboF}, ${carboE}, ${carboD}, ${carboC}, ${carboB}, ${carboA}],
 	       borderColor: "rgba(155,255,255,1)",
 	       backgroundColor: "rgba(0,0,0,0)"
         }
      ],
    },
    options: {
      /* title: {
        display: true,
        text: '気温（8月1日~8月7日）'
      }, */
      scales: {
        yAxes: [{
          ticks: {
            suggestedMax: 10,
            suggestedMin: 0,
            stepSize: 50,
            callback: function(value, index, values){
              return  value +  'kcal'
            }
          }
        }]
      },
    }
  });
  </script>
</body>

</html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/Chart.js" />"></script>
<script src="<c:url value="/resources/js/Line.js" />"></script>
</head>
<body>

	<div style="float: left; margin-right: 3px">
		<form action="main-window">
			<input type=submit class="btnLogin width-135px" value="Main window"
				onClick="main-window">
		</form>
	</div>

	<div style="float: left; margin-right: 3px">
		<form action="chart-inside">
			<input type=submit class="btnLogin width-135px" value="Chart inside"
				onClick="chart-inside">
		</form>
	</div>

	<div style="float: left; margin-left: 35px; margin-top: 17px">
		This are charts of the temperatures outside of the flat.</div>

	<div style="width: 100%">
		<div>
			<canvas id="canvas" height="240" width="1000"></canvas>
		</div>
	</div>


	<div style="float: left; margin-right: 3px">
		<form action="chart-outside-prev-hour">
			<input type=submit class="btnLogin width-135px" value="Backward"
				onClick="chart-outside-prev-hour">
		</form>
	</div>
	<div style="float: left; margin-right: 3px">
		<form action="chart-outside-next-hour">
			<input type=submit class="btnLogin width-135px" value="Forward"
				onClick="chart-outside-next-hour">
		</form>
	</div>

	<div style="float: left; margin-left: 35px; margin-top: 17px">
		Current time is
		<c:out value="${currentHour}" />:00 and date <c:out value="${date}" />
	</div>

	<div style="width: 100%">
		<div>
			<canvas id="canvas1" height="240" width="1000"></canvas>
		</div>
	</div>

	<div style="float: left; margin-right: 3px">
		<form action="chart-outside-prev-day">
			<input type=submit class="btnLogin width-135px" value="Backward"
				onClick="chart-outside-prev-day">
		</form>
	</div>
	<div style="float: left; margin-right: 3px">
		<form action="chart-outside-next-day">
			<input type=submit class="btnLogin width-135px" value="Forward"
				onClick="chart-outside-next-day">
		</form>
	</div>

	<div style="float: left; margin-left: 35px; margin-top: 17px">
		Current day is
		<c:out value="${currentDate}" />
	</div>
	
	<script>
		var forest = new Array();
		var yard = new Array();

		<c:forEach items ="${hour}" var = "outside" varStatus="status" >
		forest.push("${outside.outerForest}");
		yard.push("${outside.outerYard}");
		</c:forEach>

		Chart.defaults.global.animation = false;
		Chart.defaults.global.responsive = true;
		var lineChartData = {
			labels : [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
					17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
					32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46,
					47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59 ],
			datasets : [ {
				label : "My First dataset",
				fillColor : "rgba(250,0,0,0.2)",
				strokeColor : "rgba(250,0,0,1)",
				pointColor : "rgba(250,0,0,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba250,0,0,1)",
				data : forest
			}, {
				label : "My Second dataset",
				fillColor : "rgba(0,128,0,0.2)",
				strokeColor : "rgba(0,128,0,1)",
				pointColor : "rgba(0,128,0,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(0,128,0,1)",
				data : yard
			} ]

		}

		var forestPerDay = new Array();
		var yardPerDay = new Array();

		<c:forEach items ="${day}" var = "outside" varStatus="status" >
		forestPerDay.push("${outside.outerForest}");
		yardPerDay.push("${outside.outerYard}");
		</c:forEach>

		var lineChartData1 = {
			labels : [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
					16, 17, 18, 19, 20, 21, 22, 23 ],
			datasets : [ {
				label : "My First dataset",
				fillColor : "rgba(250,0,0,0.2)",
				strokeColor : "rgba(250,0,0,1)",
				pointColor : "rgba(250,0,0,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba250,0,0,1)",
				data : forestPerDay
			}, {
				label : "My Second dataset",
				fillColor : "rgba(0,128,0,0.2)",
				strokeColor : "rgba(0,128,0,1)",
				pointColor : "rgba(0,128,0,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(0,128,0,1)",
				data : yardPerDay
			} ]

		}

		window.onload = function() {
			var ctx = document.getElementById("canvas").getContext("2d");
			window.myLine = new Chart(ctx).Line(lineChartData, {
				responsive : true,
				scaleOverride: true,
				 scaleStartValue: -25,
				 scaleStepWidth: 5,
				 scaleSteps: 16
			});
			var ctx1 = document.getElementById("canvas1").getContext("2d");
			window.myLine = new Chart(ctx1).Line(lineChartData1, {
				responsive : true,
				scaleOverride: true,
				 scaleStartValue: -25,
				 scaleStepWidth: 5,
				 scaleSteps: 16
			});
		}
	</script>
</body>
</html>
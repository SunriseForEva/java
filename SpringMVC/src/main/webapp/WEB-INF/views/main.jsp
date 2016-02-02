<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<link href="<c:url value="/resources/css/button.css" />"
	rel="stylesheet">
<meta charset=utf-8>
<style>
h4 {
	background: #FFFFFF;
	color: 000000;
	padding: 0, 50%;
}

body {
	background-image: url('http://i055.radikal.ru/1412/a1/1e400ac1d666.png');
	background-repeat: no-repeat;
}
</style>


<table width=1000 border=0>
	<tr>
		<td width=190 height=221><div align=left>
				<h3>
					&nbsp; &nbsp;<span style="background-color: LightGray"> </span><br>&nbsp;
					<span style="background-color: White"> T= <c:out
							value="${lastData.getBalcony_1()}" /> C*
					</span>
				</h3>
			</div></td>
		<td width=216><div align=center>
				<h3>
					<br> <span style="background-color: White"> T= <c:out
							value="${lastData.getBadRoom()}" /> C*
					</span><br>
				</h3>
			</div></td>
		<td width=347><div align=center>
				<h3>
					<br> <span style="background-color: White"> T= <c:out
							value="${lastData.getHall()}" /> C*
					</span><br>
				</h3>
			</div></td>
		<td width=219><div align=right>
				<h3>
					<span style="background-color: White"></span> &nbsp; &nbsp; &nbsp;
					&nbsp;<br> <span style="background-color: White"> T= <c:out
							value="${lastData.getBalcony_2()}" /> C*
					</span> &nbsp;&nbsp;&nbsp;
				</h3>
			</div></td>
	</tr>
</table>
<table width=1000 height=544 border=0>
	<tr>
		<td width=1200 height=530><table width=1200 height=517 border=0>
				<tr>
					<td width=260 height=116><div align=center>
							<h3>
								<span style="background-color: LightGray"></span><br> <span
									style="background-color: White"> T= <c:out
										value="${lastData.getChildRoom()}" /> C*
								</span>
							</h3>
						</div></td>
					<td width=260 height=116><div align=center>
							<h3>
								<span style="background-color: LightGray"></span><br> <span
									style="background-color: White">T= <c:out
										value="${lastData.getHallWay()}" /> C*
								</span><br> <span style="background-color: LightGray"></span>
							</h3>
						</div></td>

					<td width=120 height=116><div align=center>
							<h3>
								<div style="float: right; margin-right: 3px">
									<form action="chart-inside ">
										<input type=submit class="btnLogin width-135px"
											value="Chart inside" onClick="chart-inside">
									</form>
								</div>
							</h3>
						</div></td>
					<td width=100 height=116><div align=center>
							<h3>
								<div style="float: left; margin-right: 3px">
									<form action="chart-outside">
										<input type=submit class="btnLogin width-135px"
											value="Chart outside" onClick="chart-outside">
									</form>
								</div>
							</h3>
						</div></td>
					<td width=0 height=55><div align=center>
							<h3>
								<div style="float: left; margin-right: 3px">
									<form action="login">
										<input type=submit class="btnLogin width-135px"
											value="Exit" onClick="login">
									</form>
								</div>
							</h3>
						</div></td>
				</tr>
				<tr>
					<td width=260 height=218><div align=center>
							<h3>
								<span style="background-color: LightGray"></span><br> <span
									style="background-color: White"> T= <c:out
										value="${lastData.getKitchen()}" /> C*
								</span>
							</h3>
						</div></td>
					<td width=177 height=218><div align=center>
							<h3>
								<span style="background-color: LightGray"></span><br>
							</h3>
						</div></td>

					<td width=140 height=200><div align=center></div></td>

					<td width=140 height=218><div align=center>
							Please wait 15 seconds
							Current date is -
							<c:out value="${date}" />
						</div></td>
				</tr>
				<tr>
					<td width=260 height=153><div align=center>
							<p>
							<h3>
								<span style="background-color: LightGray"></span><br> <span
									style="background-color: White"> T= <c:out
										value="${lastData.getPantry()}" /> C*
								</span>
							</h3>
						</div></td>
					<td width=177 height=153><div align=center>
							<p>
							<h3>
								<span style="background-color: LightGray"></span>
							</h3>
						</div></td>
				</tr>
			</table></td>
	</tr>
</table>
<br />

</body>
</html>
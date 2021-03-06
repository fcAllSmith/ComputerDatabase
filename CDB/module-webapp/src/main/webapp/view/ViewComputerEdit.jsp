<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/webapp/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="/webapp/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="/webapp/css/main.css" rel="stylesheet" media="screen">
<title>Edit</title>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="dashboard.html"> Application - Computer Database </a>
	</div>
	</header>
	<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<div class="label label-default pull-right">id: ${id}</div>
				<h1>Edit Computer</h1>

				<form action="edit" method="POST">
					<!--  <input type="hidden" value="${id}" id="id" /> -->
					<!-- TODO: Change this value with the computer id -->
					<fieldset>
						<input type="hidden" name="computerId" value="${computer.id}"
							id="computerId" />

						<div class="form-group">
							<label for="computerName">${name}</label> <input type="text"
								name="computerName" class="form-control" id="computerName"
								value="${computer.name}" placeholder="Computer name">
						</div>
						<div class="form-group">
							<label for="introduced">Introduced</label> <input type="date"
								class="form-control" id="introduced" name="introduced"
								value="${computer.introduced}" placeholder="Introduced date">
						</div>
						<div class="form-group">
							<label for="discontinued">Discontinued</label> <input type="date"
								class="form-control" id="discontinued" name="discontinued"
								value="${computer.discontinued}" placeholder="Discontinued date">
						</div>
						<div class="form-group">
							<label for="companyId">Company</label> <select
								class="form-control" name="companyId" id="companyId">
								<option value="${computer.companyName}">${companyId}</option>
							</select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" value="Edit" class="btn btn-primary">
						or <a href="dashboard" class="btn btn-default">Cancel</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	</section>
	<script src="/webapp/js/jquery.min.js"></script>
	<script src="/webapp/js/bootstrap.min.js"></script>
	<script src="/webapp/js/dashboard.js"></script>
</body>
</html>
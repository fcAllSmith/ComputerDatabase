<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/webapp/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="/webapp/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="/webapp/css/main.css" rel="stylesheet" media="screen">
<title>Add</title>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="dashboard.html"> Application -
			Computer Database </a>
	</div>
	</header>

	<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<h1>Add Computer</h1>
				<form action="add" method="POST">
					<fieldset>
						<div class="form-group">
							<label for="computerName"></label> <input name="computerName"
								type="text" class="form-control" id="computerName"
								placeholder="Computer name">
						</div>
						<div class="form-group">
							<label for="introduced"></label> <input type="date"
								class="form-control" id="introduced" name="introduced"
								placeholder="Introduced date">
						</div>
						<div class="form-group">
							<label for="discontinued"></label> <input name="discontinued"
								type="date" class="form-control" id="discontinued"
								placeholder="Discontinued date">
						</div>
						<div class="form-group">
							<label for="companyId"></label> <select name="companyId"
								class="form-control" id="companyId">
								<option value="1">1</option>
							</select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" value="Add" class="btn btn-primary">
						or <a href="dashboard.html" class="btn btn-default">Cancel</a>
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
<html>
	<head>
		<title>EventsBerry</title>
		
		<link href='//fonts.googleapis.com/css?family=Lato:100' rel='stylesheet' type='text/css'>

		<style>
			body {
				margin: 0;
				padding: 0;
				width: 100%;
				height: 100%;
				color: #B0BEC5;
				display: table;
				font-weight: 100;
				font-family: 'Lato';
			}

			.container {
				text-align: center;
				display: table-cell;
				vertical-align: middle;
			}

			.content {
				text-align: center;
				display: inline-block;
			}

			.title {
				font-size: 76px;
				margin-bottom: 40px;
			}

			.quote {
				font-size: 24px;
			}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="content">
				<a href="http://tutsberry.com"><img src="/img/logo.png"></a>
				<div class="title">EventsBerry</div>
				<div class="quote">{{ Inspiring::quote() }}</div>
				<p><small><a href="http://tutsberry.com"> www.TutsBerry.com</a></small> - <a href="/home">Login</a> </p>
			</div>
		</div>
	</body>
</html>

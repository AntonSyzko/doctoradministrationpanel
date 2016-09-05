<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
	<meta name="author" content="Anton Syzko antonio.shizko@gmail.com">

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Registration Confirmation Page</title>


	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js" type="text/javascript"></script>
	<link type="text/css" rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/themes/smoothness/jquery-ui.css"  />
	<script src="static/js/store.min.js" type="text/javascript"></script>
	<script src="static/js/jquery-idleTimeout.min.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css" />


	<script type="text/javascript" charset="utf-8">
		$(document).ready(function (){
			$(document).idleTimeout({
				redirectUrl: '/logout',
				idleTimeLimit: 1500,
				idleCheckHeartbeat: 2,
				customCallback: false,

				activityEvents: 'click keypress scroll wheel mousewheel mousemove',
				enableDialog: true,
				dialogDisplayLimit: 300,
				dialogTitle: 'Session Expiration Warning',
				dialogText: 'Because you have been inactive, your session is about to expire.',
				dialogTimeRemaining: 'Time remaining',
				dialogStayLoggedInButton: 'Stay Logged In',
				dialogLogOutNowButton: 'Log Out Now',
				errorAlertMessage: 'Please disable "Private Mode", or upgrade to a modern browser. Or perhaps a dependent file missing. Please see: https://github.com/marcuswestin/store.js',
				sessionKeepAliveTimer: false,
				sessionKeepAliveUrl: window.location.href
			});
		});
	</script>


</head>
<body>
<jsp:include page="/WEB-INF/views/admin_navbar.jsp"/>


<div class="panel panel-success">
	<div class="panel-body">
		<div class="generic-container">
			<%@include file="authheader.jsp" %>

			<div class="alert alert-success lead">
				<span class="glyphicon glyphicon-check"></span>	${success}
			</div>
			<div class="alert alert-success lead"   >
				<span class="glyphicon glyphicon-send"></span> ${email_confirmation}
						<div class=" alert-link alert-success " ><a href="mailto:#">${very_email}</a></div>
				<div class=" alert-success ">${close_mail}</div>
			</div>

	<span class="well pull-left" style="margin-left: 27%">

		<a href="<c:url value='/add-document-${user.id}' />" class="btn btn-info btn-lg">
          <span class="glyphicon glyphicon-download-alt"></span> CLICK HERE TO UPLOAD / MANAGE YOUR DOCUMENTS
        </a>

		</span>


		</div>

	</div>
	<span style="color: #1b6d85;margin-left: 70%"> Automatic LOGOUT after 30 mins of inactivity</span>    <p style="margin-left: 70%">&#169 developed by  <a href="mailto:antonsyzko@gmail.com"> antonsyzko@gmail.com</a></p>

</div>

</body>

</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Login Form</title>
  <link href="<c:url value="/resources/theme/css/login-page.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/theme/css/head-foot.css" />" rel="stylesheet">
  <script src="<c:url value="/resources/theme/js/jquery.js" />"></script>
  <script type="text/javascript" src="<c:url value="/resources/theme/js/script.js" />" > </script>
</head>

<body id="body">
 
  <section class="container">
  	<div class="login">
  		<img src="resources/theme/images/path1.png" height="100" width="100" style="width: 54%;margin-left: 90px;" />
    	<h2>Employee Attendance System</h2>
      	<h1>Login Here</h1>
      	<h5 id="error-login-msg" align="center" class="text-center" style="border-bottom-color:red; color : red" > </h5>
      	<form method="post" action="login" class="ajax-form-login">
        	<p><input required type="text" name="username" value="" placeholder="Username"></p>
        	<p><input required type="password" name="password" value="" placeholder="Password"></p>
        	<p class="remember_me">
          		<label>
            		<input type="checkbox" name="remember_me" id="remember_me">
            		Remember me on this computer
          		</label>
        	</p>
        	<p class="submit"><input type="submit" class="loginBtn" name="commit" value="Login"></p>
      	</form>
    </div>

    <div class="login-help">
      <p>Forgot your password? <a href="#">Click here to reset it</a>.</p>
    </div>
    <div class="login-help">
      <p>Sign Up <a href="/PSE_Attendance_System//attendance/register-user.htm">Click here to register</a>.</p>
    </div>
  </section>

  <section class="about">
    <p class="about-author">
      Copyright © 2017 - Path Solution  </section>
</body>

</html>

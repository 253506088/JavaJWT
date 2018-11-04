<%@ page language="java" import="java.util.*,java.text.*" 
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isErrorPage="true"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>登录与注册</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta name="author" content="" />
  	<!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700" rel="stylesheet">
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="<%=basePath %>static/css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="<%=basePath %>static/css/icomoon.css">
	<!-- Themify Icons-->
	<link rel="stylesheet" href="<%=basePath %>static/css/themify-icons.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="<%=basePath %>static/css/bootstrap.css">

	<!-- Magnific Popup -->
	<link rel="stylesheet" href="<%=basePath %>static/css/magnific-popup.css">

	<!-- Owl Carousel  -->
	<link rel="stylesheet" href="<%=basePath %>static/css/owl.carousel.min.css">
	<link rel="stylesheet" href="<%=basePath %>static/css/owl.theme.default.min.css">

	<!-- Theme style  -->
	<link rel="stylesheet" href="<%=basePath %>static/css/style.css">

	<!-- jQuery -->
	<script src="<%=basePath %>static/js/jquery.min.js"></script>
	<!-- JQuery Cookie -->
	<script src="<%=basePath %>static/js/jquery.cookie.js"></script>
	<!-- jQuery Easing -->
	<script src="<%=basePath %>static/js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="<%=basePath %>static/js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="<%=basePath %>static/js/jquery.waypoints.min.js"></script>
	<!-- Carousel -->
	<script src="<%=basePath %>static/js/owl.carousel.min.js"></script>
	<!-- countTo -->
	<script src="<%=basePath %>static/js/jquery.countTo.js"></script>
	<!-- Magnific Popup -->
	<script src="<%=basePath %>static/js/jquery.magnific-popup.min.js"></script>
	<script src="<%=basePath %>static/js/magnific-popup-options.js"></script>
	<!-- Main -->
	<script src="<%=basePath %>static/js/main.js"></script>

	<!-- Modernizr JS -->
	<script src="<%=basePath %>static/js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="<%=basePath %>static/js/respond.min.js"></script>
	<![endif]-->

	</head>
	<body>
		
	<div class="gtco-loader"></div>
	
	<div id="page">

	
	<div class="page-inner">
	<nav class="gtco-nav" role="navigation">
		<div class="gtco-container">
			
			<div class="row">
				<div class="col-sm-4 col-xs-12">
					<div id="gtco-logo"><a href="index.html">Splash <em>.</em></a></div>
				</div>
				<div class="col-xs-8 text-right menu-1">
					<ul>
						<li><a href="features.html">Features</a></li>
						<li><a href="tour.html">Tour</a></li>
						<li class="has-dropdown">
							<a href="#">Dropdown</a>
							<ul class="dropdown">
								<li><a href="#">Web Design</a></li>
								<li><a href="#">eCommerce</a></li>
								<li><a href="#">Branding</a></li>
								<li><a href="#">API</a></li>
							</ul>
						</li>
						<li><a href="pricing.html">Pricing</a></li>
						<li><a href="contact.html">Contact</a></li>
						<li class="btn-cta"><a href="#"><span>Get started</span></a></li>
					</ul>
				</div>
			</div>
			
		</div>
	</nav>
	
	<header id="gtco-header" class="gtco-cover" role="banner" style="background-image: url(<%=basePath %>static/img/img_4.jpg)">
		<div class="overlay"></div>
		<div class="gtco-container">
			<div class="row">
				<div class="col-md-12 col-md-offset-0 text-left">
					

					<div class="row row-mt-15em">
						<div class="col-md-7 mt-text animate-box" data-animate-effect="fadeInUp">
							<span class="intro-text-small">BlackTV</span>
							<h1 id="alert">点击登陆或注册开启异世界生活</h1>	
						</div>
						<div class="col-md-4 col-md-push-1 animate-box" data-animate-effect="fadeInRight">
							<div class="form-wrap">
								<div class="tab">
									<ul class="tab-menu">
										<li class="active gtco-first"><a href="#" data-tab="signup">登录</a></li>
										<li class="gtco-second"><a href="#" data-tab="login">注册</a></li>
									</ul>
									<div class="tab-content">
										<div class="tab-content-inner active" data-content="signup">
                                            <form id="loginForm">
                                                <div class="row form-group">
                                                    <div class="col-md-12">
                                                        <label for="username">账号 or 邮箱</label>
                                                        <font color="F01B2D" class="loginAndSignUpError" id="loginUsernameError">&nbsp&nbsp大于6位小于20位!</font>
                                                        <input type="text" class="form-control" name = "usernameOrEmail" id="loginUsername">
                                                    </div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col-md-12">
                                                        <label for="password">密码</label>
                                                        <font color="F01B2D" class="loginAndSignUpError" id="loginPasswordError">&nbsp&nbsp至少大于6位!</font>
                                                        <input type="password" class="form-control" name = "password" id="loginPassword">
                                                    </div>
                                                </div>

                                                <div class="row form-group">
                                                    <div class="col-md-12">
                                                        <input type="button" class="btn btn-primary" id="loginSubmit" value="登陆">
                                                    </div>
                                                </div>
                                            </form> 
										</div>

										<div class="tab-content-inner" data-content="login">
											<form id="signUpForm">
												<div class="row form-group">
													<div class="col-md-12">
														<label for="username">账号 or 邮箱</label>
														<font color="F01B2D" class="loginAndSignUpError" id="signUpUsernameError">&nbsp&nbsp大于6位小于20位!</font>
														<font color="F01B2D" class="loginAndSignUpError" id="signUpUsername2Error">&nbsp&nbsp该邮箱已存在!</font>
														<font color="F01B2D" class="loginAndSignUpError" id="signUpUsername3Error">&nbsp&nbsp该账号已存在!</font>
														
														<input type="text" class="form-control" name="usernameOrEmail" id="signUpUsername">
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-12">
														<label for="username">昵称</label>
														<font color="F01B2D" class="loginAndSignUpError" id="signUpNikeNameError">&nbsp&nbsp至少大于1位小于20位!</font>
														<font color="F01B2D" class="loginAndSignUpError" id="signUpNikeName2Error">&nbsp&nbsp昵称已存在!</font>
														<input type="text" class="form-control" name="nikeName" id="signUpNikeName">
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-12">
														<label for="password">密码</label>
														<font color="F01B2D" class="loginAndSignUpError" id="signUpPasswordError">&nbsp&nbsp大于6位小于20位!</font>
														<input type="password" class="form-control" name="password" id="signUpPassword">
													</div>												
												</div>
												<div class="row form-group">
													<div class="col-md-12">
														<label for="password2">确认密码</label>
														<font color="F01B2D" class="loginAndSignUpError" id="signUpPassword2Error">&nbsp&nbsp两次密码不相同!</font>
														<input type="password" class="form-control" id="signUpPassword2">
													</div>
												</div>

												<div class="row form-group">
													<div class="col-md-12">
														<input type="button" class="btn btn-primary" id="signUpSubmit" value="注册">
													</div>
												</div>
											</form>	
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
							
					
				</div>
			</div>
		</div>
	</header>
	<script type="text/javascript">
		var signUpUsernameError = false;//账号或邮箱长度是否符合要求
		var signUpUsername2Error = false;//账号或邮箱是否存在
		var signUpNikeNameError = false;//昵称长度是否符合要求
		var signUpNikeName2Error = false;//昵称是否存在
		var signUpPasswordError = false;//密码长度是否符合要求
		var signUpPassword2Error = false;;//两次密码是否相同
		
		
		var loginUsernameError = false;// 账号长度是否符合要求
		var loginPasswordError = false;// 密码长度是否符合要求
		var loginPassword2Error = false;// 密码是否正确,用于两次密码都相同的情况
		
		$(document).ready(function(){
			$(".loginAndSignUpError").hide();
			
			$("#signUpUsername").blur(function(){
				signUpUsername();
			});
			
			$("#signUpNikeName").blur(function(){
				signUpNikeName();
			});
			
			$("#signUpPassword").blur(function(){
				signUpPassword();
			});
			
			$("#signUpPassword2").blur(function(){
				signUpPassword2();
			});
			
			$("#signUpSubmit").click(function(){
		        signUpSubmit();
		    });
			
			$("#loginUsername").blur(function(){
				loginUsername();
			});
			
			$("#loginPassword").blur(function(){
				loginPassword();
			});
			
		    $("#loginSubmit").click(function(){
		        loginSubmit();
		    });
		});
		
		// 验证注册时的账号长度是否符合规则
		function signUpUsername(){
			var signUpUsername = $("#signUpUsername").val();
			if(signUpUsername.length <6 || signUpUsername > 20){
				signUpUsernameError = false;
				$("#signUpUsernameError").show();
			}else{
				signUpUsernameError = true;
				$("#signUpUsernameError").hide();
				if(isEmail(signUpUsername)){
					signUpUsername2(signUpUsername);
				}else{
					signUpUsername3(signUpUsername);
				}
			}
		}
		
		var oldEmail = "";
		// 验证邮箱是否存在
		function signUpUsername2(email){
			// 防止同一个邮箱多次请求数据库检验是否已存在
			if(email != oldEmail){
				$.get("<%= basePath%>getUserIdByEmail","email="+email,function(result){
					if(result.flag == true){
						signUpUsername2Error = true;
						$("#signUpUsername2Error").hide();
					}else{
						signUpUsername2Error = false;
						$("#signUpUsername2Error").show();
					}
					$("#signUpUsername3Error").hide();// 把邮箱的错误隐去
				},"json");
				oldEmail = email;
			}
		}
		
		var oldUsername = "";
		// 验证账号是否存在
		function signUpUsername3(username){
			// 防止同一个账号多次请求数据库检验是否已存在
			if(oldUsername != username){
				$.get("<%=basePath%>getUserIdByUsername","username="+username,function(result){
					if(result.flag == true){
						signUpUsername2Error = true;
						$("#signUpUsername3Error").hide();
					}else{
						ignUpUsername2Error = false;
						$("#signUpUsername3Error").show();
					}
					$("#signUpUsername2Error").hide();//把邮箱的错误隐去
				},"json");
				oldUsername = username;
			}
		}
		
		// 验证昵称长度是否符合规则
		function signUpNikeName(){
			var signUpNikeName = $("#signUpNikeName").val();
			if(signUpNikeName.length < 1 || signUpNikeName.length > 20){
				signUpNikeNameError = false;
				$("#signUpNikeNameError").show();
			}else{
				signUpNikeNameError = true;
				$("#signUpNikeNameError").hide();
				signUpNikeName2(signUpNikeName);
			}
		}
		
		var oldNikeName = "";
		// 验证该昵称是否已存在
		function signUpNikeName2(nikeName){
			// 为了防止同一个昵称多次访问数据库检验上是否已存在
			if(oldNikeName != nikeName){
				$.get("<%= basePath%>getUserIdByNikeName","nikeName="+nikeName,function(result){
					if(result.flag == true){
						signUpNikeName2Error = true;
						$("#signUpNikeName2Error").hide();
					}else{
						signUpNikeName2Error = false;
						$("#signUpNikeName2Error").show();
					}
				},"json");
				oldNikeName = nikeName;
			}
		}
		
		// 验证密码长度是否正确
		function signUpPassword(){
			var signUpPassword = $("#signUpPassword").val();
			if(signUpPassword.length < 6	 || signUpPassword.length > 20){
				signUpPasswordError = false;
				$("#signUpPasswordError").show();
			}else{
				signUpPasswordError = true;
				$("#signUpPasswordError").hide();
			}
		}
		
		// 验证两次密码是否相同
		function signUpPassword2(){
			if($("#signUpPassword2").val() != $("#signUpPassword").val()){
				signUpPassword2Error = false;
				$("#signUpPassword2Error").show();
			}else{
				signUpPassword2Error = true;
				$("#signUpPassword2Error").hide();
			}
		}
		
		// 验证登录用户名或邮箱长度是否正确
		function loginUsername(){
			var loginUsername = $("#loginUsername").val();
			if(loginUsername.length < 6 || loginUsername.length > 20){
				loginUsernameError = false;
				$("#loginUsernameError").show();
			}else{
				loginUsernameError = true;
				$("#loginUsernameError").hide();
			}
		}
		
		// 验证登录密码长度是否正确
		function loginPassword(){
			var loginPassword = $("#loginPassword").val();
			if(loginPassword.length < 6 || loginPassword > 20){
				loginPasswordError = false;
				$("#loginPasswordError").show();
			}else{
				loginPasswordError = true;
				$("#loginPasswordError").hide();
			}
		}
		
		var passowrdOld = "黑白大彩电abcd";// 记录上一次密码
		var usernameOrEmailold = "黑白大彩电abcd";// 记录上一次账号
		// 登录前的验证
		function loginSubmit(){
			// 如果本次提交的账号和密码与上一次登录失败的相同则不提交表单
			if($("#loginPassword").val() == passowrdOld && $("#loginUsername").val() == usernameOrEmailold)
				return ;
			// 前端验证
			loginPassword();
			loginUsername();
			if(loginPasswordError && loginUsernameError){
				var data = $("#loginForm").serialize();
				login(data);
			}
		}
		
		// 提交登录请求
		function login(data){
			$.post("<%=basePath%>login",data,function(result){
				switch(result.loginFlag){
					case "false" :
						$("#alert").text("登录失败,账号或密码错误!");
						passowrdOld = $("#loginPassword").val();
						usernameOrEmailold = $("#loginUsername").val();
						break;
					case "true" :
						$("#alert").text("登录成功,即将跳转到首页");
						// 添加两个会话cookie,随着浏览器关闭而消逝
						$.cookie("jwt",result.jwt);
						$.cookie("userMsg",result.userMsg);
						setTimeout(function(){ window.location.href = "<%=basePath%>userIndex?jwt="+$.cookie("jwt");}, 3000);// 3秒后跳转到首页 
						break;
					case "Error" :
						$("#alert").text("意外错误登录失败,请联系管理员!");
						break;
				}
			},"json");			
		}

		// 验证是否是邮箱,是邮箱返回true,反之返回false
		function isEmail(email){
			var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
			isok= reg.test(email);
			if (!isok) 
			    return false;
			return true;
		}
		
		// 提交注册
		function signUpSubmit(){
			// 再次验证一遍
			signUpUsername();
			signUpNikeName();
			signUpPassword();
			signUpPassword2();
			// 全部符合规则才可以发起注册，注册成功即登录成功
			if(signUpUsernameError && signUpUsername2Error && signUpNikeNameError && signUpNikeName2Error && signUpPasswordError 
				&& signUpPassword2Error){
				var data = $("#signUpForm").serialize();
				console.log(data);
				$.post("<%=basePath%>register",data,function(result){
					switch(result.HttpStatus){
						case "OK":
							$("#alert").text("注册成功,即将进入个人主页");
							login(data);// 发起登录请求 
							break;
						case "registerError":
							$("#alert").text("注册失败,请联系管理员");
							break;
						case "sendEmail":
							$("#alert").text("注册成功,已发送验证邮件,邮件有效期30分钟");
							break;
						case "sendEmailError":
							$("#alert").text("注册成功,验证邮件发送失败,请联系管理");
							break;
						case "valueError":
							$("#alert").text("错误的请求,非法参数!");
							break;
						default:
							console.log("未知信息:"+result.HttpStatus);
					}
				},"json");
			}
		}
	</script>
	</div>

	</div>

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
	</div>
	</body>
</html>



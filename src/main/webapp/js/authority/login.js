(function() {
	var validateCode = "";// 验证码
	var LoginModule = {
		key : "REMENBER_PASSWORD",
		getDate : function() {
			var loginInfo = {};

			loginInfo.username = $("#username").val();
			loginInfo.password = $("#password").val();
			loginInfo.remenber = $("#remenber").prop('checked');
			loginInfo.validateMsg = $("#validateMsg").val();
			
			return loginInfo;
		},
		check : function(obj, event) {
			if (!(obj.username != null && obj.username != ""
					&& obj.password != null && obj.password != "")) {
				event.preventDefault();
				return 0;
			}
			if (obj.validateMsg == null || obj.validateMsg == "") {
				event.preventDefault();
				return 1;
			} else {
				if (validateCode.toLowerCase() != obj.validateMsg
						.toLowerCase()) {
					event.preventDefault();
					return 2;
				}
			}
			return 3;
		},
		doLogin : function(msg, loginData) {
			var $tip = $("#tip");
			switch (msg) {
				case 0:
					$tip.text("用户名或密码不能为空！");
					break;
				case 1:
					$tip.text("验证码为空");
					break;
				case 2:
					$tip.text("验证码错误");
					$("#codeImg").trigger('click');
					$("#validateMsg").val("");
					break;
				default:
					LoginModule.remenberUser(loginData);
					LoginModule.submit(loginData);
			}
		},
		remenberUser : function(obj) {
			if (obj.remenber && obj.remenber == 1) {
				delete obj.remenber;
				delete obj.validateMsg;
				localStorage.setItem(LoginModule.key, JSON.stringify(obj));
			} else {
				localStorage.removeItem(LoginModule.key);
			}
		},
		submit : function(data){
			executeAjaxUrlForResult("submit",{
				data : data,
				type : 'post'
			},function(){
				debugger;
			});
		}
	};

	$(function() {
		// 如果从其他页面转向登录页，清除用户信息缓存
		if (document.referrer != "") {
			localStorage.removeItem(LoginModule.key);
		}

		setInterval(function() {// 底图切换动画
			var $active = $('.active');
			if ($active.length == 0)
				$active = $('.slideshow img:last');
			var $next = $active.next().length ? $active.next()
					: $('.slideshow img:first');
			$active.addClass('last-active');
			$next.css({
				opacity : 0
			}).addClass('active').animate({
				opacity : 1
			}, 4000, function() {
				$active.removeClass('active last-active');
			});
		}, 5000);

		/* 如果记住密码则显示用户名及密码 */
		var autoShow = JSON.parse(localStorage.getItem(LoginModule.key));
		
		if (autoShow) {
			$("#username").val(autoShow.username);
			$("#password").val(autoShow.password);

			$("#remenber").attr("checked", true);// 记住密码状态回填

			LoginModule.submit(autoShow);
		}

		/* 用户登录 */
		$("#loginBtn").click(function(e) {
			/* 获取用户账号信息 */
			var loginImfo = LoginModule.getDate();
			/* 判断空输入 */
			var msg = LoginModule.check(loginImfo, e);
			/* 用户登录 */
			LoginModule.doLogin(msg, loginImfo)
		});

		/* 刷新验证码 */
		$("#codeImg").click(
			function() {
				$.ajax({
					type : "POST",
					url : "getVerifyCode",
					success : function(result) {
						validateCode = result;
						$("#codeImg").attr('src',"/water-file/verifycode/" + result + ".jpg");
					}
			});
		});

		$("#codeImg").click();

		/* 取消错误提示信息 */
		var $tip = $("#tip"), tipval;
		$("#loginForm").find("input").on('keyup', function() {
			tipval = $tip.text();
			if (tipval.length > 0) {
				$("#tip").text("");
			}
		});
	})
}());

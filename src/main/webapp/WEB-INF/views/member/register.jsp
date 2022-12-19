<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
		<!-- 합쳐지고 최소화된 최신 CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<!-- 부가적인 테마 -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	 	
	 	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<title>register</title>
	</head>
	<script type="text/javascript">
	$(document).ready(function(){
		// 취소
		$(".cencle").on("click", function(){
			location.href = "/";
		})
		
		//회원가입
		$("#submit").on("click", function(){
			
			var id = $("#userId").val();
			var trimid = id.trim();
			
        	var pw = $("#userPass").val();
			var trimpw = pw.trim();
			
			var nn = $("#userName").val();
			var trimnn = nn.trim();
			
			if(trimid == "" || trimid == null || trimid == " " ){
				alert("아이디를 입력해주세요.");
				$("#userId").focus();
				return false;
			}
			
			if(trimpw == "" || trimpw == null || trimpw == " " ){
				alert("비밀번호를 입력해주세요.");
				$("#userPass").focus();
				return false;
			}
			if(trimnn == "" || trimnn == null || trimnn == " " ){
				alert("성명을 입력해주세요.");
				$("#userName").focus();
				return false;
			}
			var idChkVal = $("#idChk").val();
			if(idChkVal == "N"){
				alert("중복확인 버튼을 눌러주세요.");
				return false;
			}else if(idChkVal == "Y"){
				$("#regForm").submit();
			}
		});
	})
	
	//회원가입 할때 아이디 중복 체크 누르고 id 체크 해서  < 체크하라고 눌렀을때 
	//		input 값에  특수문자 , 한글 , 13자 이상  들어가면 alert 띄어서 "특수문자 , 한글 , 13자 이상이 포함되어있습니다." 띄우고 false 만들으셈 
	//	저거 위에 포함되어있으면 컨트롤러로 못넘어가게하는거다 그리고 포함되어있으면 input 값 다 지워
	
	function fn_idChk(){

	    //var regExp = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\ '\"\\(\=]/gi;
		
		var str = $("#userId").val();
		
		var strchk =  str.indexOf(" ") == -1 ? true : false 
		var special_pattern = /[ `~!@#$%^&*|\\\'\";:\/?]/gi;
		if(!strchk || special_pattern.test(str)){
			document.getElementById("userId").value = "";
			alert("아이디에 특수문자나 공백이 들어갈 수 없습니다.");
			return false;
		}

		/* // 이거 내가 한거임
		if(!$("#userId").val(text.trim())){
	//		alert("특수문자 , 한글 , 공백 , 13자 이상은 포함될수 없습니다.");
			alert("제발 되라 ㅅㅂ");
	//      trimid = trimid.trim.substring( 0 , trimid.trim.length - 1 );
			$("#userId").focus();
			return false;
		}  */
		
		/* var id = $("#userId").val();
		var trimid = id.trim();
		
		if(trimid == "" || trimid == null || trimid == " " ){
			alert("아이디를 입력해주세요.");
			$("#userId").focus();
			return false;
		} */
		
		 $.ajax({
				url : "/member/idchk",
				type : "post",
				dataType : "json",
				data : {"memberID" : $("#userId").val()},
				success : function(data){
					if(data == 1){
						alert("중복된 아이디입니다.");
					}else if(data == 0){
						$("#idChk").attr("value", "Y");
						alert("사용가능한 아이디입니다.")
					}
				}
			}) 
		}
	</script>
	
	<body>
		<section id="container">
			<form action="/member/register" method="post" id="regForm">
				<div class="form-group has-feedback">
					<label class="control-label" for="userId">아이디</label>
					<input class="form-control" type="text" id="userId" name="MemberID" />
					<button class="idChk" type="button" id="idChk" onclick="fn_idChk();" value="N">중복확인</button>
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="userPass">패스워드</label>
					<input class="form-control" type="password" id="userPass" name="MemberPW" />
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="userName">성명</label>
					<input class="form-control" type="text" id="userName" name="MemberNN" />
				</div>
				<div class="form-group has-feedback">
					<button class="btn btn-success" type="submit" id="submit">회원가입</button>
					<button class="cencle btn btn-danger" type="button">취소</button>
				</div>
			</form>
		</section>
		
	</body>
	
</html>
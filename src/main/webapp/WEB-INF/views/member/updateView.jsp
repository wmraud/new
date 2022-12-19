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
		<title>회원가입</title>
	</head>
	<script type="text/javascript">
	// 틀렸음 내가 마했지 컨펌 띄어가지고 하라고  저거 지우고 회아니 야 잠시만
	// 이거 원리 모르냐 ? ajax 로 비밀번호 체크만 한다음에 비밀번호 체크 완료하면 회원수정 컨트롤러 타잖아 ?? 그리고 ??뭐가 그리고야 회원정보 수정 true일떄 회원수정이 가능하자나
		$(document).ready(function(){
			
			// 취소
			$(".cencle").on("click", function(){
				location.href = "/member/login";    
			})
			
			//회원탈퇴
	        $("#deletebtn").on("click", function(){
		        location.href="/member/deleteView";
	        })

	        //회원정보수정
			$("#submit").on("click", function(){
				
		        if(confirm("수정하시겠습니까 ?")){
		        	var pass = $("#userPass").val();
					var trimPass = pass.trim();
					
					var name = $("#userName").val();
					var trimname = name.trim();
					
					if(trimPass == "" || trimPass == null || trimPass == " " ){
						alert("비밀번호를 입력해주세요.");
						$("#userPass").focus();
						return false;
								
					}
					if(trimname == "" || trimname == null || trimname == " " ){
						alert("성명을 입력해주세요.");
						$("#userName").focus();
						return false;
					}
					$.ajax({
						url : "/member/update", 
						type : "POST",
						dateType : "json",
						data : $("#updateForm").serializeArray(),  
						success: function(data){ 
								if(data == 1){ 
									alert("변경완료 / 다시 로그인을 해주세요")
									location.href = "/member/login";    
								}
								else{ 	
									alert("수정실패");
								}
					}})
		        }else{
		        	return false;
		        }
			});
		})
	
	
		
	</script>
	<body>
		<section id="container">
			<form id="updateForm" action="/member/update" method="post">
				<div class="form-group has-feedback">
					<label class="control-label" for="userId">아이디</label>
					<input class="form-control" type="text" id="userId" name="MemberID" value="${member.memberID}" readonly="readonly"/>
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="userPass">패스워드</label>
					<input class="form-control" type="password" id="userPass" name="MemberPW" />
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="userName">성명</label>
					<input class="form-control" type="text" id="userName" name="MemberNN" value="${member.memberNN}"/>
				</div>
			</form>
				<div class="form-group has-feedback">
					<button class="btn btn-success" type="button" id="submit">회원정보수정</button>
					<button class="btn btn-success" type="button" id="deletebtn">회원탈퇴</button>
					<button class="cencle btn btn-danger" type="button">취소</button>
				</div>
		</section>
		
	</body>
	
</html>
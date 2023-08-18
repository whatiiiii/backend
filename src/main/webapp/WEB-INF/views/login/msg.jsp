<%@ page contentType="text/html;charset=utf-8" import="hj.backend.service.MemberLoginConst"%>
<script>
    if(${result} == <%=MemberLoginConst.NO_ID%>){
        alert("그런 이메일을 가진 회원이 없어요");
        //location.href="form.do";
        history.back();
    }else if(${result} == <%=MemberLoginConst.NO_PWD%>){
        alert("비밀번호가 틀렸어요");
        //location.href="form.do";
        history.back();
    }else{
        alert("로긴 성공"+"${sessionScope.forward_url}");
        if(${empty sessionScope.forward_url}){
            location.href ="../";
        }else{
            location.href= "../${sessionScope.forward_url}";
            <% session.removeAttribute("forward_url");%>
            alert("제거후: ${sessionScope.forward_url}")
        }
        //alert("로긴성공")
        //location.href="../";
        //location.href="../file/list.do " // 해당 세션으로 넘어가는 것
    }
</script>
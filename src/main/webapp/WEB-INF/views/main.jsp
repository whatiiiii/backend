<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <meta charset="UTF-8">
    <body style="text-align:center">
	    <style>
            a{text-decoration:none}
        </style>
        <h3>main.jsp ( ${data} )</h3>
        <h5>뷰(View)</h5>
        <a href="hello.html">스테이틱(static)</a>
        <a href="main.do">제이에스피(JSP)</a>

        <h6>컨트롤러(Controller): 리턴방식</h6>
        <a href="template.do?na=tiger">템플릿(template)</a>
        <a href="string.do?na=lion">문자열(String)</a>
        <a href="json.do?title=스프링&price=20000">제이슨(JSON)</a>

        <h5>컨트롤러(Controller): 다양한 Give&Take</h5>
        <a href="test">m00</a>
        <a href="test/base1">m01</a>
        <a href="test/base2">m02</a>
        <a href="test/base3">m03</a>
        <a href="test/param1?name=홍길동&age=30">m04</a>
        <a href="test/param2?name=이순신&age=40">m05</a>
        <a href="test/param3?names=이순신&names=강감찬&names=홍길동">m06</a>
        <a href="test/param4?ns=이순신&ns=강감찬&ns=홍길동">m07</a>
        <a href="test/param5?names=이순신&names=강감찬&names=홍길동">m08</a>
        <!--
            <a href="test/param6?list[0].name=이순신&list[0].age=27&list[1].name=홍길동&list[1].age=40">m09</a>
            [ -> %5B
            ] -> %5D
        -->
        <a href="test/param6?list%5B0%5D.name=이순신&list%5B0%5D.age=27&list%5B1%5D.name=홍길동&list%5B1%5D.age=40">m09</a>
        <a href="test/param7?name=이순신&page=10&age=30">m10</a>
		<a href="test/param8?subject=데이트&cdate=2023/08/01 18:02:30">m11</a>
        <a href="test/json1">m12</a>
        <a href="test/json2">m13</a>
    </body>
</html>




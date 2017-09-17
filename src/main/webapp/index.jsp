<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>slimming-test</title>
</head>
<script src="./js/jquery.js"></script>
<script type="text/javascript">
    function updateUserInfo() {
        var user = {"id":1,"username":"yanweijia","password":"weijia","phone":"18621703545","email":"admin@yanweijia.cn","name":"严唯嘉","birthday":843753600000,"gender":"男","height":178.00,"weight":75.10,"status":0,"regTime":1505577600000,"regIp":"127.0.0.1","lastLogin":1505577600000};
        $.ajax({
            url : "http://localhost:8080/slimming/api/user/updateUserInfo.action",
            type : "POST",
            data : JSON.stringify(user), //转JSON字符串
            dataType: 'json',
            contentType:'application/json;charset=UTF-8', //contentType很重要
            success : function(result) {
                alert(result.toString());
            }
        });
    }
</script>
<body>
<p>Slimming项目</p>
<button type="button" onclick="updateUserInfo()">updateUserInfo</button>
</body>
</html>

$(function () {
    //根据后台数据进行页面跳转或弹出提示
    $("#form1").ajaxForm(function (data) {
        console.log(data);
        if (data === "1") {
            window.location.href = "./admin.html";
        } else if (data === "0") {
            alert("密码错误！");
        } else if (data === "2") {
            alert("管理员不存在！");
        } else if (data === "4") {
            window.location.href = "./user.html";
        } else if (data === "5") {
            alert("用户不存在！");
        }
    });
});

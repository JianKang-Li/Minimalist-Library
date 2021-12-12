$(function () {
  //表单提交结果处理
  $("#form2").ajaxForm(function (data) {
    console.log(data);
    if (data === "1") {
      alert("注册成功！");
      window.location.href = "./login.html";
    } else {
      alert(data);
    }
  });
});
//验证用户名是否存在
function checkconfirm() {
  var uname = document.getElementById("uname").value;
  $.get("./checkUser?uname=" + uname, function (str) {
    if (str === "1") {
      document.getElementById("tishi1").innerHTML =
          "<font color='red'>用户已存在!</font>";
    } else {
      document.getElementById("tishi1").innerHTML = "";
    }
  });
}

function checkpassword() {
  //检测两次密码输入是否一致
  var password = document.getElementById("pswd").value;
  var repassword = document.getElementById("repswd").value;

  if (password !== repassword) {
    document.getElementById("tishi2").innerHTML =
        "<font color='red'>两次输入密码不一致!</font>";
  } else {
    document.getElementById("tishi2").innerHTML = "";
  }
}
function checkemail() {
  //检查邮箱格式是否正确
  var temp = document.getElementById("email");
  var myreg =
      /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
  if (!myreg.test(temp.value)) {
    document.getElementById("tishi3").innerHTML =
        "<font color='red'>邮箱输入错误!</font>";
  } else {
    document.getElementById("tishi3").innerHTML = "";
  }
}

function change() {
  var t = new Date();
  document.getElementById("codeImg").src = "./GenerateCodeServlet?time=" + t;
}

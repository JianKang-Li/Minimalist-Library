function checkemail() {
  //检测邮箱格式
  var temp = document.getElementById("email");
  var myreg =
      /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
  if (!myreg.test(temp.value)) {
    document.getElementById("tishi1").innerHTML =
        "<font color='red'>邮箱输入错误!</font>";
  } else {
    document.getElementById("tishi1").innerHTML = "";
  }
}

function checkconfirm() {
  //检查用户名是否重复
  var uname = document.getElementById("recipient-name").value;
  var olduname = document.getElementById("olduname").value;
  if (uname != olduname) {
    $.get("./checkUser?uname=" + uname, function (str) {
      if (str === "1") {
        document.getElementById("tishi").innerHTML =
            "<font color='red'>用户已存在!</font>";
      } else {
        document.getElementById("tishi").innerHTML = "";
      }
    });
  } else {
    document.getElementById("tishi").innerHTML = "";
  }
}

//借阅信息查看
var msg = {
  template: "#msg",
  data() {
    return {
      list: [],
      keywords: "",
      limit: 8,
      Currentpage: 1,
      totalpage: 1,
      number: "",
    };
  },
  methods: {
    calculate(index) {
      //比较应还书时间和当前时间
      var date1 = this.bookmsg[index].backdate;
      var oDate1 = new Date(date1);
      var oDate2 = new Date();
      if (oDate1.getTime() > oDate2.getTime()) {
        // console.log('第一个大');
        return "table-light";
      } else if (oDate1.getTime() <= oDate2.getTime()) {
        // console.log('第二个大');
        return "table-danger";
      }
    },
    currentpage(val) {
      //计算当前页
      if (
          (this.Currentpage === 1 && val === "-") ||
          (this.Currentpage === this.totalpage && val === "+")
      ) {
        return;
      }
      switch (
          val //判断操作
          ) {
        case "-": {
          this.Currentpage -= 1;
          // console.log(this.Currentpage);
          this.show();
          break;
        }
        case "+": {
          this.Currentpage += 1;
          this.show();
          // console.log(this.Currentpage);
          break;
        }
      }
      // console.log(this.msg)
    },
    show() {
      //数据拆分
      var page = this.Currentpage;
      this.totalpage = Math.ceil(this.flit.length / this.limit);
      start = (page - 1) * this.limit;
      end = start + this.limit;
      var sj = this.flit.slice(start, end);
      this.number = sj.length;
      this.bookmsg = sj;
    },
    get() {
      //获取数据
      var _this = this;
      $.getJSON("./getUserMessage?type=2", function (data) {
        _this.list = data;
        _this.flit = data;
        _this.show();
      });
    },
    search() {
      //关键词搜索
      var keywors = this.keywords;
      if (keywors === "" || keywors == null) {
        this.get();
        this.show();
      }
      return this.list.filter((item) => {
        if (item.bname.includes(keywors)) {
          return item;
        }
        if (item.date.includes(keywors)) {
          return item;
        }
        if (item.backdate.includes(keywors)) {
          return item;
        }
      });
    },
    backBook(index) {
      //还书
      var book = this.bookmsg[index];
      $.get(
          "./backBook?uname=" +
          book.uname +
          "&bname=" +
          book.bname +
          "&happentime=" +
          book.date,
          function (data) {
            if (data === "1") {
              alert("还书成功！");
              window.location.reload(true);
            } else {
              alert("还书失败！");
            }
          }
      );
    },
  },
  created() {
    this.get();
  },
};

//查看个人信息
var check = {
  template: "#check",
  data() {
    return {
      user: {},
    };
  },
  methods: {
    get() {
      //获取个人信息
      var _this = this;
      $.getJSON("./getUserMessage?type=1", function (data) {
        // console.log(data)
        _this.user = data[0];
      });
    },
  },
  created() {
    this.get();
  },
};

//图书查看
var book = {
  template: "#book",
  data() {
    return {
      username: "",
      book: {},
      list: [],
      keywords: "",
      Currentpage: 1,
      number: "",
    };
  },
  methods: {
    currentpage(val) {
      //计算当前页数
      if (
          (this.Currentpage == 1 && val == "-") ||
          (this.number < 8 && val == "+")
      ) {
        return;
      }
      switch (
          val //判断页数变化
          ) {
        case "-": {
          //当前页数减少
          this.Currentpage -= 1;
          this.get();
          break;
        }
        case "+": {
          //当前页数增加
          this.Currentpage += 1;
          this.get();
          break;
        }
      }
    },
    get() {
      //获取数据
      var _this = this;
      var page = _this.Currentpage;
      var keyword = _this.keywords;
      if (keyword.trim() == "") {
        $.getJSON("./getBooks?page=" + page, function (data) {
          _this.list = data;
          _this.number = data.length;
        });
      } else {
        $.getJSON(
            "./search?type=book&page=" + page + "&keyword=" + keyword,
            function (data) {
              _this.list = data;
              _this.number = data.length;
            }
        );
      }
    },
    showbook(index) {
      //展示书籍信息
      this.book = this.list[index];
    },
    borrowBook(index) {
      //借书
      var book = this.list[index];
      $.get(
          "./borrowBook?bname=" + book.bname + "&uname=" + this.username,
          function (data) {
            if (data == "1") {
              alert("借阅成功！");
              window.location.reload(true);
            } else {
              alert("借阅失败！");
            }
          }
      );
    },
  },
  created() {
    this.get();
    var _this = this;
    $.get("./getName?type=user", {}, function (data) {
      //获取用户名
      _this.username = data;
    });
  },
};
var routerObj = new VueRouter({
  //注册路由
  routes: [
    { path: "/", component: check },
    { path: "/msg", component: msg },
    { path: "/check", component: check },
    { path: "/book", component: book },
  ],
});

function changeUser() {
  //修改用户信息
  $("#changuser").ajaxSubmit(function (message) {
    console.log(message);
    if (message === "1") {
      alert("修改成功！");
      window.location.reload(true);
    } else {
      alert("修改失败！");
    }
  });
  return false;
}

function checkBook() {
    //用于检测添加的图书名字是否重复
    var bname = document.getElementById("bname").value;
    $.get("./checkBook?bname=" + bname, function (str) {
        if (str === "1") {
            document.getElementById("tishi").innerHTML = //添加提示信息
                "<font color='red'>图书已存在!</font>";
        } else {
            document.getElementById("tishi").innerHTML = "";
        }
    });
}

function checkBook1() {
    //用于检测更改的图书名是否重复
    var bname = document.getElementById("recipient-name").value;
    var oldbname = document.getElementById("oldbname").value;
    if (bname != oldbname) {
        $.get("./checkBook?bname=" + bname, function (str) {
            if (str === "1") {
                document.getElementById("tishi1").innerHTML = //添加提示信息
                    "<font color='red'>图书已存在!</font>";
            } else {
                document.getElementById("tishi1").innerHTML = "";
            }
        });
    } else {
        document.getElementById("tishi1").innerHTML = "";
    }
}

//借阅信息
var checkjy = {
    template: "#checkjy",
    data() {
        return {
            list: [], //数据存储
            keywords: "", //搜索关键词
            Currentpage: 1, //当前页数
            number: "", //当前页数据量
        };
    },
    methods: {
        calculate(index) {
            //比较当前时间和应还书时间
            var date1 = this.list[index].backdate;
            var oDate1 = new Date(date1); //应还书时间
            var oDate2 = new Date(); //当前时间
            if (oDate1.getTime() > oDate2.getTime()) {
                //未到时间
                return "table-light";
            } else if (oDate1.getTime() <= oDate2.getTime()) {
                //到达还书时间
                return "table-danger";
            }
        },
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
            //获取借阅数据
            var _this = this;
            var page = _this.Currentpage;
            var keyword = _this.keywords;
            if (keyword.trim() == "") {
                $.getJSON("./getLendData?page=" + page, function (data) {
                    _this.list = data;
                    _this.number = data.length;
                });
            } else {
                $.getJSON(
                    "./search?type=lend&page=" + page + "&keyword=" + keyword,
                    function (data) {
                        _this.list = data;
                        _this.number = data.length;
                    }
                );
            }
        },
        search() {
            //关键词搜索
        },
    },
    created() {
        this.get();
    },
};

//读者信息
var dzmsg = {
    template: "#dzmsg",
    data() {
        return {
            list: [], //存储数据
            keywords: "", //关键词
            Currentpage: 1, //当前页数
            user: {}, //用户信息存储
            number: "", //数据数量
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
            switch (val) {
                case "-": {
                    this.Currentpage -= 1;
                    this.get();
                    break;
                }
                case "+": {
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
                $.getJSON("./getUsers?page=" + page, function (data) {
                    _this.list = data;
                    _this.number = data.length;
                });
            } else {
                $.getJSON(
                    "./search?type=user&page=" + page + "&keyword=" + keyword,
                    function (data) {
                        _this.list = data;
                        _this.number = data.length;
                    }
                );
            }
        },
        showdz(index) {
            this.user = this.list[index];
        },
    },
    created() {
        this.get();
    },
};

//图书管理
var magabook = {
    template: "#magabook",
    data() {
        return {
            list: [], //数据存储
            keywords: "", //关键词
            Currentpage: 1, //当前页
            book: {}, //当前书籍信息
            number: "", //数据数量
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
            switch (val) {
                case "-": {
                    this.Currentpage -= 1;
                    this.get();
                    break;
                }
                case "+": {
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
            //展示当前数据信息
            this.book = this.list[index];
        },
        deleteBook(index) {
            //删除书籍
            var bname = this.list[index].bname;
            $.get("./deleteBook?bname=" + bname, function (data) {
                if (data === "1") {
                    alert("删除成功！");
                    window.location.reload(true);
                } else {
                    alert("图书存在借阅记录！");
                }
            });
        },
    },
    created() {
        this.get();
    },
};

//添加图书
var addbook = {
    template: "#addbook",
    data() {
        return {};
    },
    methods: {},
};

var routerObj = new VueRouter({
    //注册页面路由
    routes: [
        { path: "", component: checkjy },
        { path: "/dzmsg", component: dzmsg },
        { path: "/checkjy", component: checkjy },
        { path: "/magabook", component: magabook },
        { path: "/addbook", component: addbook },
    ],
});
function addBook() {
    //添加图书
    $("#book").ajaxSubmit(function (message) {
        if (message === "1") {
            alert("添加成功！");
            window.location.reload(true);
        } else {
            alert("添加失败！请仔细检查数据格式是否正确！");
        }
    });
    return false;
}

function changeBook() {
    //修改图书信息
    $("#changebook").ajaxSubmit(function (message) {
        if (message === "1") {
            alert("修改成功！");
            window.location.reload(true);
        } else {
            alert("修改失败！");
        }
    });
    return false;
}

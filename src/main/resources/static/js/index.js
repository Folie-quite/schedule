var toMonth;
var localStorage = window.localStorage;
var userId = localStorage.getItem('userId');
var datas;

var Calendar = function(t) {
	this.divId = t.RenderID ? t.RenderID : '[data-render="calendar"]', this.DaysOfWeek = t.DaysOfWeek ? t
		.DaysOfWeek : ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"], this.Months = t.Months ? t.Months : ["Jan",
			"Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
		];
	var e = new Date;
	this.CurrentMonth = e.getMonth(), this.CurrentYear = e.getFullYear();
	var r = t.Format;
	this.f = "string" == typeof r ? r.charAt(0).toUpperCase() : "M"

};
Calendar.prototype.nextMonth = function() {
	11 == this.CurrentMonth ? (this.CurrentMonth = 0, this.CurrentYear = this.CurrentYear + 1) : this.CurrentMonth =
		this.CurrentMonth + 1, this.divId = '[data-active="false"] .render', this.showCurrent()
}, Calendar.prototype.prevMonth = function() {
	0 == this.CurrentMonth ? (this.CurrentMonth = 11, this.CurrentYear = this.CurrentYear - 1) : this.CurrentMonth =
		this.CurrentMonth - 1, this.divId = '[data-active="false"] .render', this.showCurrent()
}, Calendar.prototype.previousYear = function() {
	this.CurrentYear = this.CurrentYear - 1, this.showCurrent()
}, Calendar.prototype.nextYear = function() {
	this.CurrentYear = this.CurrentYear + 1, this.showCurrent()
}, Calendar.prototype.showCurrent = function() {
	this.Calendar(this.CurrentYear, this.CurrentMonth)
}, Calendar.prototype.checkActive = function() {
	1 == document.querySelector(".months").getAttribute("class").includes("active") ? document.querySelector(
		".months").setAttribute("class", "months") : document.querySelector(".months").setAttribute("class",
		"months active"), "true" == document.querySelector(".month-a").getAttribute("data-active") ? (document
		.querySelector(".month-a").setAttribute("data-active", !1), document.querySelector(".month-b")
		.setAttribute("data-active", !0)) : (document.querySelector(".month-a").setAttribute("data-active", !0),
		document.querySelector(".month-b").setAttribute("data-active", !1)), setTimeout(function() {
		document.querySelector(".calendar .header").setAttribute("class", "header active")
	}, 200), document.querySelector("body").setAttribute("data-theme", this.Months[document.querySelector(
		'[data-active="true"] .render').getAttribute("data-month")].toLowerCase())

	initialize((this.CurrentMonth + 1), this.CurrentYear);
	setTimeout(function() {
		for (let j = 1; j < 33; j++) {
			addBob(j);
		}
		for (let data of datas) {
			$("#my" + data.day).addClass("studied");
		}
	}, 1000);
}, Calendar.prototype.Calendar = function(t, e) {
	"number" == typeof t && (this.CurrentYear = t), "number" == typeof t && (this.CurrentMonth = e);
	var date = new Date();
	var r = date.getDate(),
		n = date.getMonth(),
		a = date.getFullYear(),
		o = new Date(t, e, 1).getDay(),
		i = new Date(t, e + 1, 0).getDate(),
		u = 0 == e ? new Date(t - 1, 11, 0).getDate() : new Date(t, e, 0).getDate(),
		s = "<span>" + this.Months[e] + " &nbsp; " + t + "</span>",
		d = '<div class="table">';
	d += '<div class="row head">';
	for (var c = 0; c < 7; c++) d += '<div class="cell">' + this.DaysOfWeek[c] + "</div>";
	d += "</div>";
	for (var h, l = dm = "M" == this.f ? 1 : 0 == o ? -5 : 2, v = (c = 0, 0); v < 6; v++) {
		d += '<div class="row">';
		for (var m = 0; m < 7; m++) {
			if ((h = c + dm - o) < 1) d += '<div class="cell disable">' + (u - o + l++) + "</div>";
			else if (h > i) d += '<div class="cell disable">' + l++ + "</div>";
			else {
				//增加了点击控制方法
				d += '<div onclick="check(' + h + ')" value="' + a + '-' + (this.CurrentMonth + 1) + '-' + h +
					'" id="my' + h + '"class="cell bg' + (r == h && this.CurrentMonth == n && this.CurrentYear ==
						a ? " active" : "") + '"><span>' + h + "</span></div>", l = 1
			}
			c % 7 == 6 && h >= i && (v = 10), c++
		}
		d += "</div>"
	}
	d += "</div>", document.querySelector('[data-render="month-year"]').innerHTML = s, document.querySelector(this
		.divId).innerHTML = d, document.querySelector(this.divId).setAttribute("data-date", this.Months[e] +
		" - " + t), document.querySelector(this.divId).setAttribute("data-month", e)
}, window.onload = function() {
	var t = new Calendar({
		RenderID: ".render-a",
		Format: "M"
	});
	t.showCurrent(), t.checkActive();
	var e = document.querySelectorAll(".header [data-action]");
	for (i = 0; i < e.length; i++) e[i].onclick = function() {
		if (document.querySelector(".calendar .header").setAttribute("class", "header"), "true" == document
			.querySelector(".months").getAttribute("data-loading")) return document.querySelector(
			".calendar .header").setAttribute("class", "header active"), !1;
		var e;
		document.querySelector(".months").setAttribute("data-loading", "true"), this.getAttribute("data-action")
			.includes("prev") ? (t.prevMonth(), e = "left") : (t.nextMonth(), e = "right"), t.checkActive(),
			document.querySelector(".months").setAttribute("data-flow", e), document.querySelector(
				'.month[data-active="true"]').addEventListener("webkitTransitionEnd", function() {
				document.querySelector(".months").removeAttribute("data-loading")
			}), document.querySelector('.month[data-active="true"]').addEventListener("transitionend",
				function() {
					document.querySelector(".months").removeAttribute("data-loading")
				})
	}

};


function initialize(month, year) {
	$.ajax({
		type: "get", //请求方式
		url: "/getStudyTimes", //请求地址
		dataType: 'json',
		data: {
			'month': month,
			'year': year,
			'userId': userId
		}, //数据，json字符串
		success: function(data) { //请求成功
			datas = data.data;
		}
	});
	
	$.ajax({
		type : "get",
		url : "/getClassHours",
		dataType:'json',
		data : {
				'userId':userId
				},
		success : function(data) {
			$("#myHours").html(data.data);
		}
	});
	
	
}

function addBob(id) {

	$("#my" + id).hover(function() {

		let turn = true;
		for (let data of datas) {
			if(data.day == id){
				$("#jumpWindow").html("Hour: " + data.hour +",<br> Student: " + data.student + 
									",<br> Teacher: " + data.teacher + ",<br>" + data.specificTime);
				turn = false;					
			}
		}
		
		if(turn){
			$("#jumpWindow").html("Sorry, there were any classes this day!");
		}
		
		show_jumpWindow(this);
	}, function() {
		show_jumpWindow();
	});

	function show_jumpWindow(f) {
		var d = $("#jumpWindow");
		if (!f) {
			d.fadeOut(0);
		} else {
			var c = $(f);
			var e = c.offset();
			var a = e.left;
			var b = e.top + 44;
			d.css({
				left: a + "px",
				top: b + "px"
			}).show();
		}
	}
}

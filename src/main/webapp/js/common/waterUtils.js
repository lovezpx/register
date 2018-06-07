//IE中trim函数的兼容性
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}

/**
 * 执行一定规则下的ajax
 * 
 * @param url
 * @param method
 * @param func
 */
function executeAjaxUrlForResult(url, setting, done, fail, always) {
	setting.url = url;
	setting.traditional = true;
	$.ajax(setting).done(function(result) {
		if (undefined != done) {
			done(result);
		}
	}).fail(function(jqXHR, textStatus) {
		if (undefined != fail) {
			fail(jqXHR, textStatus);
		}
	}).always(function() {
		if (undefined != always) {
			always();
		}
	})

}

/**
 * js获取url参数
 * 
 * @param name
 * @returns
 */
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURI(r[2]);
	return null;
}

/**
 * 2015-10-10 00:00:00 转date
 * 
 * @param str
 */
function toDate(str) {
	str = str.replace(/-/g, "/");
	var oDate1 = new Date(str);
	return oDate1;
}

/**
 * 计算传入日期与num求和后得到新日期
 * 
 * @param num
 * @param date
 * @returns {Date}
 */
function dateAdd(num, date) {
	var v = date.valueOf();
	v += num;
	return new Date(v);
}

// 把时间转换成字符串
function timeStamp2String(datetime, formate) {
	var year = datetime.getFullYear();
	var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
	var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
	var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
	var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
	var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
	var str = formate;
	str = str.replace("yyyy", year);
	str = str.replace("MM", month);
	str = str.replace("dd", date);
	str = str.replace("HH", hour);
	str = str.replace("mm", minute);
	str = str.replace("ss", second);
	return str;
}

/**
 * js获取当前时间
 * 
 * @returns {String}
 */
function getCurentDate() {
	var now = new Date();
	return getDate(now);

}

function getDate(now) {
	var year = now.getFullYear(); // 年
	var month = now.getMonth() + 1; // 月
	var day = now.getDate(); // 日

	var clock = year + "-";

	if (month < 10)
		clock += "0";

	clock += month + "-";

	if (day < 10)
		clock += "0";

	clock += day;

	return (clock);
}

function addDate(num) {
	var now = new Date();
	var v = now.valueOf();
	v = v + num;
	return getDate(new Date(v));
}

/**
 * 获取时间间隔
 * 
 * @param unit
 *            时间基数 minute, hour, day, month
 * @param inv
 *            时间间隔数
 * @returns {Number}
 */
function getMillisecondByInv(unit, inv) {
	var base;
	if (unit == 'minute') {
		base = 60 * 1000;
	} else if (unit == 'hour') {
		base = 60 * 60 * 1000;
	} else if (unit == 'day') {
		base = 24 * 60 * 60 * 1000;
	} else if (unit == 'month') {
		base = 31 * 24 * 60 * 60 * 1000;
	}
	return base * parseInt(inv);
}

var easyuiGrid = {
	title : '',
	nowrap : true,
	border : true,
	striped : true,
	sortName : 'id',
	sortOrder : 'desc',
	remoteSort : true,
	editable : false,
	singleSelect : true,
	idField : 'name',
	loadMsg : '读取数据中',
	frozenColumns : [[]],
	fit : true,
	columns : [[{
		field : 'name',
		title : '方法中文名',
		width : 100
	}, {
		field : 'packageName',
		title : '包名',
		width : 250
	}, {
		field : 'methodName',
		title : '方法名',
		width : 150
	}, {
		field : 'returnName',
		title : '返回名称',
		width : 100
	}, {
		field : 'type',
		title : '类型',
		width : 100
	}, {
		field : 'remark',
		title : '注释',
		width : 100
	}]],
	pagination : false,
	rownumbers : false,
	pageList : [20, 40, 60, 80],
	pageSize : 20
};

// 获取配置
function getConfig(name) {
	var value;
	executeAjaxUrlForResult("action/configUniq/list", {
		data : {
			name : name
		},
		async : false
	}, function(data) {
		if (data.rows.length > 0) {
			value = data.rows[0].value;
		} else {
			value = null;
		}
	});
	return value;
}

function getConfigList(type) {
	var tempdata;
	// 初始化配置
	executeAjaxUrlForResult("action/configlist/list", {
		data : {
			type : type
		},
		async : false
	}, function(data) {
		tempdata = data.data;
	});
	return tempdata;
}

/**
 * 获取当前月的第一天
 */
function getMonthFirst(date) {
	date.setDate(1);
	return date;
}
/**
 * 获取当前月的最后一天
 */
function getMonthLast(date) {
	var currentMonth = date.getMonth();
	var nextMonth = ++currentMonth;
	var nextMonthFirstDay = new Date(date.getFullYear(), nextMonth, 1);
	var oneDay = 1000 * 60 * 60 * 24;
	return new Date(nextMonthFirstDay - oneDay);
}
var storageSkin = {
		storage_key : "MY_SCADA_SKIN",
		save : function(value) {
			localStorage.setItem(this.storage_key, value)
		},
		getSkin : function() {
			var skin_stg = localStorage.getItem(this.storage_key);
			if(null != skin_stg && skin_stg != ""){
				return skin_stg;
			}
			return "normal";
		},
		clear : function() {
			localStorage.removeItem(this.storage_key)
		}
	}
function DateAdd(interval, number, date) {
	/*
	 * --------------- DateAdd(interval,number,date) -----------------
	 * DateAdd(interval,number,date) 功能:实现VBScript的DateAdd功能.
	 * 参数:interval,字符串表达式，表示要添加的时间间隔. 参数:number,数值表达式，表示要添加的时间间隔的个数.
	 * 参数:date,时间对象. 返回:新的时间对象. var now = new Date(); var newDate = DateAdd( "d
	 * ",5,now); author:wanghr100(灰豆宝宝.net) update:2004-5-28 11:46
	 * --------------- DateAdd(interval,number,date) -----------------
	 */
	switch (interval) {
		case "y" : {
			date.setFullYear(date.getFullYear() + number);
			return date;
			break;
		}
		case "q" : {
			date.setMonth(date.getMonth() + number * 3);
			return date;
			break;
		}
		case "m" : {
			date.setMonth(date.getMonth() + number);
			return date;
			break;
		}
		case "w" : {
			date.setDate(date.getDate() + number * 7);
			return date;
			break;
		}
		case "d" : {
			date.setDate(date.getDate() + number);
			return date;
			break;
		}
		case "h" : {
			date.setHours(date.getHours() + number);
			return date;
			break;
		}
		case "m" : {
			date.setMinutes(date.getMinutes() + number);
			return date;
			break;
		}
		case "s" : {
			date.setSeconds(date.getSeconds() + number);
			return date;
			break;
		}
		default : {
			date.setDate(date.getDate() + number);
			return date;
			break;
		}
	}
}

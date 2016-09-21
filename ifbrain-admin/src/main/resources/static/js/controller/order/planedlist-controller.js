var PlanedlistController = function () {
    var _dataTable = null;

    var showTable = function () {
    	var url = Web.contextPath +"/order/planedorder";
    	
    	$.get(url, function (data) {
        _dataTable = $('#table').DataTable({
            language: {
                url: Web.contextPath + "/js/lib/datatables/Chinese.json"
            },
            lengthChange: false,
            searching: true,
            ordering: true,
            processing: true,
            bRetrieve: true,
            autoWidth: true,
            data : JSOG.decode(data),
            columns: [
                {data: "orderNumber"},
                {data: "createTime"},
                {data: "traveler"},
                {data: "startDate"},
                {data: "endDate"},
                {data: "purposes"},
                {data: "orderStatus"}
            ] ,
            columnDefs: [
                {
                    render: function (data, type, row) {
                        var link = "";
                        var orderStatus = row.orderStatus;
                        var id = row.id;
                        if (orderStatus == 0) {
                            link = "<a href='/trips'>" + row.orderNumber + "</a>"
                        }
                        else if (orderStatus == 1) {
                            link = row.orderNumber;
//            			alert("您的订单"+row.orderNumber+"正在规划中");
                        }
                        else if (orderStatus == 2) {
                            link = "<a href='/order/" + id + "/planedInfo'>" + row.orderNumber + "</a>";
                        }
                        else if (orderStatus == 3) {
                            link = "<a href='/order/" + id + "/planedInfo'>" + row.orderNumber + "</a>";
                        }
                        else if (orderStatus == 4) {
                            link = "<a href='/order/" + id + "/planedInfo'>" + row.orderNumber + "</a>";
                        }
                        else if (orderStatus == 5) {
                            link = "<a href='/order/" + id + "/planedInfo'>" + row.orderNumber + "</a>";
                        }
                        else if (orderStatus == 6) {
                            link = row.orderNumber;
                            alert("您的订单" + row.orderNumber + "正在规划中");
                        }
                        return link;
                    },
                    targets: 0
                },
                {
                    render: function (data) {
                        return data.firstName + " " + data.lastName;
                    },
                    targets: 2
                },
                {
                    render: function (data) {
                        var orderStatus = "";
                        if (data == 0) {
                            orderStatus = "未提交";
                        }
                        else if (data == 1) {
                            orderStatus = "已提交";
                        }
                        else if (data == 2) {
                            orderStatus = "已规划";
                        }
                        else if (data == 3) {
                            orderStatus = "已结算";
                        }
                        else if (data == 4) {
                            orderStatus = "已过期";
                        }
                        else if (data == 5) {
                            orderStatus = "已完成";
                        }
                        else if (data == 6) {
                            orderStatus = "重新规划";
                        }
                        return orderStatus;
                    },
                    targets: 6
                }
            ]
        });
          });
    };
    return {
        init: function () {
            showTable();
        }
    };
}();

$(function () {
    PlanedlistController.init();
});

function toOnclik() {
    var orderStatus = row.orderStatus;
    if (orderStatus == 1 || orderStatus == 6) {
        alert("您的订单正在规划中");
    }
}
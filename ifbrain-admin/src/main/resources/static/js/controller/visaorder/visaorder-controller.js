var VisaOrderController = function () {
    var _dataTable = null;

    var showTable = function () {

        var orderNumber = $('#orderNumber').val();

        var orderStatus = $('#orderStatus').val();

        _dataTable = $("#table").DataTable({
            language: {
                url: "/js/lib/datatables/Chinese.json"
            },
            lengthChange: false,
            searching: false,
            ordering: true,
            processing: true,
            serverSide: true,
            ajax: "/visa/search?orderNumber=" + orderNumber + "&orderStatus=" + orderStatus,
            columns: [{
                data: "createTime",
            }, {
                data: "orderNumber",
            }, {
                data: "taobaoOrderNumber",
                orderable: false
            }, {
                data: "orderStatus"
            }, {
                data: null,
                orderable: false
            }],
            columnDefs: [
                {
                    render: function (data, type, row) {
                        return link = "<a href='/visa/" + row.id + "/visaInfo'>" + row.orderNumber + "</a>";

                    },
                    targets: 1
                },
                {
                    render: function (data, type, row) {
                        var orderStatus = "";
                        if (data == 0) {
                            orderStatus = "未支付";
                        }
                        else if (data == 1) {
                            orderStatus = "已支付";
                        }
                        else if (data == 2) {
                            orderStatus = "资料已提交";
                        }
                        else if (data == 3) {
                            orderStatus = "资料审核通过";
                        }
                        else if (data == 4) {
                            orderStatus = "预约成功";
                        }
                        // 该状态不再使用
                        else if (data == 5) {
                            orderStatus = "面试成功";
                        }
                        else if (data == 6) {
                            orderStatus = "签证申请成功";
                        }
                        // 该状态不再使用
                        else if (data == 7) {
                            orderStatus = "支付失败";
                        }
                        else if (data == 8) {
                            orderStatus = "资料审核失败";
                        }
                        // 该状态不再使用
                        else if (data == 9) {
                            orderStatus = "预约失败";
                        }
                        else if (data == 10) {
                            orderStatus = "签证申请失败";
                        } else if (data == 11) {
                            orderStatus = "已邮寄";
                        }
                        return orderStatus;
                    },
                    targets: 3
                },
                {
                    render: function (data, type, row) {
                        var orderStatus = row.orderStatus;
                        var link = "";
                        //已支付 录入淘宝信息
                        if (orderStatus == 1) {
                            link = "<a href='/visa/findById?orderId=" + row.id + "' style='color:blue'>" + "录入淘宝信息" + "</a>";
                        }
                        // 材料已提交 审核材料。 如果淘宝订单号未填写就进入录入淘宝订单号，如果填写就进入审核材料
                        else if (orderStatus == 2) {
                            if (row.taobaoOrderNumber != null) {
                                link = "<a href='/visaaudit/" + row.id + "/orders'" +"' style='color:blue'>" + "审核材料" + "</a>";
                            } else {
                                link = "<a href='/visa/findById?orderId=" + row.id + "' style='color:blue'>" + "录入淘宝信息" + "</a>";
                            }
                        }
                        // 资料审核通过 预约录入。
                        else if (orderStatus == 3) {
                            link = "<a href='/visa/" + row.id + "/interview'" + "' style='color:blue'>" + "预约录入" + "</a>";
                        }
                        // 预约成功 面试通过/不通过，通过则签证修改成功，否则签证申请失败
                        else if (orderStatus == 4) {
                            link = "<a href='/visa/" + row.id + "/interviewPass'" +"' style='color:blue'>" + "面试通过" + "</a>";
                            link += "   ";
                            link +="<a href='/visa/" + row.id + "/interviewFail'" +"' style='color:blue'>" + "不通过" + "</a>";
                        }
                        // 签证申请成功 邮寄签证
                        else if (orderStatus == 6) {
                            link = "<a href='/visa/" + row.id + "/postAddress'" +"' style='color:blue'>" + "邮寄签证" + "</a>";
                        } else {
                            link = "";
                        }

                        return link;
                    },
                    targets: 4
                }
            ]
        });
    };
    return {
        init: function () {
            showTable();
        },
        search: function () {
            _dataTable.destroy();
            showTable();
        }
    };
}();
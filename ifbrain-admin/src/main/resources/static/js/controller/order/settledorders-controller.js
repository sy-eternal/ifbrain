var SettledordersController = function () {
    var _dataTable = null;

    var showTable = function () {
    	var url = Web.contextPath +"/order/settledorders";
    	
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
                {data:""},
                {data: "orderStatus"}
            ] ,
            columnDefs: [
                {
                    render: function (data, type, row) {
                        var link = "";
                        var id = row.id;
                            link = "<a href='/order/" + id + "/settledInfo'>" + row.orderNumber + "</a>";
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
                            var link = "";
                        return link;
                    },
                    targets: 6
                },
                {
                    render: function (data) {
                            var orderStatus = "已结算";
                        return orderStatus;
                    },
                    targets: 7
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
	SettledordersController.init();
});


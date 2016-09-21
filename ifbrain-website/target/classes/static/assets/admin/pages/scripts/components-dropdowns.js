var ComponentsDropdowns = function () {

    var handleSelect2 = function () {

        function format(state) {
            if (!state.id) return state.text; // optgroup
            return "<img class='flag' src='" + Metronic.getGlobalImgPath() + "flags/" + state.id.toLowerCase() + ".png'/>&nbsp;&nbsp;" + state.text;
        }
        $("#select2_sample4").select2({
            placeholder: "Select a Country",
            allowClear: true,
            formatResult: format,
            formatSelection: format,
            escapeMarkup: function (m) {
                return m;
            }
        });
        
        var startA = [];
        var s=$("input[name='startA']");
        for(var i=0;i<s.length;i++)
        {
        	startA.push(s[i].value);
        }
        $("#select2_1").select2({
            tags: startA
        });
        var accompanyT = [];
        var s=$("input[name='accompanyT']");
        for(var i=0;i<s.length;i++)
        {
        	accompanyT.push(s[i].value);
        }
        $("#select2_2").select2({
            tags: accompanyT
        });
        var themeA = [];
        var s=$("input[name='themeA']");
        for(var i=0;i<s.length;i++)
        {
        	themeA.push(s[i].value);
        }
        $("#select2_3").select2({
            tags: themeA
        });
        $('#select2_4').select2({
            placeholder: "从列表中选择一个地区",
            allowClear: true
        });
        var inCityA = [];
        var s=$("input[name='inCityA']");
        for(var i=0;i<s.length;i++)
        {
        	inCityA.push(s[i].value);
        }
        $("#select2_5").select2({
            tags: inCityA
        });
        $("#select2_6").select2({
            placeholder: "添加交通工具（可多选）",
            allowClear: true
        });
        $('#select2_7').select2({
            placeholder: "从列表中选择一个地区",
            allowClear: true
        });
        $('#backCity').select2({
            placeholder: "从列表中选择一个城市",
            allowClear: true
        });
        $('#select2_8').select2({
            placeholder: "从列表中选择一个城市",
            allowClear: true
        });


        function movieFormatResult(movie) {
            var markup = "<table class='movie-result'><tr>";
            if (movie.posters !== undefined && movie.posters.thumbnail !== undefined) {
                markup += "<td valign='top'><img src='" + movie.posters.thumbnail + "'/></td>";
            }
            markup += "<td valign='top'><h5>" + movie.title + "</h5>";
            if (movie.critics_consensus !== undefined) {
                markup += "<div class='movie-synopsis'>" + movie.critics_consensus + "</div>";
            } else if (movie.synopsis !== undefined) {
                markup += "<div class='movie-synopsis'>" + movie.synopsis + "</div>";
            }
            markup += "</td></tr></table>"
            return markup;
        }

        function movieFormatSelection(movie) {
            return movie.title;
        }

        $("#select2_sample6").select2({
            placeholder: "Search for a movie",
            minimumInputLength: 1,
            ajax: { // instead of writing the function to execute the request we use Select2's convenient helper
                url: "http://api.rottentomatoes.com/api/public/v1.0/movies.json",
                dataType: 'jsonp',
                data: function (term, page) {
                    return {
                        q: term, // search term
                        page_limit: 10,
                        apikey: "ju6z9mjyajq2djue3gbvv26t" // please do not use so this example keeps working
                    };
                },
                results: function (data, page) { // parse the results into the format expected by Select2.
                    // since we are using custom formatting functions we do not need to alter remote JSON data
                    return {
                        results: data.movies
                    };
                }
            },
            initSelection: function (element, callback) {
                // the input tag has a value attribute preloaded that points to a preselected movie's id
                // this function resolves that id attribute to an object that select2 can render
                // using its formatResult renderer - that way the movie name is shown preselected
                var id = $(element).val();
                if (id !== "") {
                    $.ajax("http://api.rottentomatoes.com/api/public/v1.0/movies/" + id + ".json", {
                        data: {
                            apikey: "ju6z9mjyajq2djue3gbvv26t"
                        },
                        dataType: "jsonp"
                    }).done(function (data) {
                        callback(data);
                    });
                }
            },
            formatResult: movieFormatResult, // omitted for brevity, see the source of this page
            formatSelection: movieFormatSelection, // omitted for brevity, see the source of this page
            dropdownCssClass: "bigdrop", // apply css that makes the dropdown taller
            escapeMarkup: function (m) {
                return m;
            } // we do not want to escape markup since we are displaying html in results
        });
    }

    var handleSelect2Modal = function () {

        $('#select2_sample_modal_1').select2({
            placeholder: "Select an option",
            allowClear: true
        });

        $('#select2_sample_modal_2').select2({
            placeholder: "Select a State",
            allowClear: true
        });

        $("#select2_sample_modal_3").select2({
            allowClear: true,
            minimumInputLength: 1,
            query: function (query) {
                var data = {
                    results: []
                }, i, j, s;
                for (i = 1; i < 5; i++) {
                    s = "";
                    for (j = 0; j < i; j++) {
                        s = s + query.term;
                    }
                    data.results.push({
                        id: query.term + i,
                        text: s
                    });
                }
                query.callback(data);
            }
        });

        function format(state) {
            if (!state.id) return state.text; // optgroup
            return "<img class='flag' src='" + Metronic.getGlobalImgPath() + "flags/" + state.id.toLowerCase() + ".png'/>&nbsp;&nbsp;" + state.text;
        }
        $("#select2_sample_modal_4").select2({
            allowClear: true,
            formatResult: format,
            formatSelection: format,
            escapeMarkup: function (m) {
                return m;
            }
        });

        $("#select2_sample_modal_5").select2({
            tags: ["red", "green", "blue", "yellow", "pink"]
        });


        function movieFormatResult(movie) {
            var markup = "<table class='movie-result'><tr>";
            if (movie.posters !== undefined && movie.posters.thumbnail !== undefined) {
                markup += "<td valign='top'><img src='" + movie.posters.thumbnail + "'/></td>";
            }
            markup += "<td valign='top'><h5>" + movie.title + "</h5>";
            if (movie.critics_consensus !== undefined) {
                markup += "<div class='movie-synopsis'>" + movie.critics_consensus + "</div>";
            } else if (movie.synopsis !== undefined) {
                markup += "<div class='movie-synopsis'>" + movie.synopsis + "</div>";
            }
            markup += "</td></tr></table>"
            return markup;
        }

        function movieFormatSelection(movie) {
            return movie.title;
        }

        $("#select2_sample_modal_6").select2({
            placeholder: "Search for a movie",
            minimumInputLength: 1,
            ajax: { // instead of writing the function to execute the request we use Select2's convenient helper
                url: "http://api.rottentomatoes.com/api/public/v1.0/movies.json",
                dataType: 'jsonp',
                data: function (term, page) {
                    return {
                        q: term, // search term
                        page_limit: 10,
                        apikey: "ju6z9mjyajq2djue3gbvv26t" // please do not use so this example keeps working
                    };
                },
                results: function (data, page) { // parse the results into the format expected by Select2.
                    // since we are using custom formatting functions we do not need to alter remote JSON data
                    return {
                        results: data.movies
                    };
                }
            },
            initSelection: function (element, callback) {
                // the input tag has a value attribute preloaded that points to a preselected movie's id
                // this function resolves that id attribute to an object that select2 can render
                // using its formatResult renderer - that way the movie name is shown preselected
                var id = $(element).val();
                if (id !== "") {
                    $.ajax("http://api.rottentomatoes.com/api/public/v1.0/movies/" + id + ".json", {
                        data: {
                            apikey: "ju6z9mjyajq2djue3gbvv26t"
                        },
                        dataType: "jsonp"
                    }).done(function (data) {
                        callback(data);
                    });
                }
            },
            formatResult: movieFormatResult, // omitted for brevity, see the source of this page
            formatSelection: movieFormatSelection, // omitted for brevity, see the source of this page
            dropdownCssClass: "bigdrop", // apply css that makes the dropdown taller
            escapeMarkup: function (m) {
                return m;
            } // we do not want to escape markup since we are displaying html in results
        });
    }

    var handleBootstrapSelect = function() {
        $('.bs-select').selectpicker({
            iconBase: 'fa',
            tickIcon: 'fa-check'
        });
    }

    var handleMultiSelect = function () {
        $('#my_multi_select1').multiSelect();
        $('#my_multi_select2').multiSelect({
            selectableOptgroup: true
        });
    }

    return {
        //main function to initiate the module
        init: function () {            
            handleSelect2();
            handleSelect2Modal();
            handleMultiSelect();
            handleBootstrapSelect();
        }
    };

}();
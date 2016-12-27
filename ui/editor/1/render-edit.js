$(document).ready(function () {
    edit();
    render();
});


var edit = function () {
    $('#edit').click(function () {
        var title = $('.columns').children('.container-title')[0].innerHTML;
        $('.columns').children('.container-title').replaceWith("<li class='container-title'><textarea class='edit'></textarea></li>");
        $('.columns').children('.container-title').children('textarea.edit').text(title);

        var text = $('.columns').children('.container-text')[0].innerHTML;
        $('.columns').children('.container-text').replaceWith("<li class='container-text'><textarea class='edit'></textarea></li>");
        $('.columns').children('.container-text').children('textarea.edit').text(text);

    });
};

var render = function () {
    $('#render').click(function () {
        var inputTitle = $('.columns').children('.container-title').children('textarea.edit').val();
        // $('.columns').children('.container-title').children('textarea.edit').attr("data", inputTitle);
        $('.columns').children('.container-title').replaceWith("<li class='container-title'>" + inputTitle + "</li>");

        var inputText = $('.columns').children('.container-text').children('textarea.edit').val();
        $('.columns').children('.container-text').replaceWith("<li class='container-text'>" + inputText + "</li>");
    });
};

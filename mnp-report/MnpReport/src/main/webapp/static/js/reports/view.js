//<![CDATA[
var disableSubmitTypeUi = function() {
    setTimeout(function() {
        $('select').attr('disabled', 'disabled');
        $('input').attr('disabled', 'disabled');
    }, 1);
};
var enableSubmitTypeUi = function() {
    $('select').removeAttr('disabled');
    $('input').removeAttr('disabled');
};
$('#viewReportForm').submit(function(event) {
    var result = false;
    $(this).formvalidate({
        messageParent: 'div', // CSS selector of parent element of message (success or failure) messages
        messageElement: '<small />', // Wrap success and failure messages inside this element
        messageFailureClass: 'error',
        onSuccess: function(form) {
            disableSubmitTypeUi();
            result = true;
        },
        localization: localization
    });
    return result;
});
$(function() {
    var $viewReportContainer = $("#viewReportContainer");
    var $birtTags = $viewReportContainer.find('title, meta, style, script');
    if ($birtTags.length > 0) {
        $('a.export-btn').show();
        $birtTags.remove();
        $viewReportContainer.find('table:first').css('width', '');

        //remove first row if empty
        var tr = $viewReportContainer.find('tr:first, tr:last');
        if (tr.find('td').html().length === 0) {
            tr.remove();
        }
        $viewReportContainer.show();
    }
});
//]]>
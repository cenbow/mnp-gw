//<![CDATA[
$('#checkAllReport').click(function() {
    var $checkAllReportChkbox = $(this),
    $runReportNameChkbox = $('input[name="runReportName"]');
    $runReportNameChkbox
    .prop('checked', $checkAllReportChkbox.is(':checked'))
    .click(function() {
        if ($checkAllReportChkbox.is(':checked') && $runReportNameChkbox.is(':not(:checked)')) {
            $checkAllReportChkbox.prop('checked', false);
        } else if ($checkAllReportChkbox.is(':not(:checked)') && !$runReportNameChkbox.is(':not(:checked)')) {
            $checkAllReportChkbox.prop('checked', true);
        }
    });
});
$("form input[type=submit]").click(function() {
    var $input = $(this);
    $("input[type=submit]", $input.parents("form")).removeAttr("clicked");
    $input.attr("clicked", "true");
});
$('#runReportForm').submit(function(event) {
    var $form = $(this),
    $runReportNameValidate = $('#runReportNameValidate'),
    $submitBtn = $form.find('input[type="submit"][clicked=true]');
                
    if ($runReportNameValidate.length > 0 && $submitBtn.val() !== undefined && $submitBtn.val() !== 'List') {
        if ($('input[name="runReportName"]').is(':checked')) {
            $runReportNameValidate.hide();
        } else {
            $runReportNameValidate.show();
            return false;
        }
    }
                
    var result = false;
    $form.formvalidate({
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

var handleUpdateStatus = function() {
    var $errorStatus = $('#runReportContainer div.row:first'),
    $runReportStatusTable = $('#runReportStatusTable'),
    runReportStatus = 'Done';
                
    $.get('status.json', {
        reportMonth: $('input[name="reportMonth"]').val()
    })
    .done(function(data) {
        $errorStatus.hide();
        
        $runReportStatusTable
        .find('td[data-report-name]').each(function() {
            var $container = $(this),
            reportName = $container.attr('data-report-name'),
            tmpReportStatus = data[reportName].Status;
            
            if (tmpReportStatus !== undefined) {
                $container.html(tmpReportStatus);
                
                if (tmpReportStatus != 'Done') {
                    runReportStatus = tmpReportStatus;
                }
            }
        });
    })
    .fail(function() {
        runReportStatus = 'Error';
        $errorStatus.show();
    })
    .always(function() {
        if (runReportStatus == 'Error') {
            setTimeout(handleUpdateStatus, 5000);
        } else if (runReportStatus != 'Done') {
            setTimeout(handleUpdateStatus, 1000);
        }
    });
}

$(function() {
    if ($('#runReportContainer').length > 0) {
        handleUpdateStatus();
    }
});
//]]>
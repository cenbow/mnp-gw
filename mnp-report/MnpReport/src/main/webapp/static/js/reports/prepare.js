//<![CDATA[
$('#prepareReportForm').submit(function(event) {
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

var reportTimeoutId;
var handleProgressBar = function($container, data, reportStatus) {
    var $label = $container.find('div'),
    $bar = $container.find('span'),
    date = $container.data('date'),
    portType = $container.attr('data-port-type'),
    totalMsg = data[portType][date].TotalMsg,
    countListedMsg = 0,
    countSavedMsg = 0,
    status = '',
    percent = '0%',
    $runPrepareByDateBtn = $('button[name="runPrepareByDateBtn"][value="' + date + '"]');

    if (totalMsg > 0) {
        totalMsg *= 2;
        countListedMsg = data[portType][date].CountListedMsg===undefined ? 0 : data[portType][date].CountListedMsg,
        countSavedMsg = data[portType][date].CountSavedMsg===undefined ? 0 : data[portType][date].CountSavedMsg,
        status = data[portType][date].Status;
        
        $runPrepareByDateBtn.prop('disabled', status !== 'Done');
        if (status !== 'Done') {
            reportStatus = status;
        }
        status += ': ';
        percent = Math.round(countListedMsg*100/totalMsg + countSavedMsg*100/totalMsg).toString() + '%';
    } else {
        status = data[portType][date].Status===undefined ? '' : data[portType][date].Status;
        reportStatus = status;
        
        if (status !== 'Done' && status !== '') {
            status += ': ';
            $runPrepareByDateBtn.prop('disabled', true);
        } else if (totalMsg === 0) {
            status = 'No data: ';
            $runPrepareByDateBtn.prop('disabled', false);
        } else {
            status = '';
            $runPrepareByDateBtn.prop('disabled', false);
        }
        percent = '0%';
    }

    $label.html(status + percent);
    $bar.css('width', percent);
    return reportStatus;
};

var handleRunPrepareByDateBtn = function(e) {
    $this = $(this);
    $this.prop('disabled', true);
    if (reportTimeoutId !== undefined) {
        clearTimeout(reportTimeoutId);
    }
    
    $.post('submitByDate/' + $this.val())
    .done(function(result) {
        if (result !== 'Done') {
            alert('Error: ' +  result);
        }
    })
    .fail(function(result) {
        alert('Error ' + result.status + ': ' +  result.statusText);
    })
    .always(function() {
        handleUpdateStatus();
        $this.prop('disabled', false);
    });
    return false;
};

var handleUpdateStatus = function() {
    var $runPrepareBtn = $('#runPrepareBtn'),
    $forceEnableRunPrepareBtn = $('#forceEnableRunPrepareBtn'),
    $errorStatus = $('#prepareReportContainer div.row:first div.row:first'),
    $importReportStatusTable = $('#importReportStatusTable'),
    importReportStatus = 'Done';

    $.get('status.json', {
        reportMonth: $('input[name="reportMonth"]').val()
    })
    .done(function(data) {
        $errorStatus.hide();
        
        $importReportStatusTable
        .find('div[data-date]').each(function() {
            importReportStatus = handleProgressBar($(this), data, importReportStatus);
        });
        
        var isPrepareBtnEnabled = $('button[name="runPrepareByDateBtn"]:disabled').length > 0;
        $runPrepareBtn.prop('disabled', isPrepareBtnEnabled);
        $forceEnableRunPrepareBtn.prop('disabled', !isPrepareBtnEnabled);
        if (!isPrepareBtnEnabled === true) {
            $forceEnableRunPrepareBtn.hide();
        } else {
            $forceEnableRunPrepareBtn.show();
        }
    })
    .fail(function() {
        importReportStatus = 'Error';
        $errorStatus.show();
    })
    .always(function() {
        if (reportTimeoutId !== undefined) {
            clearTimeout(reportTimeoutId);
        }
        if (importReportStatus === 'Error') {
            reportTimeoutId = setTimeout(handleUpdateStatus, 5000);
        } else if (importReportStatus !== 'Done') {
            reportTimeoutId = setTimeout(handleUpdateStatus, 1000);
        } else {
            reportTimeoutId = setTimeout(handleUpdateStatus, 2000);
        }
    });
};

$(function() {
    if ($('#prepareReportContainer').length > 0) {
        handleUpdateStatus();
        $('button[name="runPrepareByDateBtn"]').click(handleRunPrepareByDateBtn);
        $('#forceEnableRunPrepareBtn').click(function() {
            if (reportTimeoutId !== undefined) {
                clearTimeout(reportTimeoutId);
            }
            $('#runPrepareBtn, button[name="runPrepareByDateBtn"]:disabled').prop('disabled', false);
            $(this).prop('disabled', true);
            return false;
        });
    }
});
//]]>
//<![CDATA[
var disableSubmitTypeUi = function() {
    setTimeout(function() {
        $('select, input, button').prop('disabled', true);
    }, 1);
}
var enableSubmitTypeUi = function() {
    $('select, input, button').prop('disabled', false);
}
var localization = {
    en: {
        failure: {
            date: '{0} format must be in {2}.'
        }
    }
}
//]]>
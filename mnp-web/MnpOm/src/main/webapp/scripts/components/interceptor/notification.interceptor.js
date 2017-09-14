 'use strict';

angular.module('mnpOmApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-mnpOmApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-mnpOmApp-params')});
                }
                return response;
            }
        };
    });

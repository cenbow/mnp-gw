'use strict';

angular.module('mnpOmApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });



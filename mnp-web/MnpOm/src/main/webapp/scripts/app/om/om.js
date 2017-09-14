'use strict';

angular.module('mnpOmApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('om', {
                //abstract: true,
                parent: 'site',
                url: '/om',
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/om/om.html'
                    }
                },
            });
    });

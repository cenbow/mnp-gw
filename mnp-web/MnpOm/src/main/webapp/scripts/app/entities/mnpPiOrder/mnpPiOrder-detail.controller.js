'use strict';

angular.module('mnpOmApp')
    .controller('MnpPiOrderDetailController', function ($scope, $rootScope, $stateParams, entity, MnpPiOrder) {
        $scope.mnpPiOrder = entity;
        $scope.load = function (id) {
            MnpPiOrder.get({id: id}, function(result) {
                $scope.mnpPiOrder = result;
            });
        };
        var unsubscribe = $rootScope.$on('mnpOmApp:mnpPiOrderUpdate', function(event, result) {
            $scope.mnpPiOrder = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });

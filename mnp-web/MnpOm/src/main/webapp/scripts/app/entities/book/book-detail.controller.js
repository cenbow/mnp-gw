'use strict';

angular.module('mnpOmApp')
    .controller('BookDetailController', function ($scope, $rootScope, $stateParams, entity, Book, Author) {
        $scope.book = entity;
        $scope.load = function (id) {
            Book.get({id: id}, function(result) {
                $scope.book = result;
            });
        };
        var unsubscribe = $rootScope.$on('mnpOmApp:bookUpdate', function(event, result) {
            $scope.book = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });

'use strict';

angular.module('mnpOmApp')
    .controller('MnpPiOrderController', function ($scope, $state, MnpPiOrder, ParseLinks) {
         
        $scope.mnpPiOrders = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            MnpPiOrder.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.mnpPiOrders = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.delete = function (id) {
            MnpPiOrder.get({id: id}, function(result) {
                $scope.mnpPiOrder = result;
                $('#deleteMnpPiOrderConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            MnpPiOrder.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteMnpPiOrderConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.mnpPiOrder = {
                orderId: null,
                orderType: null,
                orderState: null,
                orderStatus: null,
                createdDate: null,
                submittedDate: null,
                updatedDate: null,
                remark: null,
                id: null
            };
        };
    });

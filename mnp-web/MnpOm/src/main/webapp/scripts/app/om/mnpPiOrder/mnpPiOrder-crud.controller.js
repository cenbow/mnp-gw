'use strict';

angular.module('mnpOmApp').controller('MnpPiOrderCrudController',
    ['$scope', '$stateParams', 'entity', 'MnpPiOrder',
        function($scope, $stateParams, entity, MnpPiOrder) {
        $scope.isLoading = false;
        $scope.isSubmitting = false;
        $scope.svcMaxLength = 20;
        $scope.postalCodeListMap = {};
        $scope.svcTypeMapper = {150: 1, 110: 2};
        var init = function() {
            $scope.staticTypeList = [];
            $scope.ca = {catSvcTypeLkp: 150};
            $scope.ca.batchServices = [];
            $scope.ba = {};
            $scope.svc = {};
            $scope.packageTypeList = [];
            $scope.billAddr = {};
            $scope.vatAddr = {};

            $scope.ca.customerType = 'individual';
            $scope.catThaiTitleInput = undefined;
            $scope.catThaiCorpTypeInput = undefined;
        };
        init();
            
		$scope.pageState = $stateParams.pageState;
        $scope.mnpPiOrder = entity;
        $scope.load = function(id) {
            MnpPiOrder.get({id : id}, function(result) {
                $scope.mnpPiOrder = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('mnpOmApp:mnpPiOrderUpdate', result);
        };

        $scope.save = function () {
            if ($scope.mnpPiOrder.id != null) {
                MnpPiOrder.update($scope.mnpPiOrder, onSaveFinished);
            } else {
                MnpPiOrder.save($scope.mnpPiOrder, onSaveFinished);
            }
        };

}]);

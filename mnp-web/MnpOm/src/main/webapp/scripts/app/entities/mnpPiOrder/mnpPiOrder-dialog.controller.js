'use strict';

angular.module('mnpOmApp').controller('MnpPiOrderDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'MnpPiOrder',
        function($scope, $stateParams, $modalInstance, entity, MnpPiOrder) {

        $scope.mnpPiOrder = entity;
        $scope.load = function(id) {
            MnpPiOrder.get({id : id}, function(result) {
                $scope.mnpPiOrder = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('mnpOmApp:mnpPiOrderUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.mnpPiOrder.id != null) {
                MnpPiOrder.update($scope.mnpPiOrder, onSaveFinished);
            } else {
                MnpPiOrder.save($scope.mnpPiOrder, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);

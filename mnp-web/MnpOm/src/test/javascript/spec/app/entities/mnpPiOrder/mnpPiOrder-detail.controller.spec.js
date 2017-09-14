'use strict';

describe('MnpPiOrder Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockMnpPiOrder;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockMnpPiOrder = jasmine.createSpy('MockMnpPiOrder');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'MnpPiOrder': MockMnpPiOrder
        };
        createController = function() {
            $injector.get('$controller')("MnpPiOrderDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'mnpOmApp:mnpPiOrderUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});

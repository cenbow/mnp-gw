'use strict';

angular.module('mnpOmApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('mnpPiOrder', {
                parent: 'entity',
                url: '/mnpPiOrders',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'mnpOmApp.mnpPiOrder.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/mnpPiOrder/mnpPiOrders.html',
                        controller: 'MnpPiOrderController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('mnpPiOrder');
                        $translatePartialLoader.addPart('orderType');
                        $translatePartialLoader.addPart('orderState');
                        $translatePartialLoader.addPart('orderStatus');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('mnpPiOrder.detail', {
                parent: 'entity',
                url: '/mnpPiOrder/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'mnpOmApp.mnpPiOrder.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/mnpPiOrder/mnpPiOrder-detail.html',
                        controller: 'MnpPiOrderDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('mnpPiOrder');
                        $translatePartialLoader.addPart('orderType');
                        $translatePartialLoader.addPart('orderState');
                        $translatePartialLoader.addPart('orderStatus');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'MnpPiOrder', function($stateParams, MnpPiOrder) {
                        return MnpPiOrder.get({id : $stateParams.id});
                    }]
                }
            })
            .state('mnpPiOrder.new', {
                parent: 'mnpPiOrder',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/mnpPiOrder/mnpPiOrder-dialog.html',
                        controller: 'MnpPiOrderDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
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
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('mnpPiOrder', null, { reload: true });
                    }, function() {
                        $state.go('mnpPiOrder');
                    })
                }]
            })
            .state('mnpPiOrder.edit', {
                parent: 'mnpPiOrder',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/mnpPiOrder/mnpPiOrder-dialog.html',
                        controller: 'MnpPiOrderDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['MnpPiOrder', function(MnpPiOrder) {
                                return MnpPiOrder.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('mnpPiOrder', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });

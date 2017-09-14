'use strict';

angular.module('mnpOmApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('om.mnpPiOrder', {
                parent: 'om',
                url: '/mnpPiOrders',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'mnpOmApp.mnpPiOrder.home.title'
                },
                views: {
                    'content@om': {
                        templateUrl: 'scripts/app/om/mnpPiOrder/mnpPiOrders.html',
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
            .state('om.mnpPiOrder.new', {
                parent: 'om.mnpPiOrder',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'mnpOmApp.mnpPiOrder.new.title'
                },
				params: {
					pageState: 'new'
				},
                views: {
                    'content@om': {
                        templateUrl: 'scripts/app/om/mnpPiOrder/mnpPiOrder-crud.html',
                        controller: 'MnpPiOrderCrudController'
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
                                    id: null,
                                    
//                                    staticTypeList: [],
                                    ca: {
                                        customerType: 'individual',
                                        catSvcTypeLkp: 150,
                                        batchServices: []
                                    },
                                    ba: {},
                                    svc: {},
//            packageTypeList = [];
                                    billAddr: {},
                                    vatAddr: {},

                                    catThaiTitleInput: undefined,
                                    catThaiCorpTypeInput: undefined,
                                }
                            }
                }
            })
            .state('om.mnpPiOrder.detail', {
                parent: 'om.mnpPiOrder',
                url: '/om/mnpPiOrder/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'mnpOmApp.mnpPiOrder.detail.title'
                },
                views: {
                    'content@om': {
                        templateUrl: 'scripts/app/om/mnpPiOrder/mnpPiOrder-detail.html',
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
            /*.state('om.mnpPiOrder.new', {
                parent: 'om.mnpPiOrder',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/om/mnpPiOrder/mnpPiOrder-dialog.html',
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
                        $state.go('om.mnpPiOrder', null, { reload: true });
                    }, function() {
                        $state.go('om.mnpPiOrder');
                    })
                }]
            })*/
            .state('om.mnpPiOrder.edit', {
                parent: 'om.mnpPiOrder',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/om/mnpPiOrder/mnpPiOrder-dialog.html',
                        controller: 'MnpPiOrderDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['MnpPiOrder', function(MnpPiOrder) {
                                return MnpPiOrder.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('om.mnpPiOrder', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });

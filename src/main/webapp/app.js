/* 
 * @Author: chenhao
 * @Date:   2015-11-23 19:29:04
 * @Last Modified by:   chenhao
 * @Last Modified time: 2015-11-27 13:01:54
 */

'use strict';
define([
    'app.route',
    'angular',
    'angular-animate',
    'angular-bootstrap',
    'angular-bootstrap-nav-tree',
    'angular-chart',
    'angular-i18n',
    'angular-loading-bar',
    'angular-route',
    'angular-slider',
    'angular-ui-grid',
    'angular-upload',
    'textAngular'
], function (routesConfig) {

    // 创建app模块
    var app = angular.module('app', [
        'angularBootstrapNavTree',
        'cfp.loadingBar',
        'chart.js',
        'ngAnimate',
        'ngRoute',
        'ngFileUpload',
        'rzModule',
        'ui.bootstrap',
        'ui.grid',
        'ui.grid.selection',
        'ui.grid.autoResize',
        'ui.grid.resizeColumns',
        'textAngular'
    ]);

    // 配置分页参数
    app.constant('uibPaginationConfig', {
        itemsPerPage: 10,
        boundaryLinks: false,
        directionLinks: true,
        firstText: '第一页',
        previousText: '前一页',
        nextText: '下一页',
        lastText: '最后页',
        rotate: true
    });

    //日期参数
    app.constant('uibDatepickerPopupConfig', {
        datepickerPopup: 'yyyy-MM-dd',
        datepickerPopupTemplateUrl: 'template/datepicker/popup.html',
        datepickerTemplateUrl: 'template/datepicker/datepicker.html',
        html5Types: {
            date: 'yyyy-MM-dd',
            'datetime-local': 'yyyy-MM-ddTHH:mm:ss.sss',
            'month': 'yyyy-MM'
        },
        currentText: '今天',
        clearText: '清除',
        closeText: '关闭',
        closeOnDateSelection: true,
        appendToBody: false,
        showButtonBar: true,
        onOpenFocus: true
    });

    //http拦截器
    app.factory("httpInterceptor", ["$q", "$rootScope", function ($q, $rootScope) {
        return {
            request: function (config) {
                if (config.url.indexOf("rest") > -1) {
                    config.headers.token = window.sessionStorage.getItem("token");
                }
                return config || $q.when(config);
            },
            requestError: function (rejection) {
                return $q.reject(rejection)
            },
            response: function (response) {
                return response || $q.when(response);
            },
            responseError: function (rejection) {
                if (rejection.data.meta.message == "bad_token") {
                    window.location.href = "login.html";
                }
                return $q.reject(rejection);
            }
        };
    }]);

    // 配置app
    app.config(function ($httpProvider, $routeProvider, $locationProvider, $controllerProvider, $compileProvider, $filterProvider, $provide, cfpLoadingBarProvider) {

        $httpProvider.interceptors.push("httpInterceptor");

        // 注册组件
        app.registerController = $controllerProvider.register;
        app.registerDirective = $compileProvider.directive;
        app.registerFilter = $filterProvider.register;
        app.registerFactory = $provide.factory;
        app.registerService = $provide.service;

        // 进度条配置
        cfpLoadingBarProvider.includeSpinner = false;
        cfpLoadingBarProvider.includeBar = true;
        cfpLoadingBarProvider.latencyThreshold = 500;
        /*cfpLoadingBarProvider.barColor = '#fff';*/


        // 支持html uri优化
        $locationProvider.html5Mode(false);
        //$locationProvider.hashPrefix("!");

        //遍历路由对象，动态加载路由依赖
        if (routesConfig.routes != undefined) {
            angular.forEach(routesConfig.routes, function (route, path) {
                $routeProvider.when(path, {
                    templateUrl: route.templateUrl,
                    controller: route.controller,
                    resolve: {
                        resolver: function ($q) {
                            var deferred = $q.defer();
                            require([route.dependencies], function (controller) {
                                $controllerProvider.register(route.controller, controller);
                                deferred.resolve();
                            });
                            return deferred.promise;
                        }
                    },
                    allowAnonymous: route.allowAnonymous
                });
            });
        }

        if (routesConfig.defaultRoute != undefined) {
            $routeProvider.otherwise({redirectTo: routesConfig.defaultRoute});
        }

    });

    // 配置启动
    app.run(function ($rootScope, $location, cfpLoadingBar) {
        // 路由开始事件
        $rootScope.$on('$routeChangeStart', function (evt, next, current) {
            cfpLoadingBar.start();
        });

        // 路由结束事件
        $rootScope.$on('$routeChangeSuccess', function (evt, next, current) {
            cfpLoadingBar.complete();
        });
    });

    //配置提示工具
    app.service('ngConfirm', function ($q, $uibModal) {
        //confirm确认框
        this.open = function (scope, msg, callback) {
            $uibModal.open({
                animation: true,
                /*backdrop: false,*/
                size: 'sm',
                templateUrl: 'modules/ngConfirm/ngConfirm.html',
                controller: function ($scope, $uibModalInstance, $sce, parentScope) {
                    $scope.msg = $sce.trustAsHtml(msg);
                    //保存
                    $scope.ok = function () {
                        callback();
                        $uibModalInstance.close();
                    };
                    //取消
                    $scope.cancel = function () {
                        $uibModalInstance.dismiss('cancel');
                    };
                },
                resolve: {
                    parentScope: function () {
                        return scope;
                    }
                }
            });
        }
    }).service('ngAlert', function ($q, $uibModal, $timeout) {
        //alert提示框
        this.open = function (msg, time) {
            $uibModal.open({
                animation: true,
                /*backdrop: false,*/
                size: 'sm',
                templateUrl: 'modules/ngAlert/ngAlert.html',
                controller: function ($scope, $uibModalInstance, $sce) {
                    $scope.msg = $sce.trustAsHtml(msg);
                    if (time) {
                        $timeout($scope.ok, time);
                    } else {
                        $timeout($scope.ok);
                    }
                    $scope.ok = function () {
                        $uibModalInstance.close();
                    };
                }
            });
        }
    });

    //配置文件上传工具
    app.directive('fileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;

                element.bind('change', function () {
                    scope.$apply(function () {
                        modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        };
    }]).directive('multiFileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var model = $parse(attrs.multiFileModel);
                var modelSetter = model.assign;

                element.bind('change', function () {
                    scope.$apply(function () {
                        modelSetter(scope, element[0].files);
                    });
                });
            }
        };
    }]);


    //配置导航栏相关控制
    app.controller('navbarCtrl', function ($scope) {
        //导航栏controller
        $scope.logout = function () {
            window.location.href = "login.html";
        };
    });

    return app;

});


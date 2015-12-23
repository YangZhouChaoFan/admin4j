/*
 * @Author: chenhao
 * @Date:   2015-11-23 19:33:02
 * @Last Modified by:   chenhao
 * @Last Modified time: 2015-11-23 20:11:35
 */

'use strict';
define(['angular'], function (angular) {
    return function ($scope, $http, $timeout, $uibModal, i18nService, ngConfirm, ngAlert) {

        //当前选择标志位
        $scope.zeroFlag = true;
        $scope.singleFlag = false;
        $scope.multiFlag = false;

        //分页参数
        $scope.totalItems = 0;
        $scope.pageSize = 10;
        $scope.pageNo = 1;
        $scope.totalPages = 1;
        $scope.btnSize = 5;

        //分页事件
        $scope.pageChanged = function () {
            //当前页改变时，去加载分页数据
            $scope.load();
        };

        //设置ui-grid国际化语言为中文
        i18nService.setCurrentLang('zh-cn');

        //grid参数
        $scope.gridOptions = {
            data: 'data',
            enableRowSelection: false,
            enableRowHeaderSelection: true,
            enableSelectAll: true,
            enableColumnResizing: true,
            multiSelect: true,
            selectionRowHeaderWidth: 35,
            rowHeight: 35,
            columnDefs: [
                {field: 'id', displayName: 'ID'},
                {field: 'name', displayName: '中文名称'},
                {field: 'ename', displayName: '英文名称'},
                {field: 'password', displayName: '密码'},
                {field: 'age', displayName: '年龄'},
                {field: 'birthday', displayName: '生日'},
                {field: 'sex', displayName: '性别'},
                {field: 'role', displayName: '角色'}
            ],
            onRegisterApi: function (gridApi) {
                //获取ui-grid的API
                $scope.gridApi = gridApi;
                //ui-grid的选择事件
                gridApi.selection.on.rowSelectionChanged($scope, function (row) {
                    var rows = $scope.gridApi.selection.getSelectedRows();
                    changeSelectFlag(rows);
                });
                gridApi.selection.on.rowSelectionChangedBatch($scope, function (rows) {
                    changeSelectFlag(rows);
                });

                function changeSelectFlag(rows) {
                    //标志位
                    if (rows.length == 0) {
                        $scope.zeroFlag = true;
                        $scope.singleFlag = false;
                        $scope.multiFlag = false;
                    } else if (rows.length == 1) {
                        $scope.zeroFlag = false;
                        $scope.singleFlag = true;
                        $scope.multiFlag = false;
                    } else if (rows.length > 1) {
                        $scope.zeroFlag = false;
                        $scope.singleFlag = false;
                        $scope.multiFlag = true;
                    }
                }
            }
        };

        //加载grid数据
        $scope.load = function () {
            //打开等待动画，ajax请求数据
            $scope.data = [];
            $scope.loading = true;
            $http({
                url: 'rest/userController/queryUser',
                method: 'POST',
                data: {
                    pageNo: $scope.pageNo,
                    pageSize: $scope.pageSize
                }
            }).success(function (result) {
                if (result.status == 'success') {
                    //关闭等待动画，获取数据
                    $scope.loading = false;
                    $scope.data = result.data;
                    $scope.totalItems = result.count;
                }
            }).error(function (msg) {
                console.log(msg);
            });
        };

        //新增
        $scope.insert = function () {
            $uibModal.open({
                animation: true,
                templateUrl: 'modules/grid/gridModal.html',
                controller: function ($scope, $uibModalInstance, parentScope) {

                    //角色选项
                    $scope.options = {
                        role: [
                            {id: 1, name: '管理员'},
                            {id: 2, name: '普通用户'},
                        ]
                    }

                    //保存
                    $scope.ok = function () {
                        $http({
                            url: 'rest/userController/insertUser',
                            method: 'POST',
                            data: $scope.user || {}
                        }).success(function (result) {
                            if (result.status == 'success') {
                                $uibModalInstance.close();
                                parentScope.load();
                            }else if(result.status == 'error'){
                                ngAlert.open(result.msg);
                            }
                        }).error(function (msg) {
                            console.log(msg);
                        });
                    };
                    //取消
                    $scope.cancel = function () {
                        $uibModalInstance.dismiss('cancel');
                    };
                },
                resolve: {
                    parentScope: function () {
                        return $scope;
                    }
                }
            });
        };

        //修改
        $scope.update = function () {
            $uibModal.open({
                animation: true,
                templateUrl: 'modules/grid/gridModal.html',
                controller: function ($scope, $uibModalInstance, parentScope) {

                    //角色选项
                    $scope.options = {
                        role: [
                            {id: 1, name: '管理员'},
                            {id: 2, name: '普通用户'},
                        ]
                    }

                    //获取当前选择的数据
                    $http({
                        url: 'rest/userController/queryUserById?id=' + parentScope.gridApi.selection.getSelectedRows()[0].id,
                        method: 'POST'
                    }).success(function (result) {
                        if (result.status == 'success') {
                            $scope.user = result.data;
                            $scope.user.birthday = new Date($scope.user.birthday);
                        }
                    }).error(function (msg) {
                        console.log(msg);
                    });

                    //保存
                    $scope.ok = function () {
                        $http({
                            url: 'rest/userController/updateUser',
                            method: 'POST',
                            data: $scope.user || {}
                        }).success(function (result) {
                            if (result.status == 'success') {
                                $uibModalInstance.close();
                                parentScope.load();
                            }else if(result.status == 'error'){
                                ngAlert.open(result.msg);
                            }
                        }).error(function (msg) {
                            console.log(msg);
                        });
                    };
                    //取消
                    $scope.cancel = function () {
                        $uibModalInstance.dismiss('cancel');
                    };
                },
                resolve: {
                    parentScope: function () {
                        return $scope;
                    }
                }
            });
        };

        //删除
        $scope.delete = function () {

            ngConfirm.open($scope, "需要删除选中的记录？", function () {
                var users = $scope.gridApi.selection.getSelectedRows();
                var ids = []
                for (var i = 0; i < users.length; i++) {
                    ids.push(users[i].id);
                }
                $http({
                    url: 'rest/userController/deleteUser',
                    method: 'POST',
                    data: ids
                }).success(function (result) {
                    if (result.status == 'success') {
                        $scope.load();
                    }
                }).error(function (msg) {
                    console.log(msg);
                });
            })

        };

        //a标签测试
        $scope.demo = function () {
            ngAlert.open("操作n");
        }

    };
});
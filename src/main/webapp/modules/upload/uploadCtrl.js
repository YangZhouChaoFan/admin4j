/*
 * @Author: chenhao
 * @Date:   2015-11-23 19:33:02
 * @Last Modified by:   chenhao
 * @Last Modified time: 2015-11-23 20:11:35
 */

'use strict';
define(['angular'], function (angular) {
    return function ($scope, $http) {

        //单文件上传
        $scope.upload = function () {
            var formData = new FormData();
            formData.append('file', $scope.file);
            formData. $scope.user
            $http.post('rest/upload', formData, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).success(function (data, status) {
                console.log(data);
            }).error(function (msg, status) {
                console.log(msg);
            });
        }

        //多文件上传
        $scope.multiUpload = function () {
            var formData = new FormData();
            angular.forEach($scope.files,function(file){
                formData.append('files',file);
            })
            $http.post('rest/multiUpload', formData, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).success(function (data, status) {
                console.log(data);
            }).error(function (msg, status) {
                console.log(msg);
            });
        }
    };
});
/* 
 * @Author: chenhao
 * @Date:   2015-11-23 19:33:02
 * @Last Modified by:   chenhao
 * @Last Modified time: 2015-11-23 20:11:35
 */

'use strict';
define(['angular'], function (angular) {
    return function ($scope, $uibModal) {

        $scope.showModal = function (size) {
            $uibModal.open({
                animation: true,
                size: size,
                templateUrl: 'modules/modal/modalTpl.html',
                controller: function ($scope, $uibModalInstance, info) {
                    console.log(info);
                    $scope.ok = function () {
                        $uibModalInstance.close();
                    };
                    $scope.cancel = function () {
                        $uibModalInstance.dismiss('cancel');
                    };
                },
                resolve: {
                    info: function () {
                        return '打开窗口';
                    }
                }
            });
        };

    };
});
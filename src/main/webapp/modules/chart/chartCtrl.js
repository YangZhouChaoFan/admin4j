/*
 * @Author: chenhao
 * @Date:   2015-11-23 19:33:02
 * @Last Modified by:   chenhao
 * @Last Modified time: 2015-11-23 20:11:35
 */

'use strict';
define(['angular'], function (angular) {
    return function ($scope, $timeout) {
        $timeout(function(){

            //曲线图
            $scope.labelsLine = ["January", "February", "March", "April", "May", "June", "July"];
            $scope.seriesLine = ['Series A', 'Series B'];
            $scope.dataLine = [
                [65, 59, 80, 81, 56, 55, 40],
                [28, 48, 40, 19, 86, 27, 90]
            ];
            $scope.onClickLine = function (points, evt) {
                console.log(points, evt);
            };

            //条形图
            $scope.labelsBar = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
            $scope.seriesBar = ['Series A', 'Series B'];

            $scope.dataBar = [
                [65, 59, 80, 81, 56, 55, 40],
                [28, 48, 40, 19, 86, 27, 90]
            ];

            //饼状图
            $scope.labelsPie = ["Download Sales", "In-Store Sales", "Mail-Order Sales"];
            $scope.dataPie = [300, 500, 100];

            //雷达图
            $scope.labelsArea = ["Download Sales", "In-Store Sales", "Mail-Order Sales", "Tele Sales", "Corporate Sales"];
            $scope.dataArea = [300, 500, 100, 40, 120];

        }, 100);

    };
});
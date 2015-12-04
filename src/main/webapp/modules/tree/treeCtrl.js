/*
 * @Author: chenhao
 * @Date:   2015-11-23 19:33:02
 * @Last Modified by:   chenhao
 * @Last Modified time: 2015-11-23 20:11:35
 */

'use strict';
define(['angular'], function (angular) {
    return function ($scope) {

        $scope.treeData = [
            {
                label: 'North America',
                children: [
                    {
                        label: 'Canada',
                        children: ['Toronto', 'Vancouver']
                    }, {
                        label: 'USA',
                        children: ['New York', 'Los Angeles']
                    }, {
                        label: 'Mexico',
                        children: ['Mexico City', 'Guadalajara']
                    }
                ]
            }, {
                label: 'South America',
                children: [
                    {
                        label: 'Venezuela',
                        children: ['Caracas', 'Maracaibo']
                    }, {
                        label: 'Brazil',
                        children: ['Sao Paulo', 'Rio de Janeiro']
                    }, {
                        label: 'Argentina',
                        children: ['Buenos Aires', 'Cordoba']
                    }
                ]
            }
        ];

        $scope.treeSelect = function(branch){
            $scope.output = branch.label;
        }

    };
});
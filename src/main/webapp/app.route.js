/* 
* @Author: chenhao
* @Date:   2015-11-23 19:30:42
* @Last Modified by:   chenhao
* @Last Modified time: 2015-11-24 10:45:47
*/

'use strict';
define([], function () {
    return {
        defaultRoute: '/home',
        routes: {
            '/home': {
                templateUrl: 'modules/home/home.html',
                controller: 'homeCtrl',
                dependencies: 'modules/home/homeCtrl',
                allowAnonymous: true
            },
            '/grid': {
                templateUrl: 'modules/grid/grid.html',
                controller: 'gridCtrl',
                dependencies: 'modules/grid/gridCtrl',
                allowAnonymous: true
            },
            '/modal': {
                templateUrl: 'modules/modal/modal.html',
                controller: 'modalCtrl',
                dependencies: 'modules/modal/modalCtrl',
                allowAnonymous: true
            },
            '/tree': {
                templateUrl: 'modules/tree/tree.html',
                controller: 'treeCtrl',
                dependencies: 'modules/tree/treeCtrl',
                allowAnonymous: true
            },
            '/slider': {
                templateUrl: 'modules/slider/slider.html',
                controller: 'sliderCtrl',
                dependencies: 'modules/slider/sliderCtrl',
                allowAnonymous: true
            },
            '/upload': {
                templateUrl: 'modules/upload/upload.html',
                controller: 'uploadCtrl',
                dependencies: 'modules/upload/uploadCtrl',
                allowAnonymous: true
            }
        }
    };
});
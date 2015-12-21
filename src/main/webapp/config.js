require.config({
    paths: {
        'angular': 'bower_components/angular/angular',
        'angular-animate': 'bower_components/angular-animate/angular-animate.min',
        'angular-bootstrap': 'bower_components/angular-bootstrap/ui-bootstrap-tpls.min',
        'angular-bootstrap-nav-tree': 'bower_components/angular-bootstrap-nav-tree/dist/abn_tree_directive',
        'angular-chart': 'bower_components/angular-chart.js/dist/angular-chart.min',
        'angular-i18n': 'bower_components/angular-i18n/angular-locale_zh-cn',
        'angular-loading-bar': 'bower_components/angular-loading-bar/build/loading-bar.min',
        'angular-route': 'bower_components/angular-route/angular-route.min',
        'angular-slider': 'bower_components/angularjs-slider/dist/rzslider.min',
        'angular-ui-grid': 'bower_components/angular-ui-grid/ui-grid.min',
        'angular-upload': 'bower_components/ng-file-upload/ng-file-upload-all.min',
        'chart': 'bower_components/Chart.js/Chart.min',
        'rangy-core': 'bower_components/rangy/rangy-core.min',
        'rangy-selectionsave': 'bower_components/rangy/rangy-selectionsaverestore.min',
        'textAngular-sanitize': 'bower_components/textAngular/dist/textAngular-sanitize.min',
        'textAngularSetup': 'bower_components/textAngular/dist/textAngularSetup',
        'textAngular': 'bower_components/textAngular/dist/textAngular'
    },
    shim: {
        'angular': {
            exports: 'angular'
        },
        'angular-animate': ['angular'],
        'angular-bootstrap': ['angular'],
        'angular-bootstrap-nav-tree': ['angular'],
        'angular-chart': ['angular', 'chart'],
        'angular-i18n': ['angular'],
        'angular-loading-bar': ['angular'],
        'angular-route': ['angular'],
        'angular-slider': ['angular'],
        'angular-ui-grid': ['angular'],
        'angular-upload': ['angular'],
        'rangy-selectionsave': ['rangy-core'],
        'textAngular-sanitize': ['angular'],
        'textAngularSetup': ['angular'],
        'textAngular': ['angular', 'textAngular-sanitize', 'textAngularSetup']
    }
});

require(['rangy-core', 'rangy-selectionsave'], function(rangyCore){
    window.rangy = rangyCore;
});

require(['angular', 'app'], function () {
    angular.bootstrap(document, ['app']);
});
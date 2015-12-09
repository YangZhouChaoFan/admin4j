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
		'chart': 'bower_components/Chart.js/Chart.min'
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
		'angular-ui-grid': ['angular']
	}
});

require(['angular', 'app'], function(){
	angular.bootstrap(document, ['app']);
});
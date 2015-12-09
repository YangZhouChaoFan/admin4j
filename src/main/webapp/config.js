require.config({
	paths: {                   
		'angular': 'bower_components/angular/angular',
		'angular-i18n': 'bower_components/angular-i18n/angular-locale_zh-cn',
		'angular-animate': 'bower_components/angular-animate/angular-animate.min',
		'angular-route': 'bower_components/angular-route/angular-route.min',
		'angular-bootstrap': 'bower_components/angular-bootstrap/ui-bootstrap-tpls.min',
		'angular-ui-grid': 'bower_components/angular-ui-grid/ui-grid.min',
		'angular-loading-bar': 'bower_components/angular-loading-bar/build/loading-bar.min',
		'angular-bootstrap-nav-tree': 'bower_components/angular-bootstrap-nav-tree/dist/abn_tree_directive',
		'angularjs-slider': 'bower_components/angularjs-slider/dist/rzslider.min'
	},
	shim: {                  
		'angular': {
			exports: 'angular'
		},
		'angular-i18n': ['angular'],
		'angular-animate': ['angular'],
		'angular-route': ['angular'],
		'angular-bootstrap': ['angular'],
		'angular-ui-grid': ['angular'],
		'angular-loading-bar': ['angular'],
		'angular-bootstrap-nav-tree': ['angular'],
		'angularjs-slider': ['angular'],
		'ng-file-upload': ['angular']
	}
});

require(['angular', 'app'], function(){
	angular.bootstrap(document, ['app']);
});
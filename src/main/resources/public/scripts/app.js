//app.js
var app = angular.module('placement', ['placement.controllers', 'ui.router']);
angular.module('placement.controllers', ['placement.services']);
angular.module('placement.services', []);

app.config(function($stateProvider, $urlRouterProvider) {

	$urlRouterProvider.otherwise('/home');

	$stateProvider
	.state('home', {
		url: '/home',
		templateUrl: 'views/partial-home.html'
	})
	.state('region', {
		url: '/region',
		views: {

			// the main template will be placed here (relatively named)
			'': { 
				templateUrl: 'views/partial-region.html',
				controller: 'regionController' 
			},

			// the child views will be defined here (absolutely named)
			'columnOne@region': { 
				templateUrl: 'views/region-filter.html'
			},
			// for column two, we'll define a separate controller 
			'columnTwo@region': { 
				templateUrl: 'views/region-table.html'
			}
		}
	})

	.state('cm', {
		url: '/cm',
		views: {

			// the main template will be placed here (relatively named)
			'': { 
				templateUrl: 'views/partial-cm.html',
				controller: 'cmController' 
			},

			// the child views will be defined here (absolutely named)
			'columnOne@cm': { 
				templateUrl: 'views/cm-filter.html'
			},
			// for column two, we'll define a separate controller 
			'columnTwo@cm': { 
				templateUrl: 'views/cm-table.html'
			}
		}
	})
	
	.state('vacancy', {
		url: '/vacancy',
		views: {

			// the main template will be placed here (relatively named)
			'': { 
				templateUrl: 'views/partial-vacancy.html',
				controller: 'vacancyController' 
			},

			// the child views will be defined here (absolutely named)
			'columnOne@vacancy': { 
				templateUrl: 'views/vacancy-filter.html'
			},
			// for column two, we'll define a separate controller 
			'columnTwo@vacancy': { 
				templateUrl: 'views/vacancy-table.html'
			}
		}
	})
	
	.state('hiringManager', {
		url: '/hiringManager',
		views: {

			// the main template will be placed here (relatively named)
			'': { 
				templateUrl: 'views/partial-hiringManager.html',
				controller: 'hiringManagerController' 
			},

			// the child views will be defined here (absolutely named)
			'columnOne@hiringManager': { 
				templateUrl: 'views/hiringManager-filter.html'
			},
			// for column two, we'll define a separate controller 
			'columnTwo@hiringManager': { 
				templateUrl: 'views/hiringManager-table.html'
			}
		}
	});
});

app.directive('filterCheckboxes', function(){
	return {
		restrict: "E",
		scope:{
			inputs: "="
		},
		template: function(element, attrs){
			return '<li ng-repeat="input in inputs"><input type="checkbox" value="{{input.value}}" ng-model="input.selected"> {{input.label}}</li>';
		}
	}
});



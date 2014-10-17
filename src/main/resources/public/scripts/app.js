// app.js
var app = angular.module('placement', ['ui.router']);

app.config(function($stateProvider, $urlRouterProvider) {
    
    $urlRouterProvider.otherwise('/home');
    
    $stateProvider

	// HOME STATES AND NESTED VIEWS ========================================
    .state('home', {
        url: '/home',
        templateUrl: 'views/partial-home.html'
    })

	// nested list with custom controller
	.state('home.list', {
        url: '/list',
        templateUrl: 'views/partial-home-list.html',
        controller: 'regionController'
    })

	// nested list with just some random string data
    .state('home.paragraph', {
        url: '/paragraph',
        template: 'I could sure use a drink right now.'
    })
        
    .state('about', {
        url: '/about',
        views: {

            // the main template will be placed here (relatively named)
            '': { templateUrl: 'views/partial-about.html' },

            // the child views will be defined here (absolutely named)
            'columnOne@about': { template: 'Look I am a column!' },

            // for column two, we'll define a separate controller 
            'columnTwo@about': { 
                templateUrl: 'views/table-data.html',
                controller: 'regionController'
            }
        }
    });
        
});



app.controller('regionController', function ($scope, $http) {
    $http.get('/api/v1/regions').success(function (data) {
        $scope.regions = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })
});

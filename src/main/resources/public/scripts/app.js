//app.js
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
		templateUrl: 'views/partial-home-list.html'
	})

	// nested list with just some random string data
	.state('home.paragraph', {
		url: '/paragraph',
		template: 'I could sure use a drink right now.'
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
	});
});

app.controller('regionController', function ($scope, $http, regionService) {

	$scope.regions = [];

	var applyRemoteData = function(data) {

		$scope.regions = data.regionList;
		$scope.regionTypesInput = data.regionTypesInput;
	}

	var loadRemoteData = function(regionTypesInput){
		regionService.searchRegions(regionTypesInput)
		.then(
				function( data ) {
					applyRemoteData( data );
				}
		);
	}

	loadRemoteData();

	$scope.searchRegions = function(){
		loadRemoteData($scope.regionTypesInput);
	}
});

app.controller('cmController', function ($scope, $http, cmService) {

	var applyRemoteData = function(data) {

		$scope.cms = data.cmList;
		$scope.regionsInput = data.regionsInput;
		$scope.timePeriodsInput = data.timePeriodsInput;
		$scope.selectedRegion = data.selectedRegion;
		$scope.selectedTimePeriod = data.selectedTimePeriod;
	}

	var loadRemoteData = function(regionsInput, timePeriodsInput, selectedRegion, selectedTimePeriod){
		cmService.searchCms(regionsInput, timePeriodsInput, selectedRegion, selectedTimePeriod)
		.then(
				function( data ) {
					applyRemoteData( data );
				}
		);
	}

	loadRemoteData();

	$scope.searchCms = function(){
		loadRemoteData($scope.regionsInput, $scope.timePeriodsInput, $scope.selectedRegion, $scope.selectedTimePeriod);
	}
	
	$scope.changeRegion = function(option){
		$scope.selectedRegion = option;
	}
	
	$scope.changeTimePeriod = function(option){
		$scope.selectedTimePeriod = option;
	}
});

app.controller('vacancyController', function ($scope, $http, vacancyService) {

	var applyRemoteData = function(data) {

		$scope.vacancies = data.vacancyList;
		$scope.regionsInput = data.regionsInput;
		$scope.timePeriodsInput = data.timePeriodsInput;
		$scope.selectedRegion = data.selectedRegion;
		$scope.selectedTimePeriod = data.selectedTimePeriod;
	}

	var loadRemoteData = function(regionsInput, timePeriodsInput, selectedRegion, selectedTimePeriod){
		vacancyService.searchVacancies(regionsInput, timePeriodsInput, selectedRegion, selectedTimePeriod)
		.then(
				function( data ) {
					applyRemoteData( data );
				}
		);
	}

	loadRemoteData();

	$scope.searchVacancies = function(){
		loadRemoteData($scope.regionsInput, $scope.timePeriodsInput, $scope.selectedRegion, $scope.selectedTimePeriod);
	}
	
	$scope.changeRegion = function(option){
		$scope.selectedRegion = option;
	}
	
	$scope.changeTimePeriod = function(option){
		$scope.selectedTimePeriod = option;
	}
});

app.service(
		"regionService",
		function( $http, $q ) {

			// Return public API.
			return({
				searchRegions: searchRegions
			});

			function searchRegions( regionTypesInput ) {

				var request = $http({
					method: "post",
					url: "/api/v1/regions",
					params: {

					},
					data: {
						regionTypesInput: regionTypesInput
					}
				});
				return( request.then( handleSuccess, handleError ) );
			}

			// I transform the error response, unwrapping the application dta from
			// the API response payload.
			function handleError( response ) {

				// The API response from the server should be returned in a
				// nomralized format. However, if the request was not handled by the
				// server (or what not handles properly - ex. server error), then we
				// may have to normalize it on our end, as best we can.
				if (
						! angular.isObject( response.data ) ||
						! response.data.message
				) {

					return( $q.reject( "An unknown error occurred." ) );

				}
				// Otherwise, use expected error message.
				return( $q.reject( response.data.message ) );
			}

			// I transform the successful response, unwrapping the application data
			// from the API response payload.
			function handleSuccess( response ) {

				return( response.data );

			}
		}
);

app.service(
		"cmService",
		function( $http, $q ) {

			// Return public API.
			return({
				searchCms: searchCms
			});

			function searchCms( regionsInput, timePeriodsInput, selectedRegion, selectedTimePeriod ) {

				var request = $http({
					method: "post",
					url: "/api/v1/cms",
					params: {

					},
					data: {
						regionsInput: regionsInput,
						timePeriodsInput: timePeriodsInput,
						selectedRegion: selectedRegion,
						selectedTimePeriod: selectedTimePeriod
					}
				});
				return( request.then( handleSuccess, handleError ) );
			}

			// I transform the error response, unwrapping the application dta from
			// the API response payload.
			function handleError( response ) {

				// The API response from the server should be returned in a
				// nomralized format. However, if the request was not handled by the
				// server (or what not handles properly - ex. server error), then we
				// may have to normalize it on our end, as best we can.
				if (
						! angular.isObject( response.data ) ||
						! response.data.message
				) {

					return( $q.reject( "An unknown error occurred." ) );

				}
				// Otherwise, use expected error message.
				return( $q.reject( response.data.message ) );
			}

			// I transform the successful response, unwrapping the application data
			// from the API response payload.
			function handleSuccess( response ) {

				return( response.data );

			}
		}
);

app.service(
		"vacancyService",
		function( $http, $q ) {

			// Return public API.
			return({
				searchVacancies: searchVacancies
			});

			function searchVacancies( regionsInput, timePeriodsInput, selectedRegion, selectedTimePeriod ) {

				var request = $http({
					method: "post",
					url: "/api/v1/vacancies",
					params: {

					},
					data: {
						regionsInput: regionsInput,
						timePeriodsInput: timePeriodsInput,
						selectedRegion: selectedRegion,
						selectedTimePeriod: selectedTimePeriod
					}
				});
				return( request.then( handleSuccess, handleError ) );
			}

			// I transform the error response, unwrapping the application dta from
			// the API response payload.
			function handleError( response ) {

				// The API response from the server should be returned in a
				// nomralized format. However, if the request was not handled by the
				// server (or what not handles properly - ex. server error), then we
				// may have to normalize it on our end, as best we can.
				if (
						! angular.isObject( response.data ) ||
						! response.data.message
				) {

					return( $q.reject( "An unknown error occurred." ) );

				}
				// Otherwise, use expected error message.
				return( $q.reject( response.data.message ) );
			}

			// I transform the successful response, unwrapping the application data
			// from the API response payload.
			function handleSuccess( response ) {

				return( response.data );

			}
		}
);

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
        templateUrl: 'views/partial-home-list.html'
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
            '': { 
            	templateUrl: 'views/partial-about.html',
                controller: 'regionController' 
            },

            // the child views will be defined here (absolutely named)
            'columnOne@about': { 
                templateUrl: 'views/filter-data.html'
            },
            // for column two, we'll define a separate controller 
            'columnTwo@about': { 
                templateUrl: 'views/table-data.html'
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

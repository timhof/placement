angular.module('placement.controllers').controller('regionController', function ($scope, $http, regionService) {

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

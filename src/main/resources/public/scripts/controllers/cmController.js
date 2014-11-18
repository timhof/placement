angular.module('placement.controllers').controller('cmController', function ($scope, $http, cmService) {

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

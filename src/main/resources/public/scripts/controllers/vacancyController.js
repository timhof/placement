angular.module('placement.controllers').controller('vacancyController', function ($scope, $http, vacancyService) {

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

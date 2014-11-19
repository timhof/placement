angular.module('placement.controllers').controller('cmController', function ($scope, $http, cmService) {

	var applyRemoteData = function(data) {

		$scope.cms = data.cmList;
		$scope.regionsInput = data.regionsInput;
		$scope.timePeriodsInput = data.timePeriodsInput;
		$scope.selectedRegion = data.selectedRegion;
		$scope.selectedTimePeriod = data.selectedTimePeriod;
		$scope.corpsYearsInput = data.corpsYearsInput;
		$scope.stagesInput = data.stagesInput;
		$scope.stepsInput = data.stepsInput;
		$scope.releaseCodesInput = data.releaseCodesInput;
		$scope.releaseStepsInput = data.releaseStepsInput;
		$scope.timePeriodChanged = false;
		$scope.regionChanged = false;
	}

	var loadRemoteData = function(regionsInput, timePeriodsInput, selectedRegion, selectedTimePeriod, 
			corpsYearsInput, stagesInput, stepsInput, releaseCodesInput, releaseStepsInput){
		
		cmService.searchCms(regionsInput, timePeriodsInput, selectedRegion, selectedTimePeriod, 
				corpsYearsInput, stagesInput, stepsInput, releaseCodesInput, releaseStepsInput)
		.then(
				function( data ) {
					applyRemoteData( data );
				}
		);
	}

	loadRemoteData();

	$scope.searchCms = function(){
		
		// If region or time period changed, we should reset corps year options
		if($scope.regionChanged || $scope.timePeriodChanged){
			loadRemoteData($scope.regionsInput, $scope.timePeriodsInput, $scope.selectedRegion, $scope.selectedTimePeriod);
		}
		else{
			loadRemoteData($scope.regionsInput, $scope.timePeriodsInput, $scope.selectedRegion, $scope.selectedTimePeriod, 
					$scope.corpsYearsInput, $scope.stagesInput, $scope.stepsInput, $scope.releaseCodesInput, $scope.releaseStepsInput);
		}
	}
	
	$scope.changeRegion = function(option){
		$scope.selectedRegion = option;
		$scope.regionChanged = true;
	}
	
	$scope.changeTimePeriod = function(option){
		$scope.selectedTimePeriod = option;
		$scope.timePeriodChanged = true;
	}
});

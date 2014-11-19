angular.module('placement.controllers').controller('hiringManagerController', function ($scope, $http, hiringManagerService) {

	var applyRemoteData = function(data) {

		$scope.hiringManagers = data.hiringManagerList;
		$scope.regionsInput = data.regionsInput;
		$scope.selectedRegion = data.selectedRegion;
		$scope.entityTypesInput = data.entityTypesInput;
	}

	var loadRemoteData = function(regionsInput, selectedRegion, entityTypesInput){
		hiringManagerService.searchHiringManagers(regionsInput, selectedRegion, entityTypesInput)
		.then(
				function( data ) {
					applyRemoteData( data );
				}
		);
	}

	loadRemoteData();

	$scope.searchHiringManagers = function(){
		loadRemoteData($scope.regionsInput, $scope.selectedRegion, $scope.entityTypesInput);
	}
	
	$scope.changeRegion = function(option){
		$scope.selectedRegion = option;
	}
	
});
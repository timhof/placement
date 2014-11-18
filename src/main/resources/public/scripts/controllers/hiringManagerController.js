angular.module('placement.controllers').controller('hiringManagerController', function ($scope, $http, hiringManagerService) {

	var applyRemoteData = function(data) {

		$scope.hiringManagers = data.hiringManagerList;
		$scope.regionsInput = data.regionsInput;
		$scope.selectedRegion = data.selectedRegion;
	}

	var loadRemoteData = function(regionsInput, selectedRegion){
		hiringManagerService.searchHiringManagers(regionsInput, selectedRegion)
		.then(
				function( data ) {
					applyRemoteData( data );
				}
		);
	}

	loadRemoteData();

	$scope.searchHiringManagers = function(){
		loadRemoteData($scope.regionsInput, $scope.selectedRegion);
	}
	
	$scope.changeRegion = function(option){
		$scope.selectedRegion = option;
	}
	
});
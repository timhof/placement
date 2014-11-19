angular.module('placement.services').service(
		"cmService",
		function( $http, $q ) {

			// Return public API.
			return({
				searchCms: searchCms
			});

			function searchCms( regionsInput, timePeriodsInput, selectedRegion, selectedTimePeriod, 
					corpsYearsInput, stagesInput, stepsInput, releaseCodesInput, releaseStepsInput ) {

				var request = $http({
					method: "post",
					url: "/api/v1/cms",
					params: {

					},
					data: {
						regionsInput: regionsInput,
						timePeriodsInput: timePeriodsInput,
						selectedRegion: selectedRegion,
						selectedTimePeriod: selectedTimePeriod,
						corpsYearsInput: corpsYearsInput,
						stagesInput: stagesInput,
						stepsInput: stepsInput,
						releaseCodesInput: releaseCodesInput,
						releaseStepsInput: releaseStepsInput
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

angular.module('myApp.urbanizationservice', []).
  factory('urbanizationservice', function ($http, $q) {

    var BASE = "http://localhost:9082/api/v1/ub/";
    var api = {};

    api.getNeighborHoods = function (rows,cols,startNodeX,startNodeY,range) {
      var request = $http({
        cache: false,
        method: "get",
        url: BASE + 'neighborhoods',
        params: {
          rows: rows,
          columns: cols,
          startNodeX: startNodeX,
          startNodeY: startNodeY,
          range: range
        },
      });
      return (request.then(handleSuccess, handleError));
    }

    // ---
    // PRIVATE METHODS.
    // ---
    // I transform the error response, unwrapping the application dta from
    // the API response payload.
    function handleError(response) {
      // The API response from the server should be returned in a
      // nomralized format. However, if the request was not handled by the
      // server (or what not handles properly - ex. server error), then we
      // may have to normalize it on our end, as best we can.
      if (
        !angular.isObject(response.data) ||
        !response.data.message
      ) {
        return ($q.reject("An unknown error occurred."));
      }
      // Otherwise, use expected error message.
      return ($q.reject(response.data.message));
    }


    // I transform the successful response, unwrapping the application data
    // from the API response payload.
    function handleSuccess(response) {
      return (response.data);
    }

    return api;
  });
'use strict';

angular.module('myApp.view1', ['ngRoute', 'ui.bootstrap'])

  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/view1', {
      templateUrl: 'view1/view1.html',
      controller: 'View1Ctrl'
    });
  }])

  .controller('View1Ctrl', ['$scope', 'taskservice', 'systeminfo',
    function ($scope, taskservice, systeminfo) {

      console.log("ViewControl");
      $scope.rows = 3;
      $scope.cols = 3;
      $scope.startNodeX = 1;
      $scope.startNodeY = 1;
      $scope.range = 1;
      $scope.matrix = [];

      $scope.error = '';

      $scope.getNeighborHoods = function () {
        getNeighborHoods();
      };

      function getNeighborHoods() {
        $scope.error = "";
        console.log("getNeighborHoods "+$scope.myForm.$valid);
        if(!$scope.myForm.$valid){
          console.error("Input params not valid");
          return;
        }
        taskservice.getNeighborHoods($scope.rows, $scope.cols,
          $scope.startNodeX, $scope.startNodeY, $scope.range).then(
          function (info) {
            console.log(info.data);
            $scope.matrix = info.data.matrix;
          }, function (reason) {
            handleError("NeighborHoods " + reason);
          }
          );
      }

      function checkSystemStatus() {
        systeminfo.getSystemStatus().then(
          function (info) {
            console.log(info.data);
            $scope.status = info.data;
            console.log($scope.status);
          }, function (reason) {
            handleError("SytemInfo " + reason);
          }
        );
      }


      function handleError(reason) {
        $scope.error = reason;
      }

      function init() {
        console.log("init");
        //getNeighborHoods();
      }

      init();
    }]);
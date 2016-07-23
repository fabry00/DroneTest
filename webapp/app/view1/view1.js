'use strict';

angular.module('myApp.view1', ['ngRoute', 'ui.bootstrap'])

  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/view1', {
      templateUrl: 'view1/view1.html',
      controller: 'View1Ctrl'
    });
  }])

  .controller('View1Ctrl', ['$scope', 'taskservice', 'systeminfo', 'socket',
    function ($scope, taskservice, systeminfo, socket) {

      console.log("ViewControl");
      $scope.rows = 7;
      $scope.cols = 7;
      $scope.startNodeX = 3;
      $scope.startNodeY = 3;
      $scope.range = 2;
      $scope.droneconnected = false;
      $scope.dronestatus = "STOPPED";
      $scope.droneposition = { x: -1, y: -1 };
      $scope.matrix = [];
      $scope.neighborhoods = [];
      $scope.disabled = false;

      $scope.error = '';

      $scope.getNeighborHoods = function () {
        getNeighborHoods();
      };

      $scope.startDrone = function () {
        startDrone();
      };

      function getNeighborHoods() {
        $scope.error = "";
        $scope.droneposition = { x: -1, y: -1 };
        console.log("getNeighborHoods " + $scope.myForm.$valid);
        if (!$scope.myForm.$valid) {
          console.error("Input params not valid");
          return;
        }
        taskservice.getNeighborHoods($scope.rows, $scope.cols,
          $scope.startNodeX, $scope.startNodeY, $scope.range).then(
          function (info) {
            console.log(info.data);
            $scope.matrix = info.data.matrix;
            $scope.neighborhoods = info.data.neighborhoods;
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

      function startDrone() {
        console.log("START");
        $scope.droneposition = { x: -1, y: -1 };
        socket.emit('drone:start', {
          matrix: $scope.matrix,
          neighs: $scope.neighborhoods
        });
      }

      socket.on('drone:position', function (message) {        
        $scope.droneposition = message;
        console.log($scope.droneposition);
      });

      socket.on('position', function (data) {
        console.log("Drone new position");
        console.log(data);
      });

      socket.on('dronestatus', function (data) {
        console.log("Drone new status "+data);
        var status = data;
        $scope.dronestatus = status;
        if (status == "MOVING") {
          $scope.disabled = true;
        } else{
           $scope.disabled = false;
        }
        console.log(data);
      });

      socket.on('connect', function () {
        console.log("drone connected");
        $scope.droneconnected = true;

      });

      socket.on('disconnect', function () {
        console.log("drone disconnected");
        $scope.droneconnected = false;
      });

      function init() {
        console.log("init");
      }

      init();
    }]);
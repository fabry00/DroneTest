'use strict';

angular.module('myApp.view1', ['ngRoute', 'ui.bootstrap'])

  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/view1', {
      templateUrl: 'view1/view1.html',
      controller: 'View1Ctrl'
    });
  }])

  .controller('View1Ctrl', ['$scope', 'urbanizationservice', 'socket',
    function ($scope, urbanizationservice, socket) {

      console.log("ViewControl");
      $scope.rows = 7;
      $scope.cols = 7;
      $scope.startNodeX = 3;
      $scope.startNodeY = 3;
      $scope.range = 2;
      $scope.loading = false;
      $scope.droneconnected = false;
      $scope.dronestatus = "STOPPED";
      $scope.droneposition = { x: -1, y: -1 };
      $scope.matrix = [];
      $scope.pools = [];
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
        $scope.loading = true;
        $scope.disabled = true;
        $scope.droneposition = { x: -1, y: -1 };
        $scope.pools = [];
        console.log("getNeighborHoods " + $scope.myForm.$valid);
        if (!$scope.myForm.$valid) {
          console.error("Input params not valid");
          return;
        }
        urbanizationservice.getNeighborHoods($scope.rows, $scope.cols,
          $scope.startNodeX, $scope.startNodeY, $scope.range).then(
          function (info) {
            console.log(info.data);
            $scope.matrix = info.data.matrix;
            $scope.neighborhoods = info.data.neighborhoods;
            $scope.loading = false;
            $scope.disabled = false;
          }, function (reason) {
            handleError("NeighborHoods " + reason);
            $scope.loading = false;
            $scope.disabled = false;
          }
          );
      }

      function handleError(reason) {
        $scope.error = reason;
      }

      function startDrone() {
        console.log("START");
        $scope.droneposition = { x: -1, y: -1 };
        $scope.pools = [];
        socket.emit('drone:start', {
          matrix: $scope.matrix,
          neighs: $scope.neighborhoods
        });
      }

      socket.on('drone:position', function (message) {
        console.log(message);
        $scope.droneposition = message.position;
        if (message.pool) {
          console.log("Pool found");
          $scope.pools.push(message.node);
          console.log("POOL FOUND IN NODE: " + message.node);
        }

      });

      socket.on('dronestatus', function (data) {
        console.log("Drone new status " + data);
        var status = data.status;
        $scope.dronestatus = status;
       // $scope.matrix = data.matrix;
        if (status == "MOVING") {
          $scope.disabled = true;
        } else {
          $scope.disabled = false;
        }
        console.log(data);
      });

      socket.on('connect', function (message) {
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
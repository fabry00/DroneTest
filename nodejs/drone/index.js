var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var port = 3001;
app.get('/', function (req, res) {
    res.sendfile('index.html');
});

var droneStatus = "STOPPED";
var matrix = [];
io.on('connection', function (socket) {
    console.log('a user connected');
    socket.emit('dronestatus', {
        status: droneStatus,
        matrix: matrix
    });

    socket.on('drone:start', function (data) {
        console.log("start drone");
        console.log(data.neighs);
        droneStatus = "MOVING";
        socket.emit('dronestatus', {
            status: droneStatus,
            matrix: matrix
        });
        var counter = 1;
        var sent = 0;
        var coordinates = [];
        for (index in data.neighs) {
            //console.log(node);
            var node = data.neighs[index];
            var x = 0;
            var y = 0;
            var found = false;
            for (row in data.matrix) {

                for (col in data.matrix[row]) {

                    if (data.matrix[row][col] == node) {
                        console.log("Node: " + node + " new position: " + x + "," + y);

                        setTimeout(function (cordx, cordy, mynode) {
                            var pool = (Math.floor((Math.random() * 100) + 1)) > 70 ? true : false;
                            socket.emit('drone:position', {
                                position: {
                                    x: cordx,
                                    y: cordy
                                },
                                pool: pool,
                                node: mynode
                            });
                            sent++;
                            if (sent >= data.neighs.length) {
                                droneStatus = "STOPPED";
                                socket.emit('dronestatus', {
                                    status: droneStatus,
                                    matrix: matrix
                                });
                            }
                        }, counter * 1000, x, y, node);
                        counter++;
                        found = true;
                        break;
                    }
                    y++;
                }
                x++;
                y = 0;
                if (found) { break; }
            }
        }


    });

});




http.listen(port, function () {
    console.log('listening on *:' + port);
});
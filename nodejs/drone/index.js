var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var port = 3001;
app.get('/', function (req, res) {
    res.sendfile('index.html');
});

io.on('connection', function (socket) {
    console.log('a user connected');

    socket.on('drone:start', function (data) {
        console.log("start drone");
        console.log(data.neighs);
        socket.emit('dronestatus', "MOVING");
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
                        
                        setTimeout(function (cordx, cordy) {
                            socket.emit('drone:position', {
                                x: cordx,
                                y: cordy
                            });
                            sent++;
                            if (sent >= data.neighs.length) {
                                socket.emit('dronestatus', "STOPPED");
                            }
                        }, counter * 1000, x, y);
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
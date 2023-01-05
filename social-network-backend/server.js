var app = require('express')();
var http = require('http').createServer(app);
var io = require('socket.io')(http);
require("dotenv").config();
const jwt = require('jsonwebtoken');

var jwtKey = btoa(process.env.SECRET); 
var decodedKey = atob(jwtKey);
const jwtExpirySeconds = 30000;

io.use((socket, next) => {

    const token = socket.handshake.headers["my-jwt"];
    console.log("Secret key: " + jwtKey);
    console.log("Welcome: " + token);
    
    var payload;
    try {
        payload = jwt.verify(token, decodedKey, {
            algorithms: ["HS512"],
        });
        console.log("HEREEEEE");
        next();
    } catch (e) {
    if (e instanceof jwt.JsonWebTokenError) {
        // if the error thrown is because the JWT is unauthorized, return a 401 error
        console.log(e.message);
        console.log("ERROR");
        return res.status(401).end();
    }
    // otherwise, return a bad request error
    console.log("BAD REQUEST");
    console.log(e.message);
    return res.status(400).end();
    }
})


io.on('connection', (socket)=> {
    console.log('User Online');

    socket.on('canvas-data', (data)=> {
        socket.broadcast.emit('canvas-data', data);
    })
})

var server_port = process.env.YOUR_PORT || process.env.PORT || 5000;
http.listen(server_port, () => {
    console.log("Started on : "+ server_port);
})


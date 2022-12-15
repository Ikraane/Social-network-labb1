var app = require('express')();
var http = require('http').createServer(app);
var io = require('socket.io')(http);
import {v4 as uuidv4} from "uuid" 



io.on('connection', (socket)=> {
    console.log('User Online' + socket.id);

    socket.on('canvas-data', (data)=> {
        socket.broadcast.emit('canvas-data', data);
    })
})

var server_port = process.env.YOUR_PORT || process.env.PORT || 5000;
http.listen(server_port, () => {
    console.log("Started on : "+ server_port);
})


//ny
/*
io.use((socket, next) => {
  
    const username = socket.handshake.auth.username;
    if(!username) {
        return next(new Error("Invalid username"));
    } 
    socket.username = username,
    socket.id = uuidv4();
    next();
  });

  io.on('connection', (socket)=> {
    console.log('User Online' + socket.id);
    socket.emit("session", {userId: socket.userId, username: socket.username});
    socket.on('canvas-data', (data)=> {
        socket.broadcast.emit('canvas-data', data);
    })
})*/
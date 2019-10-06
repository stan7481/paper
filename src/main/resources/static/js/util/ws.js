//include libraries
import SockJS from 'sockjs-client'
import { Stomp } from '@stomp/stompjs'

var stompClient = null
const handlers = [];

export function connect() {
    var socket = new SockJS('/gs-guide-websocket')
    stompClient = Stomp.over(socket)
    stompClient.connect({}, frame => {
//        setConnected(true)
//        setConnected(true)
        console.log('Connected: ' + frame)
        stompClient.subscribe('/topic/activity', message => { // connect to channel
            handlers.forEach(handler => handler(JSON.parse(message.body)))
//            showGreeting(JSON.parse(greeting.body).content)
        })
    })
}

export function addHandler(handler) {
    handlers.push(handler)
}

export function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect()
    }
//    setConnected(false)
    console.log("Disconnected")
}

export function sendMessage(message) {
     stompClient.send("/app/changeMessage", {}, JSON.stringify(message))
 }
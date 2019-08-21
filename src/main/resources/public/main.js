/**
 * @var webSocket WebSocket
 */
var webSocket;
$(document).ready(
    function () {

        webSocket = createWebSocket();


        // window.addEventListener('beforeunload', function (e) {
        //     // Cancel the event
        //     //e.preventDefault();
        //     // Chrome requires returnValue to be set
        //     e.returnValue = true;
        //     // alert("de");
        //     webSocket.close();
        // });


        webSocket.onmessage = function (msg) {
            gotMessage(msg);
        };

        webSocket.onclose = function () {
            //("WebSocket connection closed");
        };

        /**
         * @param msg
         *            MessageEvent
         */
        function gotMessage(msg) {
            console.log("Dobio ", msg);
            console.log("Got message from server ", msg.data);
            var action = JSON.parse(msg.data);
            console.log(action);
            if (action.actionName) {
                processAction(action);
            }
        }

        function processAction(a) {
            if (a.actionName === "click") {
                $("#" + a.eleId).on('click', function (event) {
                    var ra = new ReturnAction();
                    ra.serverListenerId = a.serverListenerId;
                    console.log("Saljem click event", event);
                    webSocket.send(JSON.stringify(ra));
                });
            }
        }
    });

function createWebSocket() {
    return new WebSocket("ws://" + location.hostname + ":"
        + location.port + "/state?pageId="+PAGE_ID);

}


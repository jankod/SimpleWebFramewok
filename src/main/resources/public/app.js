class EL {
    elements;
    pageId;

    constructor(pageId) {
        this.pageId = pageId;
        this.elements = new Map();
    }

    createElement(elId, html) {
        let el = $(html);
        this.elements.set(elId, el);
        return el;
    }

    /**
     *
     * @param elId string
     * @returns {JQuery}
     */
    getEl(elId) {
        let el = this.elements.get(elId);
        if (el === undefined) {
            let el = $("#" + elId);
            if (el.length !== 1) {
                // not exist,
                console.warn("Element " + elId + " not exist. yet...")
                //throw Error("Not find element ID: " + elId);
                return null; // $("#"+elId);
            }

            return el;
        }
        return el;
    }

    appendChild(elId, elIdChild) {
        this.getEl(elId).append(this.getEl(elIdChild));
    }

    appendToBody(elId) {
        this.getEl(elId).appendTo('body');
    }

    appendText(elId, text) {
        this.getEl(elId).append(document.createTextNode(text));
    }

    text(elId, text) {
        this.getEl(elId).text(text);
    }

    addClass(elId, className) {
        this.getEl(elId).addClass(className);
    }

    setClickEvent(elId, listenerId) {
       // console.log("Command setClickEvent", elId, listenerId);
        let handler = () => {
            console.log("event", elId + " " + listenerId);
            this.sendToServer({'event': 'event neki'}, elId, listenerId);
        };

        this.onEvent('click', elId, handler);
    }

    onEvent(eventName, elId, handler) {
        let el = this.getEl(elId);
        if (el == null) {
            $('body').on(eventName, '#' + elId, handler);
        } else {
            el.on('click', handler);
        }
    }

    html(elId, html) {
        this.getEl(elId).html(html);
    }

    alert(msg) {
        alert(msg);
    }

    evalExec(elId, exec) {
        eval(exec);
    }

    attr(elId, name, value) {
        this.getEl(elId).attr(name, value);
    }

    callJquery(elId, ...params) {
       console.log("params ", params);
        this.getEl(elId)[params[0]]();
    }

    bootbox_alert(msg) {
        bootbox.alert(msg);
    }
    bootbox_alert(title, msg) {
        console.log("title  "+title + " msg "+ msg);
        bootbox.alert({
            message: msg,
            title: title,
            backdrop: true
        });
    }

    sendToServer(data, elementId, listenerId) {
        let self = this;
        $.ajax({
            type: 'post',
            url: '/page_event',
            data: data,
            headers: {
                "pageId": this.pageId,
                "elementId": elementId,
                "listenerId": listenerId
            },
            error: function (data) {
                console.debug(data);
            },
            success: function (response) {
                if (response === "") {
                    console.log("GOT empty response");
                    return;
                }
                const commands = JSON.parse(response);
                console.log("Commands GOT ", commands);
                self.runCommands(commands);
            }
        });
    }

    runCommands(commands) {
        commands.forEach(command => {
            console.log("command  ", command);
            let res = this[command.funcName].apply(this, command.args);
        });
    }
}
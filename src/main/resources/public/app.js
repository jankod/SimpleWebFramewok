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
            if (el.length !== 1)
                throw Error("Not find element ID: " + elId);

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
        console.log("Command setClickEvent", elId, listenerId);
        let handler = () => {
            // console.log("this", this);
            this.sendToServer({'event': 'event neki'}, elId, listenerId);
        };
        $("#" + elId).on('click', handler);
    }

    html(elId, html) {
        this.getEl(elId).html(html);
    }
    alert(msg) {
        alert(msg);
    }
    attr(elId, name, value) {
        this.getEl(elId).attr(name, value);
    }

    callJquery(elId, params) {
        console.log("method name ", params);
        //debugger;
        //let methodName = params.shift();
        this.getEl(elId)[params]();
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


//let el = new EL();
/*
let e = new EL();
let args = ['2', '<p>'];
let res = e['createElement'].apply(args);
console.log(res);
*/
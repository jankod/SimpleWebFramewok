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
        //return "EL";
    }

    /**
     *
     * @param elId string
     * @returns {JQuery}
     */
    getEl(elId) {
        let el = this.elements.get(elId);
        if (el === undefined) {
            throw Error("Not find element " + elId);
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

    addClass(elId, className) {
        this.getEl(elId).addClass(className);
    }

    setClickEvent(elId, listenerId) {
        console.log("setClickEvent", elId, listenerId);
        let handler = () => {
            console.log("this", this);
            this.sendToServer({'event': 'event neki'}, elId, listenerId);
        };
        $("#" + elId).on('click', handler);
    }

    sendToServer(data, elementId, listenerId) {
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
                this.runCommands(commands);
            }
        });
    }

    runCommands(commands) {
        commands.forEach(command => {

            console.log("event commands ", command);
            let res = this[command.funcName].apply(this, command.args);
        });
    }
}

/*
let e = new EL();
let args = ['2', '<p>'];
let res = e['createElement'].apply(args);
console.log(res);
*/
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Page</title>
    <script src="jquery-3.4.1.js"></script>
</head>
<body>

<h2>Ovo je naslov</h2>


demo 233 ${pero}

<script type="module">

    let command = $command;


    function exeCommnad(command) {
        let f = new MyFunctions();

        let res = f[command.funcName].apply(this, command.args);

        console.log("Result: "+ res);
    }


    class MyFunctions {
        func1(param1, param2, param3) {
            console.log("pozvao func 1 ");
            console.log("Args ", arguments);

            console.log("param1 ", param1);
            console.log("param2 ", param2);
            console.log("param3 ", param3);
        }
    }


    $(function () {
        console.log("zasto!");
        exeCommnad(command);
    });

</script>
</body>
</html>
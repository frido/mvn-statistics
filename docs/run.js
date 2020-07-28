fs = require('fs');
var md = require('markdown-it')();

fs.readFile('../README.md', "utf8", function(err, data) {
    var result = md.render(data);
    fs.writeFile('index.html', result, function (err) {
        if (err) return console.log(err);
    });
});

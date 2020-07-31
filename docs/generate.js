"use strict";
exports.__esModule = true;
var markdownIt = require("markdown-it");
var fs_1 = require("fs");
var Mustache = require("mustache");
var mdContent = fs_1.readFileSync('../README.md', 'utf-8');
var htmlBase = fs_1.readFileSync('main.html', 'utf-8');
var htmlContent = markdownIt().render(mdContent);
var view = {
    'content': htmlContent
};
var output = Mustache.render(htmlBase, view);
var re = /<table>/gi;
output = output.replace(re, '<table class="table">'); // TODO: Tricky
fs_1.writeFileSync('index.html', output);

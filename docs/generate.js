"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
exports.__esModule = true;
var markdown_it_1 = __importDefault(require("markdown-it"));
var fs_1 = require("fs");
var mustache_1 = __importDefault(require("mustache"));
var mdContent = fs_1.readFileSync('../README.md', 'utf-8');
var htmlBase = fs_1.readFileSync('main.html', 'utf-8');
var htmlContent = markdown_it_1["default"]().render(mdContent);
var view = {
    'content': htmlContent
};
var output = mustache_1["default"].render(htmlBase, view);
var re = /<table>/gi;
output = output.replace(re, '<table class="table">'); // TODO: Tricky
fs_1.writeFileSync('index.html', output);

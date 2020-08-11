"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
exports.__esModule = true;
exports.config = void 0;
var markdown_it_1 = __importDefault(require("markdown-it"));
var fs_1 = require("fs");
var mdContent = fs_1.readFileSync('../README.md', 'utf-8');
var htmlContent = markdown_it_1["default"]().render(mdContent);
var view = {
    'content': htmlContent
};
exports.config = {
    layout: 'index/main.html',
    view: view,
    output: '../docs/index.html'
};

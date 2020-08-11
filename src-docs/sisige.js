"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
exports.__esModule = true;
var index_1 = require("./index/index");
var github_1 = require("./github/github");
var fs_1 = require("fs");
var mustache_1 = __importDefault(require("mustache"));
var generate = function (x) {
    var layoutContent = fs_1.readFileSync(x.layout, 'utf-8');
    var outputContent = mustache_1["default"].render(layoutContent, x.view);
    fs_1.writeFileSync(x.output, outputContent);
};
var queue = [];
queue.push(index_1.config);
queue.push(github_1.config);
queue.forEach(function (x) { return generate(x); });

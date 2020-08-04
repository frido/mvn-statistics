"use strict";
exports.__esModule = true;
var fs_1 = require("fs");
var Mustache = require("mustache");
var json = require("../report/github.json");
var htmlBase = fs_1.readFileSync('github.html', 'utf-8');
var output = Mustache.render(htmlBase, json);
fs_1.writeFileSync('github-out.html', output);

"use strict";
var __values = (this && this.__values) || function(o) {
    var s = typeof Symbol === "function" && Symbol.iterator, m = s && o[s], i = 0;
    if (m) return m.call(o);
    if (o && typeof o.length === "number") return {
        next: function () {
            if (o && i >= o.length) o = void 0;
            return { value: o && o[i++], done: !o };
        }
    };
    throw new TypeError(s ? "Object is not iterable." : "Symbol.iterator is not defined.");
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
var _a, _b, _c;
exports.__esModule = true;
var fs_1 = require("fs");
var mustache_1 = __importDefault(require("mustache"));
var github_json_1 = __importDefault(require("../github/github.json"));
var github_json_2 = __importDefault(require("../data/github.json"));
var dependencyGroup_json_1 = __importDefault(require("../report/dependencyGroup.json"));
var githubRepo = github_json_1["default"];
// Get top of github
var sorter = function (a, b) { return (b.score) - (a.score); };
// get rid of duplicities
var view = githubRepo.data; //?.slice(0, 10);
var tmpView = new Map();
view === null || view === void 0 ? void 0 : view.forEach(function (githubRepoItem) {
    var _a;
    tmpView.set((_a = githubRepoItem.full_name) !== null && _a !== void 0 ? _a : '', githubRepoItem);
});
view = Array.from(tmpView.values());
// Join with pom info from github list
// TODO: fix java report -> remove duplicities, join poms
var githubList = github_json_2["default"];
var githubListMap = new Map();
(_a = githubList.data) === null || _a === void 0 ? void 0 : _a.forEach(function (item) {
    var _a, _b;
    githubListMap.set((_b = (_a = item.fullName) === null || _a === void 0 ? void 0 : _a.toLowerCase()) !== null && _b !== void 0 ? _b : '', item);
});
// generate dependency map
var dependencyGroup = dependencyGroup_json_1["default"];
var dependencyMap = new Map();
(_c = (_b = dependencyGroup.data) === null || _b === void 0 ? void 0 : _b.list) === null || _c === void 0 ? void 0 : _c.forEach(function (g) {
    var _a;
    (_a = g.childs) === null || _a === void 0 ? void 0 : _a.forEach(function (a) {
        var _a, _b, _c;
        var key = ((_a = g.name) === null || _a === void 0 ? void 0 : _a.toLowerCase()) + ":" + ((_b = a.name) === null || _b === void 0 ? void 0 : _b.toLowerCase());
        dependencyMap.set(key, (_c = a.value) !== null && _c !== void 0 ? _c : 0);
    });
});
view === null || view === void 0 ? void 0 : view.forEach(function (githubRepoItem) {
    var e_1, _a;
    var _b, _c, _d, _e, _f, _g;
    var githubListItem = githubListMap.get((_c = (_b = githubRepoItem.full_name) === null || _b === void 0 ? void 0 : _b.toLowerCase()) !== null && _c !== void 0 ? _c : '');
    var pomGroupMap = new Map();
    githubRepoItem.poms = [];
    githubRepoItem.usages = 0;
    (_d = githubListItem === null || githubListItem === void 0 ? void 0 : githubListItem.poms) === null || _d === void 0 ? void 0 : _d.forEach(function (p) {
        var _a, _b;
        var key = (_b = (_a = p.groupId) === null || _a === void 0 ? void 0 : _a.toLowerCase()) !== null && _b !== void 0 ? _b : '';
        var valueList = pomGroupMap.get(key);
        if (valueList) {
            valueList.push(p);
        }
        else {
            valueList = [];
            valueList.push(p);
        }
        pomGroupMap.set(key, valueList);
    });
    var _loop_1 = function (k) {
        var value = 0;
        var debug = [];
        (_e = pomGroupMap.get(k)) === null || _e === void 0 ? void 0 : _e.forEach(function (i) {
            var _a, _b, _c;
            var key = ((_a = i.groupId) === null || _a === void 0 ? void 0 : _a.toLowerCase()) + ":" + ((_b = i.artifactId) === null || _b === void 0 ? void 0 : _b.toLowerCase());
            var addon = ((_c = dependencyMap.get(key)) !== null && _c !== void 0 ? _c : 0);
            value = value + addon;
            debug.push(addon);
        });
        (_f = githubRepoItem.poms) === null || _f === void 0 ? void 0 : _f.push({ groupId: k, value: value });
        githubRepoItem.usages = githubRepoItem.usages + value;
    };
    try {
        // console.log(pomGroupMap.keys());
        for (var _h = __values(pomGroupMap.keys()), _j = _h.next(); !_j.done; _j = _h.next()) {
            var k = _j.value;
            _loop_1(k);
        }
    }
    catch (e_1_1) { e_1 = { error: e_1_1 }; }
    finally {
        try {
            if (_j && !_j.done && (_a = _h["return"])) _a.call(_h);
        }
        finally { if (e_1) throw e_1.error; }
    }
    githubRepoItem.score = ((_g = githubRepoItem.stargazers_count) !== null && _g !== void 0 ? _g : 0) + githubRepoItem.usages;
    // console.log(githubRepoItem.full_name);
    // console.log(githubRepoItem.poms); 
    // console.log(githubRepoItem.usages); 
});
var htmlBase = fs_1.readFileSync('github.html', 'utf-8');
view = view === null || view === void 0 ? void 0 : view.sort(sorter).slice(0, 500);
var output = mustache_1["default"].render(htmlBase, { data: view });
fs_1.writeFileSync('github-out.html', output);

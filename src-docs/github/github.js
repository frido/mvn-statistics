"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
var _a, _b, _c, _d;
exports.__esModule = true;
var fs_1 = require("fs");
var mustache_1 = __importDefault(require("mustache"));
var github_json_1 = __importDefault(require("../github/github.json"));
var github_json_2 = __importDefault(require("../data/github.json"));
var dependencyGroup_json_1 = __importDefault(require("../report/dependencyGroup.json"));
var githubRepo = github_json_1["default"];
// Get top of github
var sorter = function (a, b) { var _a, _b; return ((_a = b.score) !== null && _a !== void 0 ? _a : 0) - ((_b = a.score) !== null && _b !== void 0 ? _b : 0); };
// TODO: get rid of duplicities
var view = (_a = githubRepo.data) === null || _a === void 0 ? void 0 : _a.filter(function (g) { return g.full_name; }); //.slice(0, 10);
// Join with pom info from github list
// TODO: fix java report -> remove duplicities, join poms
var githubList = github_json_2["default"];
var githubListMap = new Map();
(_b = githubList.data) === null || _b === void 0 ? void 0 : _b.forEach(function (item) {
    var _a, _b;
    githubListMap.set((_b = (_a = item.fullName) === null || _a === void 0 ? void 0 : _a.toLowerCase()) !== null && _b !== void 0 ? _b : '', item);
});
// generate dependency map
var dependencyGroup = dependencyGroup_json_1["default"];
var dependencyGroupMap = new Map();
(_d = (_c = dependencyGroup.data) === null || _c === void 0 ? void 0 : _c.list) === null || _d === void 0 ? void 0 : _d.forEach(function (g) {
    var _a;
    (_a = g.childs) === null || _a === void 0 ? void 0 : _a.forEach(function (a) {
        var _a, _b, _c;
        var key = ((_a = g.name) === null || _a === void 0 ? void 0 : _a.toLowerCase()) + ":" + ((_b = a.name) === null || _b === void 0 ? void 0 : _b.toLowerCase());
        dependencyGroupMap.set(key, (_c = a.value) !== null && _c !== void 0 ? _c : 0);
    });
});
view === null || view === void 0 ? void 0 : view.forEach(function (githubRepoItem) {
    var _a, _b, _c, _d, _e, _f;
    githubRepoItem.poms = [];
    githubRepoItem.usages = 0;
    // const pomGroupMap: Map<String, GithubListItemPom[]> = pomGroupToMap(githubRepoItem);
    // console.log(githubRepoItem.full_name);
    // console.log(pomGroupMap.keys());
    var githubListItem = githubListMap.get((_b = (_a = githubRepoItem.full_name) === null || _a === void 0 ? void 0 : _a.toLowerCase()) !== null && _b !== void 0 ? _b : '');
    githubRepoItem.pomCount = (_c = githubListItem === null || githubListItem === void 0 ? void 0 : githubListItem.poms) === null || _c === void 0 ? void 0 : _c.length;
    (_d = githubListItem === null || githubListItem === void 0 ? void 0 : githubListItem.poms) === null || _d === void 0 ? void 0 : _d.forEach(function (p) {
        var _a, _b, _c, _d, _e, _f;
        var key = ((_a = p.groupId) === null || _a === void 0 ? void 0 : _a.toLowerCase()) + ":" + ((_b = p.artifactId) === null || _b === void 0 ? void 0 : _b.toLowerCase());
        var value = ((_c = dependencyGroupMap.get(key)) !== null && _c !== void 0 ? _c : 0);
        githubRepoItem.usages = ((_d = githubRepoItem.usages) !== null && _d !== void 0 ? _d : 0) + value;
        (_e = githubRepoItem.poms) === null || _e === void 0 ? void 0 : _e.push({
            artifactId: p.artifactId,
            groupId: p.groupId,
            pomLink: p.pomLink,
            value: value
        });
        githubRepoItem.poms = (_f = githubRepoItem.poms) === null || _f === void 0 ? void 0 : _f.sort(function (a, b) {
            var _a, _b;
            return ((_a = b.value) !== null && _a !== void 0 ? _a : 0) - ((_b = a.value) !== null && _b !== void 0 ? _b : 0);
        }).slice(0, 3);
    });
    // for(let groupId of pomGroupMap.keys()) {
    //     let value = 0;
    //     let debug: number[] = []
    //     pomGroupMap.get(groupId)?.forEach(i => {
    //         const key = i.groupId?.toLowerCase() + ":" + i.artifactId?.toLowerCase();
    //         const addon = (dependencyGroupMap.get(key) ?? 0);
    //         value = value + addon;
    //         githubRepoItem.pomCount = (githubRepoItem.pomCount ?? 0) + 1;
    //         // debug.push(addon);
    //     });
    //     githubRepoItem.poms?.push({groupId: groupId, value: value});
    //     githubRepoItem.usages = githubRepoItem.usages + value;
    //     // console.log(groupId); 
    //     // console.log(debug); 
    //     // console.log(pomGroupMap.get(groupId)?.length); 
    //     // const githubListItem = githubListMap.get(githubRepoItem.full_name?.toLowerCase() ?? '');
    //     // console.log(githubListItem?.poms?.length); 
    // }
    githubRepoItem.score = ((((_e = githubRepoItem.stargazers_count) !== null && _e !== void 0 ? _e : 0) * 0) + (githubRepoItem.usages / ((_f = githubRepoItem.pomCount) !== null && _f !== void 0 ? _f : 1)));
    // console.log(githubRepoItem.full_name);
    // console.log(githubRepoItem.poms); 
    // console.log(githubRepoItem.usages); 
    // console.log(githubRepoItem.pomCount); 
    // console.log(githubRepoItem.score); 
});
var htmlBase = fs_1.readFileSync('github.html', 'utf-8');
view = view === null || view === void 0 ? void 0 : view.sort(sorter).slice(0, 500);
var output = mustache_1["default"].render(htmlBase, { data: view });
fs_1.writeFileSync('github-out.html', output);
function pomGroupToMap(githubRepoItem) {
    var _a, _b, _c;
    var githubListItem = githubListMap.get((_b = (_a = githubRepoItem.full_name) === null || _a === void 0 ? void 0 : _a.toLowerCase()) !== null && _b !== void 0 ? _b : '');
    var pomGroupMap = new Map();
    (_c = githubListItem === null || githubListItem === void 0 ? void 0 : githubListItem.poms) === null || _c === void 0 ? void 0 : _c.forEach(function (p) {
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
    return pomGroupMap;
}

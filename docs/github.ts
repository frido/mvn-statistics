
import {readFileSync, writeFileSync} from 'fs';
import Mustache from 'mustache';

import githubRepoJson from '../github/github.json'
import githubListJson from '../data/github.json'
import dependencyGroupJson from '../report/dependencyGroup.json'
import { GithubRepo, GithubRepoItem, GithubList, DependencyGroup, GithubListItem, GithubListItemPom } from "./typings";

const githubRepo: GithubRepo = githubRepoJson;

// Get top of github
const sorter = (a:GithubRepoItem, b:GithubRepoItem) => (b.score) - (a.score);

// get rid of duplicities
let view = githubRepo.data;//?.slice(0, 10);
const tmpView: Map<String, GithubRepoItem> = new Map();
view?.forEach(githubRepoItem => {
    tmpView.set(githubRepoItem.full_name ?? '', githubRepoItem);
})
view = Array.from(tmpView.values());

// Join with pom info from github list
// TODO: fix java report -> remove duplicities, join poms
const githubList: GithubList = githubListJson;
const githubListMap: Map<String, GithubListItem> = new Map();
githubList.data?.forEach(item => {
    githubListMap.set(item.fullName?.toLowerCase() ?? '', item);
});

// generate dependency map
const dependencyGroup: DependencyGroup = dependencyGroupJson;
const dependencyMap: Map<String, number> = new Map();
dependencyGroup.data?.list?.forEach(g => {
    g.childs?.forEach(a => {
        const key = g.name?.toLowerCase() + ":" + a.name?.toLowerCase();
        dependencyMap.set(key, a.value ?? 0);
    })
})

view?.forEach(githubRepoItem => {
    const githubListItem = githubListMap.get(githubRepoItem.full_name?.toLowerCase() ?? '');
    const pomGroupMap: Map<String, GithubListItemPom[]> = new Map();
    githubRepoItem.poms = [];
    githubRepoItem.usages = 0;
    githubListItem?.poms?.forEach(p => {
        const key = p.groupId?.toLowerCase() ?? '';
        let valueList = pomGroupMap.get(key);
        if (valueList) {
            valueList.push(p);
        } else {
            valueList = [];
            valueList.push(p);
        }
        pomGroupMap.set(key, valueList);
    })

// console.log(pomGroupMap.keys());
    
    for(let k of pomGroupMap.keys()) {
        let value = 0;
        let debug: number[] = []
        pomGroupMap.get(k)?.forEach(i => {
            const key = i.groupId?.toLowerCase() + ":" + i.artifactId?.toLowerCase();
            const addon = (dependencyMap.get(key) ?? 0);
            value = value + addon;
            debug.push(addon);
        });
        githubRepoItem.poms?.push({groupId: k, value: value});
        githubRepoItem.usages = githubRepoItem.usages + value;
        // console.log(k); 
        // console.log(debug); 
    }

    githubRepoItem.score = (githubRepoItem.stargazers_count ?? 0) + githubRepoItem.usages

// console.log(githubRepoItem.full_name);
// console.log(githubRepoItem.poms); 
// console.log(githubRepoItem.usages); 
})

let htmlBase = readFileSync('github.html', 'utf-8');

view = view?.sort(sorter).slice(0, 500);
let output = Mustache.render(htmlBase, {data: view});

writeFileSync('github-out.html', output);
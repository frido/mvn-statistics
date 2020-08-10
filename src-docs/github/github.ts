
import {readFileSync, writeFileSync} from 'fs';
import Mustache from 'mustache';

import githubRepoJson from '../github/github.json'
import githubListJson from '../data/github.json'
import dependencyGroupJson from '../report/dependencyGroup.json'
import { GithubRepo, GithubRepoItem, GithubList, DependencyGroup, GithubListItem, GithubListItemPom, GithubRepoItemPom } from "./typings";

const githubRepo: GithubRepo = githubRepoJson;

// Get top of github
const sorter = (a:GithubRepoItem, b:GithubRepoItem) => (b.score ?? 0) - (a.score ?? 0);

// TODO: get rid of duplicities
let view = githubRepo.data?.filter(g => g.full_name );//.slice(0, 10);

// Join with pom info from github list
// TODO: fix java report -> remove duplicities, join poms
const githubList: GithubList = githubListJson;
const githubListMap: Map<String, GithubListItem> = new Map();
githubList.data?.forEach(item => {
    githubListMap.set(item.fullName?.toLowerCase() ?? '', item);
});

// generate dependency map
const dependencyGroup: DependencyGroup = dependencyGroupJson;
const dependencyGroupMap: Map<String, number> = new Map();
dependencyGroup.data?.list?.forEach(g => {
    g.childs?.forEach(a => {
        const key = g.name?.toLowerCase() + ":" + a.name?.toLowerCase();
        dependencyGroupMap.set(key, a.value ?? 0);
    })
})


view?.forEach(githubRepoItem => {
    githubRepoItem.poms = [];
    githubRepoItem.usages = 0;
    

    // const pomGroupMap: Map<String, GithubListItemPom[]> = pomGroupToMap(githubRepoItem);

    // console.log(githubRepoItem.full_name);
    // console.log(pomGroupMap.keys());

    const githubListItem = githubListMap.get(githubRepoItem.full_name?.toLowerCase() ?? '');
    githubRepoItem.pomCount = githubListItem?.poms?.length;
    githubListItem?.poms?.forEach(p => {
        const key = p.groupId?.toLowerCase() + ":" + p.artifactId?.toLowerCase();
        const value = (dependencyGroupMap.get(key) ?? 0);
        githubRepoItem.usages = (githubRepoItem.usages ?? 0) + value;
        githubRepoItem.poms?.push({
            artifactId: p.artifactId,
            groupId: p.groupId,
            pomLink: p.pomLink,
            value: value
        });
        githubRepoItem.poms = githubRepoItem.poms?.sort((a: GithubRepoItemPom , b: GithubRepoItemPom) => {
            return (b.value ?? 0) - (a.value ?? 0);
        }).slice(0,3);
    })

    
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

    githubRepoItem.score = (((githubRepoItem.stargazers_count ?? 0) * 0) + (githubRepoItem.usages / (githubRepoItem.pomCount ?? 1)));

// console.log(githubRepoItem.full_name);
// console.log(githubRepoItem.poms); 
// console.log(githubRepoItem.usages); 
// console.log(githubRepoItem.pomCount); 
// console.log(githubRepoItem.score); 
})

let htmlBase = readFileSync('github.html', 'utf-8');

view = view?.sort(sorter).slice(0, 500);
let output = Mustache.render(htmlBase, {data: view});

writeFileSync('github-out.html', output);

function pomGroupToMap(githubRepoItem: GithubRepoItem) {
    const githubListItem = githubListMap.get(githubRepoItem.full_name?.toLowerCase() ?? '');
    const pomGroupMap: Map<String, GithubListItemPom[]> = new Map();
    githubListItem?.poms?.forEach(p => {
        const key = p.groupId?.toLowerCase() ?? '';
        let valueList = pomGroupMap.get(key);
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

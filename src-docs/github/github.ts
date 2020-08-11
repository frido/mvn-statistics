
import githubRepoJson from '../../github/github.json'
import githubListJson from '../../data/github.json'
import dependencyGroupJson from '../../report/dependencyGroup.json'
import { GithubRepo, GithubRepoItem, GithubList, DependencyGroup, GithubListItem, GithubListItemPom, GithubRepoItemPom } from "../typings";
import { SiSiGeConfig, DependencyGroupItem } from '../typings';

const githubRepo: GithubRepo = githubRepoJson;

// Get top of github
const sorter = (a:GithubRepoItem, b:GithubRepoItem) => (b.score ?? 0) - (a.score ?? 0);

// TODO: get rid of duplicities
let view = githubRepo.data?.filter((g: GithubRepoItem) => g.full_name );//.slice(0, 10);

// Join with pom info from github list
// TODO: fix java report -> remove duplicities, join poms
const githubList: GithubList = githubListJson;
const githubListMap: Map<String, GithubListItem> = new Map();
githubList.data?.forEach((item: GithubListItem) => {
    githubListMap.set(item.fullName?.toLowerCase() ?? '', item);
});

// generate dependency map
const dependencyGroup: DependencyGroup = dependencyGroupJson;
const dependencyGroupMap: Map<String, number> = new Map();
dependencyGroup.data?.list?.forEach((g: DependencyGroupItem) => {
    g.childs?.forEach(a => {
        const key = g.name?.toLowerCase() + ":" + a.name?.toLowerCase();
        dependencyGroupMap.set(key, a.value ?? 0);
    })
})

view?.forEach((githubRepoItem: GithubRepoItem) => {
    githubRepoItem.poms = [];
    githubRepoItem.usages = 0;

    const githubListItem = githubListMap.get(githubRepoItem.full_name?.toLowerCase() ?? '');
    githubRepoItem.pomCount = githubListItem?.poms?.length;
    githubListItem?.poms?.forEach((p: GithubListItemPom) => {
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

    githubRepoItem.score = (((githubRepoItem.stargazers_count ?? 0) * 0) + (githubRepoItem.usages / (githubRepoItem.pomCount ?? 1)));
})


view = view?.sort(sorter).slice(0, 500);

export const config: SiSiGeConfig =  {
    layout: 'github/github.html',
    view: {data: view},
    output: '../docs/github.html'
}

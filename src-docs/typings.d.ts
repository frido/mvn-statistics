
export interface GithubRepo {
    data?: GithubRepoItem[];
}

export interface GithubRepoItem {
    full_name?: String | null;
    description?: String | null;
    stargazers_count?: number | null;
    watchers_count?: number | null;
    language?: String | null;
    forks_count?: number | null;
    network_count?: number | null;
    subscribers_count?: number | null;
    groups?: GithubRepoItemGroup[],
    poms?: GithubRepoItemPom[]
    usages?: number;
    score?: number;
    pomCount?: number;
}

export interface GithubList {
    data?: GithubListItem[];
}

export interface GithubListItem {
    fullName?: String;
    poms?: GithubListItemPom[]
}

export interface GithubListItemPom {
    pomLink?: String | null;
    groupId?: String | null;
    artifactId?: String | null;
}

export interface GithubRepoItemPom {
    pomLink?: String | null;
    groupId?: String | null;
    artifactId?: String | null;
    value?: number | null;
}

export interface GithubRepoItemGroup {
    groupId?: String | null;
    value?: number | null;
}

export interface DependencyGroup {
    data?: DependencyGroupContent;
}

export interface DependencyGroupContent {
    list?: DependencyGroupItem[];
    sum?: number;
}

export interface DependencyGroupItem {
    name?: String | null;
    value?: number | null;
    childs?: DependencyGroupItemChild[]
}

export interface DependencyGroupItemChild {
    name?: String | null;
    value?: number | null;
}


// declare module "*.json" {
//     const value: GithubRepo;
//     export default value;
// }
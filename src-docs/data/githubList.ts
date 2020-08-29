import githubListJson from '../../data/github.json'

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

const githubList: GithubList = githubListJson;

class GithubListApi {
    data: Map<String, GithubListItem> = new Map();
    
    constructor(input: GithubList) {
        this.data = this.toMap(input);
    }

    private toMap(data: GithubList): Map<String, GithubListItem> {
        const map = new Map<String, GithubListItem>();
        data.data?.forEach(i => map.set(i.fullName?.toLowerCase() ?? '', i));
        return map;
    }

    public get(key: String) {
        return this.data.get(key);
    }
}

const githubListApi = new GithubListApi(githubList);
export default githubListApi;
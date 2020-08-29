import githubRepoJson from '../../github/github.json'

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
    subscribers_count?: number | null
}

const githubRepo: GithubRepo = githubRepoJson;

class GithubRepoApi {
    data: GithubRepoItem[];
    
    constructor(input: GithubRepo) {
        this.data = this.removeDiplicities(input);
    }

    private removeDiplicities(data: GithubRepo): GithubRepoItem[] {
        const map = new Map<String, GithubRepoItem>();
        data.data?.forEach(i => map.set(i.full_name?.toLowerCase() ?? '', i));
        return Array.from(map.values());
    }

    public list() {
        return this.data;
    }
}

const githubRepoApi = new GithubRepoApi(githubRepo);
export default githubRepoApi;
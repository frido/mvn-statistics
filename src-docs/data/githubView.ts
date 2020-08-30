import githubRepoApi, { GithubRepoItem } from "./githubRepo";
import githubListApi, { GithubListItemPom } from "./githubList";
import dependencyGroupApi from "./dependencyGroup";

class GithubPom {
  pomLink?: String | null;
  groupId?: String | null;
  artifactId?: String | null;
  value: number;

  constructor(input: GithubListItemPom) {
    this.pomLink = input.pomLink;
    this.artifactId = input.artifactId;
    this.groupId = input.groupId;
    this.value = 0;
  }
}

interface GithubView {
  data: GithubViewItem[];
}

class GithubViewItem {
  full_name?: String | null;
  description?: String | null;
  stargazers_count?: number | null;
  watchers_count?: number | null;
  language?: String | null;
  forks_count?: number | null;
  network_count?: number | null;
  subscribers_count?: number | null;
  poms?: GithubPom[];
  usages?: number;
  score?: number;
  pomCount?: number;

  constructor(input: GithubRepoItem) {
    this.description = input.description;
    this.forks_count = input.forks_count;
    this.full_name = input.full_name;
    this.language = input.language;
    this.network_count = input.network_count;
    this.stargazers_count = input.stargazers_count;
    this.subscribers_count = input.subscribers_count;
    this.watchers_count = input.watchers_count;
  }

  setPoms(poms: GithubPom[], limit: number) {
    this.pomCount = poms.length;
    this.usages = poms.map((x) => x.value).reduce((sum, current) => sum + current, 0);
    this.poms = poms
      .sort((a: GithubPom, b: GithubPom) => {
        return (b.value ?? 0) - (a.value ?? 0);
      })
      .splice(0, limit);
  }

  calcScore() {
    const stargazers_count = this.stargazers_count ?? 0;
    const usage = (this.usages ?? 0) / (this.pomCount ?? 1);
    const stars = stargazers_count * 0.01;
    const forks = (this.forks_count ?? 0) * 0.1;
    const subscribers = (this.subscribers_count ?? 0) * 0.5;
    const factor = usage;
    this.score = (stars + forks + subscribers) * factor;
  }
}

let view = githubRepoApi
  .list()
  .map((x) => new GithubViewItem(x))
  .map((viewItem: GithubViewItem) => {
    const githubListItem = githubListApi.get(viewItem.full_name?.toLowerCase() ?? "");
    const poms =
      githubListItem?.poms?.map((pom) => {
        const newPom = new GithubPom(pom);
        newPom.value = dependencyGroupApi.get(newPom.groupId, newPom.artifactId) ?? 0;
        return newPom;
      }) ?? [];
    viewItem.setPoms(poms, 3);
    viewItem.calcScore();
    return viewItem;
  })
  .sort((a: GithubViewItem, b: GithubViewItem) => (b.score ?? 0) - (a.score ?? 0))
  .slice(0, 500);

const data: GithubView = {data: view};
export default data;
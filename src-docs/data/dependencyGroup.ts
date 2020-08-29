import dependencyGroupJson from '../../report/dependencyGroup.json'

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

const dependencyGroup: DependencyGroup = dependencyGroupJson;

class DependencyGroupApi {
    data: Map<String, number> = new Map();
    
    constructor(input: DependencyGroup) {
        this.data = this.toMap(input);
    }

    private toMap(data: DependencyGroup): Map<String, number> {
        const map = new Map<String, number>();
        data.data?.list?.forEach(group => {
            group.childs?.forEach(artifact => {
                const key = this.key(group.name, artifact.name);
                map.set(key, artifact.value ?? 0);
            })
        })
        return map;
    }

    public get(groupId?: String | null, artifactId?: String | null) {
        const key = this.key(groupId, artifactId);
        return this.data.get(key);
    }

    private key(groupId?: String | null, artifactId?: String | null) {
        return groupId?.toLowerCase() + ":" + artifactId?.toLowerCase();
    }
}

const dependencyGroupApi = new DependencyGroupApi(dependencyGroup);
export default dependencyGroupApi;
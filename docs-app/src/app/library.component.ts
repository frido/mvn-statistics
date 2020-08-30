import { Component, Input } from "@angular/core";

@Component({
  selector: "app-library",
  template: `
    <div class="card mb-3 border-0">
      <div class="card-body">
        <a href="https://github.com/{{library.full_name}}" target="blank">
          <h4 class="card-title">{{library.full_name}}</h4>
        </a>
        <p class="card-subtitle text-muted mb-2 mt-1">
          <span class="mr-4">
            <i class="far fa-star" aria-hidden="true"></i>
            <span class="ml-1">{{library.stargazers_count}}</span>
          </span>
          <span class="mr-4">
            <i class="fas fa-code-branch" aria-hidden="true"></i>
            <span class="ml-1">{{library.network_count}}</span>
          </span>
          <span class="mr-4">
            <i class="far fa-bell" aria-hidden="true"></i>
            <span class="ml-1">{{library.subscribers_count}}</span>
          </span>
          <span class="mr-4">
            <i class="fab fa-codepen" aria-hidden="true"></i>
            <span class="ml-1">{{library.usages}}</span>
          </span>
        </p>

        <p class="card-text mb-1">{{library.description}}</p>
        <ng-container *ngFor="let pom of library.poms">
          <a *ngIf="pom.value > 0" href="https://mvnrepository.com/artifact/{{pom.groupId}}/{{pom.artifactId}}" class="badge badge-secondary" target="blank">{{pom.groupId}}:{{pom.artifactId}}</a>
        </ng-container>
      </div>
    </div>
  `,
  styles: [],
})
export class LibraryComponent {
  @Input() library: any;
}

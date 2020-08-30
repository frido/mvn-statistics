import { Component } from "@angular/core";
import data from "./githubView.json";

@Component({
  selector: "app-root",
  template: `
    <div class="jumbotron">
      <div class="container">
        <h1 class="display-4">The State of Maven ecosystem</h1>
        <p class="lead">Analysis of 229 267 pom.xml files from Maven central repository</p>
        <a class="btn btn-secondary btn-lg" href="index.html" role="button">Maven</a>
        <a class="btn btn-secondary btn-lg" href="github.html" role="button">Github</a>
      </div>
    </div>
    <div class="container">
      <app-library *ngFor="let library of view.data" [library]="library"></app-library>
    </div>
  `,
  styles: [],
})
export class AppComponent {
  title = "docs-app";
  view = data;
}

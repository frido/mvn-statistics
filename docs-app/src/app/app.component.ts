import { Component, OnInit } from "@angular/core";
import { FormControl } from '@angular/forms';
import data from "./githubView.json";
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';

@Component({
  selector: "app-root",
  template: `
    <div class="jumbotron">
      <div class="container">
        <h1 class="display-4">The State of Maven ecosystem</h1>
        <p class="lead">Analysis of 229 267 pom.xml files from Maven central repository</p>

        <div class="input-group">
          <div class="custom-file">
            <input [formControl]="searchField" type="search" class="form-control" />
          </div>
          <div class="input-group-append">
            <button class="btn btn-secondary" type="button">Search framework</button>
          </div>
        </div>
      </div>
    </div>
    <div class="container">
      <h3 *ngIf="term">search: {{term}}</h3>
      <app-library *ngFor="let library of view" [library]="library"></app-library>
    </div>
  `,
  styles: [],
})
export class AppComponent implements OnInit {
  title = "docs-app";
  searchField: FormControl;
  view = data.data;
  term = null;

  ngOnInit() {
    this.searchField = new FormControl();
    this.searchField.valueChanges.pipe(
      debounceTime(400), 
      distinctUntilChanged()
    )
    .subscribe((term: string) => {
      this.search(term);
    });
  }

  search(term: string) {
    this.term = term;
    this.view = data.data.filter(x => {
      const text = x.full_name + " " + x.description;
      return text.match(term);
    });
  }
}

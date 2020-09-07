import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LibraryItemComponent } from './library-item.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { LibrabryComponent } from './library.component';
import { StatisticsComponent } from './statistics.component';

@NgModule({
  declarations: [
    AppComponent, LibrabryComponent, LibraryItemComponent, StatisticsComponent
  ],
  imports: [
    BrowserModule, AppRoutingModule, FormsModule, ReactiveFormsModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

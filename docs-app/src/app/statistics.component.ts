import { Component, OnInit } from "@angular/core";
import { FormControl } from "@angular/forms";
import data from "./githubView.json";
import { debounceTime, distinctUntilChanged } from "rxjs/operators";

@Component({
  template: ` 
  <div class="jumbotron pb-4">
  <div class="container">
    <h1 class="display-4">The State of Maven ecosystem</h1>
    <p class="lead">Analysis of 229 267 pom.xml files from Maven central repository</p>
    <a class="btn btn-secondary btn-lg mr-3" routerLink="/libraries" routerLinkActive="active" role="button">Libraries</a>
    <a class="btn btn-secondary btn-lg" routerLink="/statistics" routerLinkActive="active" role="button">Statistics</a>
  </div>
</div>
<div class="container">

            <h5>
                I analyzed <strong>229 267 </strong>
                    <code>pom.xml</code> files. POM files are from last version of each maven project in <a href="https://repo1.maven.org/maven2/" target="blank">central repository</a>
                </h5>

                <div class="title-border">
                    <h2>Dependencies</h2>
                </div>

                <h4>Grouped by GroupId</h4>
                <p><img src="assets/charts/dependencyGroup.png" alt="dependencyGroup"></p>

                <h4>Grouped by ArtifactId</h4>
                <p><img src="assets/charts/dependencyArtifact.png" alt="dependencyArtifact"></p>

                <div class="title-border">
                    <h2>Licence</h2>
                </div>
                <p><img src="assets/charts/licenses.png" alt="licenses"></p>
                <p>
                    <em>Legend:</em>
                </p>
                <table class="table">
                    <thead>
                        <tr>
                            <th>short name</th>
                            <th>full name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>apache</td>
                            <td>the apache software license, version 2.0</td>
                        </tr>
                        <tr>
                            <td>mit</td>
                            <td>mit license</td>
                        </tr>
                        <tr>
                            <td>bsd</td>
                            <td>bsd-style</td>
                        </tr>
                        <tr>
                            <td>gnu</td>
                            <td>gnu lesser general public license</td>
                        </tr>
                        <tr>
                            <td>lgpl</td>
                            <td>lgpl 2.1, lgpl 3.0</td>
                        </tr>
                        <tr>
                            <td>eclipse</td>
                            <td>the eclipse public license version 1.0</td>
                        </tr>
                        <tr>
                            <td>gpl</td>
                            <td>gpl v2+, gpl 3.0</td>
                        </tr>
                        <tr>
                            <td>agpl</td>
                            <td>agpl 3.0</td>
                        </tr>
                        <tr>
                            <td>mozilla</td>
                            <td>mozilla public license, version 2.0</td>
                        </tr>
                        <tr>
                            <td>isc</td>
                            <td>isc license</td>
                        </tr>
                        <tr>
                            <td>affero</td>
                            <td>affero gplv3</td>
                        </tr>
                    </tbody>
                </table>

                <div class="title-border">
                    <h2>Source Code Management</h2>
                </div>
                <p><img src="assets/charts/scm.png" alt="scm"></p>

                <div class="title-border">
                    <h2>People</h2>
                </div>

                <h4>Developers</h4>
                <p>
                    <strong>80.55%</strong>
                    <code>pom.xml</code> files are with one developer.</p>

                <h4>Contributors</h4>
                <p>
                    <strong>31.78%</strong>
                    <code>pom.xml</code> files are with one contributor.</p>
                <h4>Emails</h4>
                <p>
                    <strong>28751</strong> unique email addresses were collected</p>

                <div class="title-border">
                    <h2>Plugins</h2>
                </div>

                <h4>Build plugins</h4>
                <p><img src="assets/charts/plugins.png" alt="plugins"></p>

                <h4>Reporting plugins</h4>
                <p><img src="assets/charts/reportingPlugins.png" alt="reportingPlugins"></p>

                <div class="title-border">
                    <h2>Profiles</h2>
                </div>
                <p><img src="assets/charts/profiles.png" alt="profiles"></p>

                <div class="title-border">
                    <h2>Inception years</h2>
                </div>
                <p><img src="assets/charts/inceptionYears.png" alt="inceptionYears"></p>

                <div class="title-border">
                    <h2>Issue management</h2>
                </div>
                <p><img src="assets/charts/issueManagement.png" alt="issueManagement"></p>

                <div class="title-border">
                    <h2>Continuous Integration Management</h2>
                </div>
                <p><img src="assets/charts/ciManagement.png" alt="ciManagement"></p>

                <div class="title-border">
                    <h2>Summary</h2>
                </div>
                <p>List of used elements in <code>pom.xml</code> file.
The most used element is <code>&lt;dependencies&gt;</code>, that is listed in 83.33% of all poms.</p>
                <p><img src="assets/charts/pom.png" alt="pom"></p>
                <table class="table mb-0">
                    <thead>
                        <tr>
                            <th>Element</th>
                            <th>Used in</th>
                            <th>Average list size</th>
                            <th>Max list size</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <code>&lt;dependencies&gt;</code>
                            </td>
                            <td>83.33%</td>
                            <td>6.55</td>
                            <td>
                                <a href="https://repo1.maven.org/maven2/org/wildfly/wildfly-ee-galleon-pack/20.0.1.Final/wildfly-ee-galleon-pack-20.0.1.Final.pom" target="blank">421</a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <code>&lt;licenses&gt;</code>
                            </td>
                            <td>60.09%</td>
                            <td>1.01</td>
                            <td>
                                <a href="https://repo1.maven.org/maven2/com/dorkbox/Network-Dorkbox-Util/1.20/Network-Dorkbox-Util-1.20.pom" target="blank">29</a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <code>&lt;scm&gt;</code>
                            </td>
                            <td>58.89%</td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>
                                <code>&lt;developers&gt;</code>
                            </td>
                            <td>57.94%</td>
                            <td>1.65</td>
                            <td>
                                <a href="https://repo1.maven.org/maven2/com/cemerick/clojurescript/1.9.922/clojurescript-1.9.922.pom" target="blank">127</a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <code>&lt;contributor&gt;</code>
                            </td>
                            <td>0.93%</td>
                            <td>8.39</td>
                            <td>
                                <a href="https://repo1.maven.org/maven2/org/citationstyles/styles/2.0.0/styles-2.0.0.pom" target="blank">938</a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <code>&lt;plugins&gt;</code>
                            </td>
                            <td>34.00%</td>
                            <td>3.26</td>
                            <td>
                                <a href="https://repo1.maven.org/maven2/de/shadowhunt/subversion/4.0.0/subversion-4.0.0.pom" target="blank">29</a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <code>&lt;reportingPlugins&gt;</code>
                            </td>
                            <td>2.17%</td>
                            <td>3.00</td>
                            <td>
                                <a href="https://repo1.maven.org/maven2/com/dattack/parent/2/parent-2.pom" target="blank">17</a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <code>&lt;profiles&gt;</code>
                            </td>
                            <td>13.14%</td>
                            <td>1.64</td>
                            <td>
                                <a href="https://repo1.maven.org/maven2/cn/home1/maven-build/3.3.0/maven-build-3.3.0.pom" target="blank">55</a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <code>&lt;inceptionYear&gt;</code>
                            </td>
                            <td>10.81%</td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>
                                <code>&lt;issueManagement&gt;</code>
                            </td>
                            <td>7.41%</td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>
                                <code>&lt;ciManagement&gt;</code>
                            </td>
                            <td>1.54%</td>
                            <td></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>


            

</div>
 `,
  styles: [],
})
export class StatisticsComponent {}

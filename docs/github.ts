import * as markdownIt from "markdown-it";
import {readFileSync, writeFileSync} from 'fs';
import * as Mustache from 'mustache'
import * as json from '../github/github.json'

let htmlBase = readFileSync('github.html', 'utf-8');

let output = Mustache.render(htmlBase, json);

writeFileSync('github-out.html', output);
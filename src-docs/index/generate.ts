import markdownIt from "markdown-it";
import {readFileSync, writeFileSync} from 'fs';
import Mustache from 'mustache'

let mdContent = readFileSync('../README.md', 'utf-8');
let htmlBase = readFileSync('main.html', 'utf-8');

let htmlContent = markdownIt().render(mdContent);

let view: any = {
    'content': htmlContent
}

let output = Mustache.render(htmlBase, view);

let re = /<table>/gi
output = output.replace(re, '<table class="table">'); // TODO: Tricky

writeFileSync('index.html', output);
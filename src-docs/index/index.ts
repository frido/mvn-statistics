import markdownIt from "markdown-it";
import {readFileSync} from 'fs';
import { SiSiGeConfig } from "../typings";

let mdContent = readFileSync('../README.md', 'utf-8');
let htmlContent = markdownIt().render(mdContent);
let view: any = {
    'content': htmlContent
}

export const config: SiSiGeConfig =  {
    layout: 'index/main.html',
    view: view,
    output: '../docs/index.html'
}
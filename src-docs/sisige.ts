import { config as index } from "./index/index";
import { config as github } from "./github/github";
import { SiSiGeConfig } from "./typings";
import { readFileSync, writeFileSync } from 'fs';
import Mustache from 'mustache'

const generate = (x: SiSiGeConfig) => {
    let layoutContent = readFileSync(x.layout, 'utf-8');
    let outputContent = Mustache.render(layoutContent, x.view);
    writeFileSync(x.output, outputContent);
}

const queue = [];
queue.push(index);
queue.push(github);
queue.forEach(x => generate(x));
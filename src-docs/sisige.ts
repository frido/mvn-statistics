import { config as index } from "./index/index";
import { config as github } from "./github/github";
import { SiSiGeConfig } from "./typings";
import { readFileSync, writeFileSync } from 'fs';
import Mustache from 'mustache'
import { ncp } from "ncp";

const generate = (x: SiSiGeConfig) => {
    if (x.view) {
        let layoutContent = readFileSync(x.layout, 'utf-8');
        let outputContent = Mustache.render(layoutContent, x.view);
        writeFileSync(x.output, outputContent);
    }
    
    x.static.forEach(source => {
        ncp(source, '../docs/', (err) => {});
    });
}

const queue = [];
queue.push(index);
queue.push(github);
queue.forEach(x => generate(x));
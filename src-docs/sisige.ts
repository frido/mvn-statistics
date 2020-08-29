import { config as index } from "./index/index";
import { config as github } from "./github/github";
import { SiSiGeConfig } from "./typings";
import { readFileSync, writeFileSync, fstat, readdirSync, copyFileSync } from 'fs';
import { extname, basename } from 'path';
import Mustache from 'mustache'
import { ncp } from "ncp";
import {renderSync} from 'sass';

// const generate = (x: SiSiGeConfig) => {
//     if (x.view) {
//         let layoutContent = readFileSync(x.layout, 'utf-8');
//         let outputContent = Mustache.render(layoutContent, x.view);
//         writeFileSync(x.output, outputContent);
//     }
    
//     x.static.forEach(source => {
//         ncp(source, '../docs/', (err) => {});
//     });

//     renderSync({file: x.scss})
// }

const generate = (x: SiSiGeConfig) => {
    readdirSync(x.dir).forEach(fileName => {
        const ext = extname(fileName);
        const name = basename(fileName, ext);
        // console.log(fileName);
        // console.log(name);
        // console.log(ext);

        if (ext === '.html') {
            const source = x.dir + '/' + fileName;
            const dest = x.output + '/' + fileName
            copyFileSync(source, dest);
            console.log('copy: '+source+'->'+dest);
        }   
        
        if (fileName === 'static') {
            const staticDir = x.dir + '/static';
            ncp(staticDir, x.output, (err) => {});
            console.log('copy folder: '+staticDir+'->'+x.output);
        }

        if (ext === '.mustache') {
            const mustacheFile = x.dir + '/' + fileName;
            const htmlFile = x.output + '/' + name + '.html';
            const layoutContent = readFileSync(mustacheFile, 'utf-8');
            let outputContent = Mustache.render(layoutContent, x.view);
            writeFileSync(htmlFile, outputContent);
            console.log('mustache: '+mustacheFile+'->'+htmlFile);
        }

        if (ext === '.scss') {
            const scssFile = x.dir + '/' + fileName;
            const cssContent = renderSync({file: scssFile});
            const cssFile = x.output + '/' + name + '.css';
            writeFileSync(cssFile, cssContent.css);
            console.log('scss: '+scssFile+'->'+cssFile);
        }
    })
};

const queue = [];
queue.push(index);
queue.push(github);
queue.forEach(x => generate(x));
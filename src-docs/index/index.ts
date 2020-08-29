import { SiSiGeConfig } from "../typings";

let view: any = null

// TODO: dont need to write all, just current dir is enought
// I can search files in current dir and do actions according to file names and extensions
export const config: SiSiGeConfig =  {
    // layout: 'index/main.html',
    dir: 'index',
    view: null,
    output: '../docs',
    // static: ['index/static']
}
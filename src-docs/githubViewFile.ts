import data from './data/githubView';
import { writeFileSync } from 'fs';

const content = JSON.stringify(data);
writeFileSync('githubView.json', content);


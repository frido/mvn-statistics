# The State of Maven ecosystem 2020.

Java multithreaded application that aims to analyze pom files from the central maven repository

It analyze 229,267 pom.xml files for the latest version of each maven project in the central maven repository.

You can find the resulting statistics on the [github pages](http://petrzalka.info/mvn-statistics/).

## Collector

Java source is located in `src` as maven project.

Build executable `jar` file
```
mvn clean package
```

Run java file
```
java -cp target\statistics-1.0.jar <<CLASS>>
```

There is a list of `<<CLASS>>` options.

| name | class | input | output |
| --- | --- | --- | --- |
| Crawler | frido.mvnrepo.downloader.repo.RepoCrawler | | `data/metadata.list`
| MetadataReader | frido.mvnrepo.downloader.metadata.MetadataReader | `data/metadata.list` | `data/pom.list`
| PomReader | frido.mvnrepo.downloader.pom.PomReader | `data/pom.list` | `data/statistics.json`
| StatisticsReader | frido.mvnrepo.downloader.report.StatisticsReader | `data/statistics.json` | `report/*.json`
| GitReader | frido.mvnrepo.downloader.github.GitReader | `data/statistics.json` | `data/github.json`
| GithubReader | frido.mvnrepo.downloader.github.GitHubReader | `github.list` | `github/github.json`

*TODO: GithubReader input `github.list` should be `data/github.json`*

## Presenter

**SI**mple **SI**te **GE**nerator is in `src-docs` folder as `npm` project.

Go to presenter root folder
```
cd src-docs
```

Install dependencies
```
npm install
```

Compile `ts` to `js`
```
tsc
```

Generate html pages
```
node sisige.js
```

You can find output in `docs` folder.

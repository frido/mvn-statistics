# Maven Statistics

The State of Maven ecosystem.

Analysis and statistic from all `pom.xml` files from [maven central repository](https://repo1.maven.org/maven2/).

## Collector

Java source is located in `src`. It can be imported to `InteliJ` as maven project.

Build executable `jar` file *TODO*
```
mvn clean package
```

Run java file
```
java -jar <<CLASS>>
```

There is a list of `<<CLASS>>` options.

| name | class | input | output |
| --- | --- | --- | --- |
| Crawler | TODO | | `data/metadata.list`
TODO

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

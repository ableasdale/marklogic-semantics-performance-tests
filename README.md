### Running the tests

```bash
./gradlew clean test junitHtmlReport
```

Open report (HTML)

```bash
open build/reports/tests/junit-platform/index.html
```

Serve report over HTTP
```bash
cd build/reports/tests/junit-platform
python -m SimpleHTTPServer 9992
```


### Test Sources Used:

1. The **GNIS RDF Data Dump**
[https://datahub.io/dataset/geographic-names-information-system-gnis](https://datahub.io/dataset/geographic-names-information-system-gnis)

2. https://datahub.io/dataset/chem2bio2rdf (TODO - check - license is not clear)
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

1. The **GNIS RDF Data Dump** (RDF/Turtle)
[https://datahub.io/dataset/geographic-names-information-system-gnis](https://datahub.io/dataset/geographic-names-information-system-gnis)

2. **Bio2RDF::Sider** (n-quads)
[https://datahub.io/dataset/bio2rdf-sider](https://datahub.io/dataset/bio2rdf-sider) 


TODO:
https://datahub.io/dataset/telegraphis (XML/RDF) - license not clear?
https://datahub.io/dataset/chem2bio2rdf (TODO - check - license is not clear)

TODO:
https://datahub.io/dataset/ckan
https://www.govtrack.us/data/misc/sec.n3.gz
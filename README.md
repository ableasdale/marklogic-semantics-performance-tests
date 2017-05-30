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
http://usgs-stko.geog.ucsb.edu/resource/GNIS-LD.zip
https://datahub.io/dataset/telegraphis (XML/RDF) - license not clear?
https://datahub.io/dataset/chem2bio2rdf (TODO - check - license is not clear)
https://datahub.io/dataset/dbtune-john-peel-sessions - (TODO - check - license is not clear)

TODO:
https://datahub.io/dataset/ckan
https://www.govtrack.us/data/misc/sec.n3.gz
https://datahub.io/dataset/inertia-energy-consumption-example-linked-data
http://www.inertia-project.eu/inertia/files/document/ontologies/event-dump.n3
https://datahub.io/dataset/government-web-integration-for-linked-data
http://govwild.hpi-web.de/downloads/govwild-rdf-2012-01-30.zip

NTriples TODO
https://datahub.io/dataset/ispra-lod-ron (ron.nt)
https://datahub.io/dataset/ispra-lod-rmn

trix et al.
https://datahub.io/dataset/charging-stations

https://datahub.io/dataset/unescothes 
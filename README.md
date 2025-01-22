Use LTS 2023

Add logger
```
<Logger name="org.nuxeo.sample" level="info" />
```

Put bundle in `nxserver/bundles`


Use `Document.Create`:
```
# make ws1 first
curl -u Administrator localhost:8080/nuxeo/api/v1/automation/Document.Create -d '{"input":"/default-domain/workspaces","params":{"type":"Workspace","name":"ws1"}}' -H content-type:application/json
# make a File document
curl -u Administrator localhost:8080/nuxeo/api/v1/automation/Document.Create -d '{"input":"/default-domain/workspaces/ws1","params":{"type":"File","name":"file"}}' -H content-type:application/json
#  make another File document with same name
#  observed: 
#    * 'nope' appears in the server.log file
#    * DocumentExistsException is thrown by listener
#    * Creation of document is successful
#  expected: 
#    * DocumentExistsException is thrown by listener
#    * Creation of document is suppressed
curl -u Administrator localhost:8080/nuxeo/api/v1/automation/Document.Create -d '{"input":"/default-domain/workspaces/ws1","params":{"type":"File","name":"file"}}' -H content-type:application/json
```

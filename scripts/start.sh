#!/bin/sh
gradle run \
  -Dserver.port=80 \
  -Dserver.root=$APP_ROOT \
  -Ddb.username=$APP_DB_USERNAME \
  -Ddb.password=$APP_DB_PASSWORD \
  -Ddb.url=$APP_DB_URL \
  $@

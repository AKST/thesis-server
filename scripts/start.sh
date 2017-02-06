#!/bin/sh
gradle run \
  -Dserver.port=8081 \
  -Dserver.root=/api \
  $@

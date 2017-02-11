# thesis analysis API

This a server for servering data associated with the data collected
in my thesis research, I decided to build this with groovy and java
because I'm applying for a job that requires experince in the too.

## Startup

```bash
gradle run \
       -Ddb.url="your database url" \
       -Ddb.username="the username you're using for the db" \
       -Ddb.password="the password you're using for the db" \
       -Dserver.root="the root path of this server, it defaults to '/'" \
       -Dserver.port="the port that this server will run on, defaults to 80"
```

## Installation

First make a file of environment variables the script in `scripts/start.sh`,
then download from dockerhub run like so (add additional flags if necessary).

```
vim my-environment-variables.list
docker pull akst/thesis-api:0.1
docker run --env-file my-environment-variables.list akst/thesis-api:0.1
```

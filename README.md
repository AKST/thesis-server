# thesis analysis API

This a server for servering data associated with the data collected
in my thesis research, I decided to build this with groovy and java
because I'm applying for a job that requires experince in the too.

## Startup

```bash
gradle -Ddb.url="your database url" \
       -Ddb.username="the username you're using for the db" \
       -Ddb.username="the password you're using for the db" \
       -Dserver.root="the root path of this server, it defaults to '/'" \
       -Dserver.root="the root that this server will run on, defaults to 80" \
```

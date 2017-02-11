FROM ubuntu:xenial

# install wget
RUN apt-get update && apt-get install -y wget
RUN apt-get install -y software-properties-common python-software-properties

# Specify Repositories.
RUN echo "deb http://apt.postgresql.org/pub/repos/apt/ xenial-pgdg main" > /etc/apt/sources.list.d/pgdg.list
RUN wget -q https://www.postgresql.org/media/keys/ACCC4CF8.asc -O - | apt-key add -
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections
RUN add-apt-repository -y ppa:webupd8team/java
RUN add-apt-repository -y ppa:cwchien/gradle

# Install Dependencies.
RUN apt-get update
RUN apt-get install -y \
  postgresql-contrib-9.5 \
  postgresql-client-9.5 \
  postgresql-9.5 \
  gradle-3.1 \
  git \
  oracle-java8-installer

RUN rm -rf /var/lib/apt/lists/*
RUN rm -rf /var/cache/oracle-jdk8-installer

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
EXPOSE 80

# Define working directory.
RUN mkdir -p /root/app
WORKDIR /root/app

# Install source
RUN git clone --branch 0.1 https://github.com/AKST/thesis-server.git .

# Build source
RUN gradle build

# starts script in
CMD ["./scripts/start.sh"]

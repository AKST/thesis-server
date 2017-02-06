FROM ubuntu:xenial

# install wget
RUN apt-get update && apt-get install wget

# Specify Repositories.
RUN \
  echo "deb http://apt.postgresql.org/pub/repos/apt/ xenial-pgdg main" > /etc/apt/sources.list.d/pgdg.list && \
  wget -q https://www.postgresql.org/media/keys/ACCC4CF8.asc -O - | apt-key add - && \
  echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  add-apt-repository -y ppa:webupd8team/java && \
  add-apt-repository -y ppa:cwchien/gradle

# Install Dependencies.
RUN \
  apt-get update && \
  apt-get install -y \
    postgresql-contrib-9.5 \
    postgresql-client-9.5 \
    postgresql-9.5 \
    gradle-3.1 \
    oracle-java8-installer && \
  rm -rf /var/lib/apt/lists/* && \
  rm -rf /var/cache/oracle-jdk8-installer

# Define working directory.
WORKDIR /data

# Install source
RUN \
  git clone git@github.com:AKST/thesis-server.git /data/app


# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

# starts script in
CMD ["bash"]

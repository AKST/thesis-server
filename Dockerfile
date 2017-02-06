FROM dockerfile/ubuntu

# Install Dependencies.
RUN \
  echo "deb http://apt.postgresql.org/pub/repos/apt/ `lsb_release -cs`- main" > /etc/apt/sources.list.d/pgdg.list && \
  wget -q https://www.postgresql.org/media/keys/ACCC4CF8.asc -O - | sudo apt-key add - && \
  echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  add-apt-repository -y ppa:webupd8team/java && \
  add-apt-repository ppa:cwchien/gradle && \
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

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

package io.akst.thesis.app;

import javax.persistence.Persistence
import javax.persistence.EntityManagerFactory as SessionFactory


@groovy.transform.TypeChecked
class Config {
  final int port
  final String root
  final SessionFactory sessions

  Config(int port, String root, SessionFactory sessions) {
    this.port = port
    this.root = root
    this.sessions = sessions
  }

  static make(Hashtable<String, String> env) {
    def factor = new ConfigFactor(env)
    factor.make()
  }

  private static class ConfigFactor {
    private final Hashtable<String, String> env

    def ConfigFactor(Hashtable<String, String> env) {
      this.env = env
    }

    private int getKeyAsInt(String key, int alternative) {
      String result = this.env.get(key)
      if (result == null) {
        alternative
      }
      else {
        Integer.parseInt result, 10
      }
    }

    private SessionFactory getSessionFactory() {
      Persistence.createEntityManagerFactory("thesis/read")
    }

    def make() {
      def port = this.getKeyAsInt 'server.port', 80
      def root = this.env.get('server.root') ?: '/'
      def sessions = this.getSessionFactory()
      new Config(port, root, sessions)
    }
  }
}

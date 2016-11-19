package io.akst.thesis.app;

import org.mortbay.jetty.Server
import org.mortbay.jetty.servlet.ServletHolder
import org.mortbay.jetty.servlet.Context

import io.akst.thesis.servlet.IndexServlet
import io.akst.thesis.servlet.AverageServlet

class Main {
  static void main(String ... args) {
    def config = Config.make (System.getProperties() as Hashtable<String, String>)
    def server = this.getServer config
    def context = this.getContext config, server

    server.start()
  }

  static Server getServer(Config config) {
    new Server(config.port)
  }

  /**
   * Initialises Routes with from a configuration object
   */
  static Context getContext(config, server) {
    def context = new Context(server, config.root, Context.SESSIONS)
    def index = new IndexServlet()
    def averages = new AverageServlet(factory: config.sessions)
    context.addServlet(new ServletHolder(index), "/")
    context.addServlet(new ServletHolder(averages), "/averages")
    context
  }
}

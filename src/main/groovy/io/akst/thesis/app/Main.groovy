package io.akst.thesis.app;

import org.mortbay.jetty.Server
import org.mortbay.jetty.servlet.ServletHolder
import org.mortbay.jetty.servlet.Context

import io.akst.thesis.servlet.IndexServlet
import io.akst.thesis.servlet.AverageServlet
import io.akst.thesis.servlet.PackagesServlet
import io.akst.thesis.servlet.NotFoundServlet

import io.akst.thesis.controllers.PackageDAO
import io.akst.thesis.controllers.AverageDAO

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
    def packageDAO = new PackageDAO(factory: config.sessions)
    def averageDAO = new AverageDAO(factory: config.sessions)

    def index = new IndexServlet()
    def notfound = new NotFoundServlet()
    def averages = new AverageServlet(averages: averageDAO)
    def packages = new PackagesServlet(packages: packageDAO)

    def context = new Context(server, config.root, Context.SESSIONS)
    context.addServlet(new ServletHolder(index), "/")
    context.addServlet(new ServletHolder(averages), "/average")
    context.addServlet(new ServletHolder(packages), "/package")
    context.addServlet(new ServletHolder(notfound), "/*")
    context
  }
}

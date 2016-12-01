package io.akst.thesis.servlet;

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.persistence.EntityManagerFactory as SessionFactory

import groovy.json.StreamingJsonBuilder

import io.akst.thesis.controllers.PackageDAO

class PackagesServlet extends HttpServlet {
  def PackageDAO packages

  def void doGet(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("application/json")
    def builder = new StreamingJsonBuilder(response.getWriter())
    builder(
      type: "package",
      data: this.packages.findAll(10)
        .collect({ item ->  item.serialise() })
    )
  }
}

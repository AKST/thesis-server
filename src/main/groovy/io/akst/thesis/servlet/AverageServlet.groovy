package io.akst.thesis.servlet;

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import groovy.json.StreamingJsonBuilder

import io.akst.thesis.controllers.AverageDAO


class AverageServlet extends HttpServlet {
  def AverageDAO averages

  def void doGet(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("application/json")
    def builder = new StreamingJsonBuilder(response.getWriter())
    def type    = this.getResultType(request)
    def results = this.getResults(type).collect({ item -> item.serialise() })
    builder(type: "average/${type}", data: results)
  }

  def getResultType(HttpServletRequest request) {
    return request.getParameter("type") ?: "sizes"
  }

  def getResults(String type) {
    switch (type) {
      case "sizes": return this.averages.findAll()
      case "times": return this.averages.findAllTimes()
      default: throw new Exception()
    }
  }
}

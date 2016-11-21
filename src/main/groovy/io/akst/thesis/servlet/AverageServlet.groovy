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
    def results = this.averages.findAll()
    builder(
      type: "average",
      data: results.collect({ item -> item.serialise() })
    )
  }
}

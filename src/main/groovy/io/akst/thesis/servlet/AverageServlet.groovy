package io.akst.thesis.servlet;

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import groovy.json.StreamingJsonBuilder

import io.akst.thesis.util.AppException
import io.akst.thesis.controllers.AverageDAO
import io.akst.thesis.controllers.query.AverageQuery
import io.akst.thesis.models.util.ProjectionWrapper



@groovy.transform.TypeChecked
class AverageServlet extends HttpServlet {
  def AverageDAO averages

  def void doGet(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("application/json")
    def builder = new StreamingJsonBuilder(response.getWriter())
    try {
      def query   = AverageQuery.make(request.getParameterMap())
      def results = query.execute(this.averages).collect({ item -> item.serialise() })
      builder(type: "average/${query.getType()}", data: results)
    } catch (AppException ex) {
      ex.handleResponse(response, builder)
    }
  }
}

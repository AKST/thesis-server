package io.akst.thesis.servlet;

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import groovy.json.StreamingJsonBuilder


class IndexServlet extends HttpServlet {
  def void doGet(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("application/json")
    def builder = new StreamingJsonBuilder(response.getWriter())
    builder { message "hello" }
  }
}

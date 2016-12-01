package io.akst.thesis.servlet;

import java.util.logging.Logger

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import groovy.json.StreamingJsonBuilder


class NotFoundServlet extends HttpServlet {
  def logger = Logger.getLogger(NotFoundServlet.class.toString())

  def void doGet(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType "application/json"
    response.setStatus 404
    logger.info "404 hit ${request.getRequestURI()}"
    def builder = new StreamingJsonBuilder(response.getWriter())
    builder(type: "error", message: "invalid url")
  }
}

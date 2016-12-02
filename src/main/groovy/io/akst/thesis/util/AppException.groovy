package io.akst.thesis.util

import javax.servlet.http.HttpServletResponse
import groovy.json.StreamingJsonBuilder

@groovy.transform.TypeChecked
class AppException extends Exception {
  int status
  String message

  AppException(int status, String message) {
    this.status = status
    this.message = message
  }

  def void handleResponse(HttpServletResponse response, StreamingJsonBuilder builder) {
    response.setStatus(this.status)
    response.setContentType("application/json")
    builder(type: "error", data: [message: this.message])
  }
}

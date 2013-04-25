package com.example.app

import org.scalatra._
import scalate.ScalateSupport

class IndexServlet extends ScalatraStack {

  get("/") {
    contentType = "text/html"
    jade("index.jade")
  }
  
}

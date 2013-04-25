package com.example.app

import org.scalatra._
import scalate.ScalateSupport
import com.example.app.model.Model
import org.neo4j.graphdb.Node

class IndexServlet extends ScalatraStack {

  get("/") {
    contentType = "text/html"
    jade("index.jade")
  }

  get("/test") {
    val list = Model.getAll
    <html>
      <body>
        {for (i:Node <- list) yield i.getProperty("name")+" "}
      </body>
    </html>

  }
  
}

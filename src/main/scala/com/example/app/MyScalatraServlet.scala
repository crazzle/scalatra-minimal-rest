package com.example.app

import org.scalatra._
import scalate.ScalateSupport

class MyScalatraServlet extends ScalatraServlet with ScalateSupport {

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
      </body>
    </html>
  }

  notFound {
    resourceNotFound()
  }
}

package demo

import org.scalajs.dom
import org.scalajs.dom.document

import scala.scalajs.js.annotation._

object WebAppDemo {
  /*def main(args: Array[String]): Unit = {
    println("Hello world!")
    appendPar(document.body, "Hello World")
  }*/

  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
  }

  @JSExportTopLevel("addClickedMessage")
  def addClickedMessage(): Unit = {
    appendPar(document.body, "You clicked the button!")
  }
}
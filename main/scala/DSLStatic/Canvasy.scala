package DSLStatic

import org.scalajs.dom
import org.scalajs.dom.{document, html}

import scala.collection.mutable.ListBuffer

class Canvasy(c: html.Canvas) {
  private val ctx = c.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
  private val list_groups = new ListBuffer[Array[Shape]]

  def draw(): Unit = {
    ???
  }

  def +=(group: Array[Shape]): Unit = {
    list_groups.+=:(group)
  }
}

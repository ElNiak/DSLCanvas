package DSLStatic

import org.scalajs.dom
import org.scalajs.dom.{document, html}

import scala.collection.mutable.ListBuffer

class Canvasy(c: html.Canvas) {
  private val ctx = c.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
  private val circle_groups = new ListBuffer[Array[Circle]]
  private val rect_groups = new ListBuffer[Array[Rectangle]]

  def draw(): Unit = {
    ???
  }

  def +=(group: Array[Circle]): Unit = {
    circle_groups.+=:(group)
  }

  def +=(group: Array[Rectangle]): Unit = {
    rect_groups.+=:(group)
  }
}

package DSLStatic

import org.scalajs.dom
import org.scalajs.dom.{document, html}

import scala.collection.mutable.ListBuffer

class Canvasy[I <: Shape](c: html.Canvas) {
  private val ctx = c.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
  private val shape_groups = new ListBuffer[Array[I]]

  def draw(): Unit = {
    ???
  }

  def += [I <: Shape](group: Array[I]): Unit = {
    shape_groups.+=:(group)
  }
}

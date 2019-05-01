package DSLStatic

import DSLStatic.Modifier.{FillColor, Radius, StrokeColor, StrokeWidth, Width}
import DSLStatic._
import org.scalajs.dom
import org.scalajs.dom.html
import org.scalajs.dom.{document, html}
import DSLStatic.Shape.{Circle, Rectangle, Shape}
import DSLStatic.Style.Color
import DSLStatic.Style.ColorRGB.ColorRGBUtils

object dslDemoObject {
  def main(args: Array[String]): Unit = {
    val canvas = document.createElement("canvas").asInstanceOf[html.Canvas]
    document.body.appendChild(canvas)
    val w = 300
    canvas.width = w
    canvas.height = w
    dslDemoPerso(canvas)
  }

  def dslDemoPerso(canvas: html.Canvas) = {
    // From now on, we use the DSL

    val canvasy = new Canvasy(canvas) //isn't canvasy a nice name for a library?
    // Let us create some shapes
    val circles = Array.fill(4)(new Circle(50.0, 0, 0,1,0.5))
    val rectangles = Array.tabulate(2)(i => new Rectangle(i*50, i*50, 50, 100,2,1.0))

    // Tell the library to display both circles and rectangles in the canvas
    canvasy += circles
    canvasy += rectangles

    circles translateX 100 translateY 100

    // easily create a group with the keyword "and" => groupBy
    circles(2) and circles(3) translateX 50

    circles(2) translateX 22

    rectangles change StrokeColor(Color.red)

    circles change FillColor(rgba"0.7#4b9e5c")

    circles(0) change FillColor(rgba"0.1#a3a300")

    circles(2) change FillColor(rgba"0.8#ffa3f8")

    rectangles(0) translateX 20 translateY 30
    rectangles(0) change StrokeColor(rgba"0.7#a3a3f8")
    rectangles(0) change StrokeWidth(20)
    // let us draw all these things
    canvasy draw()
  }
}

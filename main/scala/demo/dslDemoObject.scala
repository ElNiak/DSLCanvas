package DSLStatic

import DSLStatic.Modifier._
import DSLStatic._
import org.scalajs.dom
import org.scalajs.dom.html
import org.scalajs.dom.{document, html}
import DSLStatic.Shape._
import DSLStatic.Style.Color
import DSLStatic.Style.ColorRGB.ColorRGBUtils
import DSLStatic.Style.Gradient.GradientUtils
import DSLStatic.Canvasy.drawHand

object dslDemoObject {
  def main(args: Array[String]): Unit = {
    val canvas = document.createElement("canvas").asInstanceOf[html.Canvas]
    val container = document.createElement("div").asInstanceOf[html.Div]
    document.body.appendChild(container)
    container.appendChild(canvas)
    val w = 300
    canvas.width = w*2
    canvas.height = w * 2
    container.setAttribute("height","1000px")
    container.setAttribute("width","1000px")
    dslDemoPerso(canvas)
  }

  def dslDemoPerso(canvas: html.Canvas) = {
    // From now on, we use the DSL

    val canvasy = new Canvasy(canvas) //isn't canvasy a nice name for a library?
    // Let us create some shapes
    val circles = Array.fill(1)(new Circle(10.0, 0, 0,1,0.5))
    val rectangles = Array.tabulate(1)(i => new Rectangle(i*50, i*50, 150, 300,1,1.0))
    val Recttriangles = Array.fill(1)(new RectangleTriangle(100, 100, 40, 40, 1, 0.5))
    val equTriangle = Array.fill(1)(new EquilateralTriangle(200, 200, 50, 2, 1))
    val txt = Array.fill(1)(new Text(200,150,"Smoke Weed Every Day",2,2,2,"#A7D30C","20px Times New Roman",false))

    val square = Array.fill(1)(new Square(50, 50, 150,2,1.0))
    // Tell the library to display both circles and rectangles in the canvas
    canvasy += circles
    canvasy += rectangles
    canvasy += Recttriangles
    canvasy += equTriangle
    canvasy += square
    canvasy += txt

    //Recttriangles change StrokeColor(Color.red)

    equTriangle change StrokeWidth(5)

    txt rotate((Math.PI / 180)*25)

    //circles translateX 100 translateY 100

    // easily create a group with the keyword "and" => groupBy
    //circles(2) and circles(3) translateX 50

    circles(0) translateX 22 translateY 50

    //rectangles change StrokeColor(Color.red)

    //circles change FillColor(rgba"0.7#4b9e5c")
    //circles(1) change FillColor(rgba"0.5#ffa3f8")
    rectangles(0) translateX 20 translateY 10

    square(0) translateX 30 translateY 50
    square(0) change StrokeColor(gradL"0&0&50&150&0.8,0.85,0.8&#A7D30C#fbaf6d#58f6f8")

    rectangles(0) change FillColor(gradR"45&45&52&50&10&30&0,0.5,0.7&#A7D30C#019F62#019F80")

    rectangles(0) moveMouse true


    drawHand

    //rectangles(0) change StrokeColor(rgba"0.7#a3a3f8")
    //rectangles(0) change StrokeWidth(20)*/
    // let us draw all these things
    canvasy draw()
  }
}

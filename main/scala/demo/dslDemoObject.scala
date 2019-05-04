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
    val container = document.createElement("div").asInstanceOf[html.Div]
    container.setAttribute("id","container")
    document.body.appendChild(container)
    dslDemoPerso()
  }

  def dslDemoPerso() = {
    // From now on, we use the DSL
    var ree = Rectangle(230,300,200,300,1,0.5)
    var canvasy2 = new Canvasy(ree)
    canvasy2 += Rectangle(0,0,150,30,1,1)

    var re = Rectangle(300,300,200,300,1,0.5)
    var canvasy = new Canvasy(re) //isn't canvasy a nice name for a library?
    canvasy += new Rectangle(150,0,150,30,1,0.8, Color.red)
    canvasy += new Rectangle(200,150,150,130,1,0.8, rgb"#ffffff")
    canvasy += Circle(50.0, 200, 100, 1, 0.5)
    //canvasy += re

    re change FillColor(gradL"0&50&50&5&0,0.5,1&#A7D30C#019F62#58f6f8")
    ree change FillColor(Color.red)

    //ree change FillColor(Color.red)

    re rotate 80 //bug
    ree rotate -90 //bug
    canvasy moveMouse true
    //canvasy2 moveMouse true
    canvasy keyRotate true
    canvasy2 translateX 50 translateY 100

    drawHand(500)
    canvasy draw()
    canvasy2 draw()
  }
}

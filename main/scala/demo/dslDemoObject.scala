package DSLStatic

import DSLStatic.Modifier._
import DSLStatic._
import org.scalajs.dom
import org.scalajs.dom.html
import org.scalajs.dom.{document, html}
import DSLStatic.Shape._
import DSLStatic.Style.{Color, Cap, Join, SV}
import DSLStatic.Style.ColorRGB.ColorRGBUtils
import DSLStatic.Style.Gradient.GradientUtils

import scala.collection.mutable.ListBuffer

object dslDemoObject {
  def main(args: Array[String]): Unit = {
    val container = document.createElement("div").asInstanceOf[html.Div]
    container.setAttribute("id","container")
    document.body.appendChild(container)
    dslDemoPerso()
  }

  def dslDemoPerso() = {
    // From now on, we use the DSL
    var ree = Rectangle((230,300),200,300,1,0.5)
    var canvasy2 = new Canvasy(ree)
    canvasy2 += Rectangle((0,0),150,30,1,1)

    var re = Rectangle((300,300),200,300,1,0.5)
    var canvasy = new Canvasy(re) //isn't canvasy a nice name for a library?
    canvasy += new Rectangle((150.0,0.0),150.0,30.0,1,0.8, Color.purple)
    canvasy += new Rectangle((200.0,150.0),150.0,130.0,1,0.8, Color.green)
    canvasy += Circle((200, 100),1, 0.5, 50)
    val xo = new PPLShape((100, 100), 2, 0.5, ListBuffer((0,0),(100,100),(100,50),(30,10),(25,50)),Color.orange)
    var canvasy4 = new Canvasy(xo)

    canvasy4 += new MyShapeExemple(500,400,1,1,1)
    //canvasy += re

    val Recttriangles = TriangleRectangle((100, 100), 40, 40, 2, 0.5)
    val equTriangle   = TriangleEquilateral((200, 100), 100, 1, 0.5)
    val t = Text((50, 50), "Smoke Weed", 10, 10, 10, "#ff0000", "20px Times New Roman", false)
    canvasy4 += equTriangle += Recttriangles += t
    re change FillColor(gradL"0&50&50&5&0,0.5,1&#A7D30C#019F62#58f6f8")
    ree change FillColor(Color.red)

    var canvasy5 = new Canvasy(Text((50, 50), "Smoke Weed", 0, 0, 0, "#ff0000", "20px Times New Roman", false))
    //ree change FillColor(Color.red)

    canvasy5 += TrianglePP((20,20),(20,200),(200,150),2,1)

    canvasy5 += CurveQuadratic((50,50),(100,100),2,1,(50,50))
    canvasy5 += CurveBezier((50,50),(100,100),2,1,(50,50),(60,60))

    var canvasy6 = new Canvasy(new PPAShape((0, 0), SV.fill, 0.5, ListBuffer((0,0),(100,100),(100,50),(30,10),(25,50)),10.0,Color.red))

    re rotate 80 //bug
    ree rotate -90 //bug
    //canvasy moveMouse true
    //canvasy2 moveMouse true
    //canvasy4 keyRotate true
    canvasy2 translateX 50 translateY 100

    canvasy4 rotate 0
    xo change StrokeCap(Cap.butt)
    xo change StrokeJoin(Join.bevel)
    xo change StrokeDashOff(2)


    canvasy4.strokeElement change StrokeColor(Color.green)
    canvasy4.strokeElement change StrokeWidth(10)
    canvasy4.fillElement change FillColor(Color.purple)

    canvasy5.fillElement change FillColor(Color.green)
    canvasy5.strokeElement change StrokeCap(Cap.butt)
    canvasy5.strokeElement change StrokeJoin(Join.bevel)
    canvasy5.strokeElement change StrokeDashOff(2)
    canvasy5.strokeElement change StrokeWidth(10)
    canvasy5.strokeElement change StrokeColor(Color.red)

    canvasy4.getStrokeShape[PPLShape] change StrokeColor(Color.blue)

    //canvasy4 moveMouse  true
    //drawHand(500)
    //canvasy draw()
    //canvasy2 draw()
    canvasy4 animLeftRight  (true,20,true)
    canvasy4 moveMouse true

    //canvasy4 draw()
    //canvasy6 keyRotate true


    canvasy4 draw()

    //val canvasy7 = new Canvasy()
    //canvasy7 drawHand(200,200) translateX 50 translateY 600
  }
}

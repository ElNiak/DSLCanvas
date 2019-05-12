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
    // 1st Canvas
    val re = Rectangle((300, 300), 200, 300, SV.fill, 0.5)
    var canvasy = new Canvasy(re) //isn't canvasy a nice name for a library?
    canvasy += new Rectangle((150.0,0.0),150.0,30.0,SV.fill,0.8, Color.purple)
    canvasy += new Rectangle((200.0,150.0),150.0,130.0,SV.fill,0.8, Color.green)
    canvasy += Circle((200, 100),SV.fill, 0.5, 50)
    canvasy rotate 80
    canvasy draw()

    // 2sd Canvas
    val rectangle = Rectangle((230, 300), 200, 300, SV.fill, 0.5)
    var canvasy2 = new Canvasy(rectangle) translateX 50 translateY 100
    canvasy2 += Rectangle((0,0),150,30,SV.fill,1)
    rectangle change FillColor(Color.red)
    rectangle rotate -90
    canvasy2 draw()

    // 3rd Canvas
    val xo = new PPLShape("iamid",(100.0, 100.0), SV.stroke, 0.5, ListBuffer[(Double,Double)]((0,0),(100,100),(100,50),(30,10),(25,50)),Color.orange)
    var canvasy3 = new Canvasy(xo)
    canvasy3 += new MyShapeExemple(500,400,SV.fill,1,1)
    val RectTriangle = TriangleRectangle((100, 100), 40, 40, SV.stroke, 0.5)
    val EquTriangle  = new TriangleEquilateral("iamid",(200.0, 100.0), 100, SV.fill, 0.5)
    val t = Text((50, 50), "Smoke Weed", 10, 10, 10, "#ff0000", "20px Times New Roman", false)
    canvasy3 += EquTriangle += RectTriangle += t change Speed(0,0)
    canvasy3 rotate 0 //do nothing
    xo change StrokeCap(Cap.butt) change StrokeJoin(Join.bevel) change StrokeDashOff(2)
    canvasy3.StrokeShape change StrokeColor(Color.green) change StrokeWidth(10)
    canvasy3.FillShape change FillColor(gradL"0&50&50&5&0#A7D30C,0.5#019F62,1#58f6f8")
    canvasy3.get("iamid") change Speed(0,0)
    canvasy3.StrokeShape[PPLShape] change StrokeColor(Color.blue)
    canvasy3.Shape change Speed(10,5)
    canvasy3 anim  (true,true) //set animation available and take in count the borders
    canvasy3 moveMouse true
    canvasy3 draw()

    // 4th Canvas
    var canvasy4 = new Canvasy(Text((50, 50), "Smoke Weed", 0, 0, 0, "#ff0000", "20px Times New Roman", false))
    canvasy4 += TrianglePP((20,20),(20,200),(200,150),SV.stroke,1) += CurveQuadratic((50,50),(100,100),SV.stroke,1,(50,50)) += CurveBezier((50,50),(100,100),SV.stroke,1,(50,50),(60,60))
    canvasy4.FillShape change FillColor(Color.green)
    canvasy4.StrokeShape change StrokeCap(Cap.round) change StrokeJoin(Join.bevel) change StrokeDashOff(2) change StrokeWidth(10) change StrokeColor(Color.red)
    canvasy4 automaticResize false resizeCanvas (300,300)
    canvasy4 draw()

    // 5th Canvas
    var canvasy5 = new Canvasy(new PPAShape((0.0, 0.0), SV.fill, 0.5, ListBuffer[(Double,Double)]((0,0),(100,100),(100,50),(30,10),(25,50)),10.0,Color.red))
    canvasy5 draw()
    // 6th Canvas
    val canvasy6 = new Canvasy(Video((0, 0), "src/main/scala/demo/video.ogv"))
    canvasy6 moveMouse true
    canvasy6 draw()

    // 7th Canvas
    val canvasy7 = new Canvasy()
    canvasy7 drawHand(200,200) translateX 50 translateY 600
  }
}

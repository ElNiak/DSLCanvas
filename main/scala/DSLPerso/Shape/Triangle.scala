package DSLPerso.Shape

import DSLPerso.Style.{Clear, Fill, Stroke, Style}

trait Triangle extends Shape {
  //the 3 points of the triangle
  var a: (Double, Double)
  var b: (Double, Double)
  var c: (Double, Double)
}

package DSLStatic.Shape

import DSLStatic.Style.{Clear, Fill, Stroke, Style}

trait Triangle extends Shape {
  var a: (Double, Double)
  var b: (Double, Double)
  var c: (Double, Double)
}

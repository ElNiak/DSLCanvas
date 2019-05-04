package DSLStatic.Shape

import DSLStatic.Style.{Clear, Fill, Stroke, Style}

trait Triangle extends Shape {
  var a: (Double, Double)
  var b: (Double, Double)
  var c: (Double, Double)
  override var opacity: Double
  override var style : Style
  override var x : Double
  override var y : Double
  override var size: Int
  override var rotation: Double
}

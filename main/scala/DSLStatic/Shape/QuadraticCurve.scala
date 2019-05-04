package DSLStatic.Shape
import DSLStatic.Style.Style

case class QuadraticCurve() extends Shape {
  override var style: Style = _
  override var opacity: Double = _
  override var x: Double = _
  override var y: Double = _
  override var size: Int = _
  override var rotation: Double = _
}

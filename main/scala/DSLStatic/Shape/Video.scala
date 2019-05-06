package DSLStatic.Shape
import DSLStatic.Style.Style

trait Video extends Shape {
  override var opacity: Double
  override var style : Style
  override var x : Double
  override var y : Double
  override var size: Int
  override var rotation: Double
}
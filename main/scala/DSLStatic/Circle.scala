package DSLStatic
import DSLStatic.Color.Color

case class Circle(radiusv: Double, x:Int, y:Int) extends Shape {
  override val stroke: Stroke = _
  override val color: Color = _
  override val canevasyElementModifier: Any = _
  var radius = radiusv

}

package DSLStatic.Modifier

import DSLStatic.Shape.{Circle, Shape}
import DSLStatic.{CanvasyElementModifier, ShapeAttributeException}

case class Radius(r: Double) extends CanvasyElementModifier[Shape] {
  override def change(x: Shape): Unit = {
    if (x == null)
      throw new ShapeAttributeException("shape cannot be null")
    if (r < 0)
      throw new ShapeAttributeException("Radius cannot be smaller than 0")
    x match {
      case c: Circle => c.radius = r
      case _ => throw new ShapeAttributeException("Only circle have radius")
    }
  }
}

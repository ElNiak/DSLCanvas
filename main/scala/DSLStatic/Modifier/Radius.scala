package DSLStatic.Modifier

import DSLStatic.Shape.{Circle, Shape}
import DSLStatic.{CanvasyElementModifier, ShapeAttributeException}

case class Radius(r: Double) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = {
    x match {
      case Circle((a,b),s,o,radius) =>
        x.asInstanceOf[Circle].radius = r

      case _ => throw new ShapeAttributeException("Only circle have radius")
    }
  }
}

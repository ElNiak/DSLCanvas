package DSLPerso.Modifier

import DSLPerso.Shape.{Rectangle, Shape}
import DSLPerso.{CanvasyElementModifier, ShapeAttributeException}

case class Width(w: Double) extends CanvasyElementModifier[Shape] {
  override def change(x: Shape): Unit = {
    if (x == null)
      throw new ShapeAttributeException("shape cannot be null")
    if (w <0)
      throw new ShapeAttributeException("Width cannot be smaller than 0")
    x match {
      case r: Rectangle => r.width = w
      case _ => throw new ShapeAttributeException("Only rectangle have width")
    }
  }
}

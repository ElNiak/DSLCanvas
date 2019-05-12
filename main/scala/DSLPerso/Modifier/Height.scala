package DSLPerso.Modifier

import DSLPerso.Shape.{Rectangle, Shape}
import DSLPerso.{CanvasyElementModifier, ShapeAttributeException}

case class Height(h: Double) extends CanvasyElementModifier[Shape] {
  override def change(x: Shape): Unit = {
    if (x == null)
      throw new ShapeAttributeException("shape cannot be null")
    if (h < 0)
      throw new ShapeAttributeException("Height cannot be smaller than 0")
    x match {
      case r : Rectangle => r.height = h
      case _ => throw new ShapeAttributeException("Only rectangle have height")
    }
  }

}

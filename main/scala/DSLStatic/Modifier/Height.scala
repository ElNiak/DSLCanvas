package DSLStatic.Modifier

import DSLStatic.Shape.{Rectangle, Shape}
import DSLStatic.{CanvasyElementModifier, ShapeAttributeException}

case class Height(h: Double) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = {
    if (h < 0)
      throw new ShapeAttributeException("Height cannot be smaller than 0")
    x match {
      case r : Rectangle => r.height = h
      case _ => throw new ShapeAttributeException("Only rectangle have height")
    }
  }

}

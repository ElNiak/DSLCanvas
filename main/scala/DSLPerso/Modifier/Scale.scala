package DSLPerso.Modifier

import DSLPerso.{CanvasyElementModifier, ShapeAttributeException}
import DSLPerso.Shape.{Picture, Shape, Video}

case class Scale(r: Double) extends CanvasyElementModifier[Shape] {
  override def change(x: Shape): Unit = {
    if (x == null)
      throw new ShapeAttributeException("shape cannot be null")
    if (r < 0)
      throw new ShapeAttributeException("Scale cannot be smaller than 0")
    x match {
      case c: Video => c.scale = r
      case c: Picture => c.scale = r
      case _ => throw new ShapeAttributeException("Only picture and video can be scale")
    }
  }
}

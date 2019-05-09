package DSLStatic.Modifier

import DSLStatic.Shape.Shape
import DSLStatic.Style.Stroke
import DSLStatic.{CanvasyElementModifier, ShapeAttributeException}

case class StrokeWidth(w: Double) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = {
    if (w <0)
      throw new ShapeAttributeException("Width cannot be smaller than 0")
    x.style match {
      case stroke: Stroke => stroke.width = w
      case _ => throw new ShapeAttributeException("Only stroke shape can be modified")
    }
  }

}

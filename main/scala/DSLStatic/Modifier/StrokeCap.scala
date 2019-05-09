package DSLStatic.Modifier

import DSLStatic.Shape.Shape
import DSLStatic.Style.Stroke
import DSLStatic.{CanvasyElementModifier, ShapeAttributeException}

case class StrokeCap(w: String) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = {
    if (w == null || w.isEmpty)
      throw new ShapeAttributeException("Cap cannot be null or empty")
    x.style match {
      case stroke: Stroke => stroke.cap = w
      case _ => throw new ShapeAttributeException("Only stroke shape can be modified")
    }
  }

}

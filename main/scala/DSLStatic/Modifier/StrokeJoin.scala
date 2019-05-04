package DSLStatic.Modifier

import DSLStatic.Shape.Shape
import DSLStatic.Style.Stroke
import DSLStatic.{CanvasyElementModifier, ShapeAttributeException}

case class StrokeJoin(w: String) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = {
    x.style match {
      case stroke : Stroke => stroke.join = w
      case _ => throw new ShapeAttributeException("Only stroke shape can be modified")
    }
  }

}

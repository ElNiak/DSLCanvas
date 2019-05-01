package DSLStatic.Modifier

import DSLStatic.Shape.{Rectangle, Shape}
import DSLStatic.{CanvasyElementModifier, ShapeAttributeException}

case class Height(h: Double) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = {
    x match {
      case Rectangle(a,b,width, height,s,o) =>
        x.asInstanceOf[Rectangle].height = h

      case _ => throw new ShapeAttributeException("Only rectangle have height")
    }
  }

}

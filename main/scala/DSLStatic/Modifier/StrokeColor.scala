package DSLStatic.Modifier

import DSLStatic.Shape.Shape
import DSLStatic.Style.{ColorRGB, ColorStyle, Fill, Stroke}
import DSLStatic.{CanvasyElementModifier, ShapeAttributeException}

case class StrokeColor[I <: ColorStyle](c: I) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = {
    x.style match {
      case stroke: Stroke =>
        c match {
          case b: ColorRGB =>
            stroke.colorStyle = b
            x.opacity = b.opacity
          case _ =>
        }
      case _ => throw new ShapeAttributeException("Only stroke shape can be modified")
    }
  }
}

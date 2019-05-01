package DSLStatic.Modifier

import DSLStatic.Shape.{Circle, Shape}
import DSLStatic.Style.{ColorRGB, ColorStyle, Fill, Style}
import DSLStatic.{CanvasyElementModifier, ShapeAttributeException}

case class FillColor [I <: ColorStyle](c: I) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = {
    x.style match {
      case fill: Fill =>
        c match {
          case b: ColorRGB =>
            fill.colorStyle = b
            x.opacity = b.opacity
          case _ =>
        }
      case _ => throw new ShapeAttributeException("Only filled shape can be modified")
    }
  }
}

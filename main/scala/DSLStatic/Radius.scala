package DSLStatic

case class Radius(r: Double) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = {
    x match {
      case Circle(radius, a,b,s,o) =>
        x.asInstanceOf[Circle].radius = r

      case _ => throw new ShapeAttributeException("Only circle have radius")
    }
  }
}

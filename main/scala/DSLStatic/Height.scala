package DSLStatic

case class Height(h: Double) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = {
    x match {
      case Rectangle(a,b,width, height) =>
        x.asInstanceOf[Rectangle].height = h

      case _ => throw new ShapeAttributeException("Only rectangle have height")
    }
  }

}

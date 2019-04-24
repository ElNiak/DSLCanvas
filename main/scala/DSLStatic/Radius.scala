package DSLStatic

case class Radius(r: Double) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = {
    x match {
      case Circle(radius, a,b) =>  {
        x.asInstanceOf[Circle].radius = r
      }
      case _ => print("Only rectangle have weed")
    }
  }
}

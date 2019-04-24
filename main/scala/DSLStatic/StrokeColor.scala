package DSLStatic

case class StrokeColor(c: Color.Value) extends Stroke {
  // every Shape has a stroke.
  //override def change(x: Shape): Unit = x.stroke.color = c
  override def change(x: CanvasyElement): Unit = ???
}


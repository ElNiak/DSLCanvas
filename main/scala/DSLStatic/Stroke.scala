package DSLStatic

import DSLStatic.Color.Color

abstract class Stroke {
  var width : Double
  var color : Color.Value

  def apply(w: Double): StrokeWidth = {
    StrokeWidth(w)
  }

  def apply(c: Color.Value): StrokeColor = {
    StrokeColor(c)
  }

}

object Stroke {
  def rgb(v:String) = {

  }
}


package DSLStatic

import DSLStatic.Color.Color

trait Stroke  extends CanvasyElementModifier[Shape] {
  var width : Double
  var color : Color.Value

}




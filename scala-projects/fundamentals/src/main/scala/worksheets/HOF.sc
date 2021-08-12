val weekdays = List("Mon", "Tue", "Wed", "Thu", "Fri")

weekdays.foreach(println)

weekdays.map(_.toUpperCase)

weekdays.map(_ == "Mon")

val isManicMonday = (x: String) => {x == "Mon"}: Boolean
isManicMonday("Tue")
isManicMonday("Mon")

weekdays.map(isManicMonday)

// only returns whatever is satisfied
weekdays.filter(isManicMonday)

// returns a tuple of list with one that matched and non matched.
weekdays.partition(isManicMonday)

weekdays.sortBy(_(0))

// numbers 10, 20, 30, 40, 50 and 60
val numbers = Range(0,61,10).toList.drop(1)

// scan right has 2 parameter groups, 2 pairs of ()
// an initial value and a function object.
val scanRight = numbers.scanRight(0)(_ - _)

val scanLeft = numbers.scanLeft(0)(_ - _)

val foldRight = numbers.foldRight(0)(_ - _)

val foldLeft = numbers.foldLeft(0)(_ - _)

val reduceRight = numbers.reduceRight(_ - _)

val reduceLeft = numbers.reduceLeft(_ - _)
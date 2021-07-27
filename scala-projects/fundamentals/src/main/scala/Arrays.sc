val numbers = Array(0, 2, 4, 6, 8, 10)

// accessing an array
numbers(5)

// lets multiply each number by 2
val doubleNumbers = numbers.map(_ * 2)

// filter numbers below 10
val lesserNumbers = numbers.filter(_ < 10)

// two arrays can be combined using ++ operator

val oddArr = Array(1, 3, 5, 7, 9)
val evenArr = Array(0, 2, 4, 6, 8)

val numbers1To10 = evenArr ++ oddArr // unsorted numbers!
val sortedNumbers1To10 = numbers1To10.sorted

// arrays can be reversed
sortedNumbers1To10.reverse

val fruits = Array ("banana", "apple", "pineapple", "mango", "jack fruit", "guava", "orange", "pomogranate", "kiwi", "sweet-lime")
val sortedFruits = fruits.sorted

// we can check if a predicate exists
val contains = sortedFruits.exists( f => f == "kiwi")

// this can be further simplified by using "contains"
val contains = sortedFruits.contains("blueberry")

// The find operation returns an Option and in the event of multiple occurrences, returns
// the first occurrence. Since it returns an Option, we need to use the get to get the actual
// element
val fruitOption = sortedFruits.find(f => f == "orange")
val fruit = fruitOption.get

val dupSortedFruits = sortedFruits.appended("mango")

val dupFruitOption = dupSortedFruits.find(f => f == "mango")
val dupFruit = dupFruitOption.get

// an exception is thrown if the find is unsuccessful
val nonExistentFruitOption = fruits.find(f => f == "strawberry")

// The following access will produce the java.util.NoSuchElementException and stop the flow
//nonExistentFruitOption.get

// Hence a safe way is to invoke getOrElse and provide a value if target value is not found
val nonExistentFruit = nonExistentFruitOption.getOrElse("Non Existent Fruit")


val counts = Array(
  "900,www.google.com"
  , "60,www.yahoo.com"
  , "360,www.msn.com"
  , "45,www.cnn.com"
  , "160,www.stackoverflow.com"
  , "20,www.berkley.edu"
  , "360,www.wikipedia.org"
  , "30,www.nasa.org"
  , "60,www.nsa.gov"
)

val countsMap = counts.map(_.split(",")).map {
  case Array(s1,s2) => (s1, s2)
}

// find the count for .com
val comCounts = countsMap.map {
  case (x, y) if y.endsWith(".com") => x.toInt
  case _ => 0
}
// but this gives back an array of counts. What if we were to add all of them?

val totalCounts = comCounts.reduceLeft(_ + _)
// also can be achieved using a method "sum"
comCounts.sum
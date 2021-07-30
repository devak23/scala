// 3 properties that is required by any language to proclaim support for
// supporting first class functions


// 1. Functions should be able to be stored as values.
val compare = (s1: String, s2: String) => {
  if (s1 == s2) 0
  else if (s1 > s2) -1
  else 1
}: Int

compare("Soham", "Soham")
compare("Abhay", "Manik")
compare("Soham", "Manik")

val compareReverse = (s1: String, s2: String) => {
  if (s1 == s2) 0
  else if (s1 > s2) 1
  else -1
}: Int

// 2. A function should be able to be returned from another function
def getComparator (reverse: Boolean): (String, String) => Int = {
  if (reverse) compareReverse
  else compare
}

// The getComparator is passed reverse = true which returns the compare/compareReverse
// function which gets passed in the two strings - Manik and Abhay
val output = getComparator(true)("Manik","Abhay")

// 3. Functions should be able to accept other functions as their parameters

def smartCompare(s1: String, s2: String, cmpFn: (String, String) => Int): Int = {
  cmpFn(s1, s2)
}

val forward = smartCompare("Abhay", "Manik", compare)
val reverse = smartCompare("Abhay", "Manik", compareReverse)

import java.time.LocalDateTime
import java.util.UUID

class Account (id: UUID, name: String, openingDate: LocalDateTime) {
  val _id: UUID = id
  var _name: String = name
  val _openingDate: LocalDateTime = openingDate

  // getters
  def getId: UUID = _id
  def getName: String = _name
  def getOpeningDate: LocalDateTime = _openingDate

  // setters/updaters
  def updateName (newName: String): Unit = _name = newName

  def this(name: String) {
    this(UUID.randomUUID(), name, LocalDateTime.now())
  }
}

class CreditAccount(name: String) extends Account(name: String) {
   private val _accountType = "Credit"
  def getAccountType: String = _accountType
}

class LoanAccount(name: String) extends Account(name: String) {
  private val _accountType = "Overdraft"
  def getAccountType: String = _accountType
}

object AccountRunner extends App {
  val checkingAccount = new Account(UUID.randomUUID(), "CheckingAccount", LocalDateTime.now())
  val savingsAccount = new Account(UUID.randomUUID(), "SavingsAccount", LocalDateTime.now.plusHours(6))
  val dmatAccount = new Account("DMATAccount")

  println(checkingAccount._id, checkingAccount._name, checkingAccount._openingDate)
  println(savingsAccount._id, savingsAccount._name, savingsAccount._openingDate)
  println(dmatAccount._id, dmatAccount._name, dmatAccount._openingDate)

  val creditAccount = new CreditAccount("Travel Mastercard")
  println(creditAccount._id, creditAccount._name, creditAccount._openingDate, creditAccount.getAccountType)

  val loanAccount = new LoanAccount(name = "Overdraft Account")
  println(loanAccount._id, loanAccount._name, loanAccount._openingDate, loanAccount.getAccountType)
}
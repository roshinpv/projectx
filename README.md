Dim questions(107) As String
Dim optionsA(107) As String
Dim optionsB(107) As String
Dim optionsC(107) As String
Dim optionsD(107) As String
Dim correctAnswers(107) As String
Dim currentQuestionIndex As Integer
Dim usedQuestions As Collection
Dim score As Integer
Dim questionCount As Integer
Dim selectedOption As String ' Global variable to hold the selected option
s
Sub UpdateScoreDisplay()
    ActivePresentation.Slides(1).Shapes("score").TextFrame.TextRange.Text = "Score: " & score & " / " & questionCount
End Sub
' Initialize the quiz and set up questions
Sub InitializeQuiz()
    ' Define questions and options
' Bill Pay Questions
questions(0) = "What feature of Bill Pay allows automatic scheduling?"
optionsA(0) = "Manual Scheduling"
optionsB(0) = "One-Time Payment"
optionsC(0) = "Direct Transfer"
optionsD(0) = "Automatic Payments"
correctAnswers(0) = "D"

questions(1) = "Which feature in Bill Pay provides notifications for due dates and confirmations?"
optionsA(1) = "Direct Transfers"
optionsB(1) = "Alerts and Reminders"
optionsC(1) = "Automatic Payments"
optionsD(1) = "eBills Integration"
correctAnswers(1) = "B"

questions(2) = "How does eBills in Bill Pay benefit users?"
optionsA(2) = "Provides a paper copy of bills"
optionsB(2) = "Allows paperless billing"
optionsC(2) = "Automatically schedules all bills"
optionsD(2) = "Sends physical checks"
correctAnswers(2) = "B"

' Direct Pay Questions
questions(3) = "Who primarily benefits from Direct Pay?"
optionsA(3) = "Business customers"
optionsB(3) = "International travelers"
optionsC(3) = "Students"
optionsD(3) = "Personal banking users"
correctAnswers(3) = "A"

questions(4) = "What security feature does Direct Pay use?"
optionsA(4) = "RSA SecurID Tokens"
optionsB(4) = "Text Message Verification"
optionsC(4) = "Bank Account Numbers"
optionsD(4) = "Password Protection"
correctAnswers(4) = "A"

questions(5) = "Direct Pay is ideal for paying which type of recipient?"
optionsA(5) = "Friends and family"
optionsB(5) = "Online shopping"
optionsC(5) = "Utility companies only"
optionsD(5) = "Vendors and employees"
correctAnswers(5) = "D"

' Zelle Questions
questions(6) = "What information do you need to send money with Zelle?"
optionsA(6) = "Social Security Number"
optionsB(6) = "Bank Account Number"
optionsC(6) = "Email address or mobile number"
optionsD(6) = "Home address"
correctAnswers(6) = "C"

questions(7) = "What is a primary advantage of Zelle?"
optionsA(7) = "Transfers typically complete within minutes"
optionsB(7) = "It is limited to Wells Fargo users"
optionsC(7) = "Fees apply for every transaction"
optionsD(7) = "It requires visiting a branch"
correctAnswers(7) = "A"

questions(8) = "Which type of payments is Zelle best for?"
optionsA(8) = "International transfers"
optionsB(8) = "Recurring loan payments"
optionsC(8) = "Peer-to-peer transfers"
optionsD(8) = "Monthly bill payments"
correctAnswers(8) = "C"

' ExpressSend Questions
questions(9) = "What is ExpressSend primarily used for?"
optionsA(9) = "Domestic transfers"
optionsB(9) = "Credit card payments"
optionsC(9) = "Direct deposits"
optionsD(9) = "International remittances"
correctAnswers(9) = "D"

questions(10) = "What type of recipients does ExpressSend support?"
optionsA(10) = "U.S. government agencies"
optionsB(10) = "International family and friends"
optionsC(10) = "Local utility companies"
optionsD(10) = "Only U.S. citizens"
correctAnswers(10) = "B"

questions(11) = "Which feature allows flexible payout methods in ExpressSend?"
optionsA(11) = "ATM withdrawal"
optionsB(11) = "Flexible Payout Options"
optionsC(11) = "Currency exchange rate"
optionsD(11) = "Fixed transfer options"
correctAnswers(11) = "B"

' Wires Questions
questions(12) = "What is a key feature of wire transfers?"
optionsA(12) = "Automatic recurring transfers"
optionsB(12) = "Same-day delivery for domestic wires"
optionsC(12) = "Only for U.S. transfers"
optionsD(12) = "Monthly summary of transactions"
correctAnswers(12) = "B"

questions(13) = "Wire transfers allow users to send funds in which currency options?"
optionsA(13) = "Only U.S. dollars"
optionsB(13) = "Both domestic and foreign currencies"
optionsC(13) = "Cryptocurrency only"
optionsD(13) = "Local currency only"
correctAnswers(13) = "B"

questions(14) = "Which feature provides security in Wells Fargo wire transfers?"
optionsA(14) = "Physical tokens only"
optionsB(14) = "Branch-only initiation"
optionsC(14) = "Two-factor authentication"
optionsD(14) = "Account statement codes"
correctAnswers(14) = "C"

' Check Deposit Questions
questions(15) = "What is a benefit of Mobile Check Deposit?"
optionsA(15) = "Monthly statements"
optionsB(15) = "Instant fund availability"
optionsC(15) = "Deposit checks anywhere with a smartphone"
optionsD(15) = "Physical submission of checks"
correctAnswers(15) = "C"

questions(16) = "Where can customers deposit checks using Wells Fargo?"
optionsA(16) = "ATMs and branches"
optionsB(16) = "Only in branches"
optionsC(16) = "Online banking only"
optionsD(16) = "Text messaging service"
correctAnswers(16) = "A"

questions(17) = "What is required to complete a Mobile Check Deposit?"
optionsA(17) = "Mail the check"
optionsB(17) = "Photograph both sides of the check"
optionsC(17) = "Text customer service"
optionsD(17) = "Visit a branch"
correctAnswers(17) = "B"

' Wells Fargo Paze Wallet Questions
questions(18) = "What type of payments is Wells Fargo Paze Wallet designed for?"
optionsA(18) = "Wire transfers"
optionsB(18) = "ATM withdrawals"
optionsC(18) = "Online payments"
optionsD(18) = "In-branch payments"
correctAnswers(18) = "C"

questions(19) = "Which feature enhances security in Paze Wallet?"
optionsA(19) = "Merchant access to card numbers"
optionsB(19) = "Limited transactions"
optionsC(19) = "Hidden card numbers from merchants"
optionsD(19) = "Only available in-branch"
correctAnswers(19) = "C"

questions(20) = "How does Paze Wallet streamline online shopping?"
optionsA(20) = "Provides a slow checkout process"
optionsB(20) = "Stores credit card details securely"
optionsC(20) = "Limits purchases to Wells Fargo members"
optionsD(20) = "Requires photo ID for every purchase"
correctAnswers(20) = "B"

' Debit Cards Questions
questions(21) = "What technology enhances Wells Fargo Debit Card security?"
optionsA(21) = "Password verification"
optionsB(21) = "Manual approval"
optionsC(21) = "EMV chip technology"
optionsD(21) = "Card readers only"
correctAnswers(21) = "C"

questions(22) = "Where are Wells Fargo Debit Cards accepted?"
optionsA(22) = "Only at Wells Fargo ATMs"
optionsB(22) = "Only in the U.S."
optionsC(22) = "Selected locations only"
optionsD(22) = "Worldwide wherever Visa or Mastercard is accepted"
correctAnswers(22) = "D"

questions(23) = "Which feature allows cardholders to track their spending?"
optionsA(23) = "Transaction alerts"
optionsB(23) = "Monthly statements"
optionsC(23) = "Daily bank visits"
optionsD(23) = "Email notifications only"
correctAnswers(23) = "A"

' Me2Me Transfers Questions
questions(24) = "Me2Me Transfers are primarily used for moving funds between:"
optionsA(24) = "Different customers’ accounts"
optionsB(24) = "Only checking accounts"
optionsC(24) = "A customer's own Wells Fargo accounts"
optionsD(24) = "International accounts"
correctAnswers(24) = "C"

questions(25) = "What fee applies to Me2Me Transfers between personal accounts?"
optionsA(25) = "Transaction fee only"
optionsB(25) = "International fee only"
optionsC(25) = "Monthly subscription"
optionsD(25) = "No fee"
correctAnswers(25) = "D"

questions(26) = "How quickly do Me2Me Transfers process between linked accounts?"
optionsA(26) = "After 24 hours"
optionsB(26) = "Weekly"
optionsC(26) = "Instantly"
optionsD(26) = "Monthly only"
correctAnswers(26) = "C"

' Bill Pay Additional Questions
questions(27) = "What benefit does Bill Pay provide to users for managing multiple bills?"
optionsA(27) = "It restricts multiple payments"
optionsB(27) = "It applies loyalty points"
optionsC(27) = "It offers bill discounts"
optionsD(27) = "It consolidates bills for a single payment"
correctAnswers(27) = "D"

questions(28) = "Which feature in Bill Pay sends reminders before due dates?"
optionsA(28) = "Recurring Payments"
optionsB(28) = "Quick Pay"
optionsC(28) = "Alerts and Reminders"
optionsD(28) = "eBills"
correctAnswers(28) = "C"

questions(29) = "How can Bill Pay users track each payment’s progress?"
optionsA(29) = "Account Management"
optionsB(29) = "Direct Pay"
optionsC(29) = "Monthly Summary"
optionsD(29) = "Payment Tracking"
correctAnswers(29) = "D"

' Direct Pay Additional Questions
questions(30) = "What access levels does Direct Pay provide to business users?"
optionsA(30) = "Single user access only"
optionsB(30) = "Multiple access levels"
optionsC(30) = "Direct transfer levels"
optionsD(30) = "Read-only access"
correctAnswers(30) = "B"

questions(31) = "How does Direct Pay benefit businesses for payroll?"
optionsA(31) = "It limits payroll scheduling"
optionsB(31) = "It handles cash transactions"
optionsC(31) = "It simplifies employee payroll payments"
optionsD(31) = "It calculates taxes"
correctAnswers(31) = "C"

questions(32) = "What feature in Direct Pay ensures secure access for businesses?"
optionsA(32) = "Monthly account codes"
optionsB(32) = "RSA SecurID Tokens"
optionsC(32) = "Branch verification"
optionsD(32) = "Online-only login"
correctAnswers(32) = "B"

' Zelle Additional Questions
questions(33) = "What makes Zelle suitable for immediate money transfers?"
optionsA(33) = "Instantaneous transfer processing"
optionsB(33) = "Monthly notifications"
optionsC(33) = "Bi-weekly transfer options"
optionsD(33) = "Daily summaries"
correctAnswers(33) = "A"

questions(34) = "What is a primary benefit of Zelle’s simple setup?"
optionsA(34) = "It requires only an email or mobile number"
optionsB(34) = "Users must visit a branch to enroll"
optionsC(34) = "It needs complex security steps"
optionsD(34) = "It’s available only for Wells Fargo customers"
correctAnswers(34) = "A"

questions(35) = "Which feature in Zelle allows you to split costs easily with friends?"
optionsA(35) = "Recurring Transfers"
optionsB(35) = "Split Payments"
optionsC(35) = "Group Pay"
optionsD(35) = "Expense Manager"
correctAnswers(35) = "B"

' ExpressSend Additional Questions
questions(36) = "How does ExpressSend support international remittance needs?"
optionsA(36) = "It processes domestic transfers only"
optionsB(36) = "It only allows digital wallet deposits"
optionsC(36) = "It facilitates cash pickup and bank deposits abroad"
optionsD(36) = "It’s limited to U.S.-based transactions"
correctAnswers(36) = "C"

questions(37) = "What feature in ExpressSend makes it easy for tracking transfers?"
optionsA(37) = "Monthly tracking emails"
optionsB(37) = "Online account statements"
optionsC(37) = "Manual tracking reports"
optionsD(37) = "Built-in tracking and alerts"
correctAnswers(37) = "D"

questions(38) = "Who primarily benefits from ExpressSend?"
optionsA(38) = "Only Wells Fargo employees"
optionsB(38) = "Government agencies"
optionsC(38) = "U.S.-based companies"
optionsD(38) = "Individuals sending funds to family abroad"
correctAnswers(38) = "D"

' Wires Additional Questions
questions(39) = "Which type of customers are ideal for using wire transfers?"
optionsA(39) = "Those who send frequent, small payments"
optionsB(39) = "Those needing fast, large payments"
optionsC(39) = "Cash-based transfers only"
optionsD(39) = "Students only"
correctAnswers(39) = "B"

questions(40) = "Which feature ensures wire transfers’ safety?"
optionsA(40) = "Digital wallet verification"
optionsB(40) = "Only branch visits"
optionsC(40) = "Biometric access only"
optionsD(40) = "Two-factor authentication"
correctAnswers(40) = "D"

questions(41) = "What is a key benefit of Wells Fargo’s wire transfer service?"
optionsA(41) = "It handles only domestic payments"
optionsB(41) = "It offers multi-currency options"
optionsC(41) = "Transfers can’t exceed $500"
optionsD(41) = "Only monthly transfers are allowed"
correctAnswers(41) = "B"

' Check Deposit Additional Questions
questions(42) = "How does Wells Fargo ensure the security of Mobile Check Deposits?"
optionsA(42) = "Only through email confirmations"
optionsB(42) = "Requires only in-person deposits"
optionsC(42) = "By requiring branch visits"
optionsD(42) = "Through encrypted image uploads"
correctAnswers(42) = "D"

questions(43) = "Which device capability is required for Mobile Check Deposits?"
optionsA(43) = "Smartphone camera"
optionsB(43) = "Fingerprint scanner"
optionsC(43) = "Text message verification"
optionsD(43) = "Biometric login"
correctAnswers(43) = "A"

questions(44) = "What primary benefit does ATM check deposits offer Wells Fargo customers?"
optionsA(44) = "Only monthly deposits allowed"
optionsB(44) = "It’s limited to branch hours"
optionsC(44) = "Instant check clearing"
optionsD(44) = "Deposit checks any time of the day"
correctAnswers(44) = "D"

' Wells Fargo Paze Wallet Additional Questions
questions(45) = "Which payment method is Wells Fargo Paze Wallet designed to support?"
optionsA(45) = "Cash deposits"
optionsB(45) = "ATM-only use"
optionsC(45) = "Only physical store transactions"
optionsD(45) = "Online purchases"
correctAnswers(45) = "D"

questions(46) = "What security feature does Paze Wallet offer?"
optionsA(46) = "No security for online use"
optionsB(46) = "Only limited to in-store use"
optionsC(46) = "Open card number display"
optionsD(46) = "Hides card numbers from merchants"
correctAnswers(46) = "D"

questions(47) = "How does Paze Wallet streamline the online checkout experience?"
optionsA(47) = "Limits purchases to Wells Fargo members"
optionsB(47) = "Requires SMS verification for each purchase"
optionsC(47) = "Stores card details securely for quick checkout"
optionsD(47) = "Requires physical card for each transaction"
correctAnswers(47) = "C"

' Debit Cards Additional Questions
questions(48) = "Where are Wells Fargo Debit Cards accepted?"
optionsA(48) = "Only in the U.S."
optionsB(48) = "Only at Wells Fargo ATMs"
optionsC(48) = "Worldwide wherever Visa or Mastercard is accepted"
optionsD(48) = "Only in select cities"
correctAnswers(48) = "C"

questions(49) = "What benefit does EMV chip technology provide for Wells Fargo Debit Cards?"
optionsA(49) = "Monthly statements"
optionsB(49) = "Increased security for in-store transactions"
optionsC(49) = "Exclusive to online use"
optionsD(49) = "Higher spending limits"
correctAnswers(49) = "B"

questions(50) = "How can Wells Fargo Debit Card users monitor transactions?"
optionsA(50) = "Daily in-branch checks"
optionsB(50) = "Text reminders only"
optionsC(50) = "Only monthly summaries"
optionsD(50) = "Transaction alerts"
correctAnswers(50) = "D"

' Me2Me Transfers Additional Questions
questions(51) = "What is the main purpose of Me2Me Transfers?"
optionsA(51) = "Only for business accounts"
optionsB(51) = "Transfers to third-party accounts"
optionsC(51) = "International transfers only"
optionsD(51) = "Transfers between your own Wells Fargo accounts"
correctAnswers(51) = "D"

questions(52) = "How can users manage Me2Me Transfers effectively?"
optionsA(52) = "Only with customer support"
optionsB(52) = "By visiting a branch only"
optionsC(52) = "Using a special verification token"
optionsD(52) = "Through Wells Fargo Online and Mobile Banking"
correctAnswers(52) = "D"

questions(53) = "What fee applies for Me2Me Transfers within Wells Fargo accounts?"
optionsA(53) = "Monthly subscription fee"
optionsB(53) = "Foreign transfer fee"
optionsC(53) = "No fee"
optionsD(53) = "Transaction fee"
correctAnswers(53) = "C"

' Bill Pay More Questions
questions(54) = "What advantage does eBills in Bill Pay offer to users?"
optionsA(54) = "Automatic bill scheduling"
optionsB(54) = "Allows paperless billing from billers"
optionsC(54) = "Consolidates multiple payments"
optionsD(54) = "Provides loyalty rewards"
correctAnswers(54) = "B"

questions(55) = "Which feature helps Bill Pay users track upcoming bills?"
optionsA(55) = "Alerts and Reminders"
optionsB(55) = "Recurring Payments"
optionsC(55) = "Direct Transfers"
optionsD(55) = "Manual Scheduling"
correctAnswers(55) = "A"

questions(56) = "What feature allows Bill Pay users to send payments to multiple billers?"
optionsA(56) = "Multiple Payee Management"
optionsB(56) = "Direct Pay Integration"
optionsC(56) = "One-time Payment"
optionsD(56) = "eBills Integration"
correctAnswers(56) = "A"

' Direct Pay More Questions
questions(57) = "What key feature of Direct Pay allows scheduling of payments?"
optionsA(57) = "Recurring Payments"
optionsB(57) = "Automatic Notifications"
optionsC(57) = "Real-time Payments"
optionsD(57) = "Manual Entry Only"
correctAnswers(57) = "A"

questions(58) = "What benefit does RSA SecurID provide for Direct Pay users?"
optionsA(58) = "Convenience for personal users"
optionsB(58) = "Enhanced security for business accounts"
optionsC(58) = "Instant access for vendors"
optionsD(58) = "Automated payroll tracking"
correctAnswers(58) = "B"

questions(59) = "What type of business transactions is Direct Pay best suited for?"
optionsA(59) = "Sending money to family and friends"
optionsB(59) = "Making business payroll payments"
optionsC(59) = "International travel transactions"
optionsD(59) = "In-store purchases"
correctAnswers(59) = "B"

' Zelle More Questions
questions(60) = "How does Zelle simplify peer-to-peer payments?"
optionsA(60) = "Uses email or mobile number to identify recipients"
optionsB(60) = "Requires visiting a Wells Fargo branch"
optionsC(60) = "Only works with cash payments"
optionsD(60) = "Processes checks automatically"
correctAnswers(60) = "A"

questions(61) = "What is a primary benefit of Zelle for group expenses?"
optionsA(61) = "Monthly reminders"
optionsB(61) = "Split Payment feature"
optionsC(61) = "Daily account summaries"
optionsD(61) = "Only for business expenses"
correctAnswers(61) = "B"

questions(62) = "What security feature does Zelle use to protect transactions?"
optionsA(62) = "Encrypted PINs only"
optionsB(62) = "Biometric login"
optionsC(62) = "Wells Fargo’s Secure System"
optionsD(62) = "Branch-based initiation only"
correctAnswers(62) = "C"

' ExpressSend More Questions
questions(63) = "ExpressSend primarily supports which type of transfer?"
optionsA(63) = "Transfers to domestic savings accounts"
optionsB(63) = "International remittance to select recipients"
optionsC(63) = "Local in-store payments"
optionsD(63) = "Internal account transfers only"
correctAnswers(63) = "B"

questions(64) = "Which feature of ExpressSend benefits users with family abroad?"
optionsA(64) = "Flexible Payout Options"
optionsB(64) = "Loyalty points"
optionsC(64) = "Monthly rewards program"
optionsD(64) = "Local use only"
correctAnswers(64) = "A"

questions(65) = "What security measure is included in ExpressSend?"
optionsA(65) = "Branch verification"
optionsB(65) = "Built-in transaction tracking"
optionsC(65) = "No tracking available"
optionsD(65) = "Monthly statement"
correctAnswers(65) = "B"

' Wires More Questions
questions(66) = "What type of transfer does Wells Fargo wire service provide?"
optionsA(66) = "Recurring bill payments"
optionsB(66) = "Same-day large or international transfers"
optionsC(66) = "Peer-to-peer payments"
optionsD(66) = "Monthly transfers only"
correctAnswers(66) = "B"

questions(67) = "What feature in wire transfers allows users to repeat transfers automatically?"
optionsA(67) = "Recurring Wires"
optionsB(67) = "One-time Payments"
optionsC(67) = "Express Transfers"
optionsD(67) = "Direct Transfer"
correctAnswers(67) = "A"

questions(68) = "What benefit does Wells Fargo wire transfer service offer for large transactions?"
optionsA(68) = "Same-day processing and currency options"
optionsB(68) = "Branch-only access"
optionsC(68) = "Text-based verification only"
optionsD(68) = "ATM integration"
correctAnswers(68) = "A"

' Check Deposit More Questions
questions(69) = "What is a primary benefit of using Mobile Check Deposit?"
optionsA(69) = "Provides digital access to physical checks"
optionsB(69) = "Requires branch verification for all checks"
optionsC(69) = "Only accepts deposits during business hours"
optionsD(69) = "Offers cash-back rewards"
correctAnswers(69) = "A"

questions(70) = "What device capability does Mobile Check Deposit require?"
optionsA(70) = "Text messaging"
optionsB(70) = "Smartphone camera"
optionsC(70) = "Biometric scanning"
optionsD(70) = "Fingerprint sensor"
correctAnswers(70) = "B"

questions(71) = "Where else can Wells Fargo customers deposit checks?"
optionsA(71) = "Partner stores"
optionsB(71) = "ATM and branch locations"
optionsC(71) = "Only at select branches"
optionsD(71) = "Mobile app only"
correctAnswers(71) = "B"

' Wells Fargo Paze Wallet More Questions
questions(72) = "Which type of purchase is Paze Wallet primarily designed for?"
optionsA(72) = "In-store purchases only"
optionsB(72) = "Only ATM transactions"
optionsC(72) = "Online purchases"
optionsD(72) = "Text-based purchases"
correctAnswers(72) = "C"

questions(73) = "How does Paze Wallet help secure online transactions?"
optionsA(73) = "Keeps card numbers hidden from merchants"
optionsB(73) = "Shows card details to merchants for verification"
optionsC(73) = "Only allows domestic purchases"
optionsD(73) = "Requires branch verification"
correctAnswers(73) = "A"

questions(74) = "How does Wells Fargo Paze Wallet improve checkout experiences?"
optionsA(74) = "Enables cash-back options"
optionsB(74) = "Stores card information securely for faster checkout"
optionsC(74) = "Requires verification for each purchase"
optionsD(74) = "Reduces transaction fees"
correctAnswers(74) = "B"

' Debit Cards More Questions
questions(75) = "What feature makes Wells Fargo Debit Cards globally accessible?"
optionsA(75) = "Only works at Wells Fargo ATMs"
optionsB(75) = "Worldwide acceptance at Visa/Mastercard locations"
optionsC(75) = "Restricted to U.S. locations"
optionsD(75) = "Available only for personal use"
correctAnswers(75) = "B"

questions(76) = "What primary security feature does Wells Fargo Debit Cards offer?"
optionsA(76) = "Transaction alerts"
optionsB(76) = "EMV chip technology"
optionsC(76) = "Limited online use"
optionsD(76) = "Only branch transactions"
correctAnswers(76) = "B"

questions(77) = "How can Wells Fargo Debit Cardholders monitor spending?"
optionsA(77) = "Daily in-branch checks"
optionsB(77) = "Transaction alerts sent to mobile"
optionsC(77) = "Requires monthly in-person updates"
optionsD(77) = "Only annual reviews"
correctAnswers(77) = "B"

' Me2Me Transfers More Questions
questions(78) = "What is the primary use of Me2Me Transfers?"
optionsA(78) = "Transfers between a customer’s own Wells Fargo accounts"
optionsB(78) = "International money transfers"
optionsC(78) = "Transfers to third-party accounts"
optionsD(78) = "Local in-store transfers"
correctAnswers(78) = "A"

questions(79) = "Which feature allows users to automate Me2Me Transfers?"
optionsA(79) = "Bi-weekly transfers"
optionsB(79) = "Scheduled recurring transfers"
optionsC(79) = "ATM transfer setup"
optionsD(79) = "Text message initiation"
correctAnswers(79) = "B"

questions(80) = "How much does Wells Fargo charge for Me2Me Transfers within personal accounts?"
optionsA(80) = "No fee"
optionsB(80) = "Fixed monthly fee"
optionsC(80) = "Per-transaction fee"
optionsD(80) = "Annual fee"
correctAnswers(80) = "A"
' Bill Pay Additional Questions
questions(81) = "What allows Bill Pay users to manage bills in one place?"
optionsA(81) = "eBills Integration"
optionsB(81) = "Multiple Payee Management"
optionsC(81) = "Auto Reminders"
optionsD(81) = "Direct Pay"
correctAnswers(81) = "B"

questions(82) = "Which feature in Bill Pay ensures on-time payments?"
optionsA(82) = "Automatic Payments"
optionsB(82) = "One-Time Payment"
optionsC(82) = "Direct Deposits"
optionsD(82) = "Payment History"
correctAnswers(82) = "A"

questions(83) = "What benefit does tracking in Bill Pay provide?"
optionsA(83) = "Manages spending limits"
optionsB(83) = "Shows payment history and status"
optionsC(83) = "Reverses failed payments"
optionsD(83) = "Offers rewards for timely payments"
correctAnswers(83) = "B"

' Direct Pay Additional Questions
questions(84) = "Direct Pay is most beneficial for what type of organization?"
optionsA(84) = "Retail customers"
optionsB(84) = "Government entities"
optionsC(84) = "Small businesses"
optionsD(84) = "Large corporations only"
correctAnswers(84) = "C"

questions(85) = "Which feature allows Direct Pay to offer role-specific access?"
optionsA(85) = "Multiple User Access Levels"
optionsB(85) = "Mobile Check Deposits"
optionsC(85) = "Payroll Setup"
optionsD(85) = "Monthly Statements"
correctAnswers(85) = "A"

questions(86) = "How does Direct Pay facilitate employee payroll?"
optionsA(86) = "Direct deposits to employees"
optionsB(86) = "By tracking employee taxes"
optionsC(86) = "By limiting payroll frequency"
optionsD(86) = "By offering employee loans"
correctAnswers(86) = "A"

' Zelle Additional Questions
questions(87) = "Zelle transactions typically complete within what timeframe?"
optionsA(87) = "A few minutes"
optionsB(87) = "Two business days"
optionsC(87) = "Weekly processing"
optionsD(87) = "Only monthly"
correctAnswers(87) = "A"

questions(88) = "What makes Zelle convenient for splitting bills?"
optionsA(88) = "Text-only confirmations"
optionsB(88) = "Direct payment to family"
optionsC(88) = "Split Payment option"
optionsD(88) = "Automatic transaction reports"
correctAnswers(88) = "C"

questions(89) = "Which Wells Fargo system protects Zelle transfers?"
optionsA(89) = "Biometric Authentication"
optionsB(89) = "PIN Entry"
optionsC(89) = "Secure System Integration"
optionsD(89) = "Account verification codes"
correctAnswers(89) = "C"

' ExpressSend Additional Questions
questions(90) = "ExpressSend is primarily used for which type of transfers?"
optionsA(90) = "Domestic utility payments"
optionsB(90) = "Internal account transfers"
optionsC(90) = "International remittances"
optionsD(90) = "Local in-store purchases"
correctAnswers(90) = "C"

questions(91) = "What feature in ExpressSend benefits users with overseas family?"
optionsA(91) = "Cash Pickup Options"
optionsB(91) = "Loyalty Rewards Program"
optionsC(91) = "Enhanced Security Codes"
optionsD(91) = "ATM Cash-Back"
correctAnswers(91) = "A"

questions(92) = "Which tracking feature is built into ExpressSend?"
optionsA(92) = "Daily Transaction Summaries"
optionsB(92) = "Built-in Transfer Alerts"
optionsC(92) = "Monthly Account Statements"
optionsD(92) = "Direct Phone Verification"
correctAnswers(92) = "B"

' Wires Additional Questions
questions(93) = "What is a key feature of Wells Fargo wire transfers?"
optionsA(93) = "Monthly fee-based transfers"
optionsB(93) = "Large transfers with currency options"
optionsC(93) = "Only works domestically"
optionsD(93) = "Limited to Wells Fargo customers"
correctAnswers(93) = "B"

questions(94) = "How are wire transfers protected within Wells Fargo?"
optionsA(94) = "Direct phone verification only"
optionsB(94) = "Two-factor authentication"
optionsC(94) = "Only branch verification"
optionsD(94) = "Biometric fingerprinting"
correctAnswers(94) = "B"

questions(95) = "What advantage does Wells Fargo wire transfer provide for large transactions?"
optionsA(95) = "Faster, same-day transfer options"
optionsB(95) = "Monthly bonus rewards"
optionsC(95) = "No security fees"
optionsD(95) = "Only available for business customers"
correctAnswers(95) = "A"

' Check Deposit Additional Questions
questions(96) = "What feature does Mobile Check Deposit offer for convenience?"
optionsA(96) = "Deposit from any smartphone camera"
optionsB(96) = "ATM-only access"
optionsC(96) = "Text message-only access"
optionsD(96) = "Branch-only access"
correctAnswers(96) = "A"

questions(97) = "Which feature makes Mobile Check Deposit secure?"
optionsA(97) = "Verifies by branch manager"
optionsB(97) = "Secure image encryption"
optionsC(97) = "Biometric identification"
optionsD(97) = "Limited to Wells Fargo ATMs"
correctAnswers(97) = "B"

questions(98) = "What main benefit does Mobile Check Deposit provide Wells Fargo users?"
optionsA(98) = "Loyalty points"
optionsB(98) = "24/7 deposit flexibility"
optionsC(98) = "Cash-back on deposits"
optionsD(98) = "Instant fund access"
correctAnswers(98) = "B"

' Wells Fargo Paze Wallet Additional Questions
questions(99) = "Paze Wallet is designed for which payment method?"
optionsA(99) = "Online purchases"
optionsB(99) = "ATM withdrawals"
optionsC(99) = "In-store purchases"
optionsD(99) = "Cash deposits"
correctAnswers(99) = "A"

questions(100) = "How does Paze Wallet enhance online transaction security?"
optionsA(100) = "Hides card details from merchants"
optionsB(100) = "Displays card information"
optionsC(100) = "Limits purchases to U.S. only"
optionsD(100) = "Requires text confirmation"
correctAnswers(100) = "A"

questions(101) = "Which feature of Paze Wallet speeds up online checkout?"
optionsA(101) = "Card information stored securely"
optionsB(101) = "Cash-based transaction"
optionsC(101) = "Only branch-based access"
optionsD(101) = "Monthly rewards program"
correctAnswers(101) = "A"

' Debit Cards Additional Questions
questions(102) = "Where can Wells Fargo Debit Cards be used?"
optionsA(102) = "U.S. locations only"
optionsB(102) = "Worldwide where Visa/Mastercard is accepted"
optionsC(102) = "Only at Wells Fargo ATMs"
optionsD(102) = "In-store only"
correctAnswers(102) = "B"

questions(103) = "Which technology adds security to Wells Fargo Debit Cards?"
optionsA(103) = "Magnetic strip only"
optionsB(103) = "EMV chip technology"
optionsC(103) = "Branch ID verification"
optionsD(103) = "Limited online use"
correctAnswers(103) = "B"

questions(104) = "How can Wells Fargo Debit Card users track spending?"
optionsA(104) = "Only in-branch statements"
optionsB(104) = "Online transaction alerts"
optionsC(104) = "Through monthly bank visits"
optionsD(104) = "By request only"
correctAnswers(104) = "B"

' Me2Me Transfers Additional Questions
questions(105) = "What is the main purpose of Me2Me Transfers?"
optionsA(105) = "Transfers between your own Wells Fargo accounts"
optionsB(105) = "Transfers to third-party accounts"
optionsC(105) = "Only for business accounts"
optionsD(105) = "International transfers only"
correctAnswers(105) = "A"

questions(106) = "How can users automate Me2Me Transfers?"
optionsA(106) = "By branch-only access"
optionsB(106) = "Scheduled recurring transfers"
optionsC(106) = "Text message setup"
optionsD(106) = "Monthly branch visits"
correctAnswers(106) = "B"

questions(107) = "How much does Wells Fargo charge for Me2Me Transfers within the same bank?"
optionsA(107) = "Foreign transaction fee"
optionsB(107) = "Monthly subscription fee"
optionsC(107) = "No fee"
optionsD(107) = "Transaction fee"
correctAnswers(107) = "C"
    ' (Add more questions and options here up to questions(99))
    
    ' Initialize quiz variables
    
    score = 0
    questionCount = 0
    Set usedQuestions = New Collection
    UpdateScoreDisplay

    ' Start the quiz with the first random question
    
    ShowRandomQuestion
End Sub

' Display a random question that hasn’t been used yet
Sub ShowRandomQuestion()
    Dim questionIndex As Integer
    Do
        questionIndex = Int((26) * Rnd)
    Loop While IsInCollection(usedQuestions, questionIndex)

    ' Mark this question as used
    usedQuestions.Add questionIndex
    currentQuestionIndex = questionIndex
    questionCount = questionCount + 1

    ' Display question and options
    With ActivePresentation.Slides(1)
        .Shapes("QuestionText").TextFrame.TextRange.Text = questions(questionIndex)
        .Shapes("OptionA").TextFrame.TextRange.Text = optionsA(questionIndex)
        .Shapes("OptionB").TextFrame.TextRange.Text = optionsB(questionIndex)
        .Shapes("OptionC").TextFrame.TextRange.Text = optionsC(questionIndex)
        .Shapes("OptionD").TextFrame.TextRange.Text = optionsD(questionIndex)
        
        ' Reset colors of option buttons to default (e.g., gray)
        .Shapes("OptionA").Fill.ForeColor.RGB = RGB(192, 192, 192)
        .Shapes("OptionB").Fill.ForeColor.RGB = RGB(192, 192, 192)
        .Shapes("OptionC").Fill.ForeColor.RGB = RGB(192, 192, 192)
        .Shapes("OptionD").Fill.ForeColor.RGB = RGB(192, 192, 192)
    End With
    UpdateScoreDisplay
End Sub

' Check if a question has been used
Function IsInCollection(coll As Collection, item As Variant) As Boolean
    Dim v As Variant
    On Error Resume Next
    v = coll(item)
    IsInCollection = (Err.Number = 0)
    On Error GoTo 0
End Function

' Methods to set the selected option
Sub SetOptionA()
    selectedOption = "A"
    CheckAnswer "OptionA"
End Sub

Sub SetOptionB()
    selectedOption = "B"
    CheckAnswer "OptionB"
End Sub

Sub SetOptionC()
    selectedOption = "C"
    CheckAnswer "OptionC"
End Sub

Sub SetOptionD()
    selectedOption = "D"
    CheckAnswer "OptionD"
End Sub

' Check if selected answer is correct
Sub CheckAnswer(optionShapeName As String)
MsgBox (optionShapeName)
    With ActivePresentation.Slides(1).Shapes(optionShapeName)
        If selectedOption = correctAnswers(currentQuestionIndex) Then
            .Fill.ForeColor.RGB = RGB(0, 255, 0) ' Highlight green if correct
            score = score + 1
            
        Else
            .Fill.ForeColor.RGB = RGB(255, 0, 0) ' Highlight red if incorrect
            
            ' Optionally highlight the correct answer in green
            Select Case correctAnswers(currentQuestionIndex)
                Case "A": ActivePresentation.Slides(2).Shapes("OptionA").Fill.ForeColor.RGB = RGB(0, 255, 0)
                Case "B": ActivePresentation.Slides(2).Shapes("OptionB").Fill.ForeColor.RGB = RGB(0, 255, 0)
                Case "C": ActivePresentation.Slides(2).Shapes("OptionC").Fill.ForeColor.RGB = RGB(0, 255, 0)
                Case "D": ActivePresentation.Slides(2).Shapes("OptionD").Fill.ForeColor.RGB = RGB(0, 255, 0)
            End Select
        End If
    End With
    UpdateScoreDisplay
End Sub

' Go to the next question when the "Next" button is clicked
Sub NextQuestion()
    If questionCount < 10 Then ' Set to desired number of questions per quiz
        ShowRandomQuestion
    Else
        ' Show the final score when the quiz ends
        ActivePresentation.Slides(2).Shapes("QuestionText").TextFrame.TextRange.Text = "Quiz Complete! Your score is " & score & " out of " & questionCount
        ' Optionally hide option buttons at the end of the quiz
        ActivePresentation.Slides(2).Shapes("OptionA").Visible = msoFalse
        ActivePresentation.Slides(2).Shapes("OptionB").Visible = msoFalse
        ActivePresentation.Slides(2).Shapes("OptionC").Visible = msoFalse
        ActivePresentation.Slides(2).Shapes("OptionD").Visible = msoFalse
        ActivePresentation.Slides(2).Shapes("NextButton").Visible = msoFalse
    End If
End Sub




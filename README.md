# RBCAccountProject
So, Basically the first screen 'MainActivity' on the app fetches the list of accounts from the AccountProvider Service and display it in the recyclerview.
When user selects any account from the recyclerview, it will redirect the user to second screen 'AccountDetailActivity'. This activity will show the Account information and list of all transactions made by that account.
To make the different API calls, i have used viewmodel class 'AccountDetailsViewModel. I have used coroutines with async to do two API calls paralley and handled their sucess, error, progresbar laoding state by creating a simple Sealed class called Resource.

NOTE: THE getTransactions Service call and getAdditionalCreditCardTransactions always throwing error. So i couldn't have a chance to work with the success data. But i have created recyclerview and adapter class for listing transactions.
While doing the API call, whenever an error occurs, i will display that errorMessage in a textview.

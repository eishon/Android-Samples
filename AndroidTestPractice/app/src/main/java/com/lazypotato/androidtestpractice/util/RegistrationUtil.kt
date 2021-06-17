package com.lazypotato.androidtestpractice.util

object RegistrationUtil {

    private val existingUsers = listOf("Peter", "Carl")

    /**
     * the input is not  valid if...
     * ...the username/password is empty
     * ...the username is already taken
     * ...the confirmed password is not the same as real password
     * ...the password contains less than 2 digits
     */
    fun validateRegistrationInput(
        userName: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if(userName.isEmpty() || password.isEmpty()) {
            return false
        }

        if(userName in existingUsers) {
            return false
        }

        if(password != confirmPassword) {
            return false
        }

        if(password.count { it.isDigit() } < 2) {
            return false
        }

        return true;
    }
}
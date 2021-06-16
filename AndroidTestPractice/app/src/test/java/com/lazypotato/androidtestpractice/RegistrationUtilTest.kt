package com.lazypotato.androidtestpractice

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `empty username returns false` () {
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "123",
            "123"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password returns true` () {
        val result = RegistrationUtil.validateRegistrationInput(
            "Phillip",
            "123",
            "123"
        )

        assertThat(result).isTrue()
    }

    @Test
    fun `username already exists returns false` () {
        val result = RegistrationUtil.validateRegistrationInput(
            "Carl",
            "123",
            "123"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `incorrectly confirmed password returns false` () {
        val result = RegistrationUtil.validateRegistrationInput(
            "Phillip",
            "",
            ""
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `empty passwords returns false` () {
        val result = RegistrationUtil.validateRegistrationInput(
            "Phillip",
            "",
            ""
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `less than 2 digits passwords returns false` () {
        val result = RegistrationUtil.validateRegistrationInput(
            "Phillip",
            "abcdefg5",
            "abcdefg5"
        )

        assertThat(result).isFalse()
    }
}
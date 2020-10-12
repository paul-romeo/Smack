package com.example.smack.Services

import android.graphics.Color
import java.util.*

object UserDataService {
    var id = ""
    var avatarColor = ""
    var avatarName = ""
    var email = ""
    var name = ""

    fun returnAvatarColor(components: String): Int {

        // stripped out square-bracket and commas in the components
        val strippedColor = components
            .replace("[", "")
            .replace("]", "")
            .replace(",", "")

        // Declare and initialize r, g, b
        var r = 0
        var g = 0
        var b = 0

        val scanner = Scanner(strippedColor)
        if (scanner.hasNext()) {
            r = (scanner.nextDouble() * 255).toInt()
            g = (scanner.nextDouble() * 255).toInt()
            g = (scanner.nextDouble() * 255).toInt()
        }

        return Color.rgb(r, g, b)
    }

    fun logout() {
        var id = ""
        var avatarColor = ""
        var avatarName = ""
        var email = ""
        var name = ""
        AuthService.authToken = ""
        AuthService.authEmail = ""
        AuthService.isLoggedIn = false
    }
}
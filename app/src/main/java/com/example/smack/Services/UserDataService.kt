package com.example.smack.Services

import android.graphics.Color
import com.example.smack.Controller.App
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
        App.prefs.authToken = ""
        App.prefs.userEmail = ""
        App.prefs.isLoggedIn = false

        // Clear all channels and messages
        MessageService.clearMessages()
        MessageService.clearChannels()
    }
}
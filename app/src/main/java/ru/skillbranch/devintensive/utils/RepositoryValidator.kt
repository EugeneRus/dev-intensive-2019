package ru.skillbranch.devintensive.utils

class RepositoryValidator {

    internal companion object {
        const val DOMAIN = "github.com"

        val VALID_PREFIXES =
            listOf("https://www.$DOMAIN/", "https://$DOMAIN/", "www.$DOMAIN/", "$DOMAIN/")
        val INVALID_ACCOUNT_PATHS = setOf(
            "enterprise",
            "features",
            "topics",
            "collections",
            "trending",
            "events",
            "marketplace",
            "pricing",
            "nonprofit",
            "customer-stories",
            "security",
            "login",
            "join"
        )
    }

    fun isValid(repoUrl: String): Boolean {
        if (repoUrl.isEmpty()) {
            return true
        }
        var valid = false
        loop@ for (pref in VALID_PREFIXES) {
            if (repoUrl.startsWith(pref)) {
                val userAccountPath = repoUrl.substring(pref.length)
                valid = !(userAccountPath.isEmpty() ||
                        userAccountPath.contains("/") ||
                        userAccountPath.contains(" ") ||
                        userAccountPath.contains("_") ||
                        userAccountPath.contains("!") ||
                        userAccountPath.contains("+") ||
                        userAccountPath.contains("--") ||
                        userAccountPath.startsWith("-") ||
                        userAccountPath.endsWith("-") ||
                        INVALID_ACCOUNT_PATHS.contains(userAccountPath))
                break@loop
            }
        }
        return valid
    }
}
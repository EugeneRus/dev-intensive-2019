package ru.skillbranch.devintensive.extensions

import java.lang.StringBuilder

fun String.truncate(charAmount: Int = 16): String {
    if (this.length <= charAmount || charAmount <= 0) return this

    return with(this.subSequence(0, charAmount)) {
        val subSec = if (this.last().isWhitespace()) {
            this.subSequence(0, this.length - 1)
        } else {
            this
        }
        "$subSec..."
    }
}

fun String.stripHtml(): String =
    buildString {
        var tag = false
        var previousCharIsNotWhitespace = true

        this@stripHtml.forEach {
            if (it == '<') {
                tag = true
            }

            if (!tag) {
                val currentCharIsWhitespace = it.isWhitespace()
                if (previousCharIsNotWhitespace || !currentCharIsWhitespace) {
                    this@buildString.append(it)
                }

                previousCharIsNotWhitespace = !currentCharIsWhitespace

            } else if (it == '>') {
                tag = false
            }
        }
    }
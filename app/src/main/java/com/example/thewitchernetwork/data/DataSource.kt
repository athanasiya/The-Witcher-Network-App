package com.example.thewitchernetwork.data

import com.example.thewitchernetwork.model.Monster
import com.example.thewitchernetwork.model.Witcher

object DataSource {
    val witchers = listOf(
        Witcher(
            "Geralt of Rivia",
            "School of the Woolf"
        ),
        Witcher(
            "Ciri",
            "School of the Woolf"
        ),
        Witcher(
            "Vesemir",
            "School of the Woolf"
        ),
        Witcher(
            "Coën",
            "School of the Griffin"
        ),
        Witcher(
            "Aiden",
            "School of the Cat"
        ),
        Witcher(
            "Auckes",
            "School of the Viper"
        ),
        Witcher(
            "Ivo of Belhaven",
            "School of the Bear"
        )
    )

    val monsters = listOf(
        Monster(
            "Basilisk",
            "Draconids",
            13
        ),
        Monster(
            "Fiend",
            "Relicts",
            13
        ),
        Monster(
            "Griffin",
            "Hybrids",
            12
        ),
        Monster(
            "Dark Knight",
            "Specters",
            10
        ),
        Monster(
            "Garkain",
            "Vampires",
            8
        )
    )
}
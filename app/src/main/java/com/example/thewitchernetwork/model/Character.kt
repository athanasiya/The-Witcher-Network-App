//package com.example.thewitchernetwork.model
//
//sealed class Character(
//    open val name: String,
//    open val description: String,
//    open val number: Int
//) {
//    data class Witcher(
//        override val name: String,
//        override val description: String
//    ) : Character(name, description, number = 0)
//
//    data class Monster(
//        override val name: String,
//        override val description: String,
//        override val number: Int
//    ) : Character(name, description, number)
//}

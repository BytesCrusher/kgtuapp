package com.application.kgtuapp.Enums

enum class EnumWeekDays(
    val day: String
) {
    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье");

    /*fun returnWeekDayNameById(weekDayId: Int): String {
        return when(day:EnumWeekDays) {
            EnumWeekDays.MONDAY -> "Понедельник"
            EnumWeekDays.TUESDAY -> "Понедельник"
            EnumWeekDays.WEDNESDAY -> "Понедельник"
            EnumWeekDays.THURSDAY -> "Понедельник"
            EnumWeekDays.FRIDAY -> "Понедельник"
            EnumWeekDays.SATURDAY -> "Понедельник"
            EnumWeekDays.SUNDAY -> "Понедельник"
        }
    }*/

    companion object {
        fun findWeekDayById(day: String): EnumWeekDays? {
            return values().find{ it.name == day}
        }
    }
}
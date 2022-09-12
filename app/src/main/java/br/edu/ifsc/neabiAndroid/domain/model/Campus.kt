package br.edu.ifsc.neabiAndroid.domain.model

data class Campus(
    val pk: Int,
    val name: String,
    val image: String,
    val institution: Institution,
    val address: Address,
    val courses: List<Courses> = listOf(),
    val program: List<Program> = listOf(),
    val project: List<Project> = listOf(),
    val affirmativeAction: List<AffirmativeAction> = listOf(),
)

package br.edu.ifsc.neabiAndroid.domain.model

import br.edu.ifsc.neabiAndroid.data.local.entities.CourseEntity

data class Course(
    val pk: Int,
    val name: String,
    val description: String
){
    fun toEntity(): CourseEntity {
        return CourseEntity(
            pk = pk,
            name = name,
            description = description
        )
    }
}

package com.devhub.campus.app.models

import java.util.*

data class UserModel(
    var id: String? = null,
    var name: String? = null,
    val profileImage: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val userName: String? = null,
    val campusName: Campus? = null,
    val college: College? = null,
    val pos: String? = null,
    val yos: String? = null,
    val isStudentLeader: Boolean? = null,
    val post: String? = null,
    val endDate: Date? = null,
    val isLecture: Boolean? = null,
    val programsTaught: List<String>? = null,
    val postsHelp: List<String>? = null,
    val mates: List<Int>? = null,
    val posts: List<String>? = null,
    val bio: String? = null,
)

// pos - program of study
// yos - year of study

// universities
enum class Campus(val campusName: String) {
    MAKERERE("Makerere University"),
    KYAMBOGO("Kyambogo University"),
    GULU("Gulu University"),
    IUEA("International University Of East Africa"),
    IUIU("Islamic University In Uganda"),
    KABALE("Kabale University"),
    KIU("Kampala International University"),
    KU("Kampala University"),
    MBARARA("Mbarara University of Science And Technology"),
    NDEJJE("Ndejje University"),
    NKUMBA("Nkumba University"),
    SOROTI("Soroti University"),
    LAWRENCE("St.Lawrence University"),
    UCU("Uganda Christian University"),
    VICTORIA("Victoria University")
}

enum class College(val college: String) {
    CEDAT("College of Engineering, Design, Art and Technology"),
    CAES("College Of Agricultural And Environmental Sciences"),
    COBAM("College of Business and Management Sciences"),
    COCIS("College of Computing and Information Sciences"),
    CEES("College Of Education And External Studies"),
    CHS("College of Health Sciences"),
    CHUSS("College of Humanities and Social Sciences"),
    COVAB("College of Veterinary Medicine, Animal Resources and Biosecurity"),
    SOL("School of Law"),
}

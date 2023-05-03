package io.newsystemtest.filtersapp.api.Response

data class ProductCategoryResponse(var code: String?, var message: String?, var data: Data?)
data class Data(
    var mainCategoryList: ArrayList<MainCategoryList>,
    var secMainCategoryList: ArrayList<SecMainCategoryList>
)

data class MainCategoryList(
    var id: String?,
    var name: String?,
    var image: String?,
    var icon: String?, var isSelected: Boolean = false
)

data class SecMainCategoryList(
    var id: String?,
    var name: String?,
    var image: String?,

    )

package br.edu.ifsc.neabiAndroid.ui.home

import androidx.lifecycle.*
import br.edu.ifsc.neabiAndroid.domain.model.Campus
import br.edu.ifsc.neabiAndroid.domain.repository.HomeRepository
import br.edu.ifsc.neabiAndroid.util.Resource
import kotlinx.coroutines.flow.*
import java.lang.IllegalArgumentException
import kotlinx.coroutines.launch


class HomeViewModel(private val rep: HomeRepository): ViewModel() {

    private val _filter: MutableLiveData<String> = MutableLiveData("")
    private val _campus: LiveData<List<Campus>> = rep.getCampus().asLiveData()
    val campus: LiveData<List<Campus>>
        get() {
            return if(_filter.value==""){
                _campus
            }else{
                val filterCampus: List<Campus> = _campus.value?.filter {
                    it.name.contains(_filter.value?:"", ignoreCase = true)
                            || it.institution.name.contains(_filter.value?:"", ignoreCase = true)
                } ?: listOf()
                MutableLiveData(filterCampus)
            }
        }

    val filter: LiveData<String>
        get() =_filter

    fun updateFilter(search: String){
        _filter.value = search
    }
}

class HomeVMFactory(private val repository: HomeRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java))
            return HomeViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
package ru.skillbranch.devintensive.viewmodels

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.repositories.PreferencesRepository
import ru.skillbranch.devintensive.utils.RepositoryValidator

class ProfileViewModel : ViewModel() {

    private val repository = PreferencesRepository
    private val profileData = MutableLiveData<Profile>()
    private val appTheme = MutableLiveData<Int>()
    private val validRepositoryInput = MutableLiveData<Boolean>()
    private val enableShowError = MutableLiveData<Boolean>()
    private val repositoryValidator = RepositoryValidator()
    val VALID = 32

    init {
        profileData.value = repository.getProfile()
        appTheme.value = repository.getAppTheme()
    }

    fun setRepositoryChangedText(text: String) {
        validRepositoryInput.value = repositoryValidator.isValid(text)
    }

    fun getIsValidRepositoryInput(): LiveData<Boolean> =
        validRepositoryInput

    fun setEnableShowError(enable: Boolean) {
        enableShowError.value = enable
    }

    fun getIsEnableShowError(): LiveData<Boolean> =
        enableShowError

    fun getProfileData(): LiveData<Profile> =
        profileData

    fun saveProfileData(profile: Profile) {
        repository.saveProfile(profile)
        profileData.value = profile
    }

    fun switchTheme() {
        if (appTheme.value == AppCompatDelegate.MODE_NIGHT_YES) {
            appTheme.value = AppCompatDelegate.MODE_NIGHT_NO
        } else {
            appTheme.value = AppCompatDelegate.MODE_NIGHT_YES
        }
        repository.saveAppTheme(appTheme.value!!)
    }

    fun getTheme(): LiveData<Int> =
        appTheme
}
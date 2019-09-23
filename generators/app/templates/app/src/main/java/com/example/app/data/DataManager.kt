package <%= package %>.data

import <%= package %>.network.APIService
import javax.inject.Singleton
import javax.inject.Inject


@Singleton
class DataManager
@Inject constructor(private val api: APIService, private val prefs: PreferencesHelper){

}
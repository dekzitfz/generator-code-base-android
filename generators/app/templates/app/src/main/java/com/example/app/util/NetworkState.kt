package <%= package %>.util

import <%= package %>.R

class NetworkState {

    val status: Status
    var msg: String? = null
    private var responseCode: Int = 0

    enum class Status {
        RUNNING,
        SUCCESS,
        FAILED
    }

    constructor(status: Status) {
        this.status = status
        this.msg = "Connection Failed"
    }

    constructor(status: Status, responseCode: Int) {
        this.status = status
        this.responseCode = responseCode
        generateMessageBasedResponseCode(responseCode)
    }

    constructor(status: Status, responseCode: Int, isSuccess: Boolean) {
        this.status = status
        this.responseCode = responseCode
        if (!isSuccess) {
            msg = "Connection Failed"
        } else {
            generateMessageBasedResponseCode(responseCode)
        }
    }

    private fun generateMessageBasedResponseCode(code: Int) {
        if (code == 500) {
            msg = "Connection Failed"
        } else {
            msg = "Connection Failed"
        }
    }

    companion object {
        val LOADED: NetworkState = NetworkState(Status.SUCCESS, R.string.success)
        val LOADING: NetworkState = NetworkState(Status.RUNNING, R.string.running)
    }

}

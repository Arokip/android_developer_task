package cz.arokip.androiddevelopertask

import cz.arokip.androiddevelopertask.api.GitHubJobsApi

class Repository {

    var api = GitHubJobsApi.makeRetrofitService(gitHubJobsUrl)

    suspend fun getAllPositions() = api.getAllPositions()

    companion object {
        const val gitHubJobsUrl = "https://jobs.github.com/"
    }
}
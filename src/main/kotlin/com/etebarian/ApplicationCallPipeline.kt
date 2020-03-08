package com.etebarian

import io.ktor.application.ApplicationCall
import io.ktor.request.ApplicationReceivePipeline
import io.ktor.response.ApplicationSendPipeline
import io.ktor.util.pipeline.Pipeline
import io.ktor.util.pipeline.PipelinePhase

open class ApplicationCallPipeline : Pipeline<Unit, ApplicationCall>(Setup, Monitoring, Features, Call, Fallback) {
    val receivePipeline = ApplicationReceivePipeline()
    val sendPipeline = ApplicationSendPipeline()

    companion object {
        val Setup = PipelinePhase("Setup")
        val Monitoring = PipelinePhase("Monitoring")
        val Features = PipelinePhase("Features")
        val Call = PipelinePhase("Call")
        val Fallback = PipelinePhase("Fallback")
    }
}
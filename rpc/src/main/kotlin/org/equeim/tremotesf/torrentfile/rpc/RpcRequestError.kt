// SPDX-FileCopyrightText: 2017-2023 Alexey Rochev <equeim@gmail.com>
//
// SPDX-License-Identifier: GPL-3.0-or-later

package org.equeim.tremotesf.torrentfile.rpc

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerializationException
import okhttp3.Call
import okhttp3.Headers
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.security.cert.CertPathValidatorException

sealed class RpcRequestError private constructor(
    internal open val response: Response? = null,
    internal val responseBody: String? = null,
    internal open val requestHeaders: Headers? = null,
    message: String? = null,
    cause: Exception? = null,
) : Exception(message, cause) {
    class NoConnectionConfiguration : RpcRequestError(message = "No connection configuration")

    class BadConnectionConfiguration(override val cause: Exception) :
        RpcRequestError(
            message = "Bad connection configuration",
            cause = cause
        )

    class ConnectionDisabled : RpcRequestError(message = "Connection to server is disabled")

    class Timeout internal constructor(response: Response?, requestHeaders: Headers?) :
        RpcRequestError(response = response, requestHeaders = requestHeaders, message = "Timed out when performing HTTP request")

    class NetworkError internal constructor(
        response: Response?,
        requestHeaders: Headers?,
        override val cause: IOException,
    ) :
        RpcRequestError(
            response = response,
            requestHeaders = requestHeaders,
            message = "Network error when performing HTTP request",
            cause = cause
        )

    class UnsuccessfulHttpStatusCode internal constructor(
        override val response: Response,
        responseBody: String?,
        override val requestHeaders: Headers,
    ) :
        RpcRequestError(
            response = response,
            responseBody = responseBody,
            requestHeaders = requestHeaders,
            message = response.status
        )

    class DeserializationError internal constructor(
        override val response: Response,
        override val requestHeaders: Headers,
        override val cause: SerializationException,
    ) :
        RpcRequestError(
            response = response,
            requestHeaders = requestHeaders,
            message = "Failed to deserialize server response",
            cause = cause
        )

    class AuthenticationError internal constructor(
        override val response: Response,
        override val requestHeaders: Headers,
    ) :
        RpcRequestError(
            response = response,
            requestHeaders = requestHeaders,
            message = "Server requires HTTP authentication"
        )

    class UnsupportedServerVersion internal constructor(val version: String, override val response: Response, override val requestHeaders: Headers) :
        RpcRequestError(response = response, requestHeaders = requestHeaders, message = "Transmission version $version is not supported")

    class UnsuccessfulResultField internal constructor(val result: String, override val response: Response, override val requestHeaders: Headers) :
        RpcRequestError(response = response, responseBody = "Response result is '$result'", requestHeaders = requestHeaders)

    class UnexpectedError internal constructor(override val response: Response, override val requestHeaders: Headers, override val cause: Exception) :
        RpcRequestError(response = response, requestHeaders = requestHeaders, message = "Unexpected error", cause = cause)
}

val RpcRequestError.isRecoverable: Boolean
    get() = when (this) {
        is RpcRequestError.NoConnectionConfiguration, is RpcRequestError.BadConnectionConfiguration, is RpcRequestError.ConnectionDisabled -> false
        else -> true
    }

internal fun IOException.toRpcRequestError(call: Call, response: Response?, requestHeaders: Headers?): RpcRequestError =
    if (this is SocketTimeoutException || call.isCanceled()) {
        RpcRequestError.Timeout(response = response, requestHeaders = requestHeaders)
    } else {
        RpcRequestError.NetworkError(response = response, requestHeaders = requestHeaders, cause = this)
    }

@Parcelize
data class DetailedRpcRequestErrorString(
    val detailedError: String,
    val certificates: String?,
) : Parcelable

fun RpcRequestError.makeDetailedErrorString(): DetailedRpcRequestErrorString {
    val suppressed = causes.flatMap { it.suppressed.asSequence() }.toList()
    return DetailedRpcRequestErrorString(
        detailedError = buildString {
            append("Error:\n")
            appendThrowable(this@makeDetailedErrorString)
            if (suppressed.isNotEmpty()) {
                append("Suppressed exceptions:\n")
                suppressed.forEach(::appendThrowable)
            }
            response?.withPriorResponses?.forEach { response ->
                append("HTTP request:\n")
                append("- URL: ${response.request.url}\n")
                append("HTTP Response:\n")
                append("- Protocol: ${response.protocol}\n")
                append("- Status: ${response.status}\n")
                append("- Headers:\n")
                response.headers.forEach { header ->
                    val (name, value) = header.redactHeader()
                    append("   $name: $value\n")
                }
                response.handshake?.let { handshake ->
                    append("- TLS info:\n")
                    append("  - Version: ${handshake.tlsVersion.javaName}\n")
                    append("  - Cipher suite: ${handshake.cipherSuite.javaName}\n")
                }
            }
        },
        certificates = run {
            val clientCerts =
                response?.withPriorResponses?.flatMap { it.handshake?.localCertificates.orEmpty() }?.toSet().orEmpty()
            val serverCerts =
                response?.withPriorResponses?.flatMap { it.handshake?.peerCertificates.orEmpty() }?.toSet()
                    ?: causes.filterIsInstance<CertPathValidatorException>()
                        .firstOrNull()?.certPath?.certificates.orEmpty()
            if (clientCerts.isNotEmpty() || serverCerts.isNotEmpty()) {
                buildString {
                    if (clientCerts.isNotEmpty()) {
                        append("Client certificates:\n")
                        clientCerts.forEach { append(" $it\n") }
                    }
                    if (serverCerts.isNotEmpty()) {
                        append("Server certificates:\n")
                        serverCerts.forEach { append(" $it\n") }
                    }
                }
            } else {
                null
            }
        }
    )
}

private fun StringBuilder.appendThrowable(e: Throwable) {
    append("$e\n\n")
    for (cause in e.causes) {
        append("Caused by:\n$cause\n\n")
    }
}

private val Throwable.causes: Sequence<Throwable> get() = generateSequence(cause, Throwable::cause)
private val Response.withPriorResponses: Sequence<Response> get() = generateSequence(this) { it.priorResponse }

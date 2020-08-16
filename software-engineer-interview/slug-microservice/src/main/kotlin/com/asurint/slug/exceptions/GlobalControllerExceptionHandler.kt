package com.asurint.slug.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@Suppress("unused")
@RestControllerAdvice(basePackages = ["com.asurint.slug.api"])
class GlobalControllerExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException::class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	fun handleResourceNotFoundException(e: Throwable) = ErrorContainer(e.message ?: ResourceNotFoundException().message)

	@ExceptionHandler(BackendException::class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	fun handleBackendException(e: Throwable) = ErrorContainer(e.message ?: BackendException().message)

	// This gets called if there are no matches to another exception handler.
	// The idea here is we don't want to leak low-level exceptions to the API,
	// so this handler will return a very generic error message.
	// If there's a need to convey a specific error message to the consumer of the API,
	// you should explicitly create another exception handler above, giving the ability to specify a
	// specific exception and corresponding error message.
	@ExceptionHandler(value = [RuntimeException::class, Exception::class])
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	fun handleRuntimeException(e: Throwable) = ErrorContainer("Unexpected backend exception occurred!")
}

data class ErrorContainer(
	val errorMessage: String?
)

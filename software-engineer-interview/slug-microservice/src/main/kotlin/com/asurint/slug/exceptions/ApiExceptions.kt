package com.asurint.slug.exceptions

class ResourceNotFoundException(msg: String = "Resource was not found!") : RuntimeException(msg)
class BackendException(msg: String = "Backend exception occurred!") : RuntimeException(msg)
package com.asurint.directoryservice

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class EmailController {

    @GetMapping("/email")
    fun getEmailAddress(@RequestParam(value = "name") name: String) =
        generateEmailAddress(name)


    private fun generateEmailAddress(name: String): String =
        name.replace("\\s+".toRegex(), ".").toLowerCase() + "@asurint.com"
}